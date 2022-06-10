package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentComment;

import com.springboot.boot.utils.ApiResult;

import java.util.List;

public interface CurComService {
    /**
     * 课程评论新增以及修改
     * @param dto
     * @return
     */
    ApiResult add(CurComDto dto);


    List<MpAttachmentComment>   addCurCommentSelect(CurComDto dto);


     int deleteByPrimaryKey(Long id);


}
