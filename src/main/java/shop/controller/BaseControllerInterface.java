package shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mode.*;
import shop.util.Pagination;

import java.util.List;

public interface BaseControllerInterface {
    public RequestBean baseTest()throws Exception;
    public List<Product> baseProductList(BaseParam baseParam)throws Exception;
    public Product baseProductDetail(BaseParam baseParam)throws Exception;
    public List<Category> baseCategoryList(BaseParam baseParam)throws Exception;
    public Category baseCategory(BaseParam baseParam)throws Exception;
    public List<MenuCategory> baseMenuCategoryList(BaseParam baseParam)throws Exception;
    public List<Recommend> getRecommendList()throws Exception;
    public List<SearchHistory> getSearchHistory()throws Exception;
    public String baseGetTaoKey(int id)throws Exception;
}
