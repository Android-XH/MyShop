package shop.mode.extension;

import shop.annotation.ORMAnnotation.JoinColumn;
import shop.annotation.ORMAnnotation.OneToMany;
import shop.mode.Category;
import shop.mode.Product;
import shop.mode.base.BasePOJO;

import java.util.List;

public class MenuCategoryExtension extends BasePOJO {
    @OneToMany
    @JoinColumn(pName ="id",name = "menu_id")
    private List<Category> categories;

    private List<Product>products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> productList) {
        this.products = productList;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}