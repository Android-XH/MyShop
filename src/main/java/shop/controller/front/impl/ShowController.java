package shop.controller.front.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import shop.controller.BaseParam;
import shop.controller.front.ShowBaseController;
import shop.controller.front.ShowControllerInterface;
import shop.mode.Category;
import shop.mode.CategoryItem;
import shop.mode.MenuCategory;
import shop.mode.Product;
import shop.util.Pagination;
import shop.util.TimeUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowController extends ShowBaseController implements ShowControllerInterface {
    @Override
    public String home(Model mode) throws Exception {
        BaseParam baseParam=new BaseParam();
        Pagination pagination = baseParam.getPagination();
        pagination.setSize(20);
        baseParam.setPagination(pagination);
        List<MenuCategory>menuCategories=baseMenuCategoryList(baseParam);
        mode.addAttribute("categories",menuCategories);
        baseParam.setRecommend(1);
        List<MenuCategory> recommends= baseMenuCategoryList(baseParam);
        BaseParam param=new BaseParam();
        for(MenuCategory menuCategory:recommends){
            String sql="SELECT * FROM product WHERE  category_id  IN (SELECT category_id FROM category where menu_id = "+menuCategory.getId()+" ) limit 10;";
            param.setSql(sql);
            menuCategory.setProducts(baseProductList(param));
        }

        mode.addAttribute("recommends",recommends);
        return "home";
    }

    @Override
    public String search(String keyword, String sort,Integer page, Model mode) throws Exception {
        BaseParam baseParam=new BaseParam();
        baseParam.setKeyWord(keyword);
        baseParam.setSort(sort);
        Pagination pagination=baseParam.getPagination();
        pagination.setPage(page==null?0:page);
        pagination.setSize(20);
        baseParam.setPagination(pagination);
        mode.addAttribute("products",baseProductList(baseParam));
        mode.addAttribute("keyword",keyword);
        mode.addAttribute("pagination",baseParam.getPagination());
        return "search";
    }

    @Override
    public String category(Integer categoryID, Integer categoryItemID,Integer page,String sort,Model mode) throws Exception {
        BaseParam baseParam=new BaseParam();
        baseParam.setCategory_id(categoryID==null?0:categoryID);
        baseParam.setCategory_item_id(categoryItemID==null?0:categoryItemID);
        baseParam.setSort(StringUtils.isNotEmpty(sort)?sort:"");
        Pagination pagination=baseParam.getPagination();
        pagination.setPage(page==null?0:page);
        pagination.setSize(20);
        baseParam.setPagination(pagination);

        List<Product>productList=baseProductList(baseParam);
        mode.addAttribute("products",productList);
        mode.addAttribute("pagination",baseParam.getPagination());
        return "category";
    }

    @Override
    public String product(int id, Model mode) throws Exception {
        BaseParam baseParam=new BaseParam();
        baseParam.setId(id);
        mode.addAttribute("product",baseProductDetail(baseParam));
        return "product";
    }
}
