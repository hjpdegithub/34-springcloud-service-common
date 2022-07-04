package com.springboot.boot.modules.admin.vo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springboot.boot.modules.admin.entity.MpAttachmentInfo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CertificateVo {
    private String fileUrl;
    private String fileLocalUrl;
    private Integer certificateType;
    private MpUserAuthenticationVo userVo;
}