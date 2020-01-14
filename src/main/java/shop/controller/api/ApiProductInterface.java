package shop.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.controller.param.ProductParam;
import shop.controller.RequestBean;

public interface ApiProductInterface {
    @RequestMapping("test")
    @ResponseBody
    public RequestBean test()throws Exception;
    @RequestMapping("getProductList")
    @ResponseBody
    public RequestBean getProductList(ProductParam productParam)throws Exception;
    @RequestMapping("getProductDetail")
    @ResponseBody
    public RequestBean getProductDetail(int id)throws Exception;
    @RequestMapping("getMenuCategory")
    @ResponseBody
    public RequestBean getMenuCategory()throws Exception;
    @RequestMapping("getRecommend")
    @ResponseBody
    public RequestBean getRecommend()throws Exception;
    @RequestMapping("getSearchKey")
    @ResponseBody
    public RequestBean getSearchKey()throws Exception;
    @RequestMapping("getTaoKey")
    @ResponseBody
    public RequestBean getTaoKey(int id)throws Exception;
}
