package shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mode.Category;
import shop.mode.MenuCategory;
import shop.mode.Product;
import shop.util.Pagination;

import java.util.List;

public interface BaseControllerInterface {
    public RequestBean baseTest()throws Exception;
    public List<Product> baseProductList(BaseParam baseParam)throws Exception;
    public Product baseProductDetail(BaseParam baseParam)throws Exception;
    public List<Category> baseCategoryList(BaseParam baseParam)throws Exception;
    public Category baseCategory(BaseParam baseParam)throws Exception;
    public List<MenuCategory> baseMenuCategoryList(BaseParam baseParam)throws Exception;
}
