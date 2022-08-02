package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.curriculum.CurComReplyDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentReply;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentXReply;

import java.util.List;

public interface CurComReplyService {
    /**
     * 课程评论回复的新增和修改
     * @param dto
     * @return
     */
    int add(CurComReplyDto dto);

    List<MpAttachmentCommentXReply>  curComRelySelect(CurComReplyDto dto);

    int deleteByPrimaryKey(Long id);


}
