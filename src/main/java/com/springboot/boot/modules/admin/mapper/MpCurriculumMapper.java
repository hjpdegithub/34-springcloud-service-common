package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.entity.MpCurriculumExample;
import java.util.List;

import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MpCurriculumMapper {
    long countByExample(MpCurriculumExample example);

    int deleteByExample(MpCurriculumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MpCurriculum record);

    int insertSelective(MpCurriculum record);

    List<MpCurriculum> selectByExample(MpCurriculumExample example);

    MpCurriculum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MpCurriculum record, @Param("example") MpCurriculumExample example);

    int updateByExample(@Param("record") MpCurriculum record, @Param("example") MpCurriculumExample example);

    int updateByPrimaryKeySelective(MpCurriculum record);

    int updateByPrimaryKey(MpCurriculum record);

}