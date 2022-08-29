package com.springboot.boot.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MpExaminationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MpExaminationExample() {
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

        public Criteria andExaminationTypeIsNull() {
            addCriterion("examination_type is null");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeIsNotNull() {
            addCriterion("examination_type is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeEqualTo(Integer value) {
            addCriterion("examination_type =", value, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeNotEqualTo(Integer value) {
            addCriterion("examination_type <>", value, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeGreaterThan(Integer value) {
            addCriterion("examination_type >", value, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("examination_type >=", value, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeLessThan(Integer value) {
            addCriterion("examination_type <", value, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("examination_type <=", value, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeIn(List<Integer> values) {
            addCriterion("examination_type in", values, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeNotIn(List<Integer> values) {
            addCriterion("examination_type not in", values, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeBetween(Integer value1, Integer value2) {
            addCriterion("examination_type between", value1, value2, "examinationType");
            return (Criteria) this;
        }

        public Criteria andExaminationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("examination_type not between", value1, value2, "examinationType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeIsNull() {
            addCriterion("range_type is null");
            return (Criteria) this;
        }

        public Criteria andRangeTypeIsNotNull() {
            addCriterion("range_type is not null");
            return (Criteria) this;
        }

        public Criteria andRangeTypeEqualTo(Integer value) {
            addCriterion("range_type =", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeNotEqualTo(Integer value) {
            addCriterion("range_type <>", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeGreaterThan(Integer value) {
            addCriterion("range_type >", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("range_type >=", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeLessThan(Integer value) {
            addCriterion("range_type <", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("range_type <=", value, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeIn(List<Integer> values) {
            addCriterion("range_type in", values, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeNotIn(List<Integer> values) {
            addCriterion("range_type not in", values, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeBetween(Integer value1, Integer value2) {
            addCriterion("range_type between", value1, value2, "rangeType");
            return (Criteria) this;
        }

        public Criteria andRangeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("range_type not between", value1, value2, "rangeType");
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

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNull() {
            addCriterion("unit_id is null");
            return (Criteria) this;
        }

        public Criteria andUnitIdIsNotNull() {
            addCriterion("unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnitIdEqualTo(Long value) {
            addCriterion("unit_id =", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotEqualTo(Long value) {
            addCriterion("unit_id <>", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThan(Long value) {
            addCriterion("unit_id >", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdGreaterThanOrEqualTo(Long value) {
            addCriterion("unit_id >=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThan(Long value) {
            addCriterion("unit_id <", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdLessThanOrEqualTo(Long value) {
            addCriterion("unit_id <=", value, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdIn(List<Long> values) {
            addCriterion("unit_id in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotIn(List<Long> values) {
            addCriterion("unit_id not in", values, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdBetween(Long value1, Long value2) {
            addCriterion("unit_id between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andUnitIdNotBetween(Long value1, Long value2) {
            addCriterion("unit_id not between", value1, value2, "unitId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Long value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Long value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Long value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Long value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Long value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Long> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Long> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Long value1, Long value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Long value1, Long value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountIsNull() {
            addCriterion("frequency_count is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountIsNotNull() {
            addCriterion("frequency_count is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountEqualTo(Integer value) {
            addCriterion("frequency_count =", value, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountNotEqualTo(Integer value) {
            addCriterion("frequency_count <>", value, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountGreaterThan(Integer value) {
            addCriterion("frequency_count >", value, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("frequency_count >=", value, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountLessThan(Integer value) {
            addCriterion("frequency_count <", value, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountLessThanOrEqualTo(Integer value) {
            addCriterion("frequency_count <=", value, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountIn(List<Integer> values) {
            addCriterion("frequency_count in", values, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountNotIn(List<Integer> values) {
            addCriterion("frequency_count not in", values, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountBetween(Integer value1, Integer value2) {
            addCriterion("frequency_count between", value1, value2, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andFrequencyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("frequency_count not between", value1, value2, "frequencyCount");
            return (Criteria) this;
        }

        public Criteria andPaperIsNull() {
            addCriterion("paper is null");
            return (Criteria) this;
        }

        public Criteria andPaperIsNotNull() {
            addCriterion("paper is not null");
            return (Criteria) this;
        }

        public Criteria andPaperEqualTo(Integer value) {
            addCriterion("paper =", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotEqualTo(Integer value) {
            addCriterion("paper <>", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperGreaterThan(Integer value) {
            addCriterion("paper >", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper >=", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLessThan(Integer value) {
            addCriterion("paper <", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLessThanOrEqualTo(Integer value) {
            addCriterion("paper <=", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperIn(List<Integer> values) {
            addCriterion("paper in", values, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotIn(List<Integer> values) {
            addCriterion("paper not in", values, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperBetween(Integer value1, Integer value2) {
            addCriterion("paper between", value1, value2, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotBetween(Integer value1, Integer value2) {
            addCriterion("paper not between", value1, value2, "paper");
            return (Criteria) this;
        }

        public Criteria andPassingMarkIsNull() {
            addCriterion("passing_mark is null");
            return (Criteria) this;
        }

        public Criteria andPassingMarkIsNotNull() {
            addCriterion("passing_mark is not null");
            return (Criteria) this;
        }

        public Criteria andPassingMarkEqualTo(Integer value) {
            addCriterion("passing_mark =", value, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkNotEqualTo(Integer value) {
            addCriterion("passing_mark <>", value, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkGreaterThan(Integer value) {
            addCriterion("passing_mark >", value, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("passing_mark >=", value, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkLessThan(Integer value) {
            addCriterion("passing_mark <", value, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkLessThanOrEqualTo(Integer value) {
            addCriterion("passing_mark <=", value, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkIn(List<Integer> values) {
            addCriterion("passing_mark in", values, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkNotIn(List<Integer> values) {
            addCriterion("passing_mark not in", values, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkBetween(Integer value1, Integer value2) {
            addCriterion("passing_mark between", value1, value2, "passingMark");
            return (Criteria) this;
        }

        public Criteria andPassingMarkNotBetween(Integer value1, Integer value2) {
            addCriterion("passing_mark not between", value1, value2, "passingMark");
            return (Criteria) this;
        }

        public Criteria andTimeLenghIsNull() {
            addCriterion("time_lengh is null");
            return (Criteria) this;
        }

        public Criteria andTimeLenghIsNotNull() {
            addCriterion("time_lengh is not null");
            return (Criteria) this;
        }

        public Criteria andTimeLenghEqualTo(Integer value) {
            addCriterion("time_lengh =", value, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghNotEqualTo(Integer value) {
            addCriterion("time_lengh <>", value, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghGreaterThan(Integer value) {
            addCriterion("time_lengh >", value, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghGreaterThanOrEqualTo(Integer value) {
            addCriterion("time_lengh >=", value, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghLessThan(Integer value) {
            addCriterion("time_lengh <", value, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghLessThanOrEqualTo(Integer value) {
            addCriterion("time_lengh <=", value, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghIn(List<Integer> values) {
            addCriterion("time_lengh in", values, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghNotIn(List<Integer> values) {
            addCriterion("time_lengh not in", values, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghBetween(Integer value1, Integer value2) {
            addCriterion("time_lengh between", value1, value2, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andTimeLenghNotBetween(Integer value1, Integer value2) {
            addCriterion("time_lengh not between", value1, value2, "timeLengh");
            return (Criteria) this;
        }

        public Criteria andUpTimeIsNull() {
            addCriterion("up_time is null");
            return (Criteria) this;
        }

        public Criteria andUpTimeIsNotNull() {
            addCriterion("up_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpTimeEqualTo(Date value) {
            addCriterion("up_time =", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotEqualTo(Date value) {
            addCriterion("up_time <>", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeGreaterThan(Date value) {
            addCriterion("up_time >", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("up_time >=", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeLessThan(Date value) {
            addCriterion("up_time <", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeLessThanOrEqualTo(Date value) {
            addCriterion("up_time <=", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeIn(List<Date> values) {
            addCriterion("up_time in", values, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotIn(List<Date> values) {
            addCriterion("up_time not in", values, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeBetween(Date value1, Date value2) {
            addCriterion("up_time between", value1, value2, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotBetween(Date value1, Date value2) {
            addCriterion("up_time not between", value1, value2, "upTime");
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

        public Criteria andUpTypeIsNull() {
            addCriterion("up_type is null");
            return (Criteria) this;
        }

        public Criteria andUpTypeIsNotNull() {
            addCriterion("up_type is not null");
            return (Criteria) this;
        }

        public Criteria andUpTypeEqualTo(Integer value) {
            addCriterion("up_type =", value, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeNotEqualTo(Integer value) {
            addCriterion("up_type <>", value, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeGreaterThan(Integer value) {
            addCriterion("up_type >", value, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("up_type >=", value, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeLessThan(Integer value) {
            addCriterion("up_type <", value, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeLessThanOrEqualTo(Integer value) {
            addCriterion("up_type <=", value, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeIn(List<Integer> values) {
            addCriterion("up_type in", values, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeNotIn(List<Integer> values) {
            addCriterion("up_type not in", values, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeBetween(Integer value1, Integer value2) {
            addCriterion("up_type between", value1, value2, "upType");
            return (Criteria) this;
        }

        public Criteria andUpTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("up_type not between", value1, value2, "upType");
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