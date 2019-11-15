package shop.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.controller.RequestBean;
import shop.util.Pagination;

public interface ApiProductInterface {
    @RequestMapping("test")
    @ResponseBody
    public RequestBean test()throws Exception;
    @RequestMapping("getProductList")
    @ResponseBody
    public RequestBean getProductList(Pagination pagination)throws Exception;
    @RequestMapping("getProductDetail")
    @ResponseBody
    public RequestBean getProductDetail(int id)throws Exception;
    @RequestMapping("getMenuCategory")
    @ResponseBody
    public RequestBean getMenuCategory()throws Exception;
}
