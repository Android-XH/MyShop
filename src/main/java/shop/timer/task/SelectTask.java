package shop.timer.task;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import shop.mode.Category;
import shop.mode.CategoryItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

//任务线程类
public  class SelectTask implements Callable<List<TbkDgMaterialOptionalResponse.MapData>> {
    private final long PAGE_SIZE=100;
    private final long TOTAL_SIZE=500;
    TaobaoClient client;
    Category category;
    long adzoneID;

    public SelectTask(TaobaoClient client, Category category, long adzoneID) {
        this.client = client;
        this.category = category;
        this.adzoneID = adzoneID;
    }

    @Override
    public List<TbkDgMaterialOptionalResponse.MapData> call() throws Exception {
        long totalPage = getTotalPage(TOTAL_SIZE);
        List<TbkDgMaterialOptionalResponse.MapData> dataList = new ArrayList<>();
        List<CategoryItem>categoryItems=category.getCategoryItems();
        for(CategoryItem categoryItem:categoryItems){
            for (long i = 1; i <= totalPage; i++) {
                List<TbkDgMaterialOptionalResponse.MapData> data = getBaseArrayRequestOfCat(client, categoryItem, adzoneID, i);
                if(data.isEmpty()){
                    break;
                }else{
                    for (TbkDgMaterialOptionalResponse.MapData d : data) {
                        if (!isHave(dataList, d)) {
                            dataList.add(d);
                        }
                    }
                }
            }
        }
        return dataList;
    }

    private boolean isHave(List<TbkDgMaterialOptionalResponse.MapData> dataList, TbkDgMaterialOptionalResponse.MapData d) {
        for (TbkDgMaterialOptionalResponse.MapData data : dataList) {
            if (data.getItemId().equals(d.getItemId())) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return 总页数
     */
    public long getTotalPage(long total) {
        long totalPage = total / PAGE_SIZE;
        // 如果total不能被count整除，或者为0，还要把余下的项目放到最后一页
        if (total % PAGE_SIZE != 0 || total == 0)
            totalPage = total / PAGE_SIZE+1;
        return totalPage;
    }
    private List<TbkDgMaterialOptionalResponse.MapData> getBaseArrayRequestOfCat(TaobaoClient client, CategoryItem item, long adzoneID, long page) throws ApiException {
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(adzoneID);
        req.setPageSize(PAGE_SIZE);//分页条目
        req.setPageNo(page);//分页
        req.setCat(String.valueOf(item.getCategory_item_id()));//分类ID
//        req.setNeedPrepay(true);//是否加入消费者保障
        req.setHasCoupon(true);//只取有优惠券的
//        req.setIncludeGoodRate(true);//include_good_rate -好评率是否高于行业均值
        //排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price）
        req.setSort("total_sales_des");
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        if (rsp != null) {
            List<TbkDgMaterialOptionalResponse.MapData> dataList = rsp.getResultList();
            if (dataList != null) {
                return dataList;
            }
        }
        return Collections.emptyList();
    }
}