package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthService {
    /**
     * 认证流程查看接口
     * @return
     */
    ApiResult authProcedure(Long authId, Long userId);

    /**
     * 立即预约
     * @param authBaseDto
     * @return
     */
    ApiResult authSignUp(AuthBaseDto authBaseDto);
}
