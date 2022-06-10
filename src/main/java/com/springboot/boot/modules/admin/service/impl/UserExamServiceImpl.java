package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.entity.MpUserExam;
import com.springboot.boot.modules.admin.entity.MpUserExamExample;
import com.springboot.boot.modules.admin.mapper.MpUserExamMapper;
import com.springboot.boot.modules.admin.service.UserExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName UserExamServiceImpl
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/11 0011 18:39
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserExamServiceImpl implements UserExamService {

    @Resource
    private MpUserExamMapper userExamMapper;
    @Override
    public int selectExamByCount(Long id, Long userId) {
        MpUserExamExample examExample = new MpUserExamExample();
        MpUserExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andExamIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpUserExam> mpUserExams = userExamMapper.selectByExample(examExample);
        if (!mpUserExams.isEmpty()){

//            //去重
//            List<MpUserExam> distinctClass = mpUserExams.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getAchievementId()))), ArrayList::new));
//            return distinctClass.size();
            //分组(通过成绩)
            Map<Long, List<MpUserExam>> groupBySex = mpUserExams.stream().collect(Collectors.groupingBy(MpUserExam::getAchievementId));
            //遍历分组
            List<Long> aIds= new ArrayList<>();
            for (Map.Entry<Long, List<MpUserExam>> entryUser : groupBySex.entrySet()) {
                List<MpUserExam> value = entryUser.getValue();
                List<MpUserExam> mpCurriculumList = value.stream().filter(a -> a.getTypeExam()== 2).collect(Collectors.toList());
                if (mpCurriculumList.size()>0){
                    aIds.add(entryUser.getKey());
                }
            }
            return aIds.size();
        }
        return mpUserExams.size();
    }
}
