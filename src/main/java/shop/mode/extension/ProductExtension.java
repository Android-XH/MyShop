package shop.mode.extension;

import shop.annotation.ORMAnnotation.JoinColumn;
import shop.annotation.ORMAnnotation.ManyToOne;
import shop.annotation.ORMAnnotation.OneToMany;
import shop.mode.*;
import shop.mode.base.BasePOJO;

import java.util.List;

public class ProductExtension extends BasePOJO {
    @ManyToOne
    @JoinColumn(pName ="sid",name="sid")
    private ShopUser shop;
    @ManyToOne
    @JoinColumn(pName ="coupon_id",name="id")
    private Coupon coupon;
    @OneToMany
    @JoinColumn(pName ="pid",name="pid")
    private List<ProductImages> productImages;

    public List<ProductImages> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public ShopUser getShop() {
        return shop;
    }

    public void setShop(ShopUser shop) {
        this.shop = shop;
    }
}