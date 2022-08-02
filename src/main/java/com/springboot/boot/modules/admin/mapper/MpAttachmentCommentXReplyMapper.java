package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpAttachmentCommentReply;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentReplyExample;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentXReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpAttachmentCommentXReplyMapper {

    List<MpAttachmentCommentXReply> selectByCommentId(@Param("commentId") Long commentId);


}