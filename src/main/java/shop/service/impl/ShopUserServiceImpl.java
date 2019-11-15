package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.ShopUser;
import shop.mode.ShopUserExample;
import shop.mode.mapper.ShopUserMapper;
import shop.service.ShopUserService;
@Service
public class ShopUserServiceImpl extends BaseServiceImpl<ShopUserMapper, ShopUserExample> implements ShopUserService {
    @Override
    public void insert(ShopUser shopUser) throws Exception {
        if(shopUser.getSid()!=null){
            ShopUser shopUser1= (ShopUser) getOne("sid_eq",shopUser.getSid());
            if(shopUser1!=null){
                shopUser.setId(shopUser1.getId());
                update(shopUser);
            }else{
                add(shopUser);
            }
        }
    }
}
