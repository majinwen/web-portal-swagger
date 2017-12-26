package com.toutiao.web.dao.entity.officeweb;

import java.util.ArrayList;
import java.util.List;

public class lotRatioExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public lotRatioExample() {
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

        public Criteria andBuildingIdIsNull() {
            addCriterion("building_id is null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIsNotNull() {
            addCriterion("building_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingIdEqualTo(Integer value) {
            addCriterion("building_id =", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotEqualTo(Integer value) {
            addCriterion("building_id <>", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThan(Integer value) {
            addCriterion("building_id >", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_id >=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThan(Integer value) {
            addCriterion("building_id <", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdLessThanOrEqualTo(Integer value) {
            addCriterion("building_id <=", value, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdIn(List<Integer> values) {
            addCriterion("building_id in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotIn(List<Integer> values) {
            addCriterion("building_id not in", values, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdBetween(Integer value1, Integer value2) {
            addCriterion("building_id between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("building_id not between", value1, value2, "buildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNull() {
            addCriterion("building_name is null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIsNotNull() {
            addCriterion("building_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingNameEqualTo(String value) {
            addCriterion("building_name =", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotEqualTo(String value) {
            addCriterion("building_name <>", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThan(String value) {
            addCriterion("building_name >", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameGreaterThanOrEqualTo(String value) {
            addCriterion("building_name >=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThan(String value) {
            addCriterion("building_name <", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLessThanOrEqualTo(String value) {
            addCriterion("building_name <=", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameLike(String value) {
            addCriterion("building_name like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotLike(String value) {
            addCriterion("building_name not like", value, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameIn(List<String> values) {
            addCriterion("building_name in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotIn(List<String> values) {
            addCriterion("building_name not in", values, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameBetween(String value1, String value2) {
            addCriterion("building_name between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andBuildingNameNotBetween(String value1, String value2) {
            addCriterion("building_name not between", value1, value2, "buildingName");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIsNull() {
            addCriterion("turnover_rate is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIsNotNull() {
            addCriterion("turnover_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateEqualTo(String value) {
            addCriterion("turnover_rate =", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotEqualTo(String value) {
            addCriterion("turnover_rate <>", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateGreaterThan(String value) {
            addCriterion("turnover_rate >", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateGreaterThanOrEqualTo(String value) {
            addCriterion("turnover_rate >=", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLessThan(String value) {
            addCriterion("turnover_rate <", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLessThanOrEqualTo(String value) {
            addCriterion("turnover_rate <=", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateLike(String value) {
            addCriterion("turnover_rate like", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotLike(String value) {
            addCriterion("turnover_rate not like", value, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateIn(List<String> values) {
            addCriterion("turnover_rate in", values, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotIn(List<String> values) {
            addCriterion("turnover_rate not in", values, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateBetween(String value1, String value2) {
            addCriterion("turnover_rate between", value1, value2, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andTurnoverRateNotBetween(String value1, String value2) {
            addCriterion("turnover_rate not between", value1, value2, "turnoverRate");
            return (Criteria) this;
        }

        public Criteria andHuanbiIsNull() {
            addCriterion("huanbi is null");
            return (Criteria) this;
        }

        public Criteria andHuanbiIsNotNull() {
            addCriterion("huanbi is not null");
            return (Criteria) this;
        }

        public Criteria andHuanbiEqualTo(String value) {
            addCriterion("huanbi =", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiNotEqualTo(String value) {
            addCriterion("huanbi <>", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiGreaterThan(String value) {
            addCriterion("huanbi >", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiGreaterThanOrEqualTo(String value) {
            addCriterion("huanbi >=", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiLessThan(String value) {
            addCriterion("huanbi <", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiLessThanOrEqualTo(String value) {
            addCriterion("huanbi <=", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiLike(String value) {
            addCriterion("huanbi like", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiNotLike(String value) {
            addCriterion("huanbi not like", value, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiIn(List<String> values) {
            addCriterion("huanbi in", values, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiNotIn(List<String> values) {
            addCriterion("huanbi not in", values, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiBetween(String value1, String value2) {
            addCriterion("huanbi between", value1, value2, "huanbi");
            return (Criteria) this;
        }

        public Criteria andHuanbiNotBetween(String value1, String value2) {
            addCriterion("huanbi not between", value1, value2, "huanbi");
            return (Criteria) this;
        }

        public Criteria andTongbiIsNull() {
            addCriterion("tongbi is null");
            return (Criteria) this;
        }

        public Criteria andTongbiIsNotNull() {
            addCriterion("tongbi is not null");
            return (Criteria) this;
        }

        public Criteria andTongbiEqualTo(String value) {
            addCriterion("tongbi =", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiNotEqualTo(String value) {
            addCriterion("tongbi <>", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiGreaterThan(String value) {
            addCriterion("tongbi >", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiGreaterThanOrEqualTo(String value) {
            addCriterion("tongbi >=", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiLessThan(String value) {
            addCriterion("tongbi <", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiLessThanOrEqualTo(String value) {
            addCriterion("tongbi <=", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiLike(String value) {
            addCriterion("tongbi like", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiNotLike(String value) {
            addCriterion("tongbi not like", value, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiIn(List<String> values) {
            addCriterion("tongbi in", values, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiNotIn(List<String> values) {
            addCriterion("tongbi not in", values, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiBetween(String value1, String value2) {
            addCriterion("tongbi between", value1, value2, "tongbi");
            return (Criteria) this;
        }

        public Criteria andTongbiNotBetween(String value1, String value2) {
            addCriterion("tongbi not between", value1, value2, "tongbi");
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