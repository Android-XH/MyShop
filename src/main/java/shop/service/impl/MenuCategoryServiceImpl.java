package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.MenuCategoryExample;
import shop.mode.mapper.MenuCategoryMapper;
import shop.service.MenuCategoryService;
@Service
public class MenuCategoryServiceImpl extends BaseServiceImpl<MenuCategoryMapper, MenuCategoryExample> implements MenuCategoryService {
}
