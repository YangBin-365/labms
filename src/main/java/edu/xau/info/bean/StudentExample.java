package edu.xau.info.bean;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andStuidIsNull() {
            addCriterion("stuid is null");
            return (Criteria) this;
        }

        public Criteria andStuidIsNotNull() {
            addCriterion("stuid is not null");
            return (Criteria) this;
        }

        public Criteria andStuidEqualTo(Integer value) {
            addCriterion("stuid =", value, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidNotEqualTo(Integer value) {
            addCriterion("stuid <>", value, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidGreaterThan(Integer value) {
            addCriterion("stuid >", value, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("stuid >=", value, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidLessThan(Integer value) {
            addCriterion("stuid <", value, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidLessThanOrEqualTo(Integer value) {
            addCriterion("stuid <=", value, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidIn(List<Integer> values) {
            addCriterion("stuid in", values, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidNotIn(List<Integer> values) {
            addCriterion("stuid not in", values, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidBetween(Integer value1, Integer value2) {
            addCriterion("stuid between", value1, value2, "stuid");
            return (Criteria) this;
        }

        public Criteria andStuidNotBetween(Integer value1, Integer value2) {
            addCriterion("stuid not between", value1, value2, "stuid");
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

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andStunoIsNull() {
            addCriterion("stuno is null");
            return (Criteria) this;
        }

        public Criteria andStunoIsNotNull() {
            addCriterion("stuno is not null");
            return (Criteria) this;
        }

        public Criteria andStunoEqualTo(String value) {
            addCriterion("stuno =", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoNotEqualTo(String value) {
            addCriterion("stuno <>", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoGreaterThan(String value) {
            addCriterion("stuno >", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoGreaterThanOrEqualTo(String value) {
            addCriterion("stuno >=", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoLessThan(String value) {
            addCriterion("stuno <", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoLessThanOrEqualTo(String value) {
            addCriterion("stuno <=", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoLike(String value) {
            addCriterion("stuno like", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoNotLike(String value) {
            addCriterion("stuno not like", value, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoIn(List<String> values) {
            addCriterion("stuno in", values, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoNotIn(List<String> values) {
            addCriterion("stuno not in", values, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoBetween(String value1, String value2) {
            addCriterion("stuno between", value1, value2, "stuno");
            return (Criteria) this;
        }

        public Criteria andStunoNotBetween(String value1, String value2) {
            addCriterion("stuno not between", value1, value2, "stuno");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeIsNull() {
            addCriterion("graduationtime is null");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeIsNotNull() {
            addCriterion("graduationtime is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeEqualTo(String value) {
            addCriterion("graduationtime =", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeNotEqualTo(String value) {
            addCriterion("graduationtime <>", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeGreaterThan(String value) {
            addCriterion("graduationtime >", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeGreaterThanOrEqualTo(String value) {
            addCriterion("graduationtime >=", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeLessThan(String value) {
            addCriterion("graduationtime <", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeLessThanOrEqualTo(String value) {
            addCriterion("graduationtime <=", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeLike(String value) {
            addCriterion("graduationtime like", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeNotLike(String value) {
            addCriterion("graduationtime not like", value, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeIn(List<String> values) {
            addCriterion("graduationtime in", values, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeNotIn(List<String> values) {
            addCriterion("graduationtime not in", values, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeBetween(String value1, String value2) {
            addCriterion("graduationtime between", value1, value2, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andGraduationtimeNotBetween(String value1, String value2) {
            addCriterion("graduationtime not between", value1, value2, "graduationtime");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andTeaidIsNull() {
            addCriterion("teaid is null");
            return (Criteria) this;
        }

        public Criteria andTeaidIsNotNull() {
            addCriterion("teaid is not null");
            return (Criteria) this;
        }

        public Criteria andTeaidEqualTo(Integer value) {
            addCriterion("teaid =", value, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidNotEqualTo(Integer value) {
            addCriterion("teaid <>", value, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidGreaterThan(Integer value) {
            addCriterion("teaid >", value, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidGreaterThanOrEqualTo(Integer value) {
            addCriterion("teaid >=", value, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidLessThan(Integer value) {
            addCriterion("teaid <", value, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidLessThanOrEqualTo(Integer value) {
            addCriterion("teaid <=", value, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidIn(List<Integer> values) {
            addCriterion("teaid in", values, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidNotIn(List<Integer> values) {
            addCriterion("teaid not in", values, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidBetween(Integer value1, Integer value2) {
            addCriterion("teaid between", value1, value2, "teaid");
            return (Criteria) this;
        }

        public Criteria andTeaidNotBetween(Integer value1, Integer value2) {
            addCriterion("teaid not between", value1, value2, "teaid");
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