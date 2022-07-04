package com.springboot.boot.modules.admin.vo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName IfWhereVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/23 0023 14:24
 * @Version 1.0
 **/
@Data
public class IfWhereVo {
    @ApiModelProperty("完成类型  0初始1学习2考试3证书4全部完成5全部置灰")
    private Integer finishType;

}
