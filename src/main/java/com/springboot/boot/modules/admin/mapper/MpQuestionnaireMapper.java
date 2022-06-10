package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpQuestionnaire;
import com.springboot.boot.modules.admin.entity.MpQuestionnaireExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpQuestionnaireMapper {
    long countByExample(MpQuestionnaireExample example);

    int deleteByExample(MpQuestionnaireExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpQuestionnaire record);

    int insertSelective(MpQuestionnaire record);

    List<MpQuestionnaire> selectByExample(MpQuestionnaireExample example);

    MpQuestionnaire selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpQuestionnaire record, @Param("example") MpQuestionnaireExample example);

    int updateByExample(@Param("record") MpQuestionnaire record, @Param("example") MpQuestionnaireExample example);

    int updateByPrimaryKeySelective(MpQuestionnaire record);

    int updateByPrimaryKey(MpQuestionnaire record);
}