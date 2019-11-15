package shop.timer.task;

import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import shop.mode.Coupon;
import shop.mode.Product;
import shop.mode.ProductImages;
import shop.mode.ShopUser;
import shop.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SaveTask implements Callable<Integer> {
    private ProductService productService;
    private ProductImagesService productImagesService;
    private CouponService couponService;
    private ShopUserService shopUserService;
    private List<TbkDgMaterialOptionalResponse.MapData> dataList;

    public SaveTask(ProductService productService, ProductImagesService productImagesService, CouponService couponService, ShopUserService shopUserService, List<TbkDgMaterialOptionalResponse.MapData> dataList) {
        this.productService = productService;
        this.productImagesService = productImagesService;
        this.couponService = couponService;
        this.shopUserService = shopUserService;
        this.dataList = dataList;
    }

    @Override
    public Integer call() throws Exception{
        int size=dataList.size();
        System.out.println("开始存储"+size+"条数据！");
        for (TbkDgMaterialOptionalResponse.MapData data : dataList) {
            ShopUser shopUser = new ShopUser();
            shopUser.setNick(data.getNick());
            shopUser.setSid(data.getSellerId());
            shopUser.setShop_title(data.getShopTitle());
            shopUser.setUser_type(data.getUserType());
            shopUser.setProvcity(data.getProvcity());
            shopUser.setShop_dsr(data.getShopDsr());
            shopUserService.insert(shopUser);

            Coupon coupon = new Coupon();
            coupon.setCoupon_id(data.getCouponId());
            coupon.setCoupon_total_count(data.getCouponTotalCount());
            coupon.setCoupon_remain_count(data.getCouponRemainCount());
            coupon.setCoupon_amount(Long.valueOf(data.getCouponAmount() == null ? "0" : data.getCouponAmount()));
            coupon.setCoupon_start_time(data.getCouponStartTime());
            coupon.setCoupon_end_time(data.getCouponEndTime());
            coupon.setCoupon_info(data.getCouponInfo());
            coupon.setCoupon_share_url(data.getCouponShareUrl());
            coupon.setCoupon_start_fee(data.getCouponStartFee());
            couponService.insert(coupon);
            Coupon dbCoupon= (Coupon) couponService.getOne("coupon_id_eq",data.getCouponId());

            Product product = new Product();
            product.setPid(data.getItemId());
            product.setCoupon_id(dbCoupon.getId());
            product.setCategory_id(data.getLevelOneCategoryId());
            product.setCategory_item_id(data.getCategoryId());
            product.setSid(data.getSellerId());
            product.setShort_title(data.getShortTitle());
            product.setTitle(data.getTitle());
            product.setPict_url(data.getPictUrl());
            product.setWhite_image(data.getWhiteImage());
            product.setPrice(Float.valueOf(data.getZkFinalPrice()==null?"0.00":data.getZkFinalPrice()));
            product.setCommission_rate(data.getCommissionRate());
            product.setItem_description(data.getItemDescription());
            product.setVolume(data.getVolume());
            product.setUrl(data.getUrl());
            product.setTk_total_sales(data.getTkTotalSales());
            product.setKey_word(data.getCategoryName()+"/"+data.getLevelOneCategoryName());
            productService.insertToDb(product);
            List<String> imagsList = data.getSmallImages();
            if (imagsList != null) {
                productImagesService.deleteByPid(product.getPid());
                for (String src : imagsList) {
                    ProductImages productImage = new ProductImages();
                    productImage.setPic_url(src);
                    productImage.setPid(product.getPid());
                    productImagesService.add(productImage);
                }
            }
        }
        System.out.println("存储完成，垃圾回收");
        dataList.clear();;
        dataList=null;
        System.gc();
        return size;
    }
}
