package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpSecondClassifyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpSecondClassifyExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameIsNull() {
            addCriterion("second_classify_name is null");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameIsNotNull() {
            addCriterion("second_classify_name is not null");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameEqualTo(String value) {
            addCriterion("second_classify_name =", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameNotEqualTo(String value) {
            addCriterion("second_classify_name <>", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameGreaterThan(String value) {
            addCriterion("second_classify_name >", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameGreaterThanOrEqualTo(String value) {
            addCriterion("second_classify_name >=", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameLessThan(String value) {
            addCriterion("second_classify_name <", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameLessThanOrEqualTo(String value) {
            addCriterion("second_classify_name <=", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameLike(String value) {
            addCriterion("second_classify_name like", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameNotLike(String value) {
            addCriterion("second_classify_name not like", value, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameIn(List<String> values) {
            addCriterion("second_classify_name in", values, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameNotIn(List<String> values) {
            addCriterion("second_classify_name not in", values, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameBetween(String value1, String value2) {
            addCriterion("second_classify_name between", value1, value2, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyNameNotBetween(String value1, String value2) {
            addCriterion("second_classify_name not between", value1, value2, "secondClassifyName");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrIsNull() {
            addCriterion("second_classify_descr is null");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrIsNotNull() {
            addCriterion("second_classify_descr is not null");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrEqualTo(String value) {
            addCriterion("second_classify_descr =", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrNotEqualTo(String value) {
            addCriterion("second_classify_descr <>", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrGreaterThan(String value) {
            addCriterion("second_classify_descr >", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrGreaterThanOrEqualTo(String value) {
            addCriterion("second_classify_descr >=", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrLessThan(String value) {
            addCriterion("second_classify_descr <", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrLessThanOrEqualTo(String value) {
            addCriterion("second_classify_descr <=", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrLike(String value) {
            addCriterion("second_classify_descr like", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrNotLike(String value) {
            addCriterion("second_classify_descr not like", value, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrIn(List<String> values) {
            addCriterion("second_classify_descr in", values, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrNotIn(List<String> values) {
            addCriterion("second_classify_descr not in", values, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrBetween(String value1, String value2) {
            addCriterion("second_classify_descr between", value1, value2, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyDescrNotBetween(String value1, String value2) {
            addCriterion("second_classify_descr not between", value1, value2, "secondClassifyDescr");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeIsNull() {
            addCriterion("second_classify_type is null");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeIsNotNull() {
            addCriterion("second_classify_type is not null");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeEqualTo(Integer value) {
            addCriterion("second_classify_type =", value, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeNotEqualTo(Integer value) {
            addCriterion("second_classify_type <>", value, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeGreaterThan(Integer value) {
            addCriterion("second_classify_type >", value, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_classify_type >=", value, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeLessThan(Integer value) {
            addCriterion("second_classify_type <", value, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("second_classify_type <=", value, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeIn(List<Integer> values) {
            addCriterion("second_classify_type in", values, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeNotIn(List<Integer> values) {
            addCriterion("second_classify_type not in", values, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeBetween(Integer value1, Integer value2) {
            addCriterion("second_classify_type between", value1, value2, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andSecondClassifyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("second_classify_type not between", value1, value2, "secondClassifyType");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Long value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Long value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Long value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Long value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Long value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Long> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Long> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Long value1, Long value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Long value1, Long value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(Long value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(Long value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(Long value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(Long value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(Long value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<Long> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<Long> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(Long value1, Long value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(Long value1, Long value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleFlagIsNull() {
            addCriterion("dele_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleFlagIsNotNull() {
            addCriterion("dele_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleFlagEqualTo(Integer value) {
            addCriterion("dele_flag =", value, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagNotEqualTo(Integer value) {
            addCriterion("dele_flag <>", value, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagGreaterThan(Integer value) {
            addCriterion("dele_flag >", value, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("dele_flag >=", value, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagLessThan(Integer value) {
            addCriterion("dele_flag <", value, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagLessThanOrEqualTo(Integer value) {
            addCriterion("dele_flag <=", value, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagIn(List<Integer> values) {
            addCriterion("dele_flag in", values, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagNotIn(List<Integer> values) {
            addCriterion("dele_flag not in", values, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagBetween(Integer value1, Integer value2) {
            addCriterion("dele_flag between", value1, value2, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andDeleFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("dele_flag not between", value1, value2, "deleFlag");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdIsNull() {
            addCriterion("first_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdIsNotNull() {
            addCriterion("first_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdEqualTo(Long value) {
            addCriterion("first_classify_id =", value, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdNotEqualTo(Long value) {
            addCriterion("first_classify_id <>", value, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdGreaterThan(Long value) {
            addCriterion("first_classify_id >", value, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("first_classify_id >=", value, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdLessThan(Long value) {
            addCriterion("first_classify_id <", value, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("first_classify_id <=", value, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdIn(List<Long> values) {
            addCriterion("first_classify_id in", values, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdNotIn(List<Long> values) {
            addCriterion("first_classify_id not in", values, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdBetween(Long value1, Long value2) {
            addCriterion("first_classify_id between", value1, value2, "firstClassifyId");
            return (Criteria) this;
        }

        public Criteria andFirstClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("first_classify_id not between", value1, value2, "firstClassifyId");
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