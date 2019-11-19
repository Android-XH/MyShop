package shop.mode.extension;

import shop.mode.Product;
import shop.mode.base.BasePOJO;

import java.util.List;

public class RecommendExtension extends BasePOJO {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}