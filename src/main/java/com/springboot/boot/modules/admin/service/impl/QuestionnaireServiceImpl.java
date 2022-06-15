package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.ExaTypEnum;
import com.springboot.boot.common.enums.RangeTypeEnum;
import com.springboot.boot.common.enums.UpTypeEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.examination.SubmitSlimylationDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllPageDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.QuestionnaireService;
import com.springboot.boot.modules.admin.vo.question.*;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.modules.admin.vo.test.MpOptionVo;
import com.springboot.boot.modules.admin.vo.test.MpQuestionBankVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.DataUtil;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName AppClassifyServiceImpl
 * @Description TODO 前端分页业务
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:15
 * @Version 1.0
 **/
@Service
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Resource
    private MpQuestionnaireMapper mpQuestionnaireMapper;
    //    @Resource
//    private MpQuestionnaireOptionVoMapper mpQuestionnaireOptionVoMapper;
    @Resource
    private MpOptionMapper mpOptionMapper;
    @Resource
    private MpQuestionBankMapper mpQuestionBankMapper;
    @Resource
    private MpUserExamMapper mpUserExamMapper;

    @Resource
    private MpExaminationMapper mpExaminationMapper;


    @Override
    public List<MpExaminationVo> mpQuestionnaireOptionVoList(CommonAllDto dto) {
        MpExaminationExample example = new MpExaminationExample();
        Long id = dto.getId();
        if (null == id) {
            example.createCriteria()
                    .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andUpTypeEqualTo(UpTypeEnum.up.getCode())
                    .andExaminationTypeEqualTo(ExaTypEnum.question.getCode())
                    .andRangeTypeEqualTo(RangeTypeEnum.dataTest.getCode());
        }
        if (null != id) {
            example.createCriteria()
                    .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andUpTypeEqualTo(UpTypeEnum.up.getCode())
                    .andExaminationTypeEqualTo(ExaTypEnum.question.getCode())
                    .andRangeTypeEqualTo(RangeTypeEnum.dataTest.getCode()).andIdEqualTo(id);
        }
        List<MpExamination>
                mpExaminationList
                = mpExaminationMapper.selectByExample(example);
        List<MpExaminationVo> mpExaminationVoVoList
                = new ArrayList<>();
        if (null != mpExaminationList && mpExaminationList.size() > 0) {
            for (MpExamination mpExamination : mpExaminationList) {
                MpExaminationVo mpExaminationVo = new MpExaminationVo();
                BeanCopy.copy(mpExamination, mpExaminationVo);
                mpExaminationVoVoList.add(mpExaminationVo);
            }
            for (MpExaminationVo mpExaminationVo : mpExaminationVoVoList) {
                MpQuestionBankExample bankExample = new MpQuestionBankExample();
                bankExample
                        .createCriteria()
                        .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                        .andExaminationIdEqualTo(mpExaminationVo.getId());
                List<MpQuestionBank> mpQuestionBankList = mpQuestionBankMapper.selectByExample(bankExample);
                List<MpQuestionBankVo> mpQuestionBankVoList = new ArrayList<>();
                if (null != mpQuestionBankList && mpQuestionBankList.size() > 0) {
                    for (MpQuestionBank mpQuestionBank : mpQuestionBankList) {
                        MpQuestionBankVo mpQuestionBankVo = new MpQuestionBankVo();
                        BeanCopy.copy(mpQuestionBank, mpQuestionBankVo);
                        mpQuestionBankVoList.add(mpQuestionBankVo);
                        //通过题干查找选项
                        MpOptionExample mpOptionExample = new MpOptionExample();
                        mpOptionExample.createCriteria()
                                .andQuestionIdEqualTo(mpQuestionBankVo.getId())
                                .andDeleFlagEqualTo(CommonEnum.USED.getCode());
                        List<MpOption> mpOptionList = mpOptionMapper.selectByExample(mpOptionExample);
                        List<MpOptionVo> mpOptionVoList = new ArrayList<>();
                        HashMap statMap = new HashMap();
                        if (null != mpOptionList && mpOptionList.size() > 0) {
                            Integer total = 0;
                            for (MpOption mpOption : mpOptionList) {
                                MpOptionVo mpOptionVo = new MpOptionVo();
                                BeanCopy.copy(mpOption, mpOptionVo);
                                mpOptionVoList.add(mpOptionVo);
                                Long optionId = mpOptionVo.getId();
                                String option = mpOptionVo.getOpt();
                                MpUserExamExample exmple = new MpUserExamExample();
                                exmple.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andOptionIdEqualTo(optionId.toString());
                                List<MpUserExam> mpUserExamList = mpUserExamMapper.selectByExample(exmple);
                                if (option.contains("A")) {
                                    if (null != mpUserExamList) {
                                        statMap.put("A", mpUserExamList.size());
                                    }
                                }
                                if (option.contains("B")) {
                                    if (null != mpUserExamList) {
                                        statMap.put("B", mpUserExamList.size());
                                    }
                                }
                                if (option.contains("C")) {

                                    if (null != mpUserExamList) {
                                        statMap.put("C", mpUserExamList.size());
                                    }
                                }
                                if (option.contains("D")) {
                                    if (null != mpUserExamList) {
                                        statMap.put("D", mpUserExamList.size());
                                    }
                                }
                                if (option.contains("E")) {
                                    if (null != mpUserExamList) {
                                        statMap.put("E", mpUserExamList.size());
                                    }
                                }
                                if (option.contains("F")) {
                                    if (null != mpUserExamList) {
                                        statMap.put("F", mpUserExamList.size());
                                    }
                                }
                                if (option.contains("G")) {
                                    if (null != mpUserExamList) {
                                        statMap.put("G", mpUserExamList.size());
                                    }
                                }
                                total += mpUserExamList.size();
                            }
                            mpQuestionBankVo.setStatisticsMap(statMap);
                            mpQuestionBankVo.setMpOptionVoList(mpOptionVoList);
                            mpQuestionBankVo.setTotal(total);
                        }
                    }
                }
                mpExaminationVo.setMpQuestionBankVoList(mpQuestionBankVoList);
            }
            return mpExaminationVoVoList;

        } else {
            return null;
        }
    }

    @Override
    public List<MpExaminationVo> questionnaireListWithPage(CommonAllPageDto dto) {

        MpExaminationExample example = new MpExaminationExample();
        Long id = dto.getId();
        if (null == id) {
            example.createCriteria()
                    .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andUpTypeEqualTo(UpTypeEnum.up.getCode())
                    .andExaminationTypeEqualTo(ExaTypEnum.question.getCode())
                    .andRangeTypeEqualTo(RangeTypeEnum.dataTest.getCode());
        }
        if (null != id) {
            example.createCriteria()
                    .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andUpTypeEqualTo(UpTypeEnum.up.getCode())
                    .andExaminationTypeEqualTo(ExaTypEnum.question.getCode())
                    .andRangeTypeEqualTo(RangeTypeEnum.dataTest.getCode()).andIdEqualTo(id);
        }
        List<MpExamination>
                mpExaminationList
                = mpExaminationMapper.selectByExample(example);
        List<MpExaminationVo> mpExaminationVoVoList
                = new ArrayList<>();


        if (null != mpExaminationList && mpExaminationList.size() > 0) {
            for (MpExamination mpExamination : mpExaminationList) {
                MpExaminationVo mpExaminationVo = new MpExaminationVo();
                BeanCopy.copy(mpExamination, mpExaminationVo);
                mpExaminationVoVoList.add(mpExaminationVo);
            }
            for (MpExaminationVo mpExaminationVo : mpExaminationVoVoList) {
                MpQuestionBankExample bankExample = new MpQuestionBankExample();
                bankExample
                        .createCriteria()
                        .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                        .andExaminationIdEqualTo(mpExaminationVo.getId());
                List<MpQuestionBank> mpQuestionBankList = mpQuestionBankMapper.selectByExample(bankExample);
                List<MpQuestionBankVo> mpQuestionBankVoList = new ArrayList<>();
                if (null != mpQuestionBankList && mpQuestionBankList.size() > 0) {
                    for (MpQuestionBank mpQuestionBank : mpQuestionBankList) {
                        MpQuestionBankVo mpQuestionBankVo = new MpQuestionBankVo();
                        BeanCopy.copy(mpQuestionBank, mpQuestionBankVo);
                        mpQuestionBankVoList.add(mpQuestionBankVo);
                        //通过题干查找选项
                        MpOptionExample mpOptionExample = new MpOptionExample();
                        mpOptionExample.createCriteria()
                                .andQuestionIdEqualTo(mpQuestionBankVo.getId())
                                .andDeleFlagEqualTo(CommonEnum.USED.getCode());
                        List<MpOption> mpOptionList = mpOptionMapper.selectByExample(mpOptionExample);
                        List<MpOptionVo> mpOptionVoList = new ArrayList<>();
                        HashMap statMap = new HashMap();

                    }
                    mpExaminationVo.setMpQuestionBankVoList(mpQuestionBankVoList);

                }
                return mpExaminationVoVoList;

            }

        }
        return null;
    }


    /**
     * 问卷类
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult submitSimulation(SubmitSlimylationDto dto) {
        if (dto.getQuaestVos().isEmpty()) {
            throw new BusinessException("没有进行答题");
        }

        int result = 0;
        //获取前端传过来的题库信息和 选项信息
        List<SubmitSlimylationDto.QuaestVo> quaestVos = dto.getQuaestVos();
        //将考试信息存入到考试表中
        for (SubmitSlimylationDto.QuaestVo vo : quaestVos) {
            MpQuestionnaire mpQuestionnaire = new MpQuestionnaire();
            mpQuestionnaire.setQuestjuanId(dto.getExamId());
            mpQuestionnaire.setCreateTime(new Date());
            mpQuestionnaire.setDeleFlag(CommonEnum.USED.getCode());
            mpQuestionnaire.setQuestionId(vo.getId());
            List<Long> optionIds = vo.getOptionId();
            for (Long optId : optionIds) {
                mpQuestionnaire.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
                mpQuestionnaire.setOptionId(optId);
                result = mpQuestionnaireMapper.insertSelective(mpQuestionnaire);
            }
            if (result <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("试卷新增失败");
            }

        }

        return ApiResult.success("问卷提交成功");

    }


    /**
     * 问卷类统计
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult questionnaireStatistics(CommonAllDto dto) {
        Long id = dto.getId();
        MpQuestionBankExample example = new MpQuestionBankExample();
        example.createCriteria()
                .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                .andExaminationIdEqualTo(id);
        List<MpQuestionBank> mpQuestionBankList =
                mpQuestionBankMapper.selectByExample(example);
        List<AppQuestionCountVo> appQuestionVoList = new ArrayList<>();
        for (MpQuestionBank mpQuestionBank : mpQuestionBankList) {
            AppQuestionCountVo vo = new AppQuestionCountVo();
            BeanCopy.copy(mpQuestionBank, vo);
            Long questionId = vo.getId();
            appQuestionVoList.add(vo);
            MpQuestionnaireExample example1 = new MpQuestionnaireExample();
            example1.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andQuestionIdEqualTo(vo.getId());
            long totalCount =   mpQuestionnaireMapper.countByExample(example1);
            vo.setQuestionChoicedCountTotal(totalCount);
            MpOptionExample exa = new MpOptionExample();
            exa.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode())
                    .andQuestionIdEqualTo(questionId);
            List<MpOption> mpOptionList = mpOptionMapper.selectByExample(exa);
            List<AppOptionCountVo> appOptionVoList  = new ArrayList<>();
            for(MpOption mpOption:    mpOptionList){
                AppOptionCountVo appOptionVo = new AppOptionCountVo();
                BeanCopy.copy(mpOption, appOptionVo);
                MpQuestionnaireExample example2 = new MpQuestionnaireExample();
                example2.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode())
                        .andOptionIdEqualTo(appOptionVo.getId());
                long count =   mpQuestionnaireMapper.countByExample(example2);
                appOptionVo.setChoiceCount(count);
                appOptionVo.setPerCent(DataUtil.myPercent(count,totalCount));
                appOptionVoList.add(appOptionVo);

            }
            vo.setAppOptionVos(appOptionVoList);

        }
        return ApiResult.success(appQuestionVoList)  ;
    }


}


