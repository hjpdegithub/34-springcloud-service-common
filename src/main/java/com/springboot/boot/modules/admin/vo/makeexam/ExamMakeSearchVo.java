package com.springboot.boot.modules.admin.vo.makeexam;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ExamMakeSearchVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/8/8 0008 16:41
 * @Version 1.0
 **/
@Data
public class ExamMakeSearchVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("用户编号")
    private Integer number;
    @ApiModelProperty("试卷名称")
    private String examName;
    @ApiModelProperty("考试用时")
    private Integer submitTime;
    @ApiModelProperty("类型1普通2认证")
    private  Integer examType;
    @ApiModelProperty("1待办理2已办理")
    private  Integer statusType;
    @ApiModelProperty("总分")
    private Integer countGrade;
    @ApiModelProperty("认证名称")
    private String authName;

}
