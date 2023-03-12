package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.vo.classify.app.ClassifyTypeShowStudyVo;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;
import com.springboot.boot.utils.ApiResult;

import java.util.List;

public interface BannerUploadService {
    /**
     * bannerUpload
     * @return
     */
    ApiResult bannerUpload();

}
