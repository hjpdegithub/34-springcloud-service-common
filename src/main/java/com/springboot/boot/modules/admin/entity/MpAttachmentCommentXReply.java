package com.springboot.boot.modules.admin.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
@Data
public class MpAttachmentCommentXReply {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    private String commentUserName;
    private String replyUserName;
    private Long commentId;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long userId;
    private String description;
    private Integer delFlag;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;
    private Date createDate;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;
    private Date updateDate;

}