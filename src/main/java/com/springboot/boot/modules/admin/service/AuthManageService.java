package com.springboot.boot.modules.admin.service;


import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AuthManageService {
    /**
     * 分类的新增以及修改
     * @param dto
     * @return
     */
    ApiResult addOrUpdate(MpAuthDto dto,MultipartFile file);



    /**
     * 分页查询认证
     * @param dto
     * @return
     */
    PageInfo<CurriculumVo> search(MpAuthDto dto);

}
