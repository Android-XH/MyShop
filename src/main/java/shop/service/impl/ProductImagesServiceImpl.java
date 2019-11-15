package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.ProductImages;
import shop.mode.ProductImagesExample;
import shop.mode.mapper.ProductImagesMapper;
import shop.service.ProductImagesService;

import java.util.List;
@Service
public class ProductImagesServiceImpl extends BaseServiceImpl<ProductImagesMapper, ProductImagesExample> implements ProductImagesService {
    @Override
    public void deleteByPid(long pid) {
        try {
            List<ProductImages>imagesList= list("pid_eq",pid);
            if(!imagesList.isEmpty()){
                for(ProductImages images:imagesList){
                    delete(images);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
