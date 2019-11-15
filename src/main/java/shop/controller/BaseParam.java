package shop.controller;

import org.apache.commons.lang3.StringUtils;
import shop.util.Pagination;

public class BaseParam {
    private Pagination pagination;
    private String keyWord;//搜索关键词
    private int id;//条目ID
    private long pid;//商品ID
    private long category_id;//分类ID
    private long category_item_id;//子分类ID
    private int recommend;//推荐等级
    private String sort;

    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Pagination getPagination() {
        if(pagination==null){
            pagination=new Pagination();
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
//        return sort;
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
            stringBuffer.append("&categoryItemId="+this.getCategory_item_id());
        }
        if(StringUtils.isNotEmpty(keyWord)){
            stringBuffer.append("&keyword="+this.getKeyWord());
        }
        if(StringUtils.isNotEmpty(sort)){
            stringBuffer.append("&sort="+getSort());
        }
        return stringBuffer.toString();
    }
}
