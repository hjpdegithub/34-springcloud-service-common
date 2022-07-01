package com.springboot.boot.modules.admin.dto.Auth;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("MpNameIdsDto 参数对象")
public class MpNameIdsDto extends PageDto {

    private String name;
    private Long id;
    private List<Long> ids;
    private Long userId;
    private Long cerUserId;




}