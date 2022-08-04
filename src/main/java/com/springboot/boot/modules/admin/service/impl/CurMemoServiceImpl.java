package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;

import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.CurMemoService;
import com.springboot.boot.modules.admin.vo.MyStudyVo;
import com.springboot.boot.modules.admin.vo.curriculum.CollectStatusVo;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
public class CurMemoServiceImpl implements CurMemoService {

    @Resource
    private MpCurthuMapper mpCurthuMapper;

    @Resource
    private MpCollectMapper mpCollectMapper;

    @Resource
    private MpCurriculumMapper mpCurriculumMapper;


    @Resource
    private MpAttachmentMemoMapper mpAttachmentMemoMapper;


    @Resource
    private MpAttachmentMemoXMapper mpAttachmentMemoXMapper;

    @Override
    //新增课程评论回复
    public int add(CurMemDto dto) {
        MpAttachmentMemoExample example = new MpAttachmentMemoExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId()).andDelFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAttachmentMemo> reslis = mpAttachmentMemoMapper.selectByExample(example);
        if (null == reslis || reslis.size() == 0) {
            MpAttachmentMemo mpAttachmentMemo = new MpAttachmentMemo();
            BeanCopy.copy(dto, mpAttachmentMemo);
            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
            //文件的ID主键
            mpAttachmentMemo.setId(snowFlakeUtil.nextId());
            mpAttachmentMemo.setCreateDate(new Date());
            mpAttachmentMemo.setDelFlag(CommonEnum.USED.getCode());
            return mpAttachmentMemoMapper.insert(mpAttachmentMemo);

        } else {
            MpAttachmentMemo record = reslis.get(0);
            BeanCopy.copy(dto, record);
            MpAttachmentMemoExample example1 = new MpAttachmentMemoExample();
            example.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode())
                    .andCurIdEqualTo(dto.getCurId()).andUserIdEqualTo(dto.getUserId());
            return mpAttachmentMemoMapper.updateByExampleSelective(record, example1);

        }


    }


    //新增课程评论回复查询
    @Override
    public MpAttachmentMemo curComSelect(CurMemDto dto) {
        MpAttachmentMemoExample example = new MpAttachmentMemoExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId()).andDelFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAttachmentMemo> reslis = mpAttachmentMemoMapper.selectByExample(example);
        if (null == reslis || reslis.size() == 0) {
            return null;
        } else {
            return reslis.get(0);
        }
    }

    //根据ID删除课程
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mpAttachmentMemoMapper.deleteByPrimaryKey(id);

    }


    @Override
    public CollectStatusVo curThumStatus(@RequestBody CurComDto dto) {
        CollectStatusVo  vo   = new  CollectStatusVo();
        MpCurthuExample example = new MpCurthuExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId());
        List<MpCurthu> mpCurthuList =
                mpCurthuMapper.selectByExample(example);
        if (mpCurthuList == null || mpCurthuList.size() == 0) {
            vo.setCollectStatus(false);
        } else {
            MpCurthu mpCurthu = mpCurthuList.get(0);
            vo.setCollectStatus(mpCurthu.getThuStatus());
        }
        MpCurthuExample exampleT = new MpCurthuExample();
        exampleT.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andThuStatusEqualTo(true);
        long amount  =    mpCurthuMapper.countByExample(exampleT);
        vo.setAmount(Long.valueOf(amount));
        return vo;
    }


    @Override
    public Boolean collectOrCancel(@RequestBody CurComDto dto) {
        Integer clickOrCancel = dto.getClickOrCancel();
        if (clickOrCancel.intValue() == 1) {
            //收藏
            MpCollectExample example = new MpCollectExample();
            example.createCriteria().andCurriculumIdEqualTo(dto.getCurId())
                    .andUserIdEqualTo(dto.getUserId());
            List<MpCollect> mpCollectList =
                    mpCollectMapper.selectByExample(example);
            if (mpCollectList == null || mpCollectList.size() == 0) {
                MpCollect mpCollect = new MpCollect();
                BeanCopy.copy(dto, mpCollect);
                SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
                //文件的ID主键
                mpCollect.setId(snowFlakeUtil.nextId());
                mpCollect.setCurriculumId(dto.getCurId());
                mpCollect.setStatus(1);
                mpCollect.setDeleFlag(CommonEnum.USED.getCode());
                mpCollectMapper.insert(mpCollect);
                return true;
            } else {
                MpCollect mpCollect = new MpCollect();
                mpCollect.setId(mpCollectList.get(0).getId());
                mpCollect.setStatus(1);
                mpCollectMapper.updateByPrimaryKeySelective(mpCollect);
            }
        } else if (clickOrCancel.intValue() == 0) {
            MpCollectExample exampleT = new MpCollectExample();
            exampleT.createCriteria().andCurriculumIdEqualTo(dto.getCurId())
                    .andUserIdEqualTo(dto.getUserId());
            List<MpCollect> mpCollectList =
                    mpCollectMapper.selectByExample(exampleT);
            if (null != mpCollectList && mpCollectList.size() > 0) {
                MpCollect mpCollect = mpCollectList.get(0);
                mpCollect.setStatus(dto.getClickOrCancel());
                mpCollectMapper.updateByExampleSelective(mpCollect, exampleT);
            } else {
                //donothing
            }
            return false;
        } else {
            throw new BusinessException("收藏状态请填写0或者1");
        }
        return true;
    }

    @Override
    public CollectStatusVo collectStatus(@RequestBody CurComDto dto) {
        CollectStatusVo vo = new CollectStatusVo();
        MpCollectExample example = new MpCollectExample();
        example.createCriteria().andCurriculumIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId());
        List<MpCollect> mpCollectList =
                mpCollectMapper.selectByExample(example);
        if (mpCollectList == null || mpCollectList.size() == 0) {
            vo.setCollectStatus(false);
        } else {
            MpCollect mpCollect = mpCollectList.get(0);
            vo.setCollectStatus(mpCollect.getStatus().intValue() == 1 ? true : false);
        }
        //获得收藏数量
        MpCollectExample exampleT = new MpCollectExample();
        exampleT.createCriteria().andCurriculumIdEqualTo(dto.getCurId())
                .andStatusEqualTo(1).andDeleFlagEqualTo(CommonEnum.USED.getCode());
        long amount = mpCollectMapper.countByExample(exampleT);
        vo.setAmount(amount);
        return vo;

    }

    @Override
    public List<MyStudyVo> collectCurSearCh(@RequestBody CurMemDto dto) {
        //myStudy
        //查询出该用户下的笔记对应的课程
        List<MyStudyVo> lists = new ArrayList<>();
        MpAttachmentMemoExample example = new MpAttachmentMemoExample();
        MpAttachmentMemoExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(dto.getUserId());
        criteria.andDelFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAttachmentMemo> mpAttachmentMemos = mpAttachmentMemoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(mpAttachmentMemos)) {
            mpAttachmentMemos.forEach(e -> {
                MpCurriculumExample example1 = new MpCurriculumExample();
                MpCurriculumExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andIdEqualTo(e.getCurId());
                List<MpCurriculum> mpCurricula = mpCurriculumMapper.selectByExample(example1);
                MpCurriculum i = mpCurricula.get(0);
                MyStudyVo vo = new MyStudyVo();
                vo.setType(i.getCustomizedType());
                vo.setCurrName(i.getCurriculumName());
                vo.setFormate(i.getClassFormat());
                vo.setId(i.getId());
                vo.setStudyTime(i.getStudyTime());
                lists.add(vo);
            });


        }
        return lists;
    }

    @Override
    public int curThum(@RequestBody CurComDto dto) {
        Integer clickOrCancel = dto.getClickOrCancel();
        //点赞
        MpCurthuExample example = new MpCurthuExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId());
        List<MpCurthu> mpCurthuList =
                mpCurthuMapper.selectByExample(example);
        if (mpCurthuList == null || mpCurthuList.size() == 0) {
            MpCurthu mpCurthu = new MpCurthu();
            BeanCopy.copy(dto, mpCurthu);
            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
            //文件的ID主键
            mpCurthu.setId(snowFlakeUtil.nextId());
            mpCurthu.setThuStatus(clickOrCancel.intValue()==1?true:false);
            return mpCurthuMapper.insert(mpCurthu);
        } else {
            MpCurthu mpCurthu = mpCurthuList.get(0);
            if (mpCurthu.getThuStatus()) {
                return 1;
            } else {
                mpCurthu.setThuStatus(clickOrCancel.intValue()==1?true:false);
                return mpCurthuMapper.updateByExampleSelective(mpCurthu, example);
            }
        }

    }


    @Override
    public int curThumCancel(@RequestBody CurComDto dto) {

        MpCurthuExample example = new MpCurthuExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId());
        List<MpCurthu> mpCurthuList = mpCurthuMapper.selectByExample(example);
        if (null != mpCurthuList && mpCurthuList.size() > 0) {
            MpCurthu mpCurthu = mpCurthuList.get(0);
            mpCurthu.setThuStatus(false);
            return mpCurthuMapper.updateByExample(mpCurthu, example);
        } else {
            return 1;
        }


    }


    @Override
    public Long curThumCount(@RequestBody CurComDto dto) {
        MpCurthuExample example = new MpCurthuExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andThuStatusEqualTo(true);
        return mpCurthuMapper.countByExample(example);
    }

    @Override
    public List<MyStudyVo> myCollectSelect(CurMemDto dto) {
        //myStudy
        //查询出该用户下的笔记对应的课程
        List<MyStudyVo> lists = new ArrayList<>();
        MpCollectExample example = new MpCollectExample();
        MpCollectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(dto.getUserId());
        criteria.andStatusEqualTo(1);
        List<MpCollect> mpCollectList = mpCollectMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(mpCollectList)) {
            mpCollectList.forEach(e -> {
                MpCurriculumExample example1 = new MpCurriculumExample();
                MpCurriculumExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andIdEqualTo(e.getCurriculumId());
                List<MpCurriculum> mpCurricula = mpCurriculumMapper.selectByExample(example1);
                MpCurriculum i = mpCurricula.get(0);
                MyStudyVo vo = new MyStudyVo();
                vo.setType(i.getCustomizedType());
                vo.setCurrName(i.getCurriculumName());
                vo.setFormate(i.getClassFormat());
                vo.setId(i.getId());
                vo.setStudyTime(i.getStudyTime());
                lists.add(vo);
            });


        }
        return lists;
    }


}
