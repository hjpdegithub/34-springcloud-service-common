package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.modules.admin.dto.TestUserDto;
import com.springboot.boot.modules.admin.dto.test.MpExaminationDto;
import com.springboot.boot.modules.admin.dto.test.TestResultDto;

import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.MpExamAchievementMapper;
import com.springboot.boot.modules.admin.mapper.MpExamAchievementTwoMapper;
import com.springboot.boot.modules.admin.mapper.MpExaminationMapper;
import com.springboot.boot.modules.admin.service.TestResultService;
import com.springboot.boot.modules.admin.vo.test.MpExamAchievementMultiVo;
import com.springboot.boot.modules.admin.vo.test.MpExaminationVo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.modules.admin.vo.test.TestResultPageDto;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TestResultServiceImpl implements TestResultService {
    @Resource
    private MpExaminationMapper mpExaminationMapper;
    @Resource
    private MpExamAchievementTwoMapper mpExamAchievementTwoMapper;
    @Override
    public PageInfo  resultQuery(TestResultPageDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        //java 取当天日期开始时点
        Date startTime = dto.getStartTime();
        if (null != startTime) {
            Date dateStart = DateUtils.getDayStart(startTime);
            Date dateEnd = DateUtils.getNextDay(startTime);
            dto.setDateEnd(dateEnd);
            dto.setDateStart(dateStart);
        }
        //java 取当天时间的最后
        List<MpExamAchievementMultiVo>  mpExamAchievementMultiVoList  = mpExamAchievementTwoMapper.testResultQuery(dto);
        PageInfo<MpExamAchievementMultiVo> pageInfo = new PageInfo<>(mpExamAchievementMultiVoList);
        return pageInfo;
    }
    @Override
    public void export(HttpServletResponse response, TestResultDto dto) {
        //java 取当天日期开始时点
        Date startTime = dto.getStartTime();
        if (null != startTime) {
            Date dateStart = DateUtils.getDayStart(startTime);
            Date dateEnd = DateUtils.getNextDay(startTime);
            dto.setDateEnd(dateEnd);
            dto.setDateStart(dateStart);
        }
        //java 取当天时间的最后
        List<MpExamAchievementMultiVo> mpExamAchievementMultiVoList =
                mpExamAchievementTwoMapper.testResultNoPageQuery(dto);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try{
            String fileProductName = URLEncoder.encode("考试成绩导出数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileProductName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), MpExamAchievementMultiVo.class).sheet("考试成绩导出").doWrite(mpExamAchievementMultiVoList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<MpExaminationVo> mpExaminationList(MpExaminationDto dto) {
        String name = dto.getName();
        if (null == name || name.equals(""))
            return null;
        MpExaminationExample example = new MpExaminationExample();
        example.createCriteria().andNameLike(name);
        List<MpExamination> mpExaminationList = mpExaminationMapper.selectByExample(example);
        List<MpExaminationVo> mpExaminationVoList = new ArrayList<>();
        if (null != mpExaminationList && mpExaminationList.size() > 0) {
            for (MpExamination mpExamination : mpExaminationList) {
                MpExaminationVo mpExaminationVo = new MpExaminationVo();
                BeanCopy.copy(mpExamination, mpExaminationVo);
                mpExaminationVoList.add(mpExaminationVo);
            }
        }
        return mpExaminationVoList;
    }

    @Override
    public  List<MpUserAuthenticationVo> testUserList(@RequestBody TestUserDto dto) {
        return mpExamAchievementTwoMapper.testUserList(dto);
    }

}
