package shop.timer.task;

import shop.mode.Coupon;
import shop.mode.Product;
import shop.mode.ProductExample;
import shop.mode.ShopUser;
import shop.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeleteGroup {
    private ProductService productService;
    private CouponService couponService;
    private ShopUserService shopUserService;
    private final String yyyy_MM_dd="yyyy-MM-dd";

    public DeleteGroup(ProductService productService, CouponService couponService, ShopUserService shopUserService) {
        this.productService = productService;
        this.couponService = couponService;
        this.shopUserService=shopUserService;
    }
    public void run(){
        try {
            Date date = new Date(); //获取当前的系统时间。
            SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd) ;
            String time = dateFormat.format(date);
            //查询所有已过期优惠券
            List<Coupon>couponList=couponService.list("coupon_end_time_lt",time);
            for(Coupon coupon :couponList){
                couponService.delete(coupon);
                List<Product>productList=productService.list("coupon_id_eq",coupon.getCoupon_id());
                for(Product product:productList){
                    productService.delete(product);
                }
            }
            //获取所有店铺
            List<ShopUser>shopUsers=shopUserService.list();
            for(ShopUser shopUser:shopUsers){
                List<Product>productList=productService.list("sid_eq",shopUser.getSid());
                //判断该店铺下没有商品的话 删除该店铺
                if(productList.isEmpty()){
                    shopUserService.delete(shopUser);
                }
            }
            System.out.println("已删除过期优惠券和商品");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
