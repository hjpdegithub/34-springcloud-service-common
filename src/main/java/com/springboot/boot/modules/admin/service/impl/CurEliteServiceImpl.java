package com.springboot.boot.modules.admin.service.impl;


import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.mapper.CurriEliteMapper;
import com.springboot.boot.modules.admin.service.CurEliteService;
import com.springboot.boot.modules.admin.vo.curriculum.CurSecondDetailVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
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
public class CurEliteServiceImpl implements CurEliteService {

    @Resource
    private CurriEliteMapper curriEliteMapper;

    @Override
    public ApiResult curEliteSelect() {

        List<List> retList = new ArrayList<>();

        List<CurriculumVo> list1 = new ArrayList<>();
        List<CurriculumVo> list2 = new ArrayList<>();
        List<CurriculumVo> list3 = new ArrayList<>();
        List<CurriculumVo> list4 = new ArrayList<>();

        List<CurriculumVo> curriculumVoList = curriEliteMapper.selectAll();

        for (CurriculumVo curriculumVo : curriculumVoList) {
            Long readCount = curriEliteMapper.selectReadCount(curriculumVo.getId());
            curriculumVo.setReadCount(readCount);
        }

        int i = 0;
        for (CurriculumVo curriculumVo : curriculumVoList) {
            if (i == 0 || i == 1 || i == 2) {
                list1.add(curriculumVo);
            }
            if (i == 3 || i == 4 || i == 5) {
                list2.add(curriculumVo);
            }
            if (i == 6 || i == 7 || i == 8) {
                list3.add(curriculumVo);
            }
            if (i == 9 || i == 10 || i == 11) {
                list4.add(curriculumVo);
            }
            i++;
        }
        if (list1 != null && list1.size() > 0) {
            retList.add(list1);
        }
        if (list2 != null && list2.size() > 0) {
            retList.add(list2);
        }
        if (list3 != null && list3.size() > 0) {
            retList.add(list3);
        }
        if (list4 != null && list4.size() > 0) {
            retList.add(list4);
        }

        return ApiResult.success(retList);

    }

    @Override
    public ApiResult curSecondDetailSelectById(CommonAllDto dto) {

        List<CurSecondDetailVo> curSecondDetailVoList = curriEliteMapper.curSecondDetailSelectById(dto.getId());
        CurSecondDetailVo curSecondDetailVo = null;
        if (curSecondDetailVoList != null && curSecondDetailVoList.size() > 0) {
            curSecondDetailVo = curSecondDetailVoList.get(0);
            //查询阅读量
            Long readCount = curriEliteMapper.selectReadCount(dto.getId());
            curSecondDetailVo.setReadCount(readCount);
            //查询附件只有一个附件
            String attaUrl = curriEliteMapper.selectCurAttaUrl(dto.getId());
            curSecondDetailVo.setFileUrl(attaUrl);
        }

        return ApiResult.success(curSecondDetailVo);
    }


}
