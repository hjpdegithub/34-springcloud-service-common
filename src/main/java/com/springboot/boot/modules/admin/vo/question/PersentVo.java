package com.springboot.boot.modules.admin.vo.question;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PersentVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 10:40
 * @Version 1.0
 **/
@Data
public class PersentVo {
    @ApiModelProperty("选项")
    private String name;
    @ApiModelProperty("百分比")
    private String value;

}
