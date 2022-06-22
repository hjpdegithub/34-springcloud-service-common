package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.entity.MpFirstClassify;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.AppClassifyService;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.classify.app.ClassifyTypeShowStudyVo;
import com.springboot.boot.modules.admin.vo.classify.app.CurriculumMess;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName AppClassifyServiceImpl
 * @Description TODO 前端分页业务
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:15
 * @Version 1.0
 **/
@Service
@Slf4j
public class AppClassifyServiceImpl implements AppClassifyService {

    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private CurriculumService curriculumService;

    /**
     * 自助学历列表
     * @return
     */
    @Override
    public List<SearchStudyVo> searchStudy() {
        List<SearchStudyVo> list = new ArrayList<>();
        //查询一级分类信息
        List<MpFirstClassify> firstClassifies = classifyService.searchFristClassifyNoAuth();
        firstClassifies.forEach(e->{
            //查询二级分类信息
            List<MpSecondClassify> secondClassifies = classifyService.searchByClassifyId(e.getId());
            SearchStudyVo vo = new SearchStudyVo();
            vo.setId(e.getId());
            vo.setFirstClassifyDescr(e.getFirstClassifyDescr());
            vo.setFirstClassifyName(e.getFirstClassifyName());
            vo.setFirstClassifyType(e.getFirstClassifyType());
            vo.setTypeNum(secondClassifies.size());
           //获取二级分类下的所有课程
            List<Long> ids = secondClassifies.stream().map(MpSecondClassify::getId).collect(Collectors.toList());
            //判断该分类是否是定制分类
            List<MpCurriculum> mpCurricula = new ArrayList<>();
            if (e.getFirstClassifyType() == CommonEnum.CTYPE.getCode()){
                mpCurricula = curriculumService.searchByClassIdC(ids);

            }else{
                mpCurricula = curriculumService.searchByClassId(ids);
            }

            //获取总时长
            int sum = mpCurricula.stream().mapToInt(MpCurriculum::getClassHour).sum();
            vo.setCountNum(sum);
            list.add(vo);
        });

        return list;
    }

    /**
     * 定制课程展示接口
     * @param id
     * @return
     */
    @Override
    public ClassifyTypeShowStudyVo classifyShow(Long id) {
        ClassifyTypeShowStudyVo vo = new ClassifyTypeShowStudyVo();
        //查询一级分类信息
        MpFirstClassify mpFirstClassify = classifyService.searchFirstClassify(id);
        //赋值=====================================================
        vo.setFirstId(mpFirstClassify.getId());
        vo.setFirstClassifyName(mpFirstClassify.getFirstClassifyName());
        vo.setFirstClassifyDescr(mpFirstClassify.getFirstClassifyDescr());
        log.info("查询一级分类信息=================="+ JSONObject.toJSONString(mpFirstClassify));
        //通过一级分类id查询二级分类信息
        List<MpSecondClassify> secondClassifies = classifyService.searchByClassifyId(id);
        vo.setTypeNum(secondClassifies.size());
        log.info("通过一级分类id查询二级分类信息=-================="+JSONObject.toJSONString(secondClassifies));
        List<Long> ids = secondClassifies.stream().map(MpSecondClassify::getId).collect(Collectors.toList());
        List<ClassifyTypeShowStudyVo.SecondClassify> secondClassifiesVo = new ArrayList<>();
        //判断是否为定制分类
        if (mpFirstClassify.getFirstClassifyType() == CommonEnum.CTYPE.getCode()){
            //定制课程
            List<MpCurriculum> mpCurricula = curriculumService.searchByClassIdC(ids);
            log.info("定制----通过二级分类id查询课程信息================="+JSONObject.toJSONString(mpCurricula));
            //课程总时
            int sum = mpCurricula.stream().mapToInt(MpCurriculum::getClassHour).sum();
            vo.setCountNum(sum);
            //知识点
            int num = mpCurricula.stream().mapToInt(MpCurriculum::getNum).sum();
            vo.setSumNum(num);
            //课程数
            vo.setCurriculumNum(mpCurricula.size());
            //过滤
            List<MpCurriculum> mpCurriculumList = mpCurricula.stream().filter(a -> a.getClassFormat()== 1).collect(Collectors.toList());
            //文档
            vo.setDNum(mpCurriculumList.size());
            // 差集 (list1 - list2)
            //视频
            List<MpCurriculum> reduce1 = mpCurricula.stream().filter(item -> !mpCurriculumList.contains(item)).collect(Collectors.toList());
            vo.setVNum(reduce1.size());
            secondClassifies.forEach(e->{
                ClassifyTypeShowStudyVo.SecondClassify secondClassify = new ClassifyTypeShowStudyVo.SecondClassify();
                secondClassify.setSecondClassifyName(e.getSecondClassifyName());
                secondClassify.setSecondId(e.getId());
                //查询二级分类下的课程
                List<CurriculumMess> curriculumMesses = new ArrayList<>();
                if (mpCurricula.size()>0){
                    mpCurricula.forEach(i->{
                        //判断二级分类下的课程
                        if (e.getId().intValue() == i.getCustSecondClassifyId().intValue()){
                            CurriculumMess curriculumMess = new CurriculumMess();
                            curriculumMess.setCurriId(i.getId());
                            curriculumMess.setSecondClassifyName(i.getCurriculumName());
                            curriculumMesses.add(curriculumMess);
                        }
                    });
                }

                secondClassify.setCurriculumMesses(curriculumMesses);
                secondClassifiesVo.add(secondClassify);
            });
        }else{
            List<MpCurriculum> mpCurricula = curriculumService.searchByClassId(ids);
            log.info("通过二级分类id查询课程信息================="+mpCurricula);
            //课时
            int sum = mpCurricula.stream().mapToInt(MpCurriculum::getClassHour).sum();
            vo.setCountNum(sum);
            //知识点
            int num = mpCurricula.stream().mapToInt(MpCurriculum::getNum).sum();
            vo.setSumNum(num);
            //课程数
            vo.setCurriculumNum(mpCurricula.size());
            //过滤
            List<MpCurriculum> mpCurriculumList = mpCurricula.stream().filter(a -> a.getClassFormat()== 1).collect(Collectors.toList());
            //文档
            vo.setDNum(mpCurriculumList.size());
            // 差集 (list1 - list2)
            //视频
            List<MpCurriculum> reduce1 = mpCurricula.stream().filter(item -> !mpCurriculumList.contains(item)).collect(Collectors.toList());
            vo.setVNum(reduce1.size());
            secondClassifies.forEach(e->{
                ClassifyTypeShowStudyVo.SecondClassify secondClassify = new ClassifyTypeShowStudyVo.SecondClassify();
                secondClassify.setSecondClassifyName(e.getSecondClassifyName());
                secondClassify.setSecondId(e.getId());
                //查询二级分类下的课程
                List<CurriculumMess> curriculumMesses = new ArrayList<>();
                if (mpCurricula.size()>0){
                    mpCurricula.forEach(i->{
                        //判断二级分类下的课程
                        if (e.getId().intValue() == i.getGenSecondClassifyId().intValue()){
                            CurriculumMess curriculumMess = new CurriculumMess();
                            curriculumMess.setCurriId(i.getId());
                            curriculumMess.setSecondClassifyName(i.getCurriculumName());
                            curriculumMesses.add(curriculumMess);
                        }
                    });
                }
                secondClassify.setCurriculumMesses(curriculumMesses);
                secondClassifiesVo.add(secondClassify);
            });
        }
        vo.setSecondClassifies(secondClassifiesVo);
        return vo;
    }

    @Override
    public List<MpSecondClassify> searchByFirstId(Long id) {
        List<MpSecondClassify> secondClassifies = classifyService.searchByClassifyId(id);
        return secondClassifies;
    }

}
