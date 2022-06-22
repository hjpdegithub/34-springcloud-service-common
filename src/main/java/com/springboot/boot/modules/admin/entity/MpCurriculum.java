package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class MpCurriculum {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String curriculumName;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long genFirstClassifyId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long genSecondClassifyId;

    private Date studyTime;

    private Integer classHour;

    private Integer classFormat;

    private String authorName;

    private Integer applyType;

    private Integer num;

    private Integer customizedType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long custFirstClassifyId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long custSecondClassifyId;

    private Integer openClassType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;

    private Integer propertyType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authFirstClassifyId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authSencondClassifyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName == null ? null : curriculumName.trim();
    }

    public Long getGenFirstClassifyId() {
        return genFirstClassifyId;
    }

    public void setGenFirstClassifyId(Long genFirstClassifyId) {
        this.genFirstClassifyId = genFirstClassifyId;
    }

    public Long getGenSecondClassifyId() {
        return genSecondClassifyId;
    }

    public void setGenSecondClassifyId(Long genSecondClassifyId) {
        this.genSecondClassifyId = genSecondClassifyId;
    }

    public Date getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Date studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public Integer getClassFormat() {
        return classFormat;
    }

    public void setClassFormat(Integer classFormat) {
        this.classFormat = classFormat;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCustomizedType() {
        return customizedType;
    }

    public void setCustomizedType(Integer customizedType) {
        this.customizedType = customizedType;
    }

    public Long getCustFirstClassifyId() {
        return custFirstClassifyId;
    }

    public void setCustFirstClassifyId(Long custFirstClassifyId) {
        this.custFirstClassifyId = custFirstClassifyId;
    }

    public Long getCustSecondClassifyId() {
        return custSecondClassifyId;
    }

    public void setCustSecondClassifyId(Long custSecondClassifyId) {
        this.custSecondClassifyId = custSecondClassifyId;
    }

    public Integer getOpenClassType() {
        return openClassType;
    }

    public void setOpenClassType(Integer openClassType) {
        this.openClassType = openClassType;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleFlag() {
        return deleFlag;
    }

    public void setDeleFlag(Integer deleFlag) {
        this.deleFlag = deleFlag;
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Long getAuthFirstClassifyId() {
        return authFirstClassifyId;
    }

    public void setAuthFirstClassifyId(Long authFirstClassifyId) {
        this.authFirstClassifyId = authFirstClassifyId;
    }

    public Long getAuthSencondClassifyId() {
        return authSencondClassifyId;
    }

    public void setAuthSencondClassifyId(Long authSencondClassifyId) {
        this.authSencondClassifyId = authSencondClassifyId;
    }
}