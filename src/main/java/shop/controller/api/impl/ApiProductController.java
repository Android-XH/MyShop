package shop.controller.api.impl;

import org.springframework.stereotype.Controller;
import shop.controller.param.ProductParam;
import shop.controller.api.ApiBaseController;
import shop.controller.RequestBean;
import shop.controller.api.ApiProductInterface;
import shop.mode.MenuCategory;
import shop.mode.Recommend;
import shop.mode.SearchHistory;

import java.util.List;

@Controller
public class ApiProductController extends ApiBaseController implements ApiProductInterface {

    @Override
    public RequestBean test() throws Exception {
      return baseTest();
    }

    @Override
    public RequestBean getProductList(ProductParam productParam) throws Exception {
        System.out.println(productParam.toString());
        return RequestBean.getProductList(baseProductList(productParam), productParam.getPagination());
    }


    @Override
    public RequestBean getProductDetail(int id) throws Exception {
        ProductParam productParam =new ProductParam();
        productParam.setId(id);
        return RequestBean.getProductDetail(baseProductDetail(productParam));
    }

    @Override
    public RequestBean getMenuCategory() throws Exception {
        List<MenuCategory>menuCategoryList=baseMenuCategoryList(new ProductParam());
        return RequestBean.ArrayData(menuCategoryList);
    }

    @Override
    public RequestBean getRecommend() throws Exception {
        List<Recommend>recommendList=getRecommendList();
        return RequestBean.getRecommendList(recommendList);
    }

    @Override
    public RequestBean getSearchKey() throws Exception {
        List<SearchHistory>searchHistoryList=getSearchHistory();
        return RequestBean.ArrayData(searchHistoryList);
    }

    @Override
    public RequestBean getTaoKey(int id) throws Exception {
        String key=baseGetTaoKey(id);
        return RequestBean.Data(key);
    }
}
