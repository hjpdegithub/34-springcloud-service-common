package com.springboot.boot.modules.admin.vo.question;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AppOptionVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 10:40
 * @Version 1.0
 **/
@Data
public class AppOptionCountVo {
    @ApiModelProperty("选项主键id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("选项")
    private String opt;
    @ApiModelProperty("选项名称")
    private String optionName;
    @ApiModelProperty("题库表id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long questionId;
    @ApiModelProperty("本题目被选的次数")
    private Long choiceCount;
    @ApiModelProperty("百分比")
    private String perCent;
}
