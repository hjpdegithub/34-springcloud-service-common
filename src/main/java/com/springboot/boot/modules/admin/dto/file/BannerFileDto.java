package com.springboot.boot.modules.admin.dto.file;

import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class BannerFileDto {
    @ApiModelProperty("附件id")
    private Long fileId;
    @ApiModelProperty("文件链接路径")
    private String linkurl;
}
