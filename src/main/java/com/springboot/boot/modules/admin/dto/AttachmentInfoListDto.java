package com.springboot.boot.modules.admin.dto;

import com.springboot.boot.modules.admin.vo.AttachmentInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * AttachmentInfoDto对象.对应实体描述：公用附件表
 */
@Data
@ApiModel("AttachmentInfoListDto对象")
public class AttachmentInfoListDto {

	@ApiModelProperty("附件集合信息")
	List<AttachmentInfoVo> attachmentInfoDtoList;
}
