package com.springboot.boot.modules.admin.service;

import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurriculumAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.modules.admin.vo.curriculum.GetCurrMess;
import com.springboot.boot.modules.admin.vo.curriculum.SrearchAndClassifyVo;
import com.springboot.boot.utils.ApiResult;

import java.util.List;

public interface CurriculumService {
    /**
     * 课程的新增以及修改
     * @param dto
     * @return
     */
    ApiResult addOrUpdate(CurriculumAddOrUpdateDto dto);

    /**
     * 分页查询课程相关信息
     * @param dto
     * @return
     */
    PageInfo<CurriculumVo> search(SearchCurriculumDto dto);

    MpCurriculum searchById(Long id);
    /**
     * 删除
     * @param dto
     * @return
     */
    ApiResult delete(ClassifyDelSecondDto dto);

    /**
     * 普通根据分类id集合查询课程信息
     */

    List<MpCurriculum> searchByClassId(List<Long> id);

    /**
     * 定制
     * @param id
     * @return
     */
    List<MpCurriculum> searchByClassIdC(List<Long> id);

    /**
     * 普通根据分类id集合查询课程信息
     */

    List<MpCurriculum> searchByClassIdAndType(List<Long> id,Integer ClassFromat);

    List<MpCurriculum> searchByClassIdAndTypeAndName(List<Long> id,Integer ClassFromat,String className);

    /**
     * 定制
     * @param id
     * @return
     */
    List<MpCurriculum> searchByClassIdCAndType(List<Long> id,Integer ClassFromat);

    List<MpCurriculum> searchByClassIdCAndTypeAndName(List<Long> id,Integer ClassFromat,String className);

    /**
     * 根据二级分类查询课程相关信息
     * @param id
     * @return
     */
    List<MpCurriculum> searchCurrBySecond(Long id,Integer classFormat,String className);

    MpCurriculum selectByName(String curriculumName);

    /**
     * 3.0重构课程和分类合并
     * @param id
     * @param classFormat
     * @param curriculumName
     * @return
     */
    List<SrearchAndClassifyVo>  searchCurrAndSecond(Long id, Integer classFormat, String curriculumName);

    GetCurrMess getCurrMess(Long id);
}
