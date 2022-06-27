package com.springboot.boot.modules.admin.dto.Auth;

import com.springboot.boot.common.page.PageDto;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class MpNameIdsDto extends PageDto {

    private String name;
    private Long id;
    private List<Long> ids;
    private Long userId;


}