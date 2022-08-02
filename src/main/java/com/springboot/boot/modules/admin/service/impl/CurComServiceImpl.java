package com.springboot.boot.modules.admin.service.impl;


import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.MpAttachmentCommentMapper;
import com.springboot.boot.modules.admin.mapper.MpAttachmentCommentReplyMapper;
import com.springboot.boot.modules.admin.mapper.MpAttachmentCommentXMapper;
import com.springboot.boot.modules.admin.service.CurComService;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class CurComServiceImpl implements CurComService {

    @Resource
    private MpAttachmentCommentXMapper mpAttachmentCommentXMapper;
    @Resource
    private MpAttachmentCommentMapper mpAttachmentCommentMapper;
    @Resource
    private MpAttachmentCommentReplyMapper attachmentCommentReplyMapper;
    @Override
    //新增课程评论
    public ApiResult add(CurComDto dto) {

        MpAttachmentComment mpAttachmentComment = new MpAttachmentComment();
        BeanCopy.copy(dto, mpAttachmentComment);
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //文件的ID主键
        mpAttachmentComment.setId(snowFlakeUtil.nextId());
        mpAttachmentComment.setCreateDate(new Date());
        mpAttachmentComment.setDelFlag(CommonEnum.USED.getCode());
        return ApiResult.success(mpAttachmentCommentMapper.insert(mpAttachmentComment));
    }
    //课程评论列表查询
    @Override
    public  List<MpAttachmentCommentWithUserName>  addCurCommentSelect( CurComDto dto ) {
        return   mpAttachmentCommentXMapper.selectByCurId(dto);
    }

    //根据ID删除课程
    @Override
    public  int  deleteByPrimaryKey(Long id){
       return  mpAttachmentCommentMapper.deleteByPrimaryKey(id);

   }

    @Override
    public int deleteCurCommentRel(CurComDto dto) {
        MpAttachmentCommentReplyExample example = new MpAttachmentCommentReplyExample();
        example.createCriteria().andCommentIdEqualTo(dto.getId());

        return attachmentCommentReplyMapper.deleteByExample(example);
    }

}
