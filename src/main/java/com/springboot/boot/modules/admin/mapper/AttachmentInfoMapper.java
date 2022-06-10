package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.vo.file.AttachmentInfoViewVo;

import java.util.List;

public interface AttachmentInfoMapper {


    List<AttachmentInfoViewVo> selectViewVoByPrimaryKey(Long businessId);
}