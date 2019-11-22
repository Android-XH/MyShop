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
        Product product1= (Product) getOne("pid_eq",product.getPid());
        if(product1!=null){
            product.setId(product1.getId());
            update(product);
        }else{
            add(product);
        }
    }
}
