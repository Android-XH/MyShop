package shop.service;


import org.springframework.stereotype.Service;
import shop.mode.Coupon;
import shop.mode.SearchHistory;
public interface SearchHistoryService extends BaseService{
    void insert(String keyWord)throws Exception;
}