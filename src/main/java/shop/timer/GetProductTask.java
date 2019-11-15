package shop.timer;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import shop.service.*;
import shop.timer.task.DeleteGroup;
import shop.timer.task.GetProducts;

import javax.servlet.ServletContextEvent;
import java.util.*;

public class GetProductTask extends TimerTask {
    private ConfigService configService;

    private ProductService productService;
    private ProductImagesService productImagesService;
    private CouponService couponService;
    private ShopUserService shopUserService;
    private CategoryService categoryService;

    public GetProductTask(ServletContextEvent sce){
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        configService = context.getBean(ConfigService.class);
        productService = context.getBean(ProductService.class);
        productImagesService=context.getBean(ProductImagesService.class);
        couponService=context.getBean(CouponService.class);
        shopUserService = context.getBean(ShopUserService.class);
        categoryService=context.getBean(CategoryService.class);
    }

    @Override
    public void run() {
        new DeleteGroup(productService,couponService,shopUserService).run();
        new GetProducts(configService,productService,productImagesService,couponService,shopUserService,categoryService).run();
    }
}
