package com.springboot.boot.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName OptionDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/8 0008 14:24
 * @Version 2.0
 **/
@Data
@ApiModel("OptionDto")
public class OptionDto {
    private Long id;

    private String option;

    private String opt;

    private String optionName;


}
