package com.springboot.boot.modules.admin.dto.examination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ExaminationSwitchUpDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/14 0014 10:12
 * @Version 1.0
 **/
@Data
@ApiModel("ExaminationSwitchUpDto")
public class ExaminationSwitchUpDto {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty("0未上线1以上线")
    private Integer upType;
}
