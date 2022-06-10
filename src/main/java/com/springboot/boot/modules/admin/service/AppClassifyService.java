package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.vo.classify.app.ClassifyTypeShowStudyVo;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;

import java.util.List;

public interface AppClassifyService {
    /**
     * 自助学习列表
     * @return
     */
    List<SearchStudyVo> searchStudy();

    /**
     * 定制课程展示页面
     * @param id
     * @return
     */
    ClassifyTypeShowStudyVo classifyShow(Long id);

    List<MpSecondClassify> searchByFirstId(Long id);
}
