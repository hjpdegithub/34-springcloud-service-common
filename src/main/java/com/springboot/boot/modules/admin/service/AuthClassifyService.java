package com.springboot.boot.modules.admin.service;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.MpAuthDirection;
import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.vo.auth.MpAuthDomainVo;
import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * 根据名称或者id查找认证方向列表
     */
    MpAuthHVo  searchId(MpNameIdsDto dto);


    /**
     * 根据名称或者id查找认证方向列表
     */
    PageInfo<MpAuthHVo>  searchByAuthdirectionName(MpNameIdsDto dto);



    /**
     * 根据名称或者id查找认证方向列表
     */
    List<MpAuthHVo>  search();



    /**
     * 查询认证领域
     */
    List<MpAuthDomainVo>  searchDomain(MpNameIdsDto  dto);



    /**
     * 根据id删除认证方向以及认证方向下的领域
     */
     Integer deleteByIds(MpNameIdsDto dto);









}
