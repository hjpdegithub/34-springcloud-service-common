package com.springboot.boot.modules.admin.vo.examination;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AppExaminationDetailVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 15:01
 * @Version 1.0
 **/
@Data
@ApiModel("AppExaminationDetailVo")
public class AppExaminationDetailVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("试卷名称")
    private String name;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("考试时长")
    private Integer timeLengh;
    @ApiModelProperty("试卷分数")
    private Integer paper;
    @ApiModelProperty("及格分数")
    private Integer passingMark;

    @ApiModelProperty("报名人数")
    private Integer bNum;

    @ApiModelProperty("考试次数")
    private Integer kaoNum;
    @ApiModelProperty("试卷考试次数")
    private Integer frequencyCount;
    @ApiModelProperty("是否报名0否1是")
    private Integer bType;
}
