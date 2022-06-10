package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.ExaTypEnum;
import com.springboot.boot.common.enums.RangeTypeEnum;
import com.springboot.boot.common.enums.UpTypeEnum;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllPageDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.QuestionnaireService;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.modules.admin.vo.test.MpOptionVo;
import com.springboot.boot.modules.admin.vo.test.MpQuestionBankVo;
import com.springboot.boot.utils.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

}

//    @Override
//    public List<MpQuestionnaireVo> questionnaireList() {
//        MpQuestionnaireExample example = new MpQuestionnaireExample();
//        example.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());
//        List<MpQuestionnaire>
//                mpQuestionnaireList
//                = mpQuestionnaireMapper.selectByExample(example);
//        List<MpQuestionnaireVo> mpQuestionnaireVoList
//                = new ArrayList<>();
//        MpQuestionnaireVo mpQuestionnaireVo = new MpQuestionnaireVo();
//        for (MpQuestionnaire mpQuestionnaire : mpQuestionnaireList) {
//            BeanCopy.copy(mpQuestionnaire, mpQuestionnaireVo);
//            mpQuestionnaireVoList.add(mpQuestionnaireVo);
//        }
//        return mpQuestionnaireVoList;
//    }


//
//    public List<MpQuestionnaireOptionVo> questionnaireDetail(CommonAllDto dto) {
//        List<MpQuestionnaireOptionVo> retList = new ArrayList<>();
//        List<MpQuestionnaireOptionVo> mpQuestionnaireOptionVoList = mpQuestionnaireOptionVoMapper.questionnaireDetail(dto.getId());
//        if (null != mpQuestionnaireOptionVoList && mpQuestionnaireOptionVoList.size() > 0) {
//            MpQuestionnaireOptionVo mpQuestionnaireOptionVo = mpQuestionnaireOptionVoList.get(0);
//            MpOptionExample example1 = new MpOptionExample();
//            example1.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andIdEqualTo(mpQuestionnaireOptionVo.getId());
//            List<MpOption> mpOptionList = mpOptionMapper.selectByExample(example1);
//            List<MpOptionVo> mpOptionList1 = new ArrayList<>();
//            MpOptionVo mpOptionVo = new MpOptionVo();
//            for (MpOption mpOption : mpOptionList) {
//                BeanCopy.copy(mpOption, mpOptionVo);
//                mpOptionList1.add(mpOptionVo);
//            }
//            mpQuestionnaireOptionVo.setMpOptionVoList(mpOptionList1);
//            retList.add(mpQuestionnaireOptionVo);
//            return retList;
//        } else {
//            return null;
//        }
//    }


