package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.entity.MpQuestionBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpQuestionBankMapper {
    long countByExample(MpQuestionBankExample example);

    int deleteByExample(MpQuestionBankExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpQuestionBank record);

    int insertSelective(MpQuestionBank record);

    List<MpQuestionBank> selectByExample(MpQuestionBankExample example);

    MpQuestionBank selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpQuestionBank record, @Param("example") MpQuestionBankExample example);

    int updateByExample(@Param("record") MpQuestionBank record, @Param("example") MpQuestionBankExample example);

    int updateByPrimaryKeySelective(MpQuestionBank record);

    int updateByPrimaryKey(MpQuestionBank record);
}