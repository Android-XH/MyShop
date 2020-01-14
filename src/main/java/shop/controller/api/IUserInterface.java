package shop.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.controller.RequestBean;

public interface IUserInterface {
    @RequestMapping("login")
    @ResponseBody
    public RequestBean login(String userName,String passWord)throws Exception;
    @RequestMapping("register")
    @ResponseBody
    public RequestBean register(String userName,String passWord)throws Exception;

}
