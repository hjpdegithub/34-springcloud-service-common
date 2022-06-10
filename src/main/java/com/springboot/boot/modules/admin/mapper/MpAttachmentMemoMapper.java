package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAttachmentMemo;
import com.springboot.boot.modules.admin.entity.MpAttachmentMemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAttachmentMemoMapper {
    long countByExample(MpAttachmentMemoExample example);

    int deleteByExample(MpAttachmentMemoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAttachmentMemo record);

    int insertSelective(MpAttachmentMemo record);

    List<MpAttachmentMemo> selectByExample(MpAttachmentMemoExample example);

    MpAttachmentMemo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAttachmentMemo record, @Param("example") MpAttachmentMemoExample example);

    int updateByExample(@Param("record") MpAttachmentMemo record, @Param("example") MpAttachmentMemoExample example);

    int updateByPrimaryKeySelective(MpAttachmentMemo record);

    int updateByPrimaryKey(MpAttachmentMemo record);
}