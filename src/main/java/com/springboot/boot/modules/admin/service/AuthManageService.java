package com.springboot.boot.modules.admin.service;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;

import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.entity.MpAuth;
import com.springboot.boot.modules.admin.entity.MpAuthCertificase;
import com.springboot.boot.modules.admin.entity.MpUserAuthentication;
import com.springboot.boot.modules.admin.vo.auth.CertificateVo;
import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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
     * 认证详情查询
     * @param dto
     * @return
     */
    MpAuthHVo searchById(MpAuthDto dto);

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




     /**
     * 证书信息
     * @param dto
     * @return
     */
     CertificateVo certificateGet(MpNameIdsDto dto);


    /**
     * 证书查询
     * @param dto
     * @return
     */
    PageInfo<MpUserAuthenticationVo> certifiQuery (MpNameIdsDto dto);





}
