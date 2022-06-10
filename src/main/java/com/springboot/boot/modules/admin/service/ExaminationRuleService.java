package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.examination.ExaminationAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.examination.ExaminationRuleDto;
import com.springboot.boot.modules.admin.entity.MpExaminationRule;

import java.util.List;

public interface ExaminationRuleService {
    /**
     * 试卷规则添加
     * @param rule
     * @param code
     * @param dto
     */
    void addExamRule(ExaminationRuleDto rule, String code, ExaminationAddOrUpdateDto dto,long id);

    /**
     * 试卷规则编辑
     * @param rule
     * @param code
     * @param dto
     * @param id
     * @param ruleId
     */
    void updateExanRule(ExaminationRuleDto rule, String code, ExaminationAddOrUpdateDto dto, Long id, Long ruleId);

    /**
     * 试卷规则删除
     * @param id
     * @return
     */
    int deleteByExamintionId(Long id);

    /**
     * 试卷规则查询
     * @param id 通过id
     * @return
     */
    List<MpExaminationRule> selectById(Long id);
}
