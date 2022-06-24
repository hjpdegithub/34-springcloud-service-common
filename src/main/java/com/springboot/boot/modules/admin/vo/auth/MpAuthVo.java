package com.springboot.boot.modules.admin.vo.auth;

import com.springboot.boot.common.page.PageDto;
import lombok.Data;

import java.util.Date;
@Data
public class MpAuthVo extends PageDto {
    private Long id;

    private String name;

    private String authDescr;

    private Long directionId;

    private Long domainId;

    private Long unitId;

    private Integer authLevel;

    private Long examId;

    private Long departmentId;

    private Long authFirstClassifyId;

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

    private Long userId;






}