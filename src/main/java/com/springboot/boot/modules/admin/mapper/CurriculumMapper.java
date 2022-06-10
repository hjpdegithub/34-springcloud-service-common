package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurriculumMapper {


    List<CurriculumVo> selectAll(SearchCurriculumDto dto);
}