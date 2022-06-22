package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpCurriculumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpCurriculumExample() {
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

        public Criteria andCurriculumNameIsNull() {
            addCriterion("curriculum_name is null");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameIsNotNull() {
            addCriterion("curriculum_name is not null");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameEqualTo(String value) {
            addCriterion("curriculum_name =", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameNotEqualTo(String value) {
            addCriterion("curriculum_name <>", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameGreaterThan(String value) {
            addCriterion("curriculum_name >", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameGreaterThanOrEqualTo(String value) {
            addCriterion("curriculum_name >=", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameLessThan(String value) {
            addCriterion("curriculum_name <", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameLessThanOrEqualTo(String value) {
            addCriterion("curriculum_name <=", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameLike(String value) {
            addCriterion("curriculum_name like", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameNotLike(String value) {
            addCriterion("curriculum_name not like", value, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameIn(List<String> values) {
            addCriterion("curriculum_name in", values, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameNotIn(List<String> values) {
            addCriterion("curriculum_name not in", values, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameBetween(String value1, String value2) {
            addCriterion("curriculum_name between", value1, value2, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andCurriculumNameNotBetween(String value1, String value2) {
            addCriterion("curriculum_name not between", value1, value2, "curriculumName");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdIsNull() {
            addCriterion("gen_first_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdIsNotNull() {
            addCriterion("gen_first_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdEqualTo(Long value) {
            addCriterion("gen_first_classify_id =", value, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdNotEqualTo(Long value) {
            addCriterion("gen_first_classify_id <>", value, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdGreaterThan(Long value) {
            addCriterion("gen_first_classify_id >", value, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("gen_first_classify_id >=", value, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdLessThan(Long value) {
            addCriterion("gen_first_classify_id <", value, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("gen_first_classify_id <=", value, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdIn(List<Long> values) {
            addCriterion("gen_first_classify_id in", values, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdNotIn(List<Long> values) {
            addCriterion("gen_first_classify_id not in", values, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdBetween(Long value1, Long value2) {
            addCriterion("gen_first_classify_id between", value1, value2, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenFirstClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("gen_first_classify_id not between", value1, value2, "genFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdIsNull() {
            addCriterion("gen_second_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdIsNotNull() {
            addCriterion("gen_second_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdEqualTo(Long value) {
            addCriterion("gen_second_classify_id =", value, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdNotEqualTo(Long value) {
            addCriterion("gen_second_classify_id <>", value, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdGreaterThan(Long value) {
            addCriterion("gen_second_classify_id >", value, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("gen_second_classify_id >=", value, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdLessThan(Long value) {
            addCriterion("gen_second_classify_id <", value, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("gen_second_classify_id <=", value, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdIn(List<Long> values) {
            addCriterion("gen_second_classify_id in", values, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdNotIn(List<Long> values) {
            addCriterion("gen_second_classify_id not in", values, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdBetween(Long value1, Long value2) {
            addCriterion("gen_second_classify_id between", value1, value2, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andGenSecondClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("gen_second_classify_id not between", value1, value2, "genSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIsNull() {
            addCriterion("study_time is null");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIsNotNull() {
            addCriterion("study_time is not null");
            return (Criteria) this;
        }

        public Criteria andStudyTimeEqualTo(Date value) {
            addCriterion("study_time =", value, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNotEqualTo(Date value) {
            addCriterion("study_time <>", value, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeGreaterThan(Date value) {
            addCriterion("study_time >", value, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("study_time >=", value, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeLessThan(Date value) {
            addCriterion("study_time <", value, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeLessThanOrEqualTo(Date value) {
            addCriterion("study_time <=", value, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIn(List<Date> values) {
            addCriterion("study_time in", values, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNotIn(List<Date> values) {
            addCriterion("study_time not in", values, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeBetween(Date value1, Date value2) {
            addCriterion("study_time between", value1, value2, "studyTime");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNotBetween(Date value1, Date value2) {
            addCriterion("study_time not between", value1, value2, "studyTime");
            return (Criteria) this;
        }

        public Criteria andClassHourIsNull() {
            addCriterion("class_hour is null");
            return (Criteria) this;
        }

        public Criteria andClassHourIsNotNull() {
            addCriterion("class_hour is not null");
            return (Criteria) this;
        }

        public Criteria andClassHourEqualTo(Integer value) {
            addCriterion("class_hour =", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourNotEqualTo(Integer value) {
            addCriterion("class_hour <>", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourGreaterThan(Integer value) {
            addCriterion("class_hour >", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_hour >=", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourLessThan(Integer value) {
            addCriterion("class_hour <", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourLessThanOrEqualTo(Integer value) {
            addCriterion("class_hour <=", value, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourIn(List<Integer> values) {
            addCriterion("class_hour in", values, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourNotIn(List<Integer> values) {
            addCriterion("class_hour not in", values, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourBetween(Integer value1, Integer value2) {
            addCriterion("class_hour between", value1, value2, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassHourNotBetween(Integer value1, Integer value2) {
            addCriterion("class_hour not between", value1, value2, "classHour");
            return (Criteria) this;
        }

        public Criteria andClassFormatIsNull() {
            addCriterion("class_format is null");
            return (Criteria) this;
        }

        public Criteria andClassFormatIsNotNull() {
            addCriterion("class_format is not null");
            return (Criteria) this;
        }

        public Criteria andClassFormatEqualTo(Integer value) {
            addCriterion("class_format =", value, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatNotEqualTo(Integer value) {
            addCriterion("class_format <>", value, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatGreaterThan(Integer value) {
            addCriterion("class_format >", value, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_format >=", value, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatLessThan(Integer value) {
            addCriterion("class_format <", value, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatLessThanOrEqualTo(Integer value) {
            addCriterion("class_format <=", value, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatIn(List<Integer> values) {
            addCriterion("class_format in", values, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatNotIn(List<Integer> values) {
            addCriterion("class_format not in", values, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatBetween(Integer value1, Integer value2) {
            addCriterion("class_format between", value1, value2, "classFormat");
            return (Criteria) this;
        }

        public Criteria andClassFormatNotBetween(Integer value1, Integer value2) {
            addCriterion("class_format not between", value1, value2, "classFormat");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("author_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("author_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("author_name =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("author_name <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("author_name >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("author_name >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("author_name <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("author_name <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("author_name like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("author_name not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("author_name in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("author_name not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("author_name between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("author_name not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNull() {
            addCriterion("apply_type is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNotNull() {
            addCriterion("apply_type is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeEqualTo(Integer value) {
            addCriterion("apply_type =", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotEqualTo(Integer value) {
            addCriterion("apply_type <>", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThan(Integer value) {
            addCriterion("apply_type >", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_type >=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThan(Integer value) {
            addCriterion("apply_type <", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("apply_type <=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIn(List<Integer> values) {
            addCriterion("apply_type in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotIn(List<Integer> values) {
            addCriterion("apply_type not in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeBetween(Integer value1, Integer value2) {
            addCriterion("apply_type between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_type not between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeIsNull() {
            addCriterion("customized_type is null");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeIsNotNull() {
            addCriterion("customized_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeEqualTo(Integer value) {
            addCriterion("customized_type =", value, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeNotEqualTo(Integer value) {
            addCriterion("customized_type <>", value, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeGreaterThan(Integer value) {
            addCriterion("customized_type >", value, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("customized_type >=", value, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeLessThan(Integer value) {
            addCriterion("customized_type <", value, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeLessThanOrEqualTo(Integer value) {
            addCriterion("customized_type <=", value, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeIn(List<Integer> values) {
            addCriterion("customized_type in", values, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeNotIn(List<Integer> values) {
            addCriterion("customized_type not in", values, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeBetween(Integer value1, Integer value2) {
            addCriterion("customized_type between", value1, value2, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustomizedTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("customized_type not between", value1, value2, "customizedType");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdIsNull() {
            addCriterion("cust_first_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdIsNotNull() {
            addCriterion("cust_first_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdEqualTo(Long value) {
            addCriterion("cust_first_classify_id =", value, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdNotEqualTo(Long value) {
            addCriterion("cust_first_classify_id <>", value, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdGreaterThan(Long value) {
            addCriterion("cust_first_classify_id >", value, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cust_first_classify_id >=", value, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdLessThan(Long value) {
            addCriterion("cust_first_classify_id <", value, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("cust_first_classify_id <=", value, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdIn(List<Long> values) {
            addCriterion("cust_first_classify_id in", values, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdNotIn(List<Long> values) {
            addCriterion("cust_first_classify_id not in", values, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdBetween(Long value1, Long value2) {
            addCriterion("cust_first_classify_id between", value1, value2, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustFirstClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("cust_first_classify_id not between", value1, value2, "custFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdIsNull() {
            addCriterion("cust_second_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdIsNotNull() {
            addCriterion("cust_second_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdEqualTo(Long value) {
            addCriterion("cust_second_classify_id =", value, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdNotEqualTo(Long value) {
            addCriterion("cust_second_classify_id <>", value, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdGreaterThan(Long value) {
            addCriterion("cust_second_classify_id >", value, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cust_second_classify_id >=", value, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdLessThan(Long value) {
            addCriterion("cust_second_classify_id <", value, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("cust_second_classify_id <=", value, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdIn(List<Long> values) {
            addCriterion("cust_second_classify_id in", values, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdNotIn(List<Long> values) {
            addCriterion("cust_second_classify_id not in", values, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdBetween(Long value1, Long value2) {
            addCriterion("cust_second_classify_id between", value1, value2, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andCustSecondClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("cust_second_classify_id not between", value1, value2, "custSecondClassifyId");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeIsNull() {
            addCriterion("open_class_type is null");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeIsNotNull() {
            addCriterion("open_class_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeEqualTo(Integer value) {
            addCriterion("open_class_type =", value, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeNotEqualTo(Integer value) {
            addCriterion("open_class_type <>", value, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeGreaterThan(Integer value) {
            addCriterion("open_class_type >", value, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_class_type >=", value, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeLessThan(Integer value) {
            addCriterion("open_class_type <", value, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeLessThanOrEqualTo(Integer value) {
            addCriterion("open_class_type <=", value, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeIn(List<Integer> values) {
            addCriterion("open_class_type in", values, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeNotIn(List<Integer> values) {
            addCriterion("open_class_type not in", values, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeBetween(Integer value1, Integer value2) {
            addCriterion("open_class_type between", value1, value2, "openClassType");
            return (Criteria) this;
        }

        public Criteria andOpenClassTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("open_class_type not between", value1, value2, "openClassType");
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

        public Criteria andPropertyTypeIsNull() {
            addCriterion("property_type is null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNotNull() {
            addCriterion("property_type is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeEqualTo(Integer value) {
            addCriterion("property_type =", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotEqualTo(Integer value) {
            addCriterion("property_type <>", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThan(Integer value) {
            addCriterion("property_type >", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_type >=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThan(Integer value) {
            addCriterion("property_type <", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("property_type <=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIn(List<Integer> values) {
            addCriterion("property_type in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotIn(List<Integer> values) {
            addCriterion("property_type not in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeBetween(Integer value1, Integer value2) {
            addCriterion("property_type between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("property_type not between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdIsNull() {
            addCriterion("auth_first_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdIsNotNull() {
            addCriterion("auth_first_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdEqualTo(Long value) {
            addCriterion("auth_first_classify_id =", value, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdNotEqualTo(Long value) {
            addCriterion("auth_first_classify_id <>", value, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdGreaterThan(Long value) {
            addCriterion("auth_first_classify_id >", value, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("auth_first_classify_id >=", value, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdLessThan(Long value) {
            addCriterion("auth_first_classify_id <", value, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("auth_first_classify_id <=", value, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdIn(List<Long> values) {
            addCriterion("auth_first_classify_id in", values, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdNotIn(List<Long> values) {
            addCriterion("auth_first_classify_id not in", values, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdBetween(Long value1, Long value2) {
            addCriterion("auth_first_classify_id between", value1, value2, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthFirstClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("auth_first_classify_id not between", value1, value2, "authFirstClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdIsNull() {
            addCriterion("auth_sencond_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdIsNotNull() {
            addCriterion("auth_sencond_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdEqualTo(Long value) {
            addCriterion("auth_sencond_classify_id =", value, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdNotEqualTo(Long value) {
            addCriterion("auth_sencond_classify_id <>", value, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdGreaterThan(Long value) {
            addCriterion("auth_sencond_classify_id >", value, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("auth_sencond_classify_id >=", value, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdLessThan(Long value) {
            addCriterion("auth_sencond_classify_id <", value, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdLessThanOrEqualTo(Long value) {
            addCriterion("auth_sencond_classify_id <=", value, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdIn(List<Long> values) {
            addCriterion("auth_sencond_classify_id in", values, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdNotIn(List<Long> values) {
            addCriterion("auth_sencond_classify_id not in", values, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdBetween(Long value1, Long value2) {
            addCriterion("auth_sencond_classify_id between", value1, value2, "authSencondClassifyId");
            return (Criteria) this;
        }

        public Criteria andAuthSencondClassifyIdNotBetween(Long value1, Long value2) {
            addCriterion("auth_sencond_classify_id not between", value1, value2, "authSencondClassifyId");
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