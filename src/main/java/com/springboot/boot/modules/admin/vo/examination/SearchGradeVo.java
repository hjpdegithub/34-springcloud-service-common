package com.springboot.boot.modules.admin.vo.examination;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SearchGradeVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/18 0018 14:49
 * @Version 1.0
 **/
@Data
@ApiModel("SearchGradeVo")
public class SearchGradeVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("试卷id")
    private Long id;
    @ApiModelProperty("试卷名称")
    private String name;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("考试用时")
    private Integer useTime;
    @ApiModelProperty("考试类型1模拟考试2在线考试")
    private Integer type;
    @ApiModelProperty("及格分数")
    private Integer passingMark;
    @ApiModelProperty("考试成绩")
    private Integer grage;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    @ApiModelProperty("成绩id")
    private Long grageId;

}
