package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.Category;
import shop.mode.CategoryExample;
import shop.mode.mapper.CategoryMapper;
import shop.service.CategoryService;
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, CategoryExample> implements CategoryService {
    @Override
    public void insert(Category category) throws Exception {
        if(null!=category.getCategory_id()){
            Category category1= (Category) getOne("category_id_eq",category.getCategory_id());
            if(category1==null){
                add(category);
            }else{
                category.setId(category1.getId());
                update(category);
            }
        }
    }
}
