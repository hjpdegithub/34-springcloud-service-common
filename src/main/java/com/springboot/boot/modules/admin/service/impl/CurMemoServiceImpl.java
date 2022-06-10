package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;

import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.MpAttachmentMemoMapper;
import com.springboot.boot.modules.admin.mapper.MpCurthuMapper;
import com.springboot.boot.modules.admin.service.CurMemoService;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
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
    private MpAttachmentMemoMapper mpAttachmentMemoMapper;

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
    public Boolean curThumStatus(@RequestBody CurComDto dto) {
        MpCurthuExample example = new MpCurthuExample();
        example.createCriteria().andCurIdEqualTo(dto.getCurId())
                .andUserIdEqualTo(dto.getUserId());
        List<MpCurthu> mpCurthuList =
                mpCurthuMapper.selectByExample(example);
        if (mpCurthuList == null || mpCurthuList.size() == 0) {
            return false;
        } else {
            MpCurthu mpCurthu = mpCurthuList.get(0);
            return mpCurthu.getThuStatus();
        }
    }

    @Override
    public int curThum(@RequestBody CurComDto dto) {
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
            mpCurthu.setThuStatus(true);
            return mpCurthuMapper.insert(mpCurthu);
        } else {
            MpCurthu mpCurthu = mpCurthuList.get(0);
            if (mpCurthu.getThuStatus()) {
                return 1;
            } else {
                mpCurthu.setThuStatus(true);
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


}
