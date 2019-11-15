package shop.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import shop.mode.Product;
import shop.mode.ProductExample;
import shop.mode.mapper.ProductMapper;
import shop.service.ProductService;

import java.util.List;
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, ProductExample> implements ProductService {

    @Override
    public void insertToDb(Product product) throws Exception {
        try{
            if(product.getPid()!=null){
                Product product1= (Product) getOne("pid_eq",product.getPid());
                if(product1!=null){
                    product.setId(product1.getId());
                    String key=product.getKey_word();
                   if(key!=null&&!key.isEmpty()){
                       String keyWord[]=key.split("/");
                       String oldKey=product1.getKey_word();
                       if(oldKey!=null&&!oldKey.isEmpty()){
                           for(String k:keyWord){
                               if(!oldKey.contains(k)){
                                   oldKey+=("/"+k);
                               }
                           }
                           product.setKey_word(oldKey);
                       }
                   }
                    update(product);
                }else{
                    add(product);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

}
