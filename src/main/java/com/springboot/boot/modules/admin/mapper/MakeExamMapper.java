package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.dto.exanmake.ExamMakeSearchDto;
import com.springboot.boot.modules.admin.vo.makeexam.ExamMakeSearchVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


public interface MakeExamMapper {
    /**
     * 判卷列表查询
     * @param makeSearchDto
     * @return
     */
    List<ExamMakeSearchVo> searchExamMake(ExamMakeSearchDto makeSearchDto);
    

}
