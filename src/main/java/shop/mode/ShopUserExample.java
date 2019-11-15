package shop.mode;

import java.util.ArrayList;
import java.util.List;
import shop.mode.base.BaseExample;

public class ShopUserExample implements BaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public ShopUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Long value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Long value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Long value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Long value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Long value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Long value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Long> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Long> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Long value1, Long value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Long value1, Long value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andProvcityIsNull() {
            addCriterion("provcity is null");
            return (Criteria) this;
        }

        public Criteria andProvcityIsNotNull() {
            addCriterion("provcity is not null");
            return (Criteria) this;
        }

        public Criteria andProvcityEqualTo(String value) {
            addCriterion("provcity =", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityNotEqualTo(String value) {
            addCriterion("provcity <>", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityGreaterThan(String value) {
            addCriterion("provcity >", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityGreaterThanOrEqualTo(String value) {
            addCriterion("provcity >=", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityLessThan(String value) {
            addCriterion("provcity <", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityLessThanOrEqualTo(String value) {
            addCriterion("provcity <=", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityLike(String value) {
            addCriterion("provcity like", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityNotLike(String value) {
            addCriterion("provcity not like", value, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityIn(List<String> values) {
            addCriterion("provcity in", values, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityNotIn(List<String> values) {
            addCriterion("provcity not in", values, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityBetween(String value1, String value2) {
            addCriterion("provcity between", value1, value2, "provcity");
            return (Criteria) this;
        }

        public Criteria andProvcityNotBetween(String value1, String value2) {
            addCriterion("provcity not between", value1, value2, "provcity");
            return (Criteria) this;
        }

        public Criteria andShop_dsrIsNull() {
            addCriterion("shop_dsr is null");
            return (Criteria) this;
        }

        public Criteria andShop_dsrIsNotNull() {
            addCriterion("shop_dsr is not null");
            return (Criteria) this;
        }

        public Criteria andShop_dsrEqualTo(Long value) {
            addCriterion("shop_dsr =", value, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrNotEqualTo(Long value) {
            addCriterion("shop_dsr <>", value, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrGreaterThan(Long value) {
            addCriterion("shop_dsr >", value, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_dsr >=", value, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrLessThan(Long value) {
            addCriterion("shop_dsr <", value, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrLessThanOrEqualTo(Long value) {
            addCriterion("shop_dsr <=", value, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrIn(List<Long> values) {
            addCriterion("shop_dsr in", values, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrNotIn(List<Long> values) {
            addCriterion("shop_dsr not in", values, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrBetween(Long value1, Long value2) {
            addCriterion("shop_dsr between", value1, value2, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_dsrNotBetween(Long value1, Long value2) {
            addCriterion("shop_dsr not between", value1, value2, "shop_dsr");
            return (Criteria) this;
        }

        public Criteria andShop_titleIsNull() {
            addCriterion("shop_title is null");
            return (Criteria) this;
        }

        public Criteria andShop_titleIsNotNull() {
            addCriterion("shop_title is not null");
            return (Criteria) this;
        }

        public Criteria andShop_titleEqualTo(String value) {
            addCriterion("shop_title =", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleNotEqualTo(String value) {
            addCriterion("shop_title <>", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleGreaterThan(String value) {
            addCriterion("shop_title >", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleGreaterThanOrEqualTo(String value) {
            addCriterion("shop_title >=", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleLessThan(String value) {
            addCriterion("shop_title <", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleLessThanOrEqualTo(String value) {
            addCriterion("shop_title <=", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleLike(String value) {
            addCriterion("shop_title like", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleNotLike(String value) {
            addCriterion("shop_title not like", value, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleIn(List<String> values) {
            addCriterion("shop_title in", values, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleNotIn(List<String> values) {
            addCriterion("shop_title not in", values, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleBetween(String value1, String value2) {
            addCriterion("shop_title between", value1, value2, "shop_title");
            return (Criteria) this;
        }

        public Criteria andShop_titleNotBetween(String value1, String value2) {
            addCriterion("shop_title not between", value1, value2, "shop_title");
            return (Criteria) this;
        }

        public Criteria andUser_typeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUser_typeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUser_typeEqualTo(Long value) {
            addCriterion("user_type =", value, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeNotEqualTo(Long value) {
            addCriterion("user_type <>", value, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeGreaterThan(Long value) {
            addCriterion("user_type >", value, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeGreaterThanOrEqualTo(Long value) {
            addCriterion("user_type >=", value, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeLessThan(Long value) {
            addCriterion("user_type <", value, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeLessThanOrEqualTo(Long value) {
            addCriterion("user_type <=", value, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeIn(List<Long> values) {
            addCriterion("user_type in", values, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeNotIn(List<Long> values) {
            addCriterion("user_type not in", values, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeBetween(Long value1, Long value2) {
            addCriterion("user_type between", value1, value2, "user_type");
            return (Criteria) this;
        }

        public Criteria andUser_typeNotBetween(Long value1, Long value2) {
            addCriterion("user_type not between", value1, value2, "user_type");
            return (Criteria) this;
        }

        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table shop_user
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table shop_user
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}