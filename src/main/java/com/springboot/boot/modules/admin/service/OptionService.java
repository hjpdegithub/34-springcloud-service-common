package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.OptionDto;
import com.springboot.boot.modules.admin.entity.MpOption;

import java.util.List;

public interface OptionService {
    /**
     * 选项的新增
     * @param optionDtos 选项
     */
    void addOptionByQuestion(List<OptionDto> optionDtos, long id, Long userId);


    /**
     * 选项编辑
     * @param id
     * @param optionDtos
     * @param userId
     */
    void updateOptionByQuestion(Long id, List<OptionDto> optionDtos, Long userId);

    /**
     * 选项信息查询
     * @param examinationId
     * @return
     */
    List<MpOption> selectByQuestionId(Long examinationId);

    /**
     * 删除选项接口
     * @param id
     */
    void deleteOptionBYQuestion(Long id);

    /**
     * 批量查询出选项信息
     * @param optionId
     * @return
     */
    List<MpOption> selectByIds(List<Long> optionId);
}
