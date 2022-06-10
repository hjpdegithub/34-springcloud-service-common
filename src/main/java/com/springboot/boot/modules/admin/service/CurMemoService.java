package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentMemo;

import org.springframework.web.bind.annotation.RequestBody;


public interface CurMemoService {
    /**
     * 课程评论回复的新增和修改
     * @param dto
     * @return
     */
    int add(CurMemDto dto);

    MpAttachmentMemo  curComSelect(CurMemDto dto);

    int deleteByPrimaryKey(Long id);


    Boolean  curThumStatus(@RequestBody CurComDto dto);

    int curThum(@RequestBody CurComDto dto);


     int curThumCancel(@RequestBody CurComDto dto);



    Long curThumCount(@RequestBody CurComDto dto);




}
