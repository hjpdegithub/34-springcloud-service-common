package com.springboot.boot.modules.admin.service;

import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.dto.QuestionBankPracticeSBDto;
import com.springboot.boot.modules.admin.dto.examination.*;
import com.springboot.boot.modules.admin.dto.sign.ChilckSignUpDto;
import com.springboot.boot.modules.admin.entity.MpExamination;
import com.springboot.boot.modules.admin.vo.examination.ExaminationVo;
import com.springboot.boot.utils.ApiResult;

import java.util.List;

public interface ExaminationPaperService {
    /**
     * 试卷新增以及修改
     * @param dto
     * @return
     */
    ApiResult addOrUpdate(ExaminationAddOrUpdateDto dto);

    /**
     * 试卷查询接口
     * @param dto
     * @return
     */
    PageInfo<ExaminationVo> search(ExaminationSearchDto dto);


    List<ExaminationVo> searchNoPage(ExaminationSearchNoPageDto dto);





    /**
     * 试卷删除接口
     * @param dto
     * @return
     */
    ApiResult delete(ExaminationDeleteDto dto);

    public MpExamination selectExamById(Long id);
    /**
     * 试卷回显接口
     * @param id
     * @return
     */
    ApiResult selectById(Long id);

    /**
     * 检查试卷题数量
     * @param dto
     * @return
     */
    ApiResult checkQuestionByExamId(QuestionBankAddAndUpdateDto dto);

    /**
     * app试卷详情
     * @param id
     * @return
     */
    ApiResult detail(Long id, Long userId);

    /***
     * 点击报名接口
     * @param dto
     * @return
     */
    ApiResult calickSignUp(ChilckSignUpDto dto);

    /**
     * 题库练习
     * @param id
     * @return
     */
    ApiResult questionBankPractice(Long id);

    /**
     * 练习交卷
     * @param dto
     * @return
     */
    ApiResult questionBankPracticeSubmit(QuestionBankPracticeSBDto dto);

    /**
     * 错题查询
     * @param userId
     * @param examId
     * @return
     */
    ApiResult searchErrorQuestion(Long userId, Long examId);

    /**
     * 上下线切换接口
     * @param switchUpDto
     * @return
     */
    ApiResult SwitchUp(ExaminationSwitchUpDto switchUpDto);

    /**
     *
     * 模拟考试
     * @param id
     * @return
     */
    ApiResult selectSimulation(Long id);

    /**
     * 在线模拟考试交卷
     * @param dto
     * @return
     */
    ApiResult submitSimulation(SubmitSlimylationDto dto);

    /**
     * 历史成绩查询
     * @param userId
     * @param id
     * @return
     */
    ApiResult searchGrade(Long userId, Long id);

    /**
     * 试卷查询
     * @param userId
     * @param id
     * @param achievementId
     * @return
     */
    ApiResult searchExamById(Long userId, Long id, Long achievementId);

    ApiResult select();
}
