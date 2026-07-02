package com.alibaba.tqn.infra.dal.h2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookParam {

    protected String orderByClause;
    protected boolean page;
    protected int pageIndex;
    protected int pageSize;
    protected int pageStart;
    protected List<Criteria> oredCriteria;

    public BookParam() {
        oredCriteria = new ArrayList<>();
    }

    public BookParam appendOrderByClause(OrderCondition orderCondition, SortType sortType) {
        if (null != orderByClause) {
            orderByClause = orderByClause + ", " + orderCondition.getColumnName() + " " + sortType.getValue();
        } else {
            orderByClause = orderCondition.getColumnName() + " " + sortType.getValue();
        }
        return this;
    }

    public String getOrderByClause() { return orderByClause; }

    public BookParam setPage(boolean page) { this.page = page; return this; }
    public boolean isPage() { return page; }
    public int getPageIndex() { return pageIndex; }
    public int getPageSize() { return pageSize; }
    public int getPageStart() { return pageStart; }

    public BookParam setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? 10 : pageSize;
        this.pageIndex = pageStart < 1 ? 0 : (pageStart - 1) * this.pageSize;
        return this;
    }

    public BookParam setPageStart(int pageStart) {
        this.pageStart = pageStart < 1 ? 1 : pageStart;
        this.pageIndex = (this.pageStart - 1) * this.pageSize;
        return this;
    }

    public List<Criteria> getOredCriteria() { return oredCriteria; }

    public Criteria createCriteria() {
        Criteria criteria = new Criteria();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    public Criteria or() {
        Criteria criteria = new Criteria();
        oredCriteria.add(criteria);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
    }

    protected abstract static class AbstractGeneratedCriteria {
        protected List<Criterion> criteria;

        protected AbstractGeneratedCriteria() {
            criteria = new ArrayList<>();
        }

        public boolean isValid() { return !criteria.isEmpty(); }
        public List<Criterion> getCriteria() { return criteria; }

        protected void addCriterion(String condition) {
            if (condition == null) throw new RuntimeException("Value for condition cannot be null");
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) throw new RuntimeException("Value for " + property + " cannot be null");
            criteria.add(new Criterion(condition, value));
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }
    }

    public static class Criteria extends AbstractGeneratedCriteria {
        protected Criteria() { super(); }
    }

    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;

        public String getCondition() { return condition; }
        public Object getValue() { return value; }
        public Object getSecondValue() { return secondValue; }
        public boolean isNoValue() { return noValue; }
        public boolean isSingleValue() { return singleValue; }
        public boolean isBetweenValue() { return betweenValue; }
        public boolean isListValue() { return listValue; }

        protected Criterion(String condition) {
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            this.condition = condition;
            this.value = value;
            this.listValue = (value instanceof List<?>);
            this.singleValue = !this.listValue;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }

    public enum OrderCondition {
        ID("id"),
        CREATOR("creator"),
        GMTCREATE("gmt_create"),
        MODIFIER("modifier"),
        GMTMODIFIED("gmt_modified"),
        DELETED("is_deleted"),
        NAME("name");

        private final String columnName;
        OrderCondition(String columnName) { this.columnName = columnName; }
        public String getColumnName() { return columnName; }
    }

    public enum SortType {
        ASC("asc"), DESC("desc");
        private final String value;
        SortType(String value) { this.value = value; }
        public String getValue() { return value; }
    }
}
