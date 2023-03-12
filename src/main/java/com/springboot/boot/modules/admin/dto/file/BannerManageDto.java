package com.springboot.boot.modules.admin.dto.file;
import com.springboot.boot.common.page.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
@Data
@ApiModel("BannerManageDto")
public class BannerManageDto extends PageDto{
    @ApiModelProperty("附件id")
    private Long id;
    @ApiModelProperty("更新人或者创建人")
    private Long userId;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("banner所属菜单")
    private String bannerBelongMenu;
    @ApiModelProperty("banner所属模块")
    private String bannerBelongModule;
    @ApiModelProperty("bannerFile")
    private List<BannerFileDto> BannerFileDtos;
}
