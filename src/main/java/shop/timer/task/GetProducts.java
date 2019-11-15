package shop.timer.task;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import shop.mode.Category;
import shop.mode.CategoryItem;
import shop.service.*;
import shop.timer.mode.DownFuture;
import shop.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetProducts {

    private ConfigService configService;

    private ProductService productService;
    private ProductImagesService productImagesService;
    private CouponService couponService;
    private ShopUserService shopUserService;
    private CategoryService categoryService;

    private final int GET_DATA_THREADS=10;//取数据同时启动线程
    private final int SAVE_DATA_THREADS=20;//存储数据同时启动线程
    private ExecutorService insterService;
    public GetProducts(ConfigService configService, ProductService productService, ProductImagesService productImagesService, CouponService couponService, ShopUserService shopUserService, CategoryService categoryService) {
        this.configService = configService;
        this.productService = productService;
        this.productImagesService = productImagesService;
        this.couponService = couponService;
        this.shopUserService = shopUserService;
        this.categoryService = categoryService;
    }

    public void run(){
        try {
            System.out.println("开始更新数据----");
            Map<String, String> config = configService.map();
            TaobaoClient client = new DefaultTaobaoClient(config.get("alimama_api_url"), config.get("alimama_appkey"), config.get("alimama_secret"));
            long adzoneID = Long.parseLong(config.get("alimama_adzoneId"));
            List<Category> categoryList = categoryService.list("order","id asc");
            ExecutorService executorService = Executors.newFixedThreadPool(GET_DATA_THREADS);
            List<DownFuture> futures = new ArrayList<>(categoryList.size());
            for (Category category : categoryList) {
                Future<List<TbkDgMaterialOptionalResponse.MapData>> future=executorService.submit(new SelectTask(client,category,adzoneID));
                futures.add(new DownFuture(future));
            }
            boolean isRun = false;
            do {
                if (!isRun) {
                    System.out.println("正在获取淘宝商品...");
                    isRun = true;
                }
            } while (!isDown(futures));
            saves(futures);
            do {
                if (!isSaveRun) {
                    System.out.println("正在存储淘宝商品...");
                    isSaveRun = true;
                }
            } while (!isSaveDown(saveFuture));
            System.out.println("共更新"+allSize+"条数据");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("更新数据异常" + e.getMessage());
        }
    }
    private boolean isDown(List<DownFuture> futures) {
        for (DownFuture future : futures) {
            Future<List<TbkDgMaterialOptionalResponse.MapData>> future1=future.getFuture();
            if (!future1.isDone()) {
                return false;
            }else if(!future.isSave()){
                save(future);
            }
        }
        return true;
    }
    private int allSize;
    boolean isSaveRun = false;
    List<Future<Integer>>saveFuture=new ArrayList<>();
    private void saves(List<DownFuture> futures){
        for(DownFuture downFuture:futures){
            save(downFuture);
        }
    }
    private void save(DownFuture future){
        if(insterService==null){
            insterService = Executors.newFixedThreadPool(SAVE_DATA_THREADS);
        }
        Future<List<TbkDgMaterialOptionalResponse.MapData>>future1=future.getFuture();
        if(!future.isSave()&&future1.isDone()){
            future.setSave(true);
            try {
                List<TbkDgMaterialOptionalResponse.MapData> dataList = future1.get();
                allSize+=dataList.size();
                saveFuture.add(insterService.submit(new SaveTask(productService,productImagesService,couponService,shopUserService,dataList)));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isSaveDown( List<Future<Integer>>saveFuture) {
        for (Future future : saveFuture) {
            if (!future.isDone()) {
                return false;
            }
        }
        return true;
    }
}
