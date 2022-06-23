package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/22 0022 14:33
 * @Version 2.0
 **/
@RestController
@RequestMapping("/mp/app/auth")
@Slf4j
@Api(tags = "10.1", description = "前端认证相关接口【周京昊】")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;
    /**
     * 2.0认证流程页面查看接口
     * @return
     */
    @ApiOperation(value = "认证流程页面查看接口", notes="认证流程页面查看接口")
    @GetMapping(value="/authProcedure")
    public ApiResult authProcedure(@RequestParam("authId") Long authId,@RequestParam("userId") Long userId){
        ApiResult result = authService.authProcedure(authId,userId);
        return result;
    }
}
