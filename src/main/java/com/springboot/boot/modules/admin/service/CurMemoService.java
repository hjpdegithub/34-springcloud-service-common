package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentMemo;

import com.springboot.boot.modules.admin.entity.MpCurthu;
import com.springboot.boot.modules.admin.vo.MyStudyVo;
import com.springboot.boot.modules.admin.vo.curriculum.CollectStatusVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface CurMemoService {
    /**
     * 课程评论回复的新增和修改
     * @param dto
     * @return
     */
    int add(CurMemDto dto);

    MpAttachmentMemo  curComSelect(CurMemDto dto);

    int deleteByPrimaryKey(Long id);


    CollectStatusVo  curThumStatus(@RequestBody CurComDto dto);



    Boolean collectOrCancel(@RequestBody CurComDto dto);


    CollectStatusVo  collectStatus(@RequestBody CurComDto dto);

    List<MyStudyVo>  collectCurSearCh(@RequestBody CurMemDto dto);


    int curThum(@RequestBody CurComDto dto);


     int curThumCancel(@RequestBody CurComDto dto);



    Long curThumCount(@RequestBody CurComDto dto);


    List<MyStudyVo>  myCollectSelect(CurMemDto dto);
}
