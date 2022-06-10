package com.springboot.boot.modules.admin.dto.file;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class AttachmentInfoDto {




    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    private Long attachmentId;
    public void setAttachmentId(Long id) {
        this.attachmentId = attachmentId;
    }
    public Long getAttachmentId() {
        return attachmentId;
    }

    //表关联用START
    //用于设定文件的类型

    @NonNull
    private Long businessId;
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    public Long getBusinessId() {
        return businessId;
    }

    @ApiModelProperty(value="1",name="business")
    @NotNull(message = "文件类型不能为空")
    private String business;
    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }
    public String getBusiness() {
        return business;
    }


    //用于同类文件的排序
    private Integer sort;
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getSort() {
        return sort;
    }

    //表关联用END

    //private Long id;

   // private String fileName;

  //  private String fileUrl;

  //  private String filePath;

    private String description;


    private Integer delFlag;

    private Long createUser;

    private Date createDate;

    private Long updateUser;

    private Date updateDate;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName == null ? null : fileName.trim();
//    }
//
//    public String getFileUrl() {
//        return fileUrl;
//    }
//
//    public void setFileUrl(String fileUrl) {
//        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
//    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath == null ? null : filePath.trim();
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}