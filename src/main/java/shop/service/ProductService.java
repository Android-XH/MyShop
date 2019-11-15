package shop.service;


import org.springframework.stereotype.Service;
import shop.mode.Product;

import java.util.List;
public interface ProductService extends BaseService{
     void insertToDb(Product product) throws Exception;
}