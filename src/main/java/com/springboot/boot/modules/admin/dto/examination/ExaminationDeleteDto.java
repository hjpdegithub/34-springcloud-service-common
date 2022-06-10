package com.springboot.boot.modules.admin.dto.examination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ExaminationDeleteDto
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/8 0008 9:42
 * @Version 1.0
 **/
@Data
@ApiModel("ExaminationDeleteDto")
public class ExaminationDeleteDto {
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("主键id")
    private List<Long> ids;


}
