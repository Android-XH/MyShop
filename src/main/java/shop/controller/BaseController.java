package shop.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import shop.controller.param.SortUtil;
import shop.exception.RequestException;
import shop.mode.*;
import shop.service.*;
import shop.util.Pagination;

import java.util.List;

import static shop.controller.RequestCommon.SUCCESS_TEST;

public class BaseController implements BaseControllerInterface{
    @Autowired
    private ConfigService configService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryItemService categoryItemService;
    @Autowired
    private MenuCategoryService menuCategoryService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private SearchHistoryService searchHistoryService;

    @Override
    public RequestBean baseTest() throws Exception {
        return RequestBean.Data(SUCCESS_TEST);
    }

    @Override
    public List<Product> baseProductList(BaseParam baseParam) throws Exception {
        List<Product>productList;
        if(StringUtils.isNotEmpty(baseParam.getSql())){
            productList=productService.selectBySql(baseParam.getSql());
        }else{
            Pagination pagination=baseParam.getPagination();
            String keyWord=baseParam.getKeyWord();
            if(StringUtils.isNotEmpty(keyWord)){
                searchHistoryService.insert(keyWord);
                if(baseParam.getCategory_item_id()!=0){
                    productList=productService.list("pagination",pagination,"category_item_id_eq",baseParam.getCategory_item_id(),"key_word_like",keyWord,"order", SortUtil.handleSort(baseParam.getSort()));
                }else if(baseParam.getCategory_id()!=0){
                    productList=productService.list("pagination",pagination,"category_id_eq",baseParam.getCategory_id(),"key_word_like",keyWord,"order", SortUtil.handleSort(baseParam.getSort()));
                }else{
                    productList=productService.list("pagination",pagination,"key_word_like",keyWord,"title_orLike",keyWord,"order", SortUtil.handleSort(baseParam.getSort()));
                }
            }else{
                if(baseParam.getCategory_item_id()!=0){
                    productList=productService.list("pagination",pagination,"category_item_id_eq",baseParam.getCategory_item_id(),"order", SortUtil.handleSort(baseParam.getSort()));
                }else if(baseParam.getCategory_id()!=0){
                    productList=productService.list("pagination",pagination,"category_id_eq",baseParam.getCategory_id(),"order", SortUtil.handleSort(baseParam.getSort()));
                }else{
                    productList=productService.list("pagination",pagination,"order", SortUtil.handleSort(baseParam.getSort()));
                }
            }
            baseParam.setPagination(pagination);
        }
        return productList;
    }

    @Override
    public Product baseProductDetail(BaseParam baseParam) throws Exception {
        Product product;
        if(baseParam.getId()!=0){
            product= (Product) productService.get(baseParam.getId());
        }else if(baseParam.getPid()!=0){
            product= (Product) productService.getOne("pid_eq",baseParam.getPid());
        }else{
            throw new RequestException(RequestCommon.ERROR_PARAM_NOT_FOUND);
        }
        return product;
    }

    @Override
    public  List<Category> baseCategoryList(BaseParam baseParam) throws Exception {
        List<Category>categoryList;
        Pagination pagination=baseParam.getPagination();
        if(StringUtils.isNotEmpty(baseParam.getKeyWord())){
            categoryList= categoryService.list("depth",1,"pagination",pagination,"name_like",baseParam.getKeyWord());
        }else{
            categoryList= categoryService.list("depth",1,"pagination",pagination);
        }
        return categoryList;
    }

    @Override
    public Category baseCategory(BaseParam baseParam) throws Exception {
        Category category= (Category) categoryService.getOne("category_id_eq",baseParam.getCategory_id());
        return category;
    }

    @Override
    public List<MenuCategory> baseMenuCategoryList(BaseParam baseParam) throws Exception {
        Pagination pagination=baseParam.getPagination();
        List<MenuCategory>menuCategories=menuCategoryService.list("pagination",pagination,"order","id asc","recommend_gt&eq",baseParam.getRecommend());
        return menuCategories;
    }
}
