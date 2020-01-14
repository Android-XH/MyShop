package shop.controller;

import shop.controller.param.ProductParam;
import shop.controller.param.UserParam;
import shop.mode.*;

import java.util.List;

public interface BaseControllerInterface {
    public RequestBean baseTest()throws Exception;
    public List<Product> baseProductList(ProductParam productParam)throws Exception;
    public Product baseProductDetail(ProductParam productParam)throws Exception;
    public List<Category> baseCategoryList(ProductParam productParam)throws Exception;
    public Category baseCategory(ProductParam productParam)throws Exception;
    public List<MenuCategory> baseMenuCategoryList(ProductParam productParam)throws Exception;
    public List<Recommend> getRecommendList()throws Exception;
    public List<SearchHistory> getSearchHistory()throws Exception;
    public String baseGetTaoKey(int id)throws Exception;
    public User getUser(String userName,String passWord)throws Exception;
    public User registerUser(String userName,String passWord)throws Exception;
}
