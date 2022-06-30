package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.entity.MpAttachmentInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class MpAuthHVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    private String name;
    private String description;
    private String authDescr;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long directionId;
    private String directionName;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long domainId;
    private String domainName;
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
    private Date certificateTime;
    private Integer certificateType;
    private Date endTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;
    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;
    private Date updateTime;
    private Integer deleFlag;
    private Integer upType;
    private List<MpAuthDomainVo> MpAuthDomainVos;
    private String  unitName;
    private String  departMentName;
    private MpAttachmentInfo fileInfo ;



}