package com.springboot.boot.modules.admin.vo.classify.app;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SearchStudyVo
 * @Description TODO 自助学习列表返回报文
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:18
 * @Version 1.0
 **/
@Data
@Api("SearchStudyVo对象")
public class SearchStudyVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("分类名称")
    private String firstClassifyName;

    @ApiModelProperty("分类描述")
    private String firstClassifyDescr;

    @ApiModelProperty("是否定制 1普通2定制")
    private Integer firstClassifyType;

    @ApiModelProperty("总课时")
    private Integer countNum;

    @ApiModelProperty("课程类型")
    private Integer typeNum;

}
