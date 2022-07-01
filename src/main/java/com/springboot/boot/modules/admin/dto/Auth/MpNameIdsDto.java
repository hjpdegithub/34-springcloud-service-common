package com.springboot.boot.modules.admin.dto.Auth;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("MpNameIdsDto 参数对象")
public class MpNameIdsDto extends PageDto {
    @ApiModelProperty("通用name")
    private String name;
    @ApiModelProperty("通用id")
    private Long id;
    @ApiModelProperty("通用id集合")
    private List<Long> ids;
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("证书所有者id")
    private Long cerUserId;




}