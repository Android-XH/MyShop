package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.Coupon;
import shop.mode.CouponExample;
import shop.mode.mapper.CouponMapper;
import shop.service.CouponService;
@Service
public class CouponServiceImpl extends BaseServiceImpl<CouponMapper, CouponExample> implements CouponService {

    @Override
    public void insert(Coupon coupon) throws Exception {
        if(coupon.getCoupon_id()!=null){
            Coupon coupon1= (Coupon) getOne("coupon_id_eq",coupon.getCoupon_id());
            if(coupon1==null){
                add(coupon);
            }else{
                coupon.setId(coupon1.getId());
                update(coupon);
            }
        }
    }
}
