package shop.controller.api.impl;

import shop.controller.RequestBean;
import shop.controller.api.ApiBaseController;
import shop.controller.api.IUserInterface;

public class ApiUserController extends ApiBaseController implements IUserInterface {
    @Override
    public RequestBean login(String userName, String passWord) throws Exception {
        return null;
    }
}
