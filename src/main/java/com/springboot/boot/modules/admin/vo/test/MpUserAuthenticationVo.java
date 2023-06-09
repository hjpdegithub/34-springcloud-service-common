package com.springboot.boot.modules.admin.vo.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;


@Data
public class MpUserAuthenticationVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    private Date certificateTime;
    private String authLevel;
    private String name;
    private String authName;
    private String unitName;
    private String departmentName;
    private String domainName;
    private String phone;
    private String directionName;
    private Integer number;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Integer deleFlag;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long departmentId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long unitId;

}