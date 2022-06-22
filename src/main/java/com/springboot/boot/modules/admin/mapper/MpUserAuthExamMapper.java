package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpUserAuthExam;
import com.springboot.boot.modules.admin.entity.MpUserAuthExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpUserAuthExamMapper {
    long countByExample(MpUserAuthExamExample example);

    int deleteByExample(MpUserAuthExamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpUserAuthExam record);

    int insertSelective(MpUserAuthExam record);

    List<MpUserAuthExam> selectByExample(MpUserAuthExamExample example);

    MpUserAuthExam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpUserAuthExam record, @Param("example") MpUserAuthExamExample example);

    int updateByExample(@Param("record") MpUserAuthExam record, @Param("example") MpUserAuthExamExample example);

    int updateByPrimaryKeySelective(MpUserAuthExam record);

    int updateByPrimaryKey(MpUserAuthExam record);
}