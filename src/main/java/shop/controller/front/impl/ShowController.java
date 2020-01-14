package shop.controller.front.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import shop.controller.param.ProductParam;
import shop.controller.front.ShowBaseController;
import shop.controller.front.ShowControllerInterface;
import shop.mode.MenuCategory;
import shop.mode.Product;
import shop.util.Pagination;

import java.util.List;

@Controller
public class ShowController extends ShowBaseController implements ShowControllerInterface {
    @Override
    public String home(Model mode) throws Exception {
        ProductParam productParam =new ProductParam();
        Pagination pagination = productParam.getPagination();
        pagination.setSize(20);
        productParam.setPagination(pagination);
        List<MenuCategory>menuCategories=baseMenuCategoryList(productParam);
        mode.addAttribute("categories",menuCategories);
        productParam.setRecommend(1);
        List<MenuCategory> recommends= baseMenuCategoryList(productParam);
        ProductParam param=new ProductParam();
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
        ProductParam productParam =new ProductParam();
        productParam.setKeyWord(keyword);
        productParam.setSort(sort);
        Pagination pagination= productParam.getPagination();
        pagination.setPage(page==null?0:page);
        pagination.setSize(20);
        productParam.setPagination(pagination);
        mode.addAttribute("products",baseProductList(productParam));
        mode.addAttribute("keyword",keyword);
        mode.addAttribute("pagination", productParam.getPagination());
        return "search";
    }

    @Override
    public String category(Integer categoryID, Integer categoryItemID,Integer page,String sort,Model mode) throws Exception {
        ProductParam productParam =new ProductParam();
        productParam.setCategory_id(categoryID==null?0:categoryID);
        productParam.setCategory_item_id(categoryItemID==null?0:categoryItemID);
        productParam.setSort(StringUtils.isNotEmpty(sort)?sort:"");
        Pagination pagination= productParam.getPagination();
        pagination.setPage(page==null?0:page);
        pagination.setSize(20);
        productParam.setPagination(pagination);
        List<Product>productList=baseProductList(productParam);
        mode.addAttribute("products",productList);
        mode.addAttribute("pagination", productParam.getPagination());
        long end=System.currentTimeMillis();
        return "category";
    }

    @Override
    public String product(int id, Model mode) throws Exception {
        ProductParam productParam =new ProductParam();
        productParam.setId(id);
        mode.addAttribute("product",baseProductDetail(productParam));
        return "product";
    }
}
