package shop.controller.api.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.controller.RequestBean;
import shop.controller.api.ApiBaseController;
import shop.controller.api.IUserInterface;
import shop.mode.User;
@Controller
public class ApiUserController extends ApiBaseController implements IUserInterface {
    @Override
    public RequestBean login(String userName, String passWord) throws Exception {
        System.out.println("login userName:"+userName+"&passWord:"+passWord);
        User user=getUser(userName,passWord);
        return RequestBean.getUser(user);
    }

    @Override
    public RequestBean register(String userName, String passWord) throws Exception {
        System.out.println("register userName:"+userName+"&passWord:"+passWord);
        User user=registerUser(userName,passWord);
        return RequestBean.getUser(user);
    }
}
