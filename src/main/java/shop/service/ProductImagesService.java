package shop.service;

import org.springframework.stereotype.Service;

public interface ProductImagesService extends BaseService{
    void deleteByPid(long pid);
}