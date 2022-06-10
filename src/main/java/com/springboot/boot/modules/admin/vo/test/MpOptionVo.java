package com.springboot.boot.modules.admin.vo.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class MpOptionVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    private String opt;
    private String optionName;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long questionId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;
    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;
    private Date updateTime;
    private Integer deleFlag;
    private HashMap statMap ;


}