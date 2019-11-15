package shop.service;


import org.springframework.stereotype.Service;
import shop.mode.Category;
public interface CategoryService extends BaseService{
    void insert(Category category)throws Exception;
}