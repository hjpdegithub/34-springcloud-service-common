package com.springboot.boot.modules.admin.mapper;

import com.springboot.boot.modules.admin.vo.curriculum.CurSecondDetailVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CurriEliteMapper {
    List<CurriculumVo> selectAll();
    List<CurSecondDetailVo> curSecondDetailSelectById(@Param("id") Long id);
    Long  selectReadCount(@Param("id") Long id);
    //根据课程ID查询附件的url一个课程只有一个文件的url
    String selectCurAttaUrl(@Param("id") Long id);

}