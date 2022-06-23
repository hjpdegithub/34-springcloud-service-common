package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.vo.auth.AuthProcedureVo;
import com.springboot.boot.modules.admin.vo.auth.IfWhereVo;
import com.springboot.boot.modules.admin.vo.curriculum.AuthClassVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AuthServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/6/22 0022 15:06
 * @Version 1.0
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private MpCurriculumMapper curriculumMapper;
    @Autowired
    private MpUserAuthClassMapper mpUserAuthClassMapper;
    @Autowired
    private MpAuthMapper authMapper;
    @Autowired
    private MpExaminationMapper examinationMapper;
    @Autowired
    private MpAuthUserSignUpMapper authUserSignUpMapper;
    @Autowired
    private MpUserAuthExamMapper userAuthExamMapper;
    @Autowired
    private MpAuthCertificaseMapper certificaseMapper;
    @Autowired
    private MpAuthUserSignUpMapper signUpMapper;

    /**
     * 认证查看流程接口版本2.0
     * @return
     */
    @Override
    public ApiResult authProcedure(Long authId, Long userId) {

        AuthProcedureVo authClassVo = new AuthProcedureVo();
        authClassVo.setId(authId);
        authClassVo.setUserId(userId);
        //查看认证信息
        MpAuthExample authExample = new MpAuthExample();
        MpAuthExample.Criteria ac = authExample.createCriteria();
        ac.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        ac.andIdEqualTo(authId);
        List<MpAuth> mpAuths = authMapper.selectByExample(authExample);
        if (mpAuths.size()<=0){
            throw new BusinessException("认证信息为空");
        }
        //通过认证id分类id查询出认证的相关课程
        List<MpCurriculum> mpCurriculumList = mpCurriculumList(mpAuths);
        List<AuthClassVo> authClassVos = new ArrayList<>();
        if (!mpCurriculumList.isEmpty()){
            mpCurriculumList.forEach(e->{
                AuthClassVo classVo = new AuthClassVo();
                classVo.setClassFormat(e.getClassFormat());
                classVo.setCurriculumName(e.getCurriculumName());
                classVo.setId(e.getId());
                //根据课程id和用户id查看是否完成了学习
                List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(e.getId(), userId,mpAuths.get(0).getId());
                if (mpUserAuthClasses.size()>0){
                    classVo.setStatus(CommonEnum.FINISH.getCode());
                }else{
                    classVo.setStatus(CommonEnum.NOT_FINISH.getCode());
                }
                authClassVos.add(classVo);
            });
        }
        authClassVo.setClassVoList(authClassVos);
        //考试次数
        //查看该认证下的试卷次数
        List<MpExamination> mpExaminations = mpExaminations(mpAuths);
        if (mpExaminations.size()<=0){
            throw new BusinessException("试卷不存在");
        }
        authClassVo.setExamCount(mpExaminations.get(0).getFrequencyCount());
        authClassVo.setExamId(mpExaminations.get(0).getId());

        //是否条件判断
        IfWhereVo ifWhereVo = ifWhere(authId, userId, mpAuths);
        BeanCopy.copy(ifWhereVo,authClassVo);
        return ApiResult.success(authClassVo);
    }

    /**
     * 立即预约
     * @param authBaseDto
     * @return
     */
    @Override
    public ApiResult authSignUp(AuthBaseDto authBaseDto) {
        //查看是否预约信息
        MpAuthUserSignUpExample signUpExample = new MpAuthUserSignUpExample();
        MpAuthUserSignUpExample.Criteria criteria = signUpExample.createCriteria();
        criteria.andAuthIdEqualTo(authBaseDto.getAuthId());
        criteria.andUserIdEqualTo(authBaseDto.getUserId());
        List<MpAuthUserSignUp> mpAuthUserSignUps = signUpMapper.selectByExample(signUpExample);
        if (mpAuthUserSignUps.size()>0){
            throw new BusinessException("该用户已经预约报名");
        }
        MpAuthUserSignUp mpAuthUserSignUp = new MpAuthUserSignUp();
        mpAuthUserSignUp.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
        mpAuthUserSignUp.setAuthId(authBaseDto.getAuthId());
        mpAuthUserSignUp.setCreateTime(new Date());
        mpAuthUserSignUp.setCreateUser(authBaseDto.getUserId());
        mpAuthUserSignUp.setDeleFlag(CommonEnum.USED.getCode());
        mpAuthUserSignUp.setUpdateTime(new Date());
        mpAuthUserSignUp.setUpdateUser(authBaseDto.getUserId());
        mpAuthUserSignUp.setUserId(authBaseDto.getUserId());
        int i = signUpMapper.insertSelective(mpAuthUserSignUp);
        if (i <= CommonEnum.ADD_ERROR.getCode()){
            throw new BusinessException("报名新增失败");
        }
        return ApiResult.success();
    }

    /**
     * 是否条件判断
     * @param
     * @param authId 认证id、
     * @param userId 用户id
     */
    public IfWhereVo ifWhere(Long authId, Long userId,List<MpAuth> mpAuths){
        IfWhereVo ifWhereVo = new IfWhereVo();
        //判断是否报名预约================================================
        MpAuthUserSignUpExample example = new MpAuthUserSignUpExample();
        MpAuthUserSignUpExample.Criteria singUpCriteria = example.createCriteria();
        singUpCriteria.andUserIdEqualTo(userId);
        singUpCriteria.andAuthIdEqualTo(authId);
        singUpCriteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAuthUserSignUp> mpAuthUserSignUps = authUserSignUpMapper.selectByExample(example);
        int singUpResult = (mpAuthUserSignUps.size()>0)?CommonEnum.NO.getCode():CommonEnum.YES.getCode();
        ifWhereVo.setSignUpType(singUpResult);
        //判断是否完成了所有的课程学习=====================================================================
        //用户完成的课程数据
        List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(null, userId, authId);
        List<Long> userIds = mpUserAuthClasses.stream().map(MpUserAuthClass::getCurriculumId).collect(Collectors.toList());
        //认证列表的课程
        List<MpCurriculum> mpCurriculumList = mpCurriculumList(mpAuths);
        List<Long>ids = mpCurriculumList.stream().map(MpCurriculum::getId).collect(Collectors.toList());
        boolean result = userIds.containsAll(ids) && ids.containsAll(userIds);
        int studyResult = (result)?CommonEnum.NO.getCode():CommonEnum.YES.getCode();
        ifWhereVo.setStudyType(studyResult);
        //判断是否可以考试=================================================================
        //查看试卷次数
        List<MpExamination> mpExaminations = mpExaminations(mpAuths);
        if (mpExaminations.size()<=0){
            throw new BusinessException("试卷不存在");
        }
        Integer frequencyCount = mpExaminations.get(0).getFrequencyCount();
        //查看用户考试信息
        MpUserAuthExamExample mpUserAuthExamExample = new MpUserAuthExamExample();
        MpUserAuthExamExample.Criteria criteria = mpUserAuthExamExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andAuthIdEqualTo(authId);
        criteria.andExamIdEqualTo(mpAuths.get(0).getExamId());
        List<MpUserAuthExam> mpUserAuthExams = userAuthExamMapper.selectByExample(mpUserAuthExamExample);
        //判断是否可以参加考试
        //是否存在合格的数据
        List<MpUserAuthExam> userCommonList = mpUserAuthExams.stream().filter(a -> a.getIfWhether().intValue() == 1).collect(Collectors.toList());
        //开始判断
        if (mpUserAuthExams.size()>=frequencyCount.intValue()||userCommonList.size()>0){
            ifWhereVo.setExamType(CommonEnum.NO.getCode());
        }else{
            ifWhereVo.setExamType(CommonEnum.YES.getCode());
        }
        //是否领取证书
        MpAuthCertificaseExample certificaseExample = new MpAuthCertificaseExample();
        MpAuthCertificaseExample.Criteria criteria1 = certificaseExample.createCriteria();
        criteria1.andAuthIdEqualTo(authId);
        criteria1.andUserIdEqualTo(userId);
        List<MpAuthCertificase> mpAuthCertificases = certificaseMapper.selectByExample(certificaseExample);
        if (mpAuthCertificases.size()>0){
            ifWhereVo.setCertificaseType(CommonEnum.NO.getCode());
        }else{
            ifWhereVo.setCertificaseType(CommonEnum.YES.getCode());
        }
        return ifWhereVo;
    }


    /**
     * 查看用户认证课程相关信息
     * @param id 课程id
     * @param userId 用户id
     *  @param authId 认证id
     * @return
     */
    public List<MpUserAuthClass>  mpUserAuthClass(Long id,Long userId,Long authId){
        MpUserAuthClassExample classExample = new MpUserAuthClassExample();
        MpUserAuthClassExample.Criteria criteria = classExample.createCriteria();
        criteria.andAuthIdEqualTo(authId);
        criteria.andUserIdEqualTo(userId);
        if (null != id){
            criteria.andCurriculumIdEqualTo(id);
        }
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClassMapper.selectByExample(classExample);
        return mpUserAuthClasses;
    }

    /**
     * 查看认证分类下的课程
     * @param mpAuths 认证信息
     * @return
     */
    public List<MpCurriculum> mpCurriculumList(List<MpAuth> mpAuths ){
        MpCurriculumExample curriculumExample = new MpCurriculumExample();
        MpCurriculumExample.Criteria criteria = curriculumExample.createCriteria();
        criteria.andPropertyTypeEqualTo(CommonEnum.AUTH.getCode());
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andAuthFirstClassifyIdEqualTo(mpAuths.get(0).getAuthFirstClassifyId());
        criteria.andAuthSencondClassifyIdEqualTo(mpAuths.get(0).getAuthSencondClassifyId());
        List<MpCurriculum> mpCurriculumList = curriculumMapper.selectByExample(curriculumExample);
        return mpCurriculumList;
    }

    /**
     * 查看试卷次数
     * @param mpAuths
     * @return
     */
    public List<MpExamination> mpExaminations(List<MpAuth> mpAuths){
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria exampleCriteria = examinationExample.createCriteria();
        exampleCriteria.andIdEqualTo(mpAuths.get(0).getExamId());
        exampleCriteria.andUpTypeEqualTo(CommonEnum.UP.getCode());
        exampleCriteria.andExaminationTypeEqualTo(3);
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        return mpExaminations;
    }
}
