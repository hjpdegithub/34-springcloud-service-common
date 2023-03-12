package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpBusinessAttachmentInfo;
import com.springboot.boot.modules.admin.entity.MpBusinessAttachmentInfoExample;
import com.springboot.boot.modules.admin.vo.file.MpAttachmentInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpBannerFileMapper {

    List<MpAttachmentInfoVo> selectByBusinessId(@Param("id") Long id);

}