package shop.service.impl;


import org.springframework.stereotype.Service;
import shop.mode.MaterialExample;
import shop.mode.mapper.MaterialMapper;
import shop.service.MaterialService;

/**
 * @see MaterialService
 */
@Service
public class MaterialServiceImpl extends BaseServiceImpl<MaterialMapper, MaterialExample>implements MaterialService {

}