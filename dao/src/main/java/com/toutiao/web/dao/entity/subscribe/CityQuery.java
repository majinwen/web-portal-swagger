package com.toutiao.web.dao.entity.subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */

public class CityQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String fields;

    public CityQuery() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

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

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andBelongIsNull() {
            addCriterion("belong is null");
            return (Criteria) this;
        }

        public Criteria andBelongIsNotNull() {
            addCriterion("belong is not null");
            return (Criteria) this;
        }

        public Criteria andBelongEqualTo(String value) {
            addCriterion("belong =", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotEqualTo(String value) {
            addCriterion("belong <>", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThan(String value) {
            addCriterion("belong >", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThanOrEqualTo(String value) {
            addCriterion("belong >=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThan(String value) {
            addCriterion("belong <", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThanOrEqualTo(String value) {
            addCriterion("belong <=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLike(String value) {
            addCriterion("belong like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotLike(String value) {
            addCriterion("belong not like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongIn(List<String> values) {
            addCriterion("belong in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotIn(List<String> values) {
            addCriterion("belong not in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongBetween(String value1, String value2) {
            addCriterion("belong between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotBetween(String value1, String value2) {
            addCriterion("belong not between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andSortingIsNull() {
            addCriterion("sorting is null");
            return (Criteria) this;
        }

        public Criteria andSortingIsNotNull() {
            addCriterion("sorting is not null");
            return (Criteria) this;
        }

        public Criteria andSortingEqualTo(Integer value) {
            addCriterion("sorting =", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotEqualTo(Integer value) {
            addCriterion("sorting <>", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThan(Integer value) {
            addCriterion("sorting >", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorting >=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThan(Integer value) {
            addCriterion("sorting <", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingLessThanOrEqualTo(Integer value) {
            addCriterion("sorting <=", value, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingIn(List<Integer> values) {
            addCriterion("sorting in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotIn(List<Integer> values) {
            addCriterion("sorting not in", values, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingBetween(Integer value1, Integer value2) {
            addCriterion("sorting between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andSortingNotBetween(Integer value1, Integer value2) {
            addCriterion("sorting not between", value1, value2, "sorting");
            return (Criteria) this;
        }

        public Criteria andCityPinyinIsNull() {
            addCriterion("city_pinyin is null");
            return (Criteria) this;
        }

        public Criteria andCityPinyinIsNotNull() {
            addCriterion("city_pinyin is not null");
            return (Criteria) this;
        }

        public Criteria andCityPinyinEqualTo(String value) {
            addCriterion("city_pinyin =", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinNotEqualTo(String value) {
            addCriterion("city_pinyin <>", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinGreaterThan(String value) {
            addCriterion("city_pinyin >", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinGreaterThanOrEqualTo(String value) {
            addCriterion("city_pinyin >=", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinLessThan(String value) {
            addCriterion("city_pinyin <", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinLessThanOrEqualTo(String value) {
            addCriterion("city_pinyin <=", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinLike(String value) {
            addCriterion("city_pinyin like", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinNotLike(String value) {
            addCriterion("city_pinyin not like", value, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinIn(List<String> values) {
            addCriterion("city_pinyin in", values, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinNotIn(List<String> values) {
            addCriterion("city_pinyin not in", values, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinBetween(String value1, String value2) {
            addCriterion("city_pinyin between", value1, value2, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinNotBetween(String value1, String value2) {
            addCriterion("city_pinyin not between", value1, value2, "cityPinyin");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsIsNull() {
            addCriterion("city_pinyin_initials is null");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsIsNotNull() {
            addCriterion("city_pinyin_initials is not null");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsEqualTo(String value) {
            addCriterion("city_pinyin_initials =", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsNotEqualTo(String value) {
            addCriterion("city_pinyin_initials <>", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsGreaterThan(String value) {
            addCriterion("city_pinyin_initials >", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsGreaterThanOrEqualTo(String value) {
            addCriterion("city_pinyin_initials >=", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsLessThan(String value) {
            addCriterion("city_pinyin_initials <", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsLessThanOrEqualTo(String value) {
            addCriterion("city_pinyin_initials <=", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsLike(String value) {
            addCriterion("city_pinyin_initials like", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsNotLike(String value) {
            addCriterion("city_pinyin_initials not like", value, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsIn(List<String> values) {
            addCriterion("city_pinyin_initials in", values, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsNotIn(List<String> values) {
            addCriterion("city_pinyin_initials not in", values, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsBetween(String value1, String value2) {
            addCriterion("city_pinyin_initials between", value1, value2, "cityPinyinInitials");
            return (Criteria) this;
        }

        public Criteria andCityPinyinInitialsNotBetween(String value1, String value2) {
            addCriterion("city_pinyin_initials not between", value1, value2, "cityPinyinInitials");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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
