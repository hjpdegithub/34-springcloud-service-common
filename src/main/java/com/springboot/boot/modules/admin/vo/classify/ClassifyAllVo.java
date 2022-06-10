package com.springboot.boot.modules.admin.vo.classify;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ClassifyAllVo
 * @Description TODO 一二级分类信息返回报文
 * @Author jhzhou
 * @Date 2022/3/11 0011 16:52
 * @Version 1.0
 **/
@Data
@ApiModel("ClassifyAllVo对象")
public class ClassifyAllVo {
    //一级分类实体
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("一级分类名称")
    private String secondClassifyName;

    private String firstClassifyDescr;

    private Integer firstClassifyType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;
    //二级分类实体
    private List<MpSecondClassify> classifies;
}
