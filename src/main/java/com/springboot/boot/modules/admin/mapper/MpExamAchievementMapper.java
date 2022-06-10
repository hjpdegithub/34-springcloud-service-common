package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.entity.MpExamAchievement;
import com.springboot.boot.modules.admin.entity.MpExamAchievementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpExamAchievementMapper {
    long countByExample(MpExamAchievementExample example);

    int deleteByExample(MpExamAchievementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpExamAchievement record);

    int insertSelective(MpExamAchievement record);

    List<MpExamAchievement> selectByExample(MpExamAchievementExample example);

    MpExamAchievement selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpExamAchievement record, @Param("example") MpExamAchievementExample example);

    int updateByExample(@Param("record") MpExamAchievement record, @Param("example") MpExamAchievementExample example);

    int updateByPrimaryKeySelective(MpExamAchievement record);

    int updateByPrimaryKey(MpExamAchievement record);
}