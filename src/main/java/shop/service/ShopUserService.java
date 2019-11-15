package shop.service;


import org.springframework.stereotype.Service;
import shop.mode.ShopUser;
public interface ShopUserService extends BaseService{
    void insert(ShopUser shopUser)throws Exception;

}