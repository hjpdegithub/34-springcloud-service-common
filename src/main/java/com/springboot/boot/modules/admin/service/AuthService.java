package com.springboot.boot.modules.admin.service;

import com.springboot.boot.modules.admin.dto.Auth.ClassStudyFinishDto;
import com.springboot.boot.modules.admin.dto.Auth.ExamStudyFinishDto;
import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import com.springboot.boot.modules.admin.entity.MpAuthUserSignUp;
import com.springboot.boot.utils.ApiResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    /**
     * 开始学习详情页面
     * @param authId
     * @param userId
     * @return
     */
    ApiResult startStudy(Long authId, Long userId);

    /**
     * 课程提价接口
     * @param classStudyFinishDto
     * @return
     */
    ApiResult classStudyFinish(ClassStudyFinishDto classStudyFinishDto);

    /**
     * 开始考试交卷接口
     * @param examStudyFinishDto
     * @return
     */
    ApiResult startExam(ExamStudyFinishDto examStudyFinishDto);

    /**
     * 查看用户报名预约
     * @param
     * @return
     */
    List<MpAuthUserSignUp> searchSignUp(Long authId,Long userId);

    /**
     * 查看是否完成课程
     * @return
     */
    Integer ifWhere(Long authId, Long userId);
}
