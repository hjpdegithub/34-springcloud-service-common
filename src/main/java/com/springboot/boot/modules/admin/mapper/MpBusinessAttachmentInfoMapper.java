package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpBusinessAttachmentInfo;
import com.springboot.boot.modules.admin.entity.MpBusinessAttachmentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpBusinessAttachmentInfoMapper {
    long countByExample(MpBusinessAttachmentInfoExample example);

    int deleteByExample(MpBusinessAttachmentInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpBusinessAttachmentInfo record);

    int insertSelective(MpBusinessAttachmentInfo record);

    List<MpBusinessAttachmentInfo> selectByExample(MpBusinessAttachmentInfoExample example);

    MpBusinessAttachmentInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpBusinessAttachmentInfo record, @Param("example") MpBusinessAttachmentInfoExample example);

    int updateByExample(@Param("record") MpBusinessAttachmentInfo record, @Param("example") MpBusinessAttachmentInfoExample example);

    int updateByPrimaryKeySelective(MpBusinessAttachmentInfo record);

    int updateByPrimaryKey(MpBusinessAttachmentInfo record);
}