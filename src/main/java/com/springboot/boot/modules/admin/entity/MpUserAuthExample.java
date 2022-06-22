package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpUserAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpUserAuthExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNull() {
            addCriterion("auth_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNotNull() {
            addCriterion("auth_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthIdEqualTo(Long value) {
            addCriterion("auth_id =", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotEqualTo(Long value) {
            addCriterion("auth_id <>", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThan(Long value) {
            addCriterion("auth_id >", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThanOrEqualTo(Long value) {
            addCriterion("auth_id >=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThan(Long value) {
            addCriterion("auth_id <", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThanOrEqualTo(Long value) {
            addCriterion("auth_id <=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIn(List<Long> values) {
            addCriterion("auth_id in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotIn(List<Long> values) {
            addCriterion("auth_id not in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdBetween(Long value1, Long value2) {
            addCriterion("auth_id between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotBetween(Long value1, Long value2) {
            addCriterion("auth_id not between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andCrateUserIsNull() {
            addCriterion("crate_user is null");
            return (Criteria) this;
        }

        public Criteria andCrateUserIsNotNull() {
            addCriterion("crate_user is not null");
            return (Criteria) this;
        }

        public Criteria andCrateUserEqualTo(Long value) {
            addCriterion("crate_user =", value, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserNotEqualTo(Long value) {
            addCriterion("crate_user <>", value, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserGreaterThan(Long value) {
            addCriterion("crate_user >", value, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserGreaterThanOrEqualTo(Long value) {
            addCriterion("crate_user >=", value, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserLessThan(Long value) {
            addCriterion("crate_user <", value, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserLessThanOrEqualTo(Long value) {
            addCriterion("crate_user <=", value, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserIn(List<Long> values) {
            addCriterion("crate_user in", values, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserNotIn(List<Long> values) {
            addCriterion("crate_user not in", values, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserBetween(Long value1, Long value2) {
            addCriterion("crate_user between", value1, value2, "crateUser");
            return (Criteria) this;
        }

        public Criteria andCrateUserNotBetween(Long value1, Long value2) {
            addCriterion("crate_user not between", value1, value2, "crateUser");
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

        public Criteria andUpdateTiimeIsNull() {
            addCriterion("update_tiime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeIsNotNull() {
            addCriterion("update_tiime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeEqualTo(Date value) {
            addCriterion("update_tiime =", value, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeNotEqualTo(Date value) {
            addCriterion("update_tiime <>", value, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeGreaterThan(Date value) {
            addCriterion("update_tiime >", value, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_tiime >=", value, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeLessThan(Date value) {
            addCriterion("update_tiime <", value, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeLessThanOrEqualTo(Date value) {
            addCriterion("update_tiime <=", value, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeIn(List<Date> values) {
            addCriterion("update_tiime in", values, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeNotIn(List<Date> values) {
            addCriterion("update_tiime not in", values, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeBetween(Date value1, Date value2) {
            addCriterion("update_tiime between", value1, value2, "updateTiime");
            return (Criteria) this;
        }

        public Criteria andUpdateTiimeNotBetween(Date value1, Date value2) {
            addCriterion("update_tiime not between", value1, value2, "updateTiime");
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