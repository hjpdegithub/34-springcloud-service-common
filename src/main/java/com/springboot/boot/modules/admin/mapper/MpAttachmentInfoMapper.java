package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAttachmentInfo;
import com.springboot.boot.modules.admin.entity.MpAttachmentInfoExample;
import java.util.List;

import com.springboot.boot.modules.admin.vo.file.AttachmentInfoViewVo;
import org.apache.ibatis.annotations.Param;

public interface MpAttachmentInfoMapper {
    long countByExample(MpAttachmentInfoExample example);

    int deleteByExample(MpAttachmentInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAttachmentInfo record);

    int insertSelective(MpAttachmentInfo record);

    List<MpAttachmentInfo> selectByExample(MpAttachmentInfoExample example);

    MpAttachmentInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAttachmentInfo record, @Param("example") MpAttachmentInfoExample example);

    int updateByExample(@Param("record") MpAttachmentInfo record, @Param("example") MpAttachmentInfoExample example);

    int updateByPrimaryKeySelective(MpAttachmentInfo record);

    int updateByPrimaryKey(MpAttachmentInfo record);


}