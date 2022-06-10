package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpUserExam;
import com.springboot.boot.modules.admin.entity.MpUserExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpUserExamMapper {
    long countByExample(MpUserExamExample example);

    int deleteByExample(MpUserExamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpUserExam record);

    int insertSelective(MpUserExam record);

    List<MpUserExam> selectByExample(MpUserExamExample example);

    MpUserExam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpUserExam record, @Param("example") MpUserExamExample example);

    int updateByExample(@Param("record") MpUserExam record, @Param("example") MpUserExamExample example);

    int updateByPrimaryKeySelective(MpUserExam record);

    int updateByPrimaryKey(MpUserExam record);
}