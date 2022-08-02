package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentComment;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentExample;
import com.springboot.boot.modules.admin.entity.MpAttachmentCommentWithUserName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpAttachmentCommentXMapper {

    List<MpAttachmentCommentWithUserName>  selectByCurId (@Param("dto") CurComDto dto) ;

}