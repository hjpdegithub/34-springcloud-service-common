package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.common.page.PageDto;
import lombok.Data;

import java.util.Date;
@Data
public class MpAuthVo extends PageDto {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String name;

    private String authDescr;

    private Long directionId;

    private Long domainId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long unitId;

    private Integer authLevel;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long departmentId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authFirstClassifyId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authSencondClassifyId;

    private Long certificateTime1;

    private Date certificateTime;

    private Integer certificateType;

    private Long endTime1;

    private Date endTime;

    private Long createUser;

    private Long createTime1;

    private Date createTime;

    private Long updateUser;

    private Long updateTime1;

    private Date updateTime;

    private Integer deleFlag;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;






}