package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpExaminationRule;
import com.springboot.boot.modules.admin.entity.MpExaminationRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpExaminationRuleMapper {
    long countByExample(MpExaminationRuleExample example);

    int deleteByExample(MpExaminationRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpExaminationRule record);

    int insertSelective(MpExaminationRule record);

    List<MpExaminationRule> selectByExample(MpExaminationRuleExample example);

    MpExaminationRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpExaminationRule record, @Param("example") MpExaminationRuleExample example);

    int updateByExample(@Param("record") MpExaminationRule record, @Param("example") MpExaminationRuleExample example);

    int updateByPrimaryKeySelective(MpExaminationRule record);

    int updateByPrimaryKey(MpExaminationRule record);
}