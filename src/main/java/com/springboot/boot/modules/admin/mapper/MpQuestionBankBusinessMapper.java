package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.QuestionSerchDto;
import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.entity.MpQuestionBankExample;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpQuestionBankBusinessMapper {

    List<QuestionSearchVo> selectQuestionAll(QuestionSerchDto dto);
}