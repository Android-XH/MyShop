package shop.controller.api.impl;

import org.springframework.stereotype.Controller;
import shop.controller.BaseParam;
import shop.controller.api.ApiBaseController;
import shop.controller.RequestBean;
import shop.controller.api.ApiProductInterface;
import shop.mode.MenuCategory;
import shop.mode.Product;
import shop.util.Pagination;

import java.util.List;

import static shop.controller.RequestCommon.SUCCESS_TEST;

@Controller
public class ApiProductController extends ApiBaseController implements ApiProductInterface {

    @Override
    public RequestBean test() throws Exception {
      return baseTest();

    }

    @Override
    public RequestBean getProductList(Pagination pagination) throws Exception {
        BaseParam baseParam=new BaseParam();
        baseParam.setPagination(pagination);
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
        return RequestBean.ArrayData(menuCategoryList,null);
    }

}
