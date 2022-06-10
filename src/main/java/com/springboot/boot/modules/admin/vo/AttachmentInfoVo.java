package com.springboot.boot.modules.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * AttachmentInfoVo对象.对应实体描述：公用附件表
 */
@Data
@ApiModel("AttachmentInfoDto对象")
public class AttachmentInfoVo {

	@ApiModelProperty("附件id")
	private String id;
	@ApiModelProperty("源文件名称包括后缀")
	private String fileName;
	@ApiModelProperty("相对url")
	private String fileUrl;
	@ApiModelProperty("文件存储相对目录")
	private String filePath;
	@ApiModelProperty("描述")
	private String description;
	@ApiModelProperty("软删除字段0未删除1已删除")
	private Integer delFlag;
	@ApiModelProperty("创建人")
	private Long createUser;
	@ApiModelProperty("创建时间")
	private Date createDate;
	@ApiModelProperty("修改人")
	private Long updateUser;
	@ApiModelProperty("修改时间")
	private Date updateDate;
	/**
	 * 对应字段：page,备注：页码
	 */
	@ApiModelProperty("页码")
	private int page;
	/**
	 * 对应字段：limit,备注：每页数量
	 */
	@ApiModelProperty("每页数量")
	private int limit;

}
