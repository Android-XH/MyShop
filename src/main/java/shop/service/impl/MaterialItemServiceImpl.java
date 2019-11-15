package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.MaterialItemExample;
import shop.mode.mapper.MaterialItemMapper;
import shop.service.MaterialItemService;

@Service
public class MaterialItemServiceImpl extends BaseServiceImpl<MaterialItemMapper, MaterialItemExample> implements MaterialItemService {

}