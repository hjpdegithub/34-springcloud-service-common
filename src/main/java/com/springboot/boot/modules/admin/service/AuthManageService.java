package com.springboot.boot.modules.admin.service;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;

import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
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
    ApiResult addOrUpdate(MpAuthDto dto);



    /**
     * 分页查询认证
     * @param dto
     * @return
     */
    PageInfo<MpAuthHVo> search(MpAuthDto dto);


    /**
     * 认证信息上下线
     * @param dto
     * @return
     */
    Integer onOffLine(MpAuthDto dto);

    /**
     * 认证信息批量删除
     * @param dto
     * @return
     */
    Integer deleteBatch(MpNameIdsDto dto);


}
