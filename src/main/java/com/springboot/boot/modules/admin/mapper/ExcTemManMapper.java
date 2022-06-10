package com.springboot.boot.modules.admin.mapper;

import com.alipay.api.domain.AttachmentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExcTemManMapper {
    List<AttachmentInfo> selectExeTemUrlById(@Param("id") Long id);
}