package com.springboot.boot.modules.admin.controller;


import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.curriculum.CurComDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurComReplyDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurMemDto;
import com.springboot.boot.modules.admin.service.CurComReplyService;
import com.springboot.boot.modules.admin.service.CurComService;
import com.springboot.boot.modules.admin.service.CurMemoService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CurriculumController
 * @Description TODO 课程管理
 * @Author jhzhou
 * @Date 2022/3/14 0014 9:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mp/curComMem")
@Slf4j
@Api(tags = "1.5", description = "课程笔记评论管理【侯建鹏】")
@CrossOrigin
public class CurMemComController {

    @Autowired
    private CurComService curComService;
    @Autowired
    private CurMemoService curMemoService;
    @Autowired
    private CurComReplyService curComReplyService;

    @ApiOperation(value = "1课程评论新增", notes = "课程评论新增")
    @PostMapping(value = "/addCurComment")
    public ApiResult addCurComment(@RequestBody CurComDto dto) {
        if (null == dto.getUserId()) {
            throw new BusinessException("请先登录！");
        }
        return ApiResult.success(curComService.add(dto));
    }

    @ApiOperation(value = "2课程评论查询", notes = "课程评论查询")
    @PostMapping(value = "/curCommentSelect")
    public ApiResult CurCommentSelect(@RequestBody CurComDto dto) {
        if (null == dto.getUserId()) {
            // throw new BusinessException("请先登录！");
        }
        return ApiResult.success(curComService.addCurCommentSelect(dto));
    }

    @ApiOperation(value = "3课程评论删除", notes = "课程评论删除")
    @PostMapping(value = "/curCommentDeleteById")
    public ApiResult addCurCommentDeleteById(@RequestBody CurComDto dto) {
        if (null == dto.getUserId()) {
            throw new BusinessException("请先登录！");
        }
        //删除回复
        int a = curComService.deleteCurCommentRel(dto);
        if (a <= CommonEnum.DELETE_ERROR.getCode()) {
            throw new BusinessException("回复删除失败");
        }
        return ApiResult.success(curComService.deleteByPrimaryKey(dto.getId()));
    }

    @ApiOperation(value = "4课程评论回复新增", notes = "课程评论回复新增")
    @PostMapping(value = "/addCurComRely")
    public ApiResult addCurComRely(@RequestBody CurComReplyDto dto) {
        return ApiResult.success(curComReplyService.add(dto));
    }

    @ApiOperation(value = "5课程评论回复查询", notes = "课程评论回复查询")
    @PostMapping(value = "/curComRelySelect")
    public ApiResult curComRelySelect(@RequestBody CurComReplyDto dto) {
        return ApiResult.success(curComReplyService.curComRelySelect(dto));
    }


    @ApiOperation(value = "6课程评论回复删除", notes = "课程评论回复删除")
    @PostMapping(value = "/curComRelyDeleteById")
    public ApiResult curComRelyDeleteById(@RequestBody CurComReplyDto dto) {
        return ApiResult.success(curComReplyService.deleteByPrimaryKey(dto.getId()));
    }

    @ApiOperation(value = "7课程笔记新增", notes = "课程笔记新增")
    @PostMapping(value = "/addCurMemo")
    public ApiResult addCurMemo(@RequestBody CurMemDto dto) {
        if (null == dto.getUserId()) {
            throw new BusinessException("请先登录！");
        }
        return ApiResult.success(curMemoService.add(dto));
    }

    @ApiOperation(value = "8课程笔记查询", notes = "课程笔记查询")
    @PostMapping(value = "/curMemoSelect")
    public ApiResult addCurMemoSelect(@RequestBody CurMemDto dto) {
        if (null == dto.getUserId()) {
            throw new BusinessException("请先登录！");
        }
        return ApiResult.success(curMemoService.curComSelect(dto));
    }

    @ApiOperation(value = "9课程笔记删除", notes = "课程笔记删除")
    @PostMapping(value = "/curMemoDeleteByid")
    public ApiResult curMemoDeleteByid(@RequestBody CurMemDto dto) {
        if (null == dto.getUserId()) {
            throw new BusinessException("请先登录！");
        }
        return ApiResult.success(curMemoService.deleteByPrimaryKey(dto.getId()));
    }

    @ApiOperation(value = "11客户点赞", notes = "11客户点赞")
    @PostMapping(value = "/curThum")
    public ApiResult curThum(@RequestBody CurComDto dto) {
        return ApiResult.success(curMemoService.curThum(dto));
    }

    //已经废弃
    @ApiOperation(value = "12客户取消点赞", notes = "12客户取消点赞")
    @PostMapping(value = "/curThumCancel")
    public ApiResult curThumCancel(@RequestBody CurComDto dto) {
        return ApiResult.success(curMemoService.curThumCancel(dto));
    }

    @ApiOperation(value = "13客户点赞数量", notes = "13客户点赞数量")
    @PostMapping(value = "/curThumCount")
    public ApiResult curThumCount(@RequestBody CurComDto dto) {
        return ApiResult.success(curMemoService.curThumCount(dto));
    }
    @ApiOperation(value = "10客户点赞状态", notes = "10客户点赞状态")
    @PostMapping(value = "/curThumStatus")
    public ApiResult curThumStatus(@RequestBody CurComDto dto) {
        return ApiResult.success(curMemoService.curThumStatus(dto));
    }


    @ApiOperation(value = "11客户收藏和取消收藏按钮", notes = "11客户收藏和取消收藏按钮")
    @PostMapping(value = "/collectOrCancel")
    public ApiResult collectOrCancel(@RequestBody CurComDto dto) {
        return ApiResult.success(curMemoService.collectOrCancel(dto));
    }


    //数量和状态写成一个接口
    @ApiOperation(value = "12客户收藏状态和课程被收藏数量查询", notes = "12客户收藏状态和课程被收藏数量查询")
    @PostMapping(value = "/collectStatus")
    public ApiResult collectStatus(@RequestBody CurComDto dto) {
        return ApiResult.success(curMemoService.collectStatus(dto));
    }

    //做过笔记的课程查询
    @ApiOperation(value = "13将该用户用户做过笔记的课程查询出来", notes = "13将该用户用户做过笔记的课程查询出来")
    @PostMapping(value = "/collectCurSearCh")
    public ApiResult collectCurSearCh(@RequestBody CurMemDto dto) {
        return ApiResult.success(curMemoService.collectCurSearCh(dto));
    }

    //做过笔记的课程查询
    @ApiOperation(value = "我的收藏", notes = "我的收藏")
    @PostMapping(value = "/myCollectSelect")
    public ApiResult myCollectSelect(@RequestBody CurMemDto dto) {
        return ApiResult.success(curMemoService.myCollectSelect(dto));
    }




}
