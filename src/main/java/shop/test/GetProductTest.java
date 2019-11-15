package shop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shop.mode.*;
import shop.service.*;
import shop.timer.task.DeleteGroup;
import shop.timer.task.GetProducts;
import shop.util.Pagination;
import shop.util.TimeUtil;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class GetProductTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private ConfigService configService;
    @Resource
    private MaterialService materialService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImagesService productImagesService;
    @Resource
    private CouponService couponService;
    @Resource
    private ShopUserService shopUserService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private MenuCategoryService menuCategoryService;

    @Test
    public void getMenuCategory() {
        try {
            List<MenuCategory>menuCategories=menuCategoryService.list("order","id asc");
            for(MenuCategory menuCategory:menuCategories){
                System.out.println("menuCategory>>>>>"+menuCategory.getName());
                List<Category>categoryList=menuCategory.getCategories();
                for(Category category:categoryList){
                    System.out.println("category>>>>>"+category.getName());
                    List<CategoryItem>categoryItems=category.getCategoryItems();
                    for(CategoryItem categoryItem:categoryItems){
                        System.out.println("categoryItem》》》》》"+categoryItem.getCategory_name());
                    }
                    System.out.println("<<<<<<<----------------->>>>>>>");
                }
                System.out.println("-----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProductFromTaoBao(){
        new GetProducts(configService,productService,productImagesService,couponService,shopUserService,categoryService).run();
    }

    @Test
    public void getProductDb() throws Exception {
//
        String sql="SELECT * FROM product WHERE  category_id  IN (SELECT category_id FROM category where menu_id = 2 ) limit 10;";
        long start=System.currentTimeMillis();
        List<Product>productList=productService.selectBySql(sql);
        long end=System.currentTimeMillis();
        System.out.println("获取"+productList.size()+"共耗时:"+ TimeUtil.getTime(end-start));
    }
    @Test
    public void delCouponDb() {
        new DeleteGroup(productService,couponService,shopUserService).run();
    }
    @Test
    public void getTimes(){
        System.out.println(TimeUtil.getTime(1234));
    }
    @Test
    public void getProductByKey(){
        try {
            long start=System.currentTimeMillis();
//            String sql  ="SELECT title,key_word FROM product Where LOCATE('%s',key_word) limit 20;";
//           String sqlS=String.format(sql,"运动文胸");
//            System.out.println(sqlS);
//            List<Product>productList=productService.selectBySql(sqlS);
            Pagination pagination=new Pagination();
            pagination.setSize(20);
            List<Product>productList=productService.list("pagination",pagination,"key_word","运动文胸");
            long end=System.currentTimeMillis();
            System.out.println("耗时:"+TimeUtil.getTime(end-start)+"取出"+productList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
