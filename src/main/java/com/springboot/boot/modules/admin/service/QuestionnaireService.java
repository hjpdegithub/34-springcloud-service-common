package com.springboot.boot.modules.admin.service;


import com.springboot.boot.modules.admin.dto.examination.SubmitSlimylationDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllPageDto;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface QuestionnaireService {
    // List<MpQuestionnaireVo> questionnaireList();
    // List<MpQuestionnaireOptionVo> questionnaireDetail( CommonAllDto dto);
    List<MpExaminationVo> mpQuestionnaireOptionVoList(CommonAllDto dto);


    List<MpExaminationVo> questionnaireListWithPage(CommonAllPageDto dto);



    /**
     * 问卷类提交
     * @param dto
     * @return
     */
    ApiResult submitSimulation(SubmitSlimylationDto dto);


    /**
     * 问卷类统计
     * @param dto
     * @return
     */
    ApiResult questionnaireStatistics(CommonAllDto dto);








}