package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.modules.admin.dto.curriculum.CurComReplyDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.MpAttachmentCommentReplyMapper;
import com.springboot.boot.modules.admin.service.CurComReplyService;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CurComReplyServiceImpl implements CurComReplyService {

    @Resource
    private MpAttachmentCommentReplyMapper mpAttachmentCommentReplyMapper;

    @Override
    //新增课程评论回复
    public int add(CurComReplyDto dto) {

        MpAttachmentCommentReply mpAttachmentCommentReply = new MpAttachmentCommentReply();
        BeanCopy.copy(dto, mpAttachmentCommentReply);
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //文件的ID主键
        mpAttachmentCommentReply.setId(snowFlakeUtil.nextId());
        mpAttachmentCommentReply.setDelFlag(CommonEnum.USED.getCode());
        return mpAttachmentCommentReplyMapper.insert(mpAttachmentCommentReply);

    }


    //新增课程评论回复查询
    @Override
    public  List<MpAttachmentCommentReply>  curComRelySelect( CurComReplyDto dto ) {
        MpAttachmentCommentReplyExample example = new MpAttachmentCommentReplyExample();
        example.createCriteria()
                .andCommentIdEqualTo(dto.getCommentId()).andDelFlagEqualTo(CommonEnum.USED.getCode());
        return   mpAttachmentCommentReplyMapper.selectByExample(example);
    }

    //根据ID删除课程
    @Override
    public  int  deleteByPrimaryKey(Long id){
       return  mpAttachmentCommentReplyMapper.deleteByPrimaryKey(id);

   }

}
