package shop.service.impl;

import org.springframework.stereotype.Service;
import shop.mode.ConfigExample;
import shop.mode.Coupon;
import shop.mode.SearchHistory;
import shop.mode.SearchHistoryExample;
import shop.mode.mapper.ConfigMapper;
import shop.mode.mapper.SearchHistoryMapper;
import shop.service.SearchHistoryService;

@Service
public class SearchHistoryImpl extends BaseServiceImpl<SearchHistoryMapper, SearchHistoryExample> implements SearchHistoryService {
    @Override
    public void insert(String keyWord) throws Exception {
        SearchHistory dbKey= (SearchHistory) getOne("keyword_eq",keyWord);
        if(dbKey!=null){
            int num=dbKey.getNum()+1;
            dbKey.setNum(num);
            update(dbKey);
        }else{
            dbKey=new SearchHistory();
            dbKey.setKeyword(keyWord);
            dbKey.setNum(1);
            dbKey.setRecommend(1);
            add(dbKey);
        }
    }
}
