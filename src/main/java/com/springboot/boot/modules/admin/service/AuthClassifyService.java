package com.springboot.boot.modules.admin.service;


import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.MpAuthDirection;
import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.utils.ApiResult;

import java.util.List;
import java.util.Map;

public interface AuthClassifyService {
    /**
     * 分类的新增以及修改
     * @param dto
     * @return
     */
    ApiResult addOrUpdate(MpAuthDirectionAddOrUpdateDto dto);

    /**
     * 根据一级分类名称查询一级分类信息接口
     */
    List<MpAuthDirection> searchByAuthdirectionName(MpAuthDto dto);
    /**
     * 根据一级分类id查看二级分类信息
     */
    List<MpSecondClassify> searchByClassifyId(Long id);

    /**
     * 查询出所有的分类信息
     * @return {@link ClassifyAllVo}
     */
    List<ClassifyAllVo> searchClassifyAll(Integer classifyType);

    /**
     * 所有一级分类信息包装成map集合
     * @return
     */
    Map<Long,MpFirstClassify > mapClassifyAll(Integer classifyType);

    /**
     * 二级分类删除接口
     * @param dto
     * @return
     */
    ApiResult deleteByids(ClassifyDelSecondDto dto);

    /**
     * 一级分类删除接口
     * @param dto
     * @return
     */
    ApiResult deleteById(ClassifyDelFirstDto dto);

    /**
     * 查询一级分类下的所有信息
     * @return
     */
    List<MpFirstClassify> searchFristClassify();

    /**
     * 查询二级下的所有信息
     * @param dto
     * @return
     */
    PageInfo<MpSecondClassify> searchSecondClassify(ClassifySearchAllDto dto);

    /**
     * 根据分类查询一级分类下的信息
     * @param id
     * @return
     */
    MpFirstClassify searchFirstClassify(Long id);

    /**
     * 根据主键id查询二级分类下的信息
     * @param id
     * @return
     */
    MpSecondClassify searchSecondClassifyById(Long id);

    /**
     * 查询二级分类
     * @return
     */
    List<MpSecondClassify> searchSecondClassifyAll();

    List<MpFirstClassify> searchFristClassifyNoAuth();
}
