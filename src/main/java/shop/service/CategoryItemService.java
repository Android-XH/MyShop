package shop.service;


import org.springframework.stereotype.Service;
import shop.mode.CategoryItem;

public interface CategoryItemService extends BaseService{
    void insert(CategoryItem categoryItem)throws Exception;

}