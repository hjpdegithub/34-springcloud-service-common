package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpExamination;
import com.springboot.boot.modules.admin.entity.MpExaminationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpExaminationMapper {
    long countByExample(MpExaminationExample example);

    int deleteByExample(MpExaminationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpExamination record);

    int insertSelective(MpExamination record);

    List<MpExamination> selectByExample(MpExaminationExample example);

    MpExamination selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpExamination record, @Param("example") MpExaminationExample example);

    int updateByExample(@Param("record") MpExamination record, @Param("example") MpExaminationExample example);

    int updateByPrimaryKeySelective(MpExamination record);

    int updateByPrimaryKey(MpExamination record);
}