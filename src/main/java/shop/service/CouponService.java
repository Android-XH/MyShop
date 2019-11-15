package shop.service;


import org.springframework.stereotype.Service;
import shop.mode.Coupon;
public interface CouponService extends BaseService{
    void insert(Coupon coupon)throws Exception;
}