package shop.controller;

import com.sun.org.apache.regexp.internal.RE;
import shop.exception.RequestException;
import shop.mode.*;
import shop.util.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestBean extends HashMap<String, Object> {
    public static RequestBean ArrayData(List<?> data){
        return ArrayData(data,null);
    }
    public static RequestBean ArrayData(List<?> data,Pagination pagination){
        RequestBean r = new RequestBean();
        r.put("code", 0);
        r.put("msg", "success");
        if(pagination!=null){
            r.put("count", pagination.getTotal());
            r.put("pagination",getPagination(pagination));
        }
        r.put("data", data);
        return r;
    }
    private static RequestBean getPagination(Pagination pagination){
        RequestBean r = new RequestBean();
        r.put("page",pagination.getPage());
        r.put("total",pagination.getTotal());
        r.put("size",pagination.getSize());
        return r;
    }
    public static RequestBean Data(Object data){
        RequestBean r = new RequestBean();
        r.put("code", 0);
        r.put("msg", "success");
        if(data instanceof RequestCommon){
            RequestCommon common= (RequestCommon) data;
            r.put("data", common.getMessage());
        }else{
            r.put("data", data);
        }
        return r;
    }
    public static RequestBean Err(RequestCommon requestCommon){
        RequestBean r = new RequestBean();
        r.put("code", requestCommon.getCode());
        r.put("msg", requestCommon.getMessage());
        return r;
    }
    public static RequestBean getRecommendList(List<Recommend> recommends){
        List<HashMap<String,Object>>products=new ArrayList<HashMap<String,Object>>(recommends.size());
        for(Recommend recommend:recommends){
            products.add(getProductMap(recommend.getProduct()));
        }
        return ArrayData(products,null);
    }

    public static RequestBean getProductList(List<Product> productList,Pagination pagination){
        List<HashMap<String,Object>>products=new ArrayList<HashMap<String,Object>>(productList.size());
        for(Product product:productList){
            products.add(getProductMap(product));
        }
        return ArrayData(products,pagination);
    }

    private static  HashMap<String,Object>getProductMap(Product product){
        HashMap<String,Object> p=new HashMap<String,Object>();
        p.put("id",product.getId());
        p.put("title",product.getTitle());
        p.put("pid",product.getPid());
        p.put("short_title",product.getShort_title());
        p.put("img_url",product.getPict_url());
        p.put("price",product.getPrice());
        p.put("description",product.getItem_description());
        p.put("sell_count",product.getVolume());
        Coupon coupon=product.getCoupon();
        if(coupon!=null){
            p.put("coupon_amount",coupon.getCoupon_amount());
            p.put("coupon_info",coupon.getCoupon_info());
            p.put("coupon_total_count",coupon.getCoupon_amount());
            p.put("coupon_remain_count",coupon.getCoupon_remain_count());
            p.put("coupon_share_url",coupon.getCoupon_share_url());
        }
        ShopUser shopUser=product.getShop();
        if(shopUser!=null){
            p.put("shop_title",shopUser.getShop_title());
            p.put("shop_city",shopUser.getProvcity());
        }
        return p;
    }

    /**
     * @param product
     * @return
     */
     public static RequestBean getProductDetail(Product product){
        HashMap<String, Object> p= getProductMap(product);
        p.put("title",product.getTitle());
        p.put("detail_url",product.getUrl());
        List<ProductImages>imagesList =product.getProductImages();
        if(!imagesList.isEmpty()){
            List<String>smallList=new ArrayList<>();
            for(ProductImages productImages:imagesList){
                smallList.add(productImages.getPic_url());
            }
            p.put("small_images",smallList);
        }
        return Data(p);
    }
    public static RequestBean getUser(User user){
        RequestBean requestBean=new RequestBean();
        requestBean.put("id",user.getId());
        requestBean.put("user_name",user.getName());
        requestBean.put("token",user.getToken());
        return Data(requestBean);
    }

}