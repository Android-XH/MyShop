package shop.mode;

import java.util.Date;
import shop.mode.extension.SearchHistoryExtension;
import shop.mode.mapper.SearchHistoryMapper;

public class SearchHistory extends SearchHistoryExtension implements shop.mode.ORM.POJOMapper<SearchHistoryMapper> {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column search_key_word.keyword
     *
     * @mbg.generated
     */
    private String keyword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column search_key_word.num
     *
     * @mbg.generated
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column search_key_word.recommend
     *
     * @mbg.generated
     */
    private Integer recommend;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column search_key_word.deleteAt
     *
     * @mbg.generated
     */
    private Date deleteAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column search_key_word.keyword
     *
     * @return the value of search_key_word.keyword
     *
     * @mbg.generated
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column search_key_word.keyword
     *
     * @param keyword the value for search_key_word.keyword
     *
     * @mbg.generated
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column search_key_word.num
     *
     * @return the value of search_key_word.num
     *
     * @mbg.generated
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column search_key_word.num
     *
     * @param num the value for search_key_word.num
     *
     * @mbg.generated
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column search_key_word.recommend
     *
     * @return the value of search_key_word.recommend
     *
     * @mbg.generated
     */
    public Integer getRecommend() {
        return recommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column search_key_word.recommend
     *
     * @param recommend the value for search_key_word.recommend
     *
     * @mbg.generated
     */
    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column search_key_word.deleteAt
     *
     * @return the value of search_key_word.deleteAt
     *
     * @mbg.generated
     */
    public Date getDeleteAt() {
        return deleteAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column search_key_word.deleteAt
     *
     * @param deleteAt the value for search_key_word.deleteAt
     *
     * @mbg.generated
     */
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}