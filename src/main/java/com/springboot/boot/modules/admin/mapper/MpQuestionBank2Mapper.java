package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.entity.MpQuestionBankExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpQuestionBank2Mapper {
    Integer examinationTypeSearch( Long id);
}