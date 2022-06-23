package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName ClassifyServiceImpl
 * @Description TODO 分类管理业务层
 * @Author jhzhou
 * @Date 2022/3/11 0011 14:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


    @Resource
    private MpAttachmentInfoMapper mpAttachmentInfoMapper;

    @Resource
    private MpAuthMapper mpAuthMapper;

    @Resource
    private MpBusinessAttachmentInfoMapper mpBusinessAttachmentInfoMapper;

    @Autowired
    private AttachmentService attachmentService;


    /**
     * 分类的新增以及修改
     *
     * @param dto
     * @return {@link ApiResult}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult addOrUpdate(MpAuthDto dto, MultipartFile file) {

        ApiResult res = attachmentService.attachmentDeal(file);
        if (res.getCode() == ApiCode.FAIL.getCode()) {
            return ApiResult.error("认证信息上传文件失败");
        }

        MpAttachmentInfo info = (MpAttachmentInfo) res.getData();
        //新增文件信息
        //雪花
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //====================创建认证实体==================-=====
        MpAuth mpAuth = new MpAuth();
        BeanCopy.copy(dto, mpAuth);
        MpBusinessAttachmentInfo mpBusinessAttachmentInfo
                = new MpBusinessAttachmentInfo();
        mpBusinessAttachmentInfo.setDelFlag(CommonEnum.USED.getCode());
        mpBusinessAttachmentInfo.setCreateUser(dto.getUserId());
        mpBusinessAttachmentInfo.setCreateTime(new Date());
        mpBusinessAttachmentInfo.setBusiness("AuthInfo");
        mpBusinessAttachmentInfo.setAttachmentId(info.getId());
        mpBusinessAttachmentInfo.setId(snowFlakeUtil.nextId());
        //是修改
        if (null != dto.getId()&&dto.getId()!=0&&!dto.getId().toString().equals("")) {
            //以下处理以下业务1删除掉 图片业务表的该认证的关联数据 ，删除以前的图片为无效。
            MpBusinessAttachmentInfoExample mpBusinessAttachmentInfoExample =
                    new MpBusinessAttachmentInfoExample();
            mpBusinessAttachmentInfoExample.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode())
                    .andBusinessIdEqualTo(dto.getId());
            List<MpBusinessAttachmentInfo> mpBusinessAttachmentInfolist =
                    mpBusinessAttachmentInfoMapper.selectByExample(mpBusinessAttachmentInfoExample);
            if (null != mpBusinessAttachmentInfolist && mpBusinessAttachmentInfolist.size() > 0) {
                for (MpBusinessAttachmentInfo e : mpBusinessAttachmentInfolist) {
                    e.setDelFlag(CommonEnum.DELETE.getCode());
                    mpBusinessAttachmentInfoMapper.updateByPrimaryKey(e);
                    MpAttachmentInfo mpAttachmentInfo = mpAttachmentInfoMapper.selectByPrimaryKey(e.getAttachmentId());
                    if (null != mpAttachmentInfo) {
                        mpAttachmentInfo.setDelFlag(CommonEnum.DELETE.getCode());
                        mpAttachmentInfo.setUpdateDate(new Date());
                        mpAttachmentInfo.setUpdateUser(dto.getUserId());
                        mpAttachmentInfoMapper.updateByPrimaryKey(mpAttachmentInfo);
                    }
                }
            }
            mpAuth.setUpdateTime(new Date());
            mpAuth.setUpdateUser(dto.getUserId());
            mpBusinessAttachmentInfo.setBusinessId(dto.getId());
            mpBusinessAttachmentInfoMapper.insert(mpBusinessAttachmentInfo);
            int i = mpAuthMapper.updateByPrimaryKey(mpAuth);
            if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("更新认证信息失败！");
            }
        } else {
            //是新增
            mpAuth.setId(snowFlakeUtil.nextId());
            mpAuth.setCreateUser(dto.getUserId());
            mpBusinessAttachmentInfo.setBusinessId(mpAuth.getId());
            mpBusinessAttachmentInfoMapper.insert(mpBusinessAttachmentInfo);
            mpAuth.setCreateTime(new Date());
            int i = mpAuthMapper.insert(mpAuth);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("新增认证信息失败！");
            }
        }
        return ApiResult.success(mpAuth);
    }

    /**
     * 分页查询认证
     * @param dto
     * @return
     */
    public   PageInfo<CurriculumVo> search(MpAuthDto dto){
        return null;

    }
}
