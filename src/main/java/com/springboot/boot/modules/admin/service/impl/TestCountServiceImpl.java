package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.ExaTypEnum;
import com.springboot.boot.common.enums.RangeTypeEnum;
import com.springboot.boot.common.enums.UpTypeEnum;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.entity.MpExamination;
import com.springboot.boot.modules.admin.entity.MpExaminationExample;
import com.springboot.boot.modules.admin.entity.MpSignUp;
import com.springboot.boot.modules.admin.mapper.MpExaminationMapper;
import com.springboot.boot.modules.admin.mapper.TestCountsMapper;
import com.springboot.boot.modules.admin.service.SignUpService;
import com.springboot.boot.modules.admin.service.TestCountService;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.modules.admin.vo.test.TestCountVo;
import com.springboot.boot.modules.admin.vo.test.UsertestVo;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestCountServiceImpl implements TestCountService {

    @Resource
    private MpExaminationMapper mpExaminationMapper;
    @Resource
    private TestCountsMapper testCountsMapper;
    @Resource
    private SignUpService signUpService;

    @Override
    public TestCountVo testCounts() {
        TestCountVo testCountVo = new TestCountVo();
        //用户考试表
        MpExaminationExample example = new MpExaminationExample();
        example.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());
        //考试次数
        Long testTimesCount = testCountsMapper.testTimes();
        testCountVo.setTestTimesCount(testTimesCount);
        //考试人数
        Long usersCount = testCountsMapper.testUsersCount();
        if (usersCount == null) {
            testCountVo.setTestUsersCount(Long.valueOf("0"));
        } else {
            testCountVo.setTestUsersCount(usersCount);
        }
        //试卷数量统计
        Long testPaperCount = testCountsMapper.testPaperCount();
        testCountVo.setTestPaperCount(testPaperCount);
        //题目数量统计
        Long titleCount = testCountsMapper.titleCount();
        testCountVo.setTitleCount(titleCount);

        return testCountVo;
    }

    @Override
    public List<UsertestVo> myTestList(CommonAllDto dto) {
        Long userId = dto.getId();
        if (null != userId && !"".equals(userId)) {
            return testCountsMapper.myTestList(userId);
        } else {
            //
            userId = Long.valueOf("123456");
            return testCountsMapper.myTestList(userId);
        }
    }

    @Override
    public List<MpExaminationVo> questionList() {
        MpExaminationExample example = new MpExaminationExample();
        example.createCriteria()
                .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                .andUpTypeEqualTo(UpTypeEnum.up.getCode())
                .andExaminationTypeEqualTo(ExaTypEnum.question.getCode())
                .andRangeTypeEqualTo(RangeTypeEnum.dataTest.getCode());

        List<MpExaminationVo> mpExaminationVoList = new ArrayList<>();
        List<MpExamination> mpExaminationList = mpExaminationMapper.selectByExample(example);

        if (null != mpExaminationList && mpExaminationList.size() > 0) {
            for (MpExamination mpExamination : mpExaminationList) {
                MpExaminationVo mpExaminationVo = new MpExaminationVo();
                BeanCopy.copy(mpExamination, mpExaminationVo);
                mpExaminationVoList.add(mpExaminationVo);
            }
            return mpExaminationVoList;
        } else return null;

    }


    @Override
    public List<MpExaminationVo> testList() {
        MpExaminationExample example = new MpExaminationExample();
        example.createCriteria()
                .andDeleFlagEqualTo(CommonEnum.USED.getCode())
                .andUpTypeEqualTo(UpTypeEnum.up.getCode())
                .andExaminationTypeEqualTo(ExaTypEnum.test.getCode())
                .andRangeTypeEqualTo(RangeTypeEnum.dataTest.getCode());

        example.setOrderByClause("create_time desc");

        List<MpExaminationVo> mpExaminationVoList = new ArrayList<>();
        List<MpExamination> mpExaminationList = mpExaminationMapper.selectByExample(example);

        if (null != mpExaminationList && mpExaminationList.size() > 0) {
            for (MpExamination mpExamination : mpExaminationList) {
                MpExaminationVo mpExaminationVo = new MpExaminationVo();
                BeanCopy.copy(mpExamination, mpExaminationVo);
                //2.0判断考试转台
                Date date = new Date();
                if (null != mpExamination.getStartTime() && null != mpExamination.getEndTime()){
                    if (date.getTime()>=mpExamination.getStartTime().getTime() && date.getTime() <= mpExamination.getEndTime().getTime()){
                        mpExaminationVo.setExamTimeType(1);
                    }else if (date.getTime() <=mpExamination.getStartTime().getTime()){
                        mpExaminationVo.setExamTimeType(3);
                    }else{
                        mpExaminationVo.setExamTimeType(2);
                    }
                }
                //报名人数
                //查看报名人数
                List<MpSignUp> mpSignUps = signUpService.selectByIdAndUserId(mpExamination.getId(), null);
                mpExaminationVo.setSignUpCount(mpSignUps.size());
                mpExaminationVoList.add(mpExaminationVo);
            }
            return mpExaminationVoList;
        } else
            return null;


    }

}
