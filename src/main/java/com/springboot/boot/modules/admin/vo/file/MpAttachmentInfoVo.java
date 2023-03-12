package com.springboot.boot.modules.admin.vo.file;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;



@Data
public class MpAttachmentInfoVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;
    private String fileName;
    private String fileId;
    private String fileUrl;
    private String filePath;
    private String description;
    private Integer delFlag;
    private Long createUser;
    private Date createDate;
    private Long updateUser;
    private Date updateDate;
    private String documentid;
    private String versionid;
    private String fileurllocal;
    private String linkurl;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
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
    public String getDocumentid() {
        return documentid;
    }
    public void setDocumentid(String documentid) {
        this.documentid = documentid == null ? null : documentid.trim();
    }
    public String getVersionid() {
        return versionid;
    }
    public void setVersionid(String versionid) {
        this.versionid = versionid == null ? null : versionid.trim();
    }
    public String getFileurllocal() {
        return fileurllocal;
    }
    public void setFileurllocal(String fileurllocal) {
        this.fileurllocal = fileurllocal == null ? null : fileurllocal.trim();
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }
}