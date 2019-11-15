package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.CategoryItem;
import shop.mode.CategoryItemExample;
import shop.mode.mapper.CategoryItemMapper;
import shop.service.CategoryItemService;
@Service
public class CategoryItemServiceImpl extends BaseServiceImpl<CategoryItemMapper, CategoryItemExample> implements CategoryItemService {
    @Override
    public void insert(CategoryItem categoryItem) throws Exception {
        if(null!=categoryItem.getCategory_id()){
            CategoryItem categoryItem1= (CategoryItem) getOne("category_id_eq",categoryItem.getCategory_id());
            if(categoryItem1!=null){
                categoryItem.setId(categoryItem1.getId());
                update(categoryItem);
            }else{
                add(categoryItem);
            }
        }
    }
}
