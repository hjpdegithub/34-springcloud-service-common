package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpMakePaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpMakePaperExample() {
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

        public Criteria andExamNameIsNull() {
            addCriterion("exam_name is null");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNotNull() {
            addCriterion("exam_name is not null");
            return (Criteria) this;
        }

        public Criteria andExamNameEqualTo(String value) {
            addCriterion("exam_name =", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotEqualTo(String value) {
            addCriterion("exam_name <>", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThan(String value) {
            addCriterion("exam_name >", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThanOrEqualTo(String value) {
            addCriterion("exam_name >=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThan(String value) {
            addCriterion("exam_name <", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThanOrEqualTo(String value) {
            addCriterion("exam_name <=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLike(String value) {
            addCriterion("exam_name like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotLike(String value) {
            addCriterion("exam_name not like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameIn(List<String> values) {
            addCriterion("exam_name in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotIn(List<String> values) {
            addCriterion("exam_name not in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameBetween(String value1, String value2) {
            addCriterion("exam_name between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotBetween(String value1, String value2) {
            addCriterion("exam_name not between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNull() {
            addCriterion("exam_type is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNotNull() {
            addCriterion("exam_type is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeEqualTo(Integer value) {
            addCriterion("exam_type =", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotEqualTo(Integer value) {
            addCriterion("exam_type <>", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThan(Integer value) {
            addCriterion("exam_type >", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_type >=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThan(Integer value) {
            addCriterion("exam_type <", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThanOrEqualTo(Integer value) {
            addCriterion("exam_type <=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeIn(List<Integer> values) {
            addCriterion("exam_type in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotIn(List<Integer> values) {
            addCriterion("exam_type not in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeBetween(Integer value1, Integer value2) {
            addCriterion("exam_type between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_type not between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Integer value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Integer value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Integer value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Integer value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Integer> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Integer> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Integer value1, Integer value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
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

        public Criteria andAchievementIdIsNull() {
            addCriterion("achievement_id is null");
            return (Criteria) this;
        }

        public Criteria andAchievementIdIsNotNull() {
            addCriterion("achievement_id is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementIdEqualTo(Long value) {
            addCriterion("achievement_id =", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdNotEqualTo(Long value) {
            addCriterion("achievement_id <>", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdGreaterThan(Long value) {
            addCriterion("achievement_id >", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdGreaterThanOrEqualTo(Long value) {
            addCriterion("achievement_id >=", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdLessThan(Long value) {
            addCriterion("achievement_id <", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdLessThanOrEqualTo(Long value) {
            addCriterion("achievement_id <=", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdIn(List<Long> values) {
            addCriterion("achievement_id in", values, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdNotIn(List<Long> values) {
            addCriterion("achievement_id not in", values, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdBetween(Long value1, Long value2) {
            addCriterion("achievement_id between", value1, value2, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdNotBetween(Long value1, Long value2) {
            addCriterion("achievement_id not between", value1, value2, "achievementId");
            return (Criteria) this;
        }

        public Criteria andStatusTypeIsNull() {
            addCriterion("status_type is null");
            return (Criteria) this;
        }

        public Criteria andStatusTypeIsNotNull() {
            addCriterion("status_type is not null");
            return (Criteria) this;
        }

        public Criteria andStatusTypeEqualTo(Integer value) {
            addCriterion("status_type =", value, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeNotEqualTo(Integer value) {
            addCriterion("status_type <>", value, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeGreaterThan(Integer value) {
            addCriterion("status_type >", value, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_type >=", value, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeLessThan(Integer value) {
            addCriterion("status_type <", value, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeLessThanOrEqualTo(Integer value) {
            addCriterion("status_type <=", value, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeIn(List<Integer> values) {
            addCriterion("status_type in", values, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeNotIn(List<Integer> values) {
            addCriterion("status_type not in", values, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeBetween(Integer value1, Integer value2) {
            addCriterion("status_type between", value1, value2, "statusType");
            return (Criteria) this;
        }

        public Criteria andStatusTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("status_type not between", value1, value2, "statusType");
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