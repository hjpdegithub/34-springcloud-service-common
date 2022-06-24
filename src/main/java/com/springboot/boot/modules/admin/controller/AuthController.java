package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.modules.admin.dto.Auth.ClassStudyFinishDto;
import com.springboot.boot.modules.admin.dto.Auth.ExamStudyFinishDto;
import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import com.springboot.boot.modules.admin.entity.MpAuthUserSignUp;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
        if (null == userId){
            return ApiResult.error("请先登录！");
        }
        ApiResult result = authService.authProcedure(authId,userId);
        return result;
    }

    @ApiOperation(value = "立即预约报名接口", notes="立即预约报名接口")
    @PostMapping(value="/authSignUp")
    public ApiResult authSignUp(@RequestBody AuthBaseDto authBaseDto){
        if (null == authBaseDto.getUserId()){
            return ApiResult.error("请先登录！");
        }

        ApiResult result = authService.authSignUp(authBaseDto);
        return result;
    }

    @ApiOperation(value = "开始学习详情页面", notes="开始学习详情页面")
    @GetMapping(value="/startStudy")
    public ApiResult startStudy(@RequestParam("authId") Long authId,@RequestParam("userId") Long userId){
        if (null == userId){
            return ApiResult.error("请先登录！");
        }
        List<MpAuthUserSignUp> authUserSignUps = authService.searchSignUp(authId,userId);
        if (CollectionUtils.isEmpty(authUserSignUps)){
            return ApiResult.error("请先预约！");
        }
        ApiResult result = authService.startStudy(authId,userId);
        return result;
    }

    @ApiOperation(value = "课程学习完成提交接口", notes="课程学习完成提交接口")
    @PostMapping(value="/classStudyFinish")
    public ApiResult classStudyFinish(@RequestBody ClassStudyFinishDto classStudyFinishDto){
        if (null == classStudyFinishDto.getUserId()){
            return ApiResult.error("请先登录！");
        }
        ApiResult result = authService.classStudyFinish(classStudyFinishDto);
        return result;
    }

    @ApiOperation(value = "开始考试交卷接口", notes="开始考试交卷接口")
    @PostMapping(value="/startExam")
    public ApiResult startExam(@RequestBody ExamStudyFinishDto examStudyFinishDto){
        if (null == examStudyFinishDto.getUserId()){
            return ApiResult.error("请先登录！");
        }
        ApiResult result = authService.startExam(examStudyFinishDto);
        return result;
    }

}
