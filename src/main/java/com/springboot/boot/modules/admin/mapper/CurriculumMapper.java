package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.modules.admin.vo.curriculum.SearchCurrAndSencondClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CurriculumMapper {


    List<CurriculumVo> selectAll(SearchCurriculumDto dto);

    List<SearchCurrAndSencondClassVo> searchCurrAndSecond(@Param("id") Long id, @Param("classFormat")Integer classFormat, @Param("curriculumName")String curriculumName);

    List<SearchCurrAndSencondClassVo> searchCurrAndSecondByGen(@Param("id") Long id, @Param("classFormat")Integer classFormat, @Param("curriculumName")String curriculumName);
}