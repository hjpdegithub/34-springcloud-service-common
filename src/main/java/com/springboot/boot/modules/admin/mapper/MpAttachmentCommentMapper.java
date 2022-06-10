package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAttachmentComment;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAttachmentCommentMapper {
    long countByExample(MpAttachmentCommentExample example);

    int deleteByExample(MpAttachmentCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAttachmentComment record);

    int insertSelective(MpAttachmentComment record);

    List<MpAttachmentComment> selectByExample(MpAttachmentCommentExample example);

    MpAttachmentComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAttachmentComment record, @Param("example") MpAttachmentCommentExample example);

    int updateByExample(@Param("record") MpAttachmentComment record, @Param("example") MpAttachmentCommentExample example);

    int updateByPrimaryKeySelective(MpAttachmentComment record);

    int updateByPrimaryKey(MpAttachmentComment record);
}