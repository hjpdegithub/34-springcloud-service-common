package com.springboot.boot.modules.admin.vo.curriculum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetCurrMess
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/7/27 0027 14:16
 * @Version 1.0
 **/
@Data
public class GetCurrMess {

    @ApiModelProperty("课程id")
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    @ApiModelProperty("二级分类名称")
    private String clssifyName;
    @ApiModelProperty("课程名称")
    private String CurrName;
    @ApiModelProperty("描述")
    private String dsc;
}
