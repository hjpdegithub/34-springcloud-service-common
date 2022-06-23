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
    @ApiModelProperty("是否报名0否1是")
    private Integer signUpType;

    @ApiModelProperty("是否学习0否1是")
    private Integer studyType;

    @ApiModelProperty("是否可以考试0否1是")
    private Integer examType;

    @ApiModelProperty("是否领取证书0否1是")
    private Integer certificaseType;
}
