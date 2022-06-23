package com.springboot.boot.modules.admin.service;


import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;

import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;



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
    PageInfo<MpAuthHVo> search(MpAuthDto dto);

}
