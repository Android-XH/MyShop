package shop.util;

/**
 * 分页类
 */
public class Pagination {
    private int page;
    private int start;
    private int size;
    private int total;
    private String param;

    public int getPage() {
        return page==0?1:page;
    }

    public void setPage(int page) {
        this.page = page;
        setStart((page-1)*size);
    }

    public int getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start<0?0:start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = getsizeValue(size);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Pagination() {
        this.start = 0;
        this.size = MIN_size;
    }

    public Pagination(int start, int size, int total) {
        this.start = start;
        this.size = size;
        this.total = total;
    }

    private final int MAX_size=100;//分页最大返回条目
    private final int MIN_size=20;//分页默认返回条目

    /**
     *设置最大以及最小返回条目数，防止返回数据过大
     * @param size
     * @return
     */
    private int getsizeValue(Integer size){
        if(size==null||size<=0){
            return MIN_size;
        }else if(size!=null&&size>MAX_size){
            return MAX_size;
        }
        return size;
    }

    /**
     * @return 总页数
     */
    public int getTotalPage() {
        int totalPage = total / size - 1;
        // 如果total不能被size整除，或者为0，还要把余下的项目放到最后一页
        if (total % size != 0 || total == 0)
            totalPage = total / size;
        return totalPage;
    }

    /**
     * @return 最后一页的第一项的页码
     */
    public int getLastPage() {
        return getTotalPage();
    }

    public boolean isHasPrevious() {
        return start > 0;
    }

    public boolean isHasNext() {
        return start != getLastPage();
    }


    @Override
    public String toString() {
        return "Pagination{" +
                "page=" + page +
                ", start=" + start +
                ", size=" + size +
                ", total=" + total +
                ", param='" + param +
                '}';
    }
}
