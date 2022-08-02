package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
@Data
public class MpAttachmentCommentWithUserName {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String userName;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long curId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;

    private String comment;

    private String description;

    private Integer delFlag;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题

    private Long createUser;

    private Date createDate;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateDate;

}