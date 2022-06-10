package com.springboot.boot.modules.admin.service;

import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.dto.QuestionDeleteDto;
import com.springboot.boot.modules.admin.dto.QuestionSerchDto;
import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import com.springboot.boot.utils.ApiResult;

import java.util.List;

public interface QuestionService {
    /**
     * 题库新增以及修改
     * @param dto
     * @return
     */
    ApiResult addOrUpdate(QuestionBankAddAndUpdateDto dto);

    /**
     * 试卷检查
     * @param dto
     * @return
     */
    List<MpQuestionBank> selectByExIdAndType(QuestionBankAddAndUpdateDto dto);

    /**
     * 题库查询接口
     * @param dto
     * @return
     */
    PageInfo<QuestionSearchVo> search(QuestionSerchDto dto);

    /**
     * 题库回显接口
     * @param id
     * @return
     */
    QuestionSearchVo searchById(Long id);

    /**
     * 接口删除接口
     * @param deleteDto
     * @return
     */
    ApiResult delete(QuestionDeleteDto deleteDto);

    /**
     * 查询错误题库
     * @param ids
     * @return
     */
    List<MpQuestionBank> selectByIds(List<Long> ids);
}
