package shop.controller;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import shop.annotation.Group;
import shop.controller.param.ProductParam;
import shop.controller.param.SortUtil;
import shop.exception.AuthException;
import shop.exception.RequestException;
import shop.mode.*;
import shop.service.*;
import shop.util.JWTUtil;
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
    @Autowired
    private UserService userService;
    @Override
    public RequestBean baseTest() throws Exception {
        return RequestBean.Data(SUCCESS_TEST);
    }

    @Override
    public List<Product> baseProductList(ProductParam productParam) throws Exception {
        List<Product> productList;
        if (StringUtils.isNotEmpty(productParam.getSql())) {
            productList = productService.selectBySql(productParam.getSql());
        } else {
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria = example.createCriteria();
            ProductExample.Criteria criteria2 = null;
            ProductExample.Criteria criteria3 = null;
            //根据关键词返回
            String keyWord = productParam.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                searchHistoryService.insert(keyWord);
                keyWord=keyWord.trim();
                criteria2 = example.or();
                criteria3 = example.or();
                criteria.andKey_wordLike("%" + keyWord + "%");
                criteria2.andTitleLike("%" + keyWord + "%");
                criteria3.andShort_titleLike("%" + keyWord + "%");
            }
            //是否根据平台返回
            if (productParam.getTypes() != null&& productParam.getTypes().length()==1) {
                List<Long> sids = new ArrayList<>();
                String[] type = productParam.getTypes().split(",");
                for (String t : type) {
                    int userType = Integer.valueOf(t);
                    List<ShopUser> shopUsers = shopUserService.list("user_type_eq", userType);
                    for (ShopUser shopUser : shopUsers) {
                        sids.add(shopUser.getSid());
                    }
                }
                criteria.andSidIn(sids);
                if (null != criteria2&&null!=criteria3) {
                    criteria2.andSidIn(sids);
                    criteria3.andSidIn(sids);
                }
            }
            //返回价格以上的商品
            if (productParam.getMinPrice() != 0f && productParam.getMaxPrice() == 0f) {
                criteria.andPriceGreaterThanOrEqualTo(productParam.getMinPrice());
                if (null != criteria2&&null!=criteria3) {
                    criteria2.andPriceGreaterThanOrEqualTo(productParam.getMinPrice());
                    criteria3.andPriceGreaterThanOrEqualTo(productParam.getMinPrice());
                }
            }
            //返回价格以下的商品
            if (productParam.getMaxPrice() != 0f) {
                criteria.andPriceBetween(productParam.getMinPrice(), productParam.getMaxPrice());
                if (null != criteria2&&null!=criteria3) {
                    criteria2.andPriceBetween(productParam.getMinPrice(), productParam.getMaxPrice());
                    criteria3.andPriceBetween(productParam.getMinPrice(), productParam.getMaxPrice());
                }
            }
            //根据传入商品ID返回类似商品
            if (productParam.getId() != 0) {
                Product product = (Product) productService.get(productParam.getId());
                if (product != null) {
                    criteria.andIdNotEqualTo(product.getId());
                    if (null != criteria2&&null!=criteria3) {
                        criteria2.andIdNotEqualTo(product.getId());
                        criteria3.andIdNotEqualTo(product.getId());
                    }
                    productParam.setCategory_item_id(product.getCategory_item_id());
                } else {
                    throw new RequestException(RequestCommon.ERROR_PRODUCT_NOT_FOUND);
                }
            }
            //返回同类商品
            if (productParam.getCategory_item_id() != 0) {
                criteria.andCategory_item_idEqualTo(productParam.getCategory_item_id());
                if (null != criteria2&&null!=criteria3) {
                    criteria2.andCategory_item_idEqualTo(productParam.getCategory_item_id());
                    criteria3.andCategory_item_idEqualTo(productParam.getCategory_item_id());
                }
            }
            //返回同一大类下的商品
            if (productParam.getCategory_id() != 0) {
                criteria.andCategory_idEqualTo(productParam.getCategory_id());
                if (null != criteria2&&null!=criteria3) {
                    criteria2.andCategory_idEqualTo(productParam.getCategory_id());
                    criteria3.andCategory_idEqualTo(productParam.getCategory_id());
                }
            }
            if(productParam.getMenu_id()!=0){
                List<Category>categoryList=categoryService.list("menu_id_eq", productParam.getMenu_id());
                List<Long>categoryIDS=new ArrayList<>();
                for(Category category:categoryList){
                    categoryIDS.add(category.getCategory_id());
                }
                criteria.andCategory_idIn(categoryIDS);
                if (null != criteria2&&null!=criteria3) {
                    criteria2.andCategory_idIn(categoryIDS);
                    criteria3.andCategory_idIn(categoryIDS);
                }
            }
            if(productParam.getSort()!=null){
                example.setOrderByClause(SortUtil.handleSort(productParam.getSort()));
            }
            Pagination pagination = productParam.getPagination();
            productList = productService.getList(example, 2, pagination);
            productParam.setPagination(pagination);

        }
        return productList;
    }

    @Override
    public Product baseProductDetail(ProductParam productParam) throws Exception {
        Product product;
        if (productParam.getId() != 0) {
            product = (Product) productService.get(productParam.getId());
        } else if (productParam.getPid() != 0) {
            product = (Product) productService.getOne("pid_eq", productParam.getPid());
        } else {
            throw new RequestException(RequestCommon.ERROR_PARAM_NOT_FOUND);
        }
        return product;
    }

    @Override
    public List<Category> baseCategoryList(ProductParam productParam) throws Exception {
        List<Category> categoryList;
        Pagination pagination = productParam.getPagination();
        if (StringUtils.isNotEmpty(productParam.getKeyWord())) {
            categoryList = categoryService.list("depth", 1, "pagination", pagination, "name_like", productParam.getKeyWord());
        } else {
            categoryList = categoryService.list("depth", 1, "pagination", pagination);
        }
        return categoryList;
    }

    @Override
    public Category baseCategory(ProductParam productParam) throws Exception {
        Category category = (Category) categoryService.getOne("category_id_eq", productParam.getCategory_id());
        return category;
    }

    @Override
    public List<MenuCategory> baseMenuCategoryList(ProductParam productParam) throws Exception {
        Pagination pagination = productParam.getPagination();
        List<MenuCategory> menuCategories = menuCategoryService.list("pagination", pagination, "order", "id asc", "recommend_gt&eq", productParam.getRecommend());
        return menuCategories;
    }

    @Override
    public List<Recommend> getRecommendList() throws Exception {
        List<Recommend> recommendList = recommendService.list("order", "is_recommend desc", "is_recommend_gt&eq", 1);
        for (Recommend recommend : recommendList) {
            ProductParam productParam = new ProductParam();
            productParam.setId(recommend.getPid());
            Product product = (Product) productService.get(productParam.getId());
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

    @Override
    public User getUser(String userName,String passWord) throws Exception {
        User user=userService.get(userName,passWord);
        if(user==null){
            throw new RequestException(RequestCommon.ERR_USER_PASS_WORD);
        }
        String token= JWTUtil.createToken(user);
        user.setToken(token);
        return user;
    }

    @Override
    public User registerUser(String userName, String passWord) throws Exception {
        if(userService.isExist(userName)){
            throw new AuthException(RequestCommon.USER_IS_EXIST);
        }
        User user=new User();
        user.setName(userName);
        user.setPassword(passWord);
        user.setGroup(Group.user);
        String token= JWTUtil.createToken(user);
        System.out.println("token=="+token);
        user.setToken(token);
        userService.add(user);
        return user;
    }
}
