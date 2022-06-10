package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAttachmentCommentReply;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpAttachmentCommentReplyMapper {
    long countByExample(MpAttachmentCommentReplyExample example);

    int deleteByExample(MpAttachmentCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpAttachmentCommentReply record);

    int insertSelective(MpAttachmentCommentReply record);

    List<MpAttachmentCommentReply> selectByExample(MpAttachmentCommentReplyExample example);

    MpAttachmentCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpAttachmentCommentReply record, @Param("example") MpAttachmentCommentReplyExample example);

    int updateByExample(@Param("record") MpAttachmentCommentReply record, @Param("example") MpAttachmentCommentReplyExample example);

    int updateByPrimaryKeySelective(MpAttachmentCommentReply record);

    int updateByPrimaryKey(MpAttachmentCommentReply record);
}