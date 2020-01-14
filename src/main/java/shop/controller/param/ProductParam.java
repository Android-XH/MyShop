package shop.controller.param;

import org.apache.commons.lang3.StringUtils;
import shop.util.Pagination;

import java.util.Arrays;

public class ProductParam {
    private String keyWord;//搜索关键词
    private int id;//条目ID
    private long pid;//商品ID
    private long category_id;//分类ID
    private long category_item_id;//子分类ID
    private int recommend;//推荐等级
    private String sort;
    private String sql;
    private int page;
    private int size;
    private float minPrice;
    private float maxPrice;
    private String types;
    private int menu_id;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    private Pagination pagination;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Pagination getPagination() {
        if(pagination==null){
            pagination=new Pagination();
            pagination.setPage(page);
            pagination.setSize(size);
        }
        pagination.setParam(getParam());
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getCategory_item_id() {
        return category_item_id;
    }

    public void setCategory_item_id(long category_item_id) {
        this.category_item_id = category_item_id;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getSort() {
        return StringUtils.substringBefore(sort,",");
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    private String getParam(){
        StringBuffer stringBuffer=new StringBuffer();
        if(id!=0){
            stringBuffer.append("&id="+this.getId());
        }
        if(pid!=0){
            stringBuffer.append("&pid="+this.getPid());
        }
        if(category_id!=0){
            stringBuffer.append("&categoryID="+this.getCategory_id());
        }
        if(category_item_id!=0){
            stringBuffer.append("&categoryItemID="+this.getCategory_item_id());
        }
        if(StringUtils.isNotEmpty(keyWord)){
            stringBuffer.append("&keyword="+this.getKeyWord());
        }
        if(StringUtils.isNotEmpty(sort)){
            stringBuffer.append("&sort="+getSort());
        }
        return stringBuffer.toString();
    }

    @Override
    public String toString() {
        return "BaseParam{" +
                "keyWord='" + keyWord + '\'' +
                ", id=" + id +
                ", pid=" + pid +
                ", category_id=" + category_id +
                ", category_item_id=" + category_item_id +
                ", recommend=" + recommend +
                ", sort='" + sort + '\'' +
                ", sql='" + sql + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", types=" + types+
                ", pagination=" + pagination +
                '}';
    }
}
