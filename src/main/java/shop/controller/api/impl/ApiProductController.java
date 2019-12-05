package shop.controller.api.impl;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.springframework.stereotype.Controller;
import shop.controller.BaseParam;
import shop.controller.api.ApiBaseController;
import shop.controller.RequestBean;
import shop.controller.api.ApiProductInterface;
import shop.mode.MenuCategory;
import shop.mode.Product;
import shop.mode.Recommend;
import shop.mode.SearchHistory;
import shop.util.Pagination;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static shop.controller.RequestCommon.SUCCESS_TEST;

@Controller
public class ApiProductController extends ApiBaseController implements ApiProductInterface {

    @Override
    public RequestBean test() throws Exception {
      return baseTest();
    }

    @Override
    public RequestBean getProductList(BaseParam baseParam) throws Exception {
        System.out.println(baseParam.toString());
        return RequestBean.getProductList(baseProductList(baseParam),baseParam.getPagination());
    }


    @Override
    public RequestBean getProductDetail(int id) throws Exception {
        BaseParam baseParam=new BaseParam();
        baseParam.setId(id);
        return RequestBean.getProductDetail(baseProductDetail(baseParam));
    }

    @Override
    public RequestBean getMenuCategory() throws Exception {
        List<MenuCategory>menuCategoryList=baseMenuCategoryList(new BaseParam());
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
