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
    public RequestBean getProductList(BaseParam baseParam) throws Exception {
        System.out.println("pagination=="+baseParam.getPagination());
        System.out.println("categoryID=="+baseParam.getCategory_id());
        System.out.println("categoryItemID=="+baseParam.getCategory_item_id());
        System.out.println("sort=="+baseParam.getSort());
        System.out.println("keyWord=="+baseParam.getKeyWord());
//        BaseParam baseParam=new BaseParam();
//        baseParam.setPagination(pagination);
//        baseParam.setCategory_id(categoryID);
//        baseParam.setCategory_item_id(categoryItemID);
//        baseParam.setSort(sort);
//        baseParam.setKeyWord(keyWord);
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
