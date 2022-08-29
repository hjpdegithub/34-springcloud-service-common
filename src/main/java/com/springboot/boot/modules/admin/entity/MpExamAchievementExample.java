package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpExamAchievementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpExamAchievementExample() {
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

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Long value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Long value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Long value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Long value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Long value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Long> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Long> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Long value1, Long value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Long value1, Long value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNull() {
            addCriterion("exam_time is null");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNotNull() {
            addCriterion("exam_time is not null");
            return (Criteria) this;
        }

        public Criteria andExamTimeEqualTo(Integer value) {
            addCriterion("exam_time =", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotEqualTo(Integer value) {
            addCriterion("exam_time <>", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThan(Integer value) {
            addCriterion("exam_time >", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_time >=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThan(Integer value) {
            addCriterion("exam_time <", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThanOrEqualTo(Integer value) {
            addCriterion("exam_time <=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeIn(List<Integer> values) {
            addCriterion("exam_time in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotIn(List<Integer> values) {
            addCriterion("exam_time not in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeBetween(Integer value1, Integer value2) {
            addCriterion("exam_time between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_time not between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamAchievementIsNull() {
            addCriterion("exam_achievement is null");
            return (Criteria) this;
        }

        public Criteria andExamAchievementIsNotNull() {
            addCriterion("exam_achievement is not null");
            return (Criteria) this;
        }

        public Criteria andExamAchievementEqualTo(Integer value) {
            addCriterion("exam_achievement =", value, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementNotEqualTo(Integer value) {
            addCriterion("exam_achievement <>", value, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementGreaterThan(Integer value) {
            addCriterion("exam_achievement >", value, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_achievement >=", value, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementLessThan(Integer value) {
            addCriterion("exam_achievement <", value, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementLessThanOrEqualTo(Integer value) {
            addCriterion("exam_achievement <=", value, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementIn(List<Integer> values) {
            addCriterion("exam_achievement in", values, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementNotIn(List<Integer> values) {
            addCriterion("exam_achievement not in", values, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementBetween(Integer value1, Integer value2) {
            addCriterion("exam_achievement between", value1, value2, "examAchievement");
            return (Criteria) this;
        }

        public Criteria andExamAchievementNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_achievement not between", value1, value2, "examAchievement");
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

        public Criteria andIfWhetherIsNull() {
            addCriterion("if_whether is null");
            return (Criteria) this;
        }

        public Criteria andIfWhetherIsNotNull() {
            addCriterion("if_whether is not null");
            return (Criteria) this;
        }

        public Criteria andIfWhetherEqualTo(Integer value) {
            addCriterion("if_whether =", value, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherNotEqualTo(Integer value) {
            addCriterion("if_whether <>", value, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherGreaterThan(Integer value) {
            addCriterion("if_whether >", value, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_whether >=", value, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherLessThan(Integer value) {
            addCriterion("if_whether <", value, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherLessThanOrEqualTo(Integer value) {
            addCriterion("if_whether <=", value, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherIn(List<Integer> values) {
            addCriterion("if_whether in", values, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherNotIn(List<Integer> values) {
            addCriterion("if_whether not in", values, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherBetween(Integer value1, Integer value2) {
            addCriterion("if_whether between", value1, value2, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andIfWhetherNotBetween(Integer value1, Integer value2) {
            addCriterion("if_whether not between", value1, value2, "ifWhether");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeIsNull() {
            addCriterion("judge_grade is null");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeIsNotNull() {
            addCriterion("judge_grade is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeEqualTo(Integer value) {
            addCriterion("judge_grade =", value, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeNotEqualTo(Integer value) {
            addCriterion("judge_grade <>", value, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeGreaterThan(Integer value) {
            addCriterion("judge_grade >", value, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("judge_grade >=", value, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeLessThan(Integer value) {
            addCriterion("judge_grade <", value, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeLessThanOrEqualTo(Integer value) {
            addCriterion("judge_grade <=", value, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeIn(List<Integer> values) {
            addCriterion("judge_grade in", values, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeNotIn(List<Integer> values) {
            addCriterion("judge_grade not in", values, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeBetween(Integer value1, Integer value2) {
            addCriterion("judge_grade between", value1, value2, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andJudgeGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("judge_grade not between", value1, value2, "judgeGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeIsNull() {
            addCriterion("single_grade is null");
            return (Criteria) this;
        }

        public Criteria andSingleGradeIsNotNull() {
            addCriterion("single_grade is not null");
            return (Criteria) this;
        }

        public Criteria andSingleGradeEqualTo(Integer value) {
            addCriterion("single_grade =", value, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeNotEqualTo(Integer value) {
            addCriterion("single_grade <>", value, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeGreaterThan(Integer value) {
            addCriterion("single_grade >", value, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_grade >=", value, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeLessThan(Integer value) {
            addCriterion("single_grade <", value, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeLessThanOrEqualTo(Integer value) {
            addCriterion("single_grade <=", value, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeIn(List<Integer> values) {
            addCriterion("single_grade in", values, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeNotIn(List<Integer> values) {
            addCriterion("single_grade not in", values, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeBetween(Integer value1, Integer value2) {
            addCriterion("single_grade between", value1, value2, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andSingleGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("single_grade not between", value1, value2, "singleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeIsNull() {
            addCriterion("multiple_grade is null");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeIsNotNull() {
            addCriterion("multiple_grade is not null");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeEqualTo(Integer value) {
            addCriterion("multiple_grade =", value, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeNotEqualTo(Integer value) {
            addCriterion("multiple_grade <>", value, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeGreaterThan(Integer value) {
            addCriterion("multiple_grade >", value, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("multiple_grade >=", value, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeLessThan(Integer value) {
            addCriterion("multiple_grade <", value, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeLessThanOrEqualTo(Integer value) {
            addCriterion("multiple_grade <=", value, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeIn(List<Integer> values) {
            addCriterion("multiple_grade in", values, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeNotIn(List<Integer> values) {
            addCriterion("multiple_grade not in", values, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeBetween(Integer value1, Integer value2) {
            addCriterion("multiple_grade between", value1, value2, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andMultipleGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("multiple_grade not between", value1, value2, "multipleGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeIsNull() {
            addCriterion("analysis_grade is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeIsNotNull() {
            addCriterion("analysis_grade is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeEqualTo(Integer value) {
            addCriterion("analysis_grade =", value, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeNotEqualTo(Integer value) {
            addCriterion("analysis_grade <>", value, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeGreaterThan(Integer value) {
            addCriterion("analysis_grade >", value, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("analysis_grade >=", value, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeLessThan(Integer value) {
            addCriterion("analysis_grade <", value, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeLessThanOrEqualTo(Integer value) {
            addCriterion("analysis_grade <=", value, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeIn(List<Integer> values) {
            addCriterion("analysis_grade in", values, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeNotIn(List<Integer> values) {
            addCriterion("analysis_grade not in", values, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeBetween(Integer value1, Integer value2) {
            addCriterion("analysis_grade between", value1, value2, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andAnalysisGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("analysis_grade not between", value1, value2, "analysisGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeIsNull() {
            addCriterion("count_grade is null");
            return (Criteria) this;
        }

        public Criteria andCountGradeIsNotNull() {
            addCriterion("count_grade is not null");
            return (Criteria) this;
        }

        public Criteria andCountGradeEqualTo(Integer value) {
            addCriterion("count_grade =", value, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeNotEqualTo(Integer value) {
            addCriterion("count_grade <>", value, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeGreaterThan(Integer value) {
            addCriterion("count_grade >", value, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_grade >=", value, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeLessThan(Integer value) {
            addCriterion("count_grade <", value, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeLessThanOrEqualTo(Integer value) {
            addCriterion("count_grade <=", value, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeIn(List<Integer> values) {
            addCriterion("count_grade in", values, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeNotIn(List<Integer> values) {
            addCriterion("count_grade not in", values, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeBetween(Integer value1, Integer value2) {
            addCriterion("count_grade between", value1, value2, "countGrade");
            return (Criteria) this;
        }

        public Criteria andCountGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("count_grade not between", value1, value2, "countGrade");
            return (Criteria) this;
        }

        public Criteria andShowTypeIsNull() {
            addCriterion("show_type is null");
            return (Criteria) this;
        }

        public Criteria andShowTypeIsNotNull() {
            addCriterion("show_type is not null");
            return (Criteria) this;
        }

        public Criteria andShowTypeEqualTo(Integer value) {
            addCriterion("show_type =", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotEqualTo(Integer value) {
            addCriterion("show_type <>", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeGreaterThan(Integer value) {
            addCriterion("show_type >", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_type >=", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeLessThan(Integer value) {
            addCriterion("show_type <", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeLessThanOrEqualTo(Integer value) {
            addCriterion("show_type <=", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeIn(List<Integer> values) {
            addCriterion("show_type in", values, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotIn(List<Integer> values) {
            addCriterion("show_type not in", values, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeBetween(Integer value1, Integer value2) {
            addCriterion("show_type between", value1, value2, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("show_type not between", value1, value2, "showType");
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