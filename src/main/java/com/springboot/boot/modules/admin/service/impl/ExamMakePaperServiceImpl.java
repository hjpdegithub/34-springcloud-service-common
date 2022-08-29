package com.springboot.boot.modules.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.exanmake.ExamMakeSearchDto;
import com.springboot.boot.modules.admin.dto.exanmake.MakerPaperButtonDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.ExamMakePaperService;
import com.springboot.boot.modules.admin.vo.makeexam.ExamMakeSearchVo;
import com.springboot.boot.modules.admin.vo.makeexam.MakePaperVo;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ExamMakePaperServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/8/8 0008 16:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class ExamMakePaperServiceImpl implements ExamMakePaperService {
    @Resource
    private MakeExamMapper makeExamMapper;
    @Resource
    private MpMakePaperMapper mpMakePaperMapper;
    @Resource
    private MpQuestionBankMapper mpQuestionBankMapper;
    @Resource
    private MpUserExamMapper mpUserExamMapper;
    @Resource
    private MpExaminationRuleMapper mpExaminationRuleMapper;
    @Resource
    private MpExamAchievementMapper mpExamAchievementMapper;
    @Resource
    private MpExaminationMapper mpExaminationMapper;
    @Resource
    private MpUserAuthExamMapper mpUserAuthExamMapper;
    @Resource
    private AuthServiceManageImpl authServiceManage;


    @Override
    public ApiResult searchExamMake(ExamMakeSearchDto makeSearchDto) {
        if (makeSearchDto.getPaging()) {
            PageHelper.startPage(makeSearchDto.getPageNo(), makeSearchDto.getPageSize());
        }
        //查询判卷信息
        List<ExamMakeSearchVo> makeSearchVos = makeExamMapper.searchExamMake(makeSearchDto);
        PageInfo<ExamMakeSearchVo> pageInfo = new PageInfo<>(makeSearchVos);
        return ApiResult.success(pageInfo);
    }

    /**
     * 判卷详情接口
     * @param id
     * @return
     */
    @Override
    public ApiResult makePaper(Long id) {

        MakePaperVo makePaperVo = new MakePaperVo();
        //通过id查询判卷相关信息
        MpMakePaper mpMakePaper = mpMakePaperMapper.selectByPrimaryKey(id);
        if (null == mpMakePaper){
            throw new BusinessException("没有找到相关判卷信息");
        }
        //set到报文里
        BeanCopy.copy(mpMakePaper,makePaperVo);
        //通过判卷信息找到对应的相关的用户的分析题
        MpUserExamExample examExample = new MpUserExamExample();
        MpUserExamExample.Criteria criteria1 = examExample.createCriteria();
        criteria1.andAchievementIdEqualTo(mpMakePaper.getAchievementId());
        criteria1.andExamIdEqualTo(mpMakePaper.getExamId());
        criteria1.andUserIdEqualTo(mpMakePaper.getUserId());
        List<MpUserExam> mpUserExams = mpUserExamMapper.selectByExample(examExample);
        List<MpUserExam> mpUserExamByType = mpUserExams.stream().filter(a -> a.getType()== 4).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(mpUserExamByType)){
            throw new BusinessException("没有找到该试卷的分析题");
        }
        //set 题库中相关分析题
        List<MakePaperVo.analysisClass> analysisClass = new ArrayList<>();

        mpUserExamByType.forEach(e->{
            MakePaperVo.analysisClass analysisClass1 =new MakePaperVo.analysisClass();
            //获取分析题答案和分析题名称
            MpQuestionBankExample example = new MpQuestionBankExample();
            MpQuestionBankExample.Criteria criteria = example.createCriteria();
            criteria.andExaminationIdEqualTo(e.getExamId());
            criteria.andIdEqualTo(e.getQuestionId());
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            List<MpQuestionBank> questionBanks = mpQuestionBankMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(questionBanks)){
                throw new BusinessException("没有找到该分析题");
            }
            analysisClass1.setId(questionBanks.get(0).getId());
            analysisClass1.setAnalysisAnswer(questionBanks.get(0).getAnalysisAnswer());
            analysisClass1.setName(questionBanks.get(0).getName());
            analysisClass1.setUserAnalysisAnswer(e.getAnalysisAnswer());
            analysisClass.add(analysisClass1);
        });
        makePaperVo.setAnalysisClass(analysisClass);
        MpExaminationRuleExample examinationRuleExample = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria criteria = examinationRuleExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andExamIdEqualTo(mpMakePaper.getExamId());
        criteria.andSubjectNameEqualTo("analysis");
        List<MpExaminationRule> mpExaminationRules = mpExaminationRuleMapper.selectByExample(examinationRuleExample);
        if (CollectionUtils.isEmpty(mpExaminationRules)){
            throw new BusinessException("试卷规则不能为空");
        }
        makePaperVo.setPoints(mpExaminationRules.get(0).getFraction());
        return ApiResult.success(makePaperVo);
    }

    /**
     * 判卷提交接口
     * @param makerPaperButtonDto
     * @return
     */
    @Transactional
    @Override
    public ApiResult makePaperButton(MakerPaperButtonDto makerPaperButtonDto) {
        if (null == makerPaperButtonDto.getId()){
            throw new BusinessException("参数id为空");
        }
        //第一步修改判卷相关信息
        MpMakePaper mpMakePaper = new MpMakePaper();
        BeanCopy.copy(makerPaperButtonDto,mpMakePaper);
        mpMakePaper.setStatusType(2);
        mpMakePaperMapper.updateByPrimaryKeySelective(mpMakePaper);
        //查看成绩是否合格
        MpExamination examination = mpExaminationMapper.selectByPrimaryKey(makerPaperButtonDto.getExamId());
        if (null == examination){
            throw new BusinessException("试卷信息为空");
        }
        //判断是认证还是考试
        if (makerPaperButtonDto.getExamType().intValue() == 1){
            //第二步修改考试成绩
            MpExamAchievement mpExamAchievement = new MpExamAchievement();
            if (makerPaperButtonDto.getCountGrade().intValue() >= examination.getPassingMark().intValue()){
                mpExamAchievement.setIfWhether(1);
            }else{
                mpExamAchievement.setIfWhether(2);
            }
            mpExamAchievement.setAnalysisGrade(makerPaperButtonDto.getAnalysisGrade());
            mpExamAchievement.setCountGrade(makerPaperButtonDto.getCountGrade());
            mpExamAchievement.setExamAchievement(makerPaperButtonDto.getCountGrade());
            mpExamAchievement.setShowType(1);
            mpExamAchievement.setId(makerPaperButtonDto.getAchievementId());
            mpExamAchievementMapper.updateByPrimaryKeySelective(mpExamAchievement);

        }
        if (makerPaperButtonDto.getExamType().intValue()==2){
            MpUserAuthExam mpUserAuthExam = new MpUserAuthExam();
            //第二修改认证表的成绩
            if (makerPaperButtonDto.getCountGrade().intValue() >= examination.getPassingMark().intValue()){
                mpUserAuthExam.setIfWhether(1);
            }else{
                mpUserAuthExam.setIfWhether(2);
            }
            mpUserAuthExam.setExamAchievement(makerPaperButtonDto.getCountGrade());
            mpUserAuthExam.setAnalysisGrade(makerPaperButtonDto.getAnalysisGrade());
            mpUserAuthExam.setId(makerPaperButtonDto.getAuthId());
            int i = mpUserAuthExamMapper.updateByPrimaryKeySelective(mpUserAuthExam);
            //判断是否可以领取证书
            //查看认证次数
            MpUserAuthExamExample mpUserAuthExamExample = new MpUserAuthExamExample();
            MpUserAuthExamExample.Criteria criteria = mpUserAuthExamExample.createCriteria();
            criteria.andUserIdEqualTo(makerPaperButtonDto.getUserId());
            criteria.andAuthIdEqualTo(makerPaperButtonDto.getAuthId());
            criteria.andExamIdEqualTo(makerPaperButtonDto.getExamId());
            List<MpUserAuthExam> mpUserAuthExams = mpUserAuthExamMapper.selectByExample(mpUserAuthExamExample);
            if (mpUserAuthExams.size()<=examination.getFrequencyCount()&& mpUserAuthExam.getIfWhether().intValue() == 1
            ){
                //领取
                authServiceManage.certificateGetAuto(makerPaperButtonDto);
            }
        }
        return ApiResult.success();

    }
}
