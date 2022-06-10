package com.springboot.boot.modules.admin.vo.question;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.entity.MpOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName QuestionSearchVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 9:53
 * @Version 1.0
 **/
@Data
@ApiModel("QuestionSearchVo")
public class QuestionSearchVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String name;

    private Integer type;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long examinationId;
    @ApiModelProperty("上线状态零否一是")
    private Integer upType;
    private String rightAnswer;

    private Integer examinationType;


    private String ExamName;

    @ApiModelProperty("选项")
    private List<MpOption> options;



}
