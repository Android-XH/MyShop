package shop.controller;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import shop.controller.param.SortUtil;
import shop.exception.RequestException;
import shop.mode.*;
import shop.service.*;
import shop.util.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static shop.controller.RequestCommon.SUCCESS_TEST;

public class BaseController implements BaseControllerInterface {
    @Autowired
    private ConfigService configService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImagesService productImagesService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ShopUserService shopUserService;
    @Autowired
    private CategoryItemService categoryItemService;
    @Autowired
    private MenuCategoryService menuCategoryService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private SearchHistoryService searchHistoryService;
    @Autowired
    private RecommendService recommendService;

    @Override
    public RequestBean baseTest() throws Exception {
        return RequestBean.Data(SUCCESS_TEST);
    }

    @Override
    public List<Product> baseProductList(BaseParam baseParam) throws Exception {
        List<Product> productList;
        if (StringUtils.isNotEmpty(baseParam.getSql())) {
            productList = productService.selectBySql(baseParam.getSql());
        } else {
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria = example.createCriteria();
            //是否根据平台返回
            if (baseParam.getTypes() != null) {
                List<Long> sids = new ArrayList<>();
                String[] type = baseParam.getTypes().split(",");
                for (String t : type) {
                    int userType = Integer.valueOf(t);
                    List<ShopUser> shopUsers = shopUserService.list("user_type_eq", userType);
                    for (ShopUser shopUser : shopUsers) {
                        sids.add(shopUser.getSid());
                    }
                }
                criteria.andSidIn(sids);
            }
            //返回价格以上的商品
            if(baseParam.getMinPrice()!=0f&&baseParam.getMaxPrice()==0f){
                criteria.andPriceGreaterThanOrEqualTo(baseParam.getMinPrice());
            }
            //返回价格以下的商品
            if(baseParam.getMaxPrice()!=0f){
                criteria.andPriceBetween(baseParam.getMinPrice(),baseParam.getMaxPrice());
            }
            //根据传入商品ID返回类似商品
            if (baseParam.getId() != 0) {
                Product product = (Product) productService.get(baseParam.getId());
                if (product != null) {
                    criteria.andIdNotEqualTo(product.getId());
                    baseParam.setCategory_item_id(product.getCategory_item_id());
                } else {
                    throw new RequestException(RequestCommon.ERROR_PRODUCT_NOT_FOUND);
                }
            }
            //返回同类商品
            if (baseParam.getCategory_item_id() != 0) {
                criteria.andCategory_item_idEqualTo(baseParam.getCategory_item_id());
            }
            //返回同一大类下的商品
            if (baseParam.getCategory_id() != 0) {
                criteria.andCategory_idEqualTo(baseParam.getCategory_id());
            }
            example.setOrderByClause(SortUtil.handleSort(baseParam.getSort()));
            //根据关键词返回
            String keyWord = baseParam.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                searchHistoryService.insert(keyWord);
                criteria.andKey_wordLike("%"+keyWord+"%");
            }
            Pagination pagination = baseParam.getPagination();
            productList = productService.getList(example, 2, pagination);
            baseParam.setPagination(pagination);
        }
        return productList;
    }

    @Override
    public Product baseProductDetail(BaseParam baseParam) throws Exception {
        Product product;
        if (baseParam.getId() != 0) {
            product = (Product) productService.get(baseParam.getId());
        } else if (baseParam.getPid() != 0) {
            product = (Product) productService.getOne("pid_eq", baseParam.getPid());
        } else {
            throw new RequestException(RequestCommon.ERROR_PARAM_NOT_FOUND);
        }
        return product;
    }

    @Override
    public List<Category> baseCategoryList(BaseParam baseParam) throws Exception {
        List<Category> categoryList;
        Pagination pagination = baseParam.getPagination();
        if (StringUtils.isNotEmpty(baseParam.getKeyWord())) {
            categoryList = categoryService.list("depth", 1, "pagination", pagination, "name_like", baseParam.getKeyWord());
        } else {
            categoryList = categoryService.list("depth", 1, "pagination", pagination);
        }
        return categoryList;
    }

    @Override
    public Category baseCategory(BaseParam baseParam) throws Exception {
        Category category = (Category) categoryService.getOne("category_id_eq", baseParam.getCategory_id());
        return category;
    }

    @Override
    public List<MenuCategory> baseMenuCategoryList(BaseParam baseParam) throws Exception {
        Pagination pagination = baseParam.getPagination();
        List<MenuCategory> menuCategories = menuCategoryService.list("pagination", pagination, "order", "id asc", "recommend_gt&eq", baseParam.getRecommend());
        return menuCategories;
    }

    @Override
    public List<Recommend> getRecommendList() throws Exception {
        List<Recommend> recommendList = recommendService.list("order", "is_recommend desc", "is_recommend_gt&eq", 1);
        for (Recommend recommend : recommendList) {
            BaseParam baseParam = new BaseParam();
            baseParam.setId(recommend.getPid());
            Product product = (Product) productService.get(baseParam.getId());
            recommend.setProduct(product);
        }
        return recommendList;
    }

    @Override
    public List<SearchHistory> getSearchHistory() throws Exception {
        return searchHistoryService.list("depth", 1, "pagination", new Pagination(), "recommend_gt", 0, "order", "recommend desc");
    }

    @Override
    public String baseGetTaoKey(int id) throws Exception {
        Map<String, String> config = configService.map();
        Product product = (Product) productService.get(id);
        Coupon coupon = product.getCoupon();
        String url;
        if (coupon != null) {
            url = coupon.getCoupon_share_url();
        } else {
            url = product.getUrl();
        }
        TaobaoClient client = new DefaultTaobaoClient(config.get("alimama_api_url"), config.get("alimama_appkey"), config.get("alimama_secret"));
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText(product.getTitle());
        req.setUrl("https:" + url);
        req.setLogo(product.getPict_url());
        TbkTpwdCreateResponse rsp = client.execute(req);
        if (rsp != null) {
            TbkTpwdCreateResponse.MapData data = rsp.getData();
            if (data != null) {
                return data.getModel();
            } else {
                throw new RequestException(RequestCommon.ERROR_PARAM_NOT_FOUND);
            }
        } else {
            throw new RequestException(RequestCommon.SYSTEM_ERROR);
        }
    }

}
