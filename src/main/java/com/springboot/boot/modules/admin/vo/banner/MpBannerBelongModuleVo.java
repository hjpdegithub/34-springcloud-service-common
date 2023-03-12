package com.springboot.boot.modules.admin.vo.banner;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.vo.file.MpAttachmentInfoVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ChengjiVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/18 0018 14:15
 * @Version 1.0
 **/
@Data
public class MpBannerBelongModuleVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    private String name;
    private String bannerBelongMenu;
    private String bannerBelongModule;
    private Integer upType;
    private Integer delFlag;
    private String description;
    private Long createUser;
    private Date createDate;
    private Long updateUser;
    private Date updateDate;
    List<MpAttachmentInfoVo>  mpAttachmentInfoVo;

}
