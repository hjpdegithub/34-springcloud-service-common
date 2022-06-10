package com.springboot.boot.modules.admin.dto.sign;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ChilckSignUpDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 9:27
 * @Version 1.0
 **/
@Data
@ApiModel("ChilckSignUpDto")
public class ChilckSignUpDto {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("考试id")
    private Long examId;
    @ApiModelProperty("考试时间")
    private Date startTime;
    @ApiModelProperty("1点击报名2取消报名")
    private Integer signType;
}
