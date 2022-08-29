package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpExaminationRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpExaminationRuleExample() {
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

        public Criteria andSingleChoiceNumIsNull() {
            addCriterion("single_choice_num is null");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumIsNotNull() {
            addCriterion("single_choice_num is not null");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumEqualTo(Integer value) {
            addCriterion("single_choice_num =", value, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumNotEqualTo(Integer value) {
            addCriterion("single_choice_num <>", value, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumGreaterThan(Integer value) {
            addCriterion("single_choice_num >", value, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_choice_num >=", value, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumLessThan(Integer value) {
            addCriterion("single_choice_num <", value, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumLessThanOrEqualTo(Integer value) {
            addCriterion("single_choice_num <=", value, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumIn(List<Integer> values) {
            addCriterion("single_choice_num in", values, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumNotIn(List<Integer> values) {
            addCriterion("single_choice_num not in", values, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumBetween(Integer value1, Integer value2) {
            addCriterion("single_choice_num between", value1, value2, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andSingleChoiceNumNotBetween(Integer value1, Integer value2) {
            addCriterion("single_choice_num not between", value1, value2, "singleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumIsNull() {
            addCriterion("multiple_choice_num is null");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumIsNotNull() {
            addCriterion("multiple_choice_num is not null");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumEqualTo(Integer value) {
            addCriterion("multiple_choice_num =", value, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumNotEqualTo(Integer value) {
            addCriterion("multiple_choice_num <>", value, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumGreaterThan(Integer value) {
            addCriterion("multiple_choice_num >", value, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("multiple_choice_num >=", value, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumLessThan(Integer value) {
            addCriterion("multiple_choice_num <", value, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumLessThanOrEqualTo(Integer value) {
            addCriterion("multiple_choice_num <=", value, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumIn(List<Integer> values) {
            addCriterion("multiple_choice_num in", values, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumNotIn(List<Integer> values) {
            addCriterion("multiple_choice_num not in", values, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumBetween(Integer value1, Integer value2) {
            addCriterion("multiple_choice_num between", value1, value2, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andMultipleChoiceNumNotBetween(Integer value1, Integer value2) {
            addCriterion("multiple_choice_num not between", value1, value2, "multipleChoiceNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumIsNull() {
            addCriterion("judge_num is null");
            return (Criteria) this;
        }

        public Criteria andJudgeNumIsNotNull() {
            addCriterion("judge_num is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeNumEqualTo(Integer value) {
            addCriterion("judge_num =", value, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumNotEqualTo(Integer value) {
            addCriterion("judge_num <>", value, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumGreaterThan(Integer value) {
            addCriterion("judge_num >", value, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("judge_num >=", value, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumLessThan(Integer value) {
            addCriterion("judge_num <", value, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumLessThanOrEqualTo(Integer value) {
            addCriterion("judge_num <=", value, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumIn(List<Integer> values) {
            addCriterion("judge_num in", values, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumNotIn(List<Integer> values) {
            addCriterion("judge_num not in", values, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumBetween(Integer value1, Integer value2) {
            addCriterion("judge_num between", value1, value2, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andJudgeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("judge_num not between", value1, value2, "judgeNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("subject_name is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("subject_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("subject_name =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("subject_name <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("subject_name >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("subject_name >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("subject_name <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("subject_name <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("subject_name like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("subject_name not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("subject_name in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("subject_name not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("subject_name between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("subject_name not between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNumIsNull() {
            addCriterion("subject_num is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNumIsNotNull() {
            addCriterion("subject_num is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNumEqualTo(Integer value) {
            addCriterion("subject_num =", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotEqualTo(Integer value) {
            addCriterion("subject_num <>", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumGreaterThan(Integer value) {
            addCriterion("subject_num >", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("subject_num >=", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumLessThan(Integer value) {
            addCriterion("subject_num <", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumLessThanOrEqualTo(Integer value) {
            addCriterion("subject_num <=", value, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumIn(List<Integer> values) {
            addCriterion("subject_num in", values, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotIn(List<Integer> values) {
            addCriterion("subject_num not in", values, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumBetween(Integer value1, Integer value2) {
            addCriterion("subject_num between", value1, value2, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andSubjectNumNotBetween(Integer value1, Integer value2) {
            addCriterion("subject_num not between", value1, value2, "subjectNum");
            return (Criteria) this;
        }

        public Criteria andFractionIsNull() {
            addCriterion("fraction is null");
            return (Criteria) this;
        }

        public Criteria andFractionIsNotNull() {
            addCriterion("fraction is not null");
            return (Criteria) this;
        }

        public Criteria andFractionEqualTo(Integer value) {
            addCriterion("fraction =", value, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionNotEqualTo(Integer value) {
            addCriterion("fraction <>", value, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionGreaterThan(Integer value) {
            addCriterion("fraction >", value, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionGreaterThanOrEqualTo(Integer value) {
            addCriterion("fraction >=", value, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionLessThan(Integer value) {
            addCriterion("fraction <", value, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionLessThanOrEqualTo(Integer value) {
            addCriterion("fraction <=", value, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionIn(List<Integer> values) {
            addCriterion("fraction in", values, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionNotIn(List<Integer> values) {
            addCriterion("fraction not in", values, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionBetween(Integer value1, Integer value2) {
            addCriterion("fraction between", value1, value2, "fraction");
            return (Criteria) this;
        }

        public Criteria andFractionNotBetween(Integer value1, Integer value2) {
            addCriterion("fraction not between", value1, value2, "fraction");
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

        public Criteria andAnalysisNumIsNull() {
            addCriterion("analysis_num is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumIsNotNull() {
            addCriterion("analysis_num is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumEqualTo(Integer value) {
            addCriterion("analysis_num =", value, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumNotEqualTo(Integer value) {
            addCriterion("analysis_num <>", value, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumGreaterThan(Integer value) {
            addCriterion("analysis_num >", value, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("analysis_num >=", value, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumLessThan(Integer value) {
            addCriterion("analysis_num <", value, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumLessThanOrEqualTo(Integer value) {
            addCriterion("analysis_num <=", value, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumIn(List<Integer> values) {
            addCriterion("analysis_num in", values, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumNotIn(List<Integer> values) {
            addCriterion("analysis_num not in", values, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumBetween(Integer value1, Integer value2) {
            addCriterion("analysis_num between", value1, value2, "analysisNum");
            return (Criteria) this;
        }

        public Criteria andAnalysisNumNotBetween(Integer value1, Integer value2) {
            addCriterion("analysis_num not between", value1, value2, "analysisNum");
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