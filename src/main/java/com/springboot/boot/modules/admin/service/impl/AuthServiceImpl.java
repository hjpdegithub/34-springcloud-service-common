package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.TypeEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Auth.ClassStudyFinishDto;
import com.springboot.boot.modules.admin.dto.Auth.ExamStudyFinishDto;
import com.springboot.boot.modules.admin.dto.AuthBaseDto;
import com.springboot.boot.modules.admin.dto.examination.SubmitSlimylationDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.service.OptionService;
import com.springboot.boot.modules.admin.service.QuestionService;
import com.springboot.boot.modules.admin.vo.ChengjiVo;
import com.springboot.boot.modules.admin.vo.auth.AuthAndUserVo;
import com.springboot.boot.modules.admin.vo.auth.AuthProcedureVo;
import com.springboot.boot.modules.admin.vo.auth.IfWhereVo;
import com.springboot.boot.modules.admin.vo.auth.StartStudyVo;
import com.springboot.boot.modules.admin.vo.curriculum.AuthClassVo;
import com.springboot.boot.modules.admin.vo.examination.SubimtExamVo;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
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
    @Resource
    private MpExaminationRuleMapper examinationRuleMapper;
    @Resource
    private QuestionService questionService;
    @Resource
    private OptionService optionService;

    @Resource
    private MpAuthBusinessMapper mpAuthBusinessMapper;
    /**
     * 认证查看流程接口版本2.0
     *
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
        if (mpAuths.size() <= 0) {
            throw new BusinessException("认证信息为空");
        }
        //通过认证id分类id查询出认证的相关课程
        List<MpCurriculum> mpCurriculumList = mpCurriculumList(mpAuths);
        List<AuthClassVo> authClassVos = new ArrayList<>();
        if (!mpCurriculumList.isEmpty()) {
            mpCurriculumList.forEach(e -> {
                AuthClassVo classVo = new AuthClassVo();
                classVo.setClassFormat(e.getClassFormat());
                classVo.setCurriculumName(e.getCurriculumName());
                classVo.setId(e.getId());
                //根据课程id和用户id查看是否完成了学习
                List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(e.getId(), userId, mpAuths.get(0).getId());
                if (mpUserAuthClasses.size() > 0) {
                    classVo.setStatus(CommonEnum.FINISH.getCode());
                } else {
                    classVo.setStatus(CommonEnum.NOT_FINISH.getCode());
                }
                authClassVos.add(classVo);
            });
        }
        authClassVo.setClassVoList(authClassVos);
        //考试次数
        //查看该认证下的试卷次数
        List<MpExamination> mpExaminations = mpExaminations(mpAuths);
        if (mpExaminations.size() <= 0) {
            throw new BusinessException("试卷不存在");
        }
        authClassVo.setExamCount(mpExaminations.get(0).getFrequencyCount());
        authClassVo.setExamId(mpExaminations.get(0).getId());

        //是否条件判断
        IfWhereVo ifWhereVo = ifWhere(authId, userId, mpAuths);
        BeanCopy.copy(ifWhereVo, authClassVo);
        return ApiResult.success(authClassVo);
    }

    /**
     * 立即预约
     *
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
        if (mpAuthUserSignUps.size() > 0) {
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
        if (i <= CommonEnum.ADD_ERROR.getCode()) {
            throw new BusinessException("报名新增失败");
        }
        return ApiResult.success();
    }

    /**
     * 开始学习详情页面
     *
     * @param authId
     * @param userId
     * @return
     */
    @Override
    public ApiResult startStudy(Long authId, Long userId) {
        StartStudyVo startStudyVo = new StartStudyVo();
        startStudyVo.setId(authId);
        startStudyVo.setUserId(userId);
        //查看认证信息
        MpAuthExample authExample = new MpAuthExample();
        MpAuthExample.Criteria ac = authExample.createCriteria();
        ac.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        ac.andIdEqualTo(authId);
        List<MpAuth> mpAuths = authMapper.selectByExample(authExample);
        if (mpAuths.size() <= 0) {
            throw new BusinessException("认证信息为空");
        }
        startStudyVo.setName(mpAuths.get(0).getName());
        //通过认证id分类id查询出认证的相关课程
        List<MpCurriculum> mpCurriculumList = mpCurriculumList(mpAuths);
        List<AuthClassVo> authClassVos = new ArrayList<>();
        if (!mpCurriculumList.isEmpty()) {
            mpCurriculumList.forEach(e -> {
                AuthClassVo classVo = new AuthClassVo();
                classVo.setClassFormat(e.getClassFormat());
                classVo.setCurriculumName(e.getCurriculumName());
                classVo.setId(e.getId());
                //根据课程id和用户id查看是否完成了学习
                List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(e.getId(), userId, mpAuths.get(0).getId());
                if (mpUserAuthClasses.size() > 0) {
                    classVo.setStatus(CommonEnum.FINISH.getCode());
                } else {
                    classVo.setStatus(CommonEnum.NOT_FINISH.getCode());
                }
                authClassVos.add(classVo);
            });
        }
        startStudyVo.setClassVoList(authClassVos);
        //已学习百分点
        //总课程数量
        int size = mpCurriculumList.size();
        BigDecimal zngSize = new BigDecimal(size);
        List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(null, userId, mpAuths.get(0).getId());
        //用户学习的课程数
        BigDecimal xueSize = new BigDecimal(mpUserAuthClasses.size());
        log.info("学习百分点：" + xueSize.divide(zngSize, 2, BigDecimal.ROUND_HALF_DOWN));
        BigDecimal divide = xueSize.divide(zngSize, 2, BigDecimal.ROUND_HALF_DOWN);
        startStudyVo.setStduyCount(divide);
        //是否全部完成
        //用户完成的课程数据
        List<Long> userIds = mpUserAuthClasses.stream().map(MpUserAuthClass::getCurriculumId).collect(Collectors.toList());
        //认证列表的课程
        List<Long> ids = mpCurriculumList.stream().map(MpCurriculum::getId).collect(Collectors.toList());
        boolean result = userIds.containsAll(ids) && ids.containsAll(userIds);
        if (result) {
            startStudyVo.setFinishType(CommonEnum.FINISH.getCode());
        } else {
            startStudyVo.setFinishType(CommonEnum.NOT_FINISH.getCode());
        }
        return ApiResult.success(startStudyVo);
    }

    @Override
    public ApiResult classStudyFinish(ClassStudyFinishDto classStudyFinishDto) {
        //查看是否已经完成了学习
        List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(classStudyFinishDto.getClassId(), classStudyFinishDto.getUserId(),
                classStudyFinishDto.getAuthId());
        if (!CollectionUtils.isEmpty(mpUserAuthClasses)) {
            throw new BusinessException("已经完成了该课程的学习");
        }
        MpUserAuthClass mpUserAuthClass = new MpUserAuthClass();
        mpUserAuthClass.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
        mpUserAuthClass.setAuthId(classStudyFinishDto.getAuthId());
        mpUserAuthClass.setCreateTime(new Date());
        mpUserAuthClass.setCreateUser(classStudyFinishDto.getUserId());
        mpUserAuthClass.setCurriculumId(classStudyFinishDto.getClassId());
        mpUserAuthClass.setDeleFlag(CommonEnum.USED.getCode());
        mpUserAuthClass.setStatus(CommonEnum.FINISH.getCode());
        mpUserAuthClass.setUserId(classStudyFinishDto.getUserId());
        int i = mpUserAuthClassMapper.insertSelective(mpUserAuthClass);
        if (i <= CommonEnum.ADD_ERROR.getCode()) {
            throw new BusinessException("错误！");
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult startExam(ExamStudyFinishDto examStudyFinishDto) {

        if (examStudyFinishDto.getQuaestVos().isEmpty()) {
            throw new BusinessException("没有进行答题");
        }
        //当是考试的时候判断考试次数
        MpExamination examination = examinationMapper.selectByPrimaryKey(examStudyFinishDto.getExamId());
        //查看考试次数
        //查看用户考试信息
        MpUserAuthExamExample mpUserAuthExamExample = new MpUserAuthExamExample();
        MpUserAuthExamExample.Criteria criteria = mpUserAuthExamExample.createCriteria();
        criteria.andUserIdEqualTo(examStudyFinishDto.getUserId());
        criteria.andAuthIdEqualTo(examStudyFinishDto.getAuthId());
        criteria.andExamIdEqualTo(examStudyFinishDto.getExamId());
        List<MpUserAuthExam> mpUserAuthExams = userAuthExamMapper.selectByExample(mpUserAuthExamExample);

        if (mpUserAuthExams.size() >= examination.getFrequencyCount().intValue()) {
            return ApiResult.error(500, "考试次数以上限无法参加考试");
        }
        //计算考试分数
        long id = SnowFlakeUtils.getFlowIdInstance().nextId();
        //成绩-------------------------
        ChengjiVo chengji = chengji(examStudyFinishDto);
        MpUserAuthExam mpUserAuthExam = new MpUserAuthExam();
        mpUserAuthExam.setAuthId(examStudyFinishDto.getAuthId());
        mpUserAuthExam.setCreateTime(new Date());
        mpUserAuthExam.setCreateUser(examStudyFinishDto.getUserId());
        mpUserAuthExam.setDeleFlag(CommonEnum.USED.getCode());
        mpUserAuthExam.setExamAchievement(chengji.getSum());
        mpUserAuthExam.setId(id);
        mpUserAuthExam.setIfWhether(chengji.getIfWhere());
        mpUserAuthExam.setUserId(examStudyFinishDto.getUserId());
        int i = userAuthExamMapper.insertSelective(mpUserAuthExam);
        log.info("----------------------------------"+i);
        //将考试信息存入到认证考试表中
        SubimtExamVo vo = new SubimtExamVo();
        vo.setExamAchievement(chengji.getSum());
        vo.setJudgeNum(chengji.getJud());
        vo.setMultipleChoiceNum(chengji.getMultple());
        vo.setSingleChoiceNum(chengji.getSigele());
        return ApiResult.success(vo);
    }

    @Override
    public List<MpAuthUserSignUp> searchSignUp(Long authId,Long userId) {
        MpAuthUserSignUpExample example = new MpAuthUserSignUpExample();
        MpAuthUserSignUpExample.Criteria singUpCriteria = example.createCriteria();
        singUpCriteria.andUserIdEqualTo(userId);
        singUpCriteria.andAuthIdEqualTo(authId);
        singUpCriteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAuthUserSignUp> mpAuthUserSignUps = authUserSignUpMapper.selectByExample(example);
        return mpAuthUserSignUps;
    }

    @Override
    public Integer ifWhere(Long authId, Long userId) {
        //查看认证信息
        MpAuthExample authExample = new MpAuthExample();
        MpAuthExample.Criteria ac = authExample.createCriteria();
        ac.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        ac.andIdEqualTo(authId);
        List<MpAuth> mpAuths = authMapper.selectByExample(authExample);
        if (mpAuths.size() <= 0) {
            throw new BusinessException("认证信息为空");
        }
        //用户完成的课程数据
        List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(null, userId, authId);
        List<Long> userIds = mpUserAuthClasses.stream().map(MpUserAuthClass::getCurriculumId).collect(Collectors.toList());
        //认证列表的课程
        List<MpCurriculum> mpCurriculumList = mpCurriculumList(mpAuths);
        List<Long>ids = mpCurriculumList.stream().map(MpCurriculum::getId).collect(Collectors.toList());
        boolean result = userIds.containsAll(ids) && ids.containsAll(userIds);
        int studyResult = (result)?CommonEnum.NO.getCode():CommonEnum.YES.getCode();
        return studyResult;
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        List<List<String>> lists = groupList(list, 2);
//        System.out.println("list:" + list.toString());
//        System.out.println(lists);
//    }


    /**
     * 认证轮播
     * @return
     */
    @Override
    public ApiResult authBanner() {
        //查看认证轮播相关信息
        List<AuthAndUserVo> userVo = mpAuthBusinessMapper.authBanner();
        if (CollectionUtils.isEmpty(userVo)){
            return ApiResult.success(userVo);
        }
        List<List<AuthAndUserVo>> lists = groupList(userVo, 3);
        return ApiResult.success(lists);
    }
    /**
     * 集合拆分
     *
     * @param list     原集合
     * @param pageSize ⼦集合长度
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> groupList(List<T> list, int pageSize) {
        List<List<T>> listGroup = new ArrayList<List<T>>();
        int listSize = list.size();
        for (int i = 0; i < listSize; i += pageSize) {
            if (i + pageSize > listSize) {
                pageSize = listSize - i;
            }
            List<T> newList = list.subList(i, i + pageSize);
            listGroup.add(newList);
        }
        return listGroup;
    }

    /**
     * 计算试卷分数
     */


    public ChengjiVo chengji(ExamStudyFinishDto dto) {
        ChengjiVo chengjiVo = new ChengjiVo();
        //通过试卷id获取试卷相关信息
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andIdEqualTo(dto.getExamId());
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        MpExamination examination = mpExaminations.get(0);
        //获取试卷分数
        Integer paper = examination.getPaper();
        //获取提醒的相关分数
        MpExaminationRuleExample ruleExample = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria rulec = ruleExample.createCriteria();
        rulec.andExamIdEqualTo(dto.getExamId());
        rulec.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExaminationRule> mpExaminationRules = examinationRuleMapper.selectByExample(ruleExample);
        //分别获取对象题目的分数
        List<MpExaminationRule> singles = mpExaminationRules.stream().filter(a -> a.getSubjectName().equals(TypeEnum.SINGLE.getCode())).collect(Collectors.toList());
        List<MpExaminationRule> mult = mpExaminationRules.stream().filter(a -> a.getSubjectName().equals(TypeEnum.MULTIPLE.getCode())).collect(Collectors.toList());
        List<MpExaminationRule> judge = mpExaminationRules.stream().filter(a -> a.getSubjectName().equals(TypeEnum.JUDGE.getCode())).collect(Collectors.toList());
        //开始计算错题数量
        //获取所有选项的id和题库的id
        List<SubmitSlimylationDto.QuaestVo> quaestVos = dto.getQuaestVos();
        AtomicReference<Integer> singlesCount = new AtomicReference<>(0);
        AtomicReference<Integer> multCount = new AtomicReference<>(0);
        AtomicReference<Integer> judgeCount = new AtomicReference<>(0);
        //------------------------------正确的数量-------------------------
        AtomicReference<Integer> rSsinglesCount = new AtomicReference<>(0);
        AtomicReference<Integer> rMultCount = new AtomicReference<>(0);
        AtomicReference<Integer> rJudgeCount = new AtomicReference<>(0);
        for (SubmitSlimylationDto.QuaestVo quaestVo : quaestVos) {
            QuestionSearchVo vo = questionService.searchById(quaestVo.getId());
            Integer type = vo.getType();
            //通过前端传过来的选项id集合查询出选项
            List<MpOption> mpOptions = optionService.selectByIds(quaestVo.getOptionId());
            //提取所有的选项（用户选的）
            List<String> ops = mpOptions.stream().map(MpOption::getOpt).collect(Collectors.toList());
            //获取本题的答案和选项名称
            String rightAnswer = vo.getRightAnswer();
            //将答案转换成list集合
            List<String> rightAnswers = Arrays.asList(rightAnswer.toUpperCase().split(","));

            //处理大小写问题
            List<String> trueResult = new ArrayList<>();
            List<String> opsResult = new ArrayList<>();
            rightAnswers.forEach(e -> {
                String s = e.toUpperCase();
                trueResult.add(s);
            });
            ops.forEach(e -> {
                String s = e.toUpperCase();
                opsResult.add(s);
            });
            boolean result = trueResult.containsAll(opsResult) && opsResult.containsAll(trueResult)
                    && trueResult.size() == opsResult.size();
            if (!result) {
                switch (type) {
                    //单选
                    case 1:
                        //开始判断
                        singlesCount.getAndSet(singlesCount.get() + 1);
                        break;
                    case 2:
                        //开始判断
                        multCount.getAndSet(multCount.get() + 1);
                        break;
                    default:
                        judgeCount.getAndSet(judgeCount.get() + 1);
                        break;
                }
            }
            //-----------------------------------------正确的数量
            if (result) {
                switch (type) {
                    //单选
                    case 1:
                        //开始判断
                        singlesCount.getAndSet(rSsinglesCount.get() + 1);
                        break;
                    case 2:
                        //开始判断
                        multCount.getAndSet(rMultCount.get() + 1);
                        break;
                    default:
                        judgeCount.getAndSet(rJudgeCount.get() + 1);
                        break;
                }
            }

        }
        //计算中分
        MpExaminationRule singleExaminationRule = singles.get(0);
        Integer sfraction = singleExaminationRule.getFraction();
        MpExaminationRule multEx = mult.get(0);
        Integer mfraction = multEx.getFraction();
        MpExaminationRule examinationRule = judge.get(0);
        Integer jfraction = examinationRule.getFraction();
        Integer sigele = sfraction * singlesCount.get();
        Integer multple = mfraction * multCount.get();
        Integer jud = jfraction * judgeCount.get();
        Integer sum = paper - (sigele + multple + jud);
        chengjiVo.setSum(sum);
        chengjiVo.setSigele(sfraction * rSsinglesCount.get());
        chengjiVo.setMultple(mfraction * rMultCount.get());
        chengjiVo.setJud(jfraction * rJudgeCount.get());
        if (examination.getPassingMark() <= sum) {
            chengjiVo.setIfWhere(CommonEnum.IF_WHERE.getCode());
        } else {
            chengjiVo.setIfWhere(CommonEnum.NOT_IF_WHERE.getCode());
        }
        return chengjiVo;
    }
    /**
     * 是否条件判断
     * @param
     * @param authId 认证id、
     * @param userId 用户id
     */
    public IfWhereVo ifWhere(Long authId, Long userId,List<MpAuth> mpAuths){
        IfWhereVo ifWhereVo = new IfWhereVo();
        // 一开始  为初始状态--------------------------
        ifWhereVo.setFinishType(0);
        //判断是否报名预约================================================
        MpAuthUserSignUpExample example = new MpAuthUserSignUpExample();
        MpAuthUserSignUpExample.Criteria singUpCriteria = example.createCriteria();
        singUpCriteria.andUserIdEqualTo(userId);
        singUpCriteria.andAuthIdEqualTo(authId);
        singUpCriteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAuthUserSignUp> mpAuthUserSignUps = authUserSignUpMapper.selectByExample(example);
        int singUpResult = (mpAuthUserSignUps.size()>0)?CommonEnum.NO.getCode():CommonEnum.YES.getCode();
        if (singUpResult ==CommonEnum.NO.getCode()){
            ifWhereVo.setFinishType(1);
        }
        //判断是否完成了所有的课程学习=====================================================================
        //用户完成的课程数据
        List<MpUserAuthClass> mpUserAuthClasses = mpUserAuthClass(null, userId, authId);
        List<Long> userIds = mpUserAuthClasses.stream().map(MpUserAuthClass::getCurriculumId).collect(Collectors.toList());
        //认证列表的课程
        List<MpCurriculum> mpCurriculumList = mpCurriculumList(mpAuths);
        List<Long>ids = mpCurriculumList.stream().map(MpCurriculum::getId).collect(Collectors.toList());
        boolean result = userIds.containsAll(ids) && ids.containsAll(userIds);
        int studyResult = (result)?CommonEnum.NO.getCode():CommonEnum.YES.getCode();
        if (studyResult ==CommonEnum.NO.getCode()){
            ifWhereVo.setFinishType(2);
        }
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

        if (userCommonList.size()>0){
            ifWhereVo.setFinishType(3);
        }else{
            if (mpUserAuthExams.size()>=frequencyCount.intValue()){
                throw new BusinessException("次数已上线");
            }
        }
        //是否领取证书
        MpAuthCertificaseExample certificaseExample = new MpAuthCertificaseExample();
        MpAuthCertificaseExample.Criteria criteria1 = certificaseExample.createCriteria();
        criteria1.andAuthIdEqualTo(authId);
        criteria1.andUserIdEqualTo(userId);
        List<MpAuthCertificase> mpAuthCertificases = certificaseMapper.selectByExample(certificaseExample);
        if (mpAuthCertificases.size()>0){
            ifWhereVo.setFinishType(4);
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
