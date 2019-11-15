package shop.mode.extension;

import shop.annotation.ORMAnnotation.JoinColumn;
import shop.annotation.ORMAnnotation.ManyToOne;
import shop.annotation.ORMAnnotation.OneToMany;
import shop.mode.CategoryItem;
import shop.mode.Product;
import shop.mode.base.BasePOJO;

import java.util.List;

public class CategoryExtension extends BasePOJO {
    @OneToMany
    @JoinColumn(pName="category_id",name="category_id")
    private List<CategoryItem> categoryItems;

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public void setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
    }

    public List<Product>productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}