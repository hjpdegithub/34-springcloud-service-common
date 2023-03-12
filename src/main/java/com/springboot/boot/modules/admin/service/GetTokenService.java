package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.vo.UserInfoVo;
import com.springboot.boot.modules.admin.vo.classify.app.ClassifyTypeShowStudyVo;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;

import java.util.List;

public interface GetTokenService {
   UserInfoVo getToken(String ticket);
}
