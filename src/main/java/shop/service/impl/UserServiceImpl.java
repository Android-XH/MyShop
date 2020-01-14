package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.User;
import shop.mode.UserExample;
import shop.mode.mapper.UserMapper;
import shop.service.UserService;
import shop.util.PasswordUtil;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserExample>implements UserService {
    @Override
    public boolean isExist(String username) throws Exception {
        List<User>userList=list("name_eq", username);
        if(userList==null){
            return false;
        }else{
            return !userList.isEmpty();
        }
    }

    @Override
    public Integer add(User user) throws Exception {
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        return super.add(user);
    }

    @Override
    public User get(String name, String password) {
        return (User) getOne("name_eq", name, "password_eq",PasswordUtil.encryptPassword(password), "order","id asc");
    }
}
