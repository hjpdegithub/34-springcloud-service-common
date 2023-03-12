package com.springboot.boot.modules.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.TypeEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.file.BannerFileDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageDto;
import com.springboot.boot.modules.admin.dto.file.BannerManageNoPageDto;
import com.springboot.boot.modules.admin.dto.file.CommonupTypeDto;
import com.springboot.boot.modules.admin.entity.MpAttachmentInfo;
import com.springboot.boot.modules.admin.entity.MpBannerManage;
import com.springboot.boot.modules.admin.entity.MpBannerManageExample;
import com.springboot.boot.modules.admin.entity.MpBusinessAttachmentInfoExample;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.BannerMangerService;
import com.springboot.boot.modules.admin.service.OssUrlStreamTestService;
import com.springboot.boot.modules.admin.vo.banner.MpBannerBelongModuleVo;
import com.springboot.boot.modules.admin.vo.banner.MpBannerManageMenueVo;
import com.springboot.boot.modules.admin.vo.banner.MpBannerManageVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.modules.admin.vo.file.MpAttachmentInfoVo;
import com.springboot.boot.utils.AliyunOSSUtil;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.schema.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class BannerMangerServicempl implements BannerMangerService {
    @Autowired
    private AttachmentService attachmentService;
    @Resource
    private MpAttachmentInfoMapper mpAttachmentInfoMapper;
    @Resource
    private MpBannerManageMapper mpBannerManageMapper;
    @Resource
    private MpBannerManageTMapper mpBannerManageTMapper;
    @Resource
    private MpBannerFileMapper mpBannerFileMapper;

    /**
     * 自助列表2.0版本加入认证筛选条件
     *
     * @return
     **/
    @Override
    public ApiResult addOrUpdate(BannerManageDto bannerManageDto) {
        //beanCopy
        MpBannerManage mpBannerManage = new MpBannerManage();
        BeanCopy.copy(bannerManageDto, mpBannerManage);
        mpBannerManage.setDelFlag(CommonEnum.USED.getCode());
        mpBannerManage.setUpType(CommonEnum.UP.getCode());
        //根据主键id判断新增还是修改
        if (null != bannerManageDto.getId()) {
            mpBannerManage.setUpdateDate(new Date());
            mpBannerManage.setUpdateUser(bannerManageDto.getUserId());
            int i = mpBannerManageMapper.updateByPrimaryKeySelective(mpBannerManage);
            if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("banner更新失败");
            }
        } else {
            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
            mpBannerManage.setId(snowFlakeUtil.nextId());
            mpBannerManage.setCreateDate(new Date());
            mpBannerManage.setCreateUser(bannerManageDto.getUserId());
            //判断是否已经有了同名数据
            MpBannerManageExample example = new MpBannerManageExample();
            example.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode())
                    .andBannerBelongMenuEqualTo(bannerManageDto.getBannerBelongMenu())
                    .andBannerBelongModuleEqualTo(bannerManageDto.getBannerBelongModule());
            Long count = mpBannerManageMapper.countByExample(example);
            if (count.longValue() != 0) {
                throw new BusinessException("该模块已经有管理数据请在原数据的基础上编辑！");
            }
            int i = mpBannerManageMapper.insertSelective(mpBannerManage);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("banner新增失败");
            }
        }
        List<BannerFileDto> bannerFileDtos = bannerManageDto.getBannerFileDtos();
        if (null != bannerFileDtos && bannerFileDtos.size() > 0) {
            List<Long> ids = new ArrayList<>();
            for (BannerFileDto fileDto : bannerFileDtos) {
                ids.add(fileDto.getFileId());
                MpAttachmentInfo info = new MpAttachmentInfo();
                info.setId(fileDto.getFileId());
                info.setLinkurl(fileDto.getLinkurl());
                mpAttachmentInfoMapper.updateByPrimaryKeySelective(info);
            }
            attachmentService.addBusinessFileForBanner(ids, TypeEnum.banner.getCode(), mpBannerManage.getId());
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult list(@RequestBody BannerManageDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        List<MpBannerManage> mpBannerManageList = mpBannerManageTMapper.selectAll(dto);
        List<MpBannerManageVo> mpBannerManageVoList = new ArrayList<>();
        for (MpBannerManage entity : mpBannerManageList) {
            MpBannerManageVo rtvo = new MpBannerManageVo();
            BeanCopy.copy(entity, rtvo);
            List<MpAttachmentInfoVo> attachmentInfoVoList = mpBannerFileMapper.selectByBusinessId(entity.getId());
            rtvo.setMpAttachmentInfoVo(attachmentInfoVoList);
            mpBannerManageVoList.add(rtvo);
        }
        PageInfo<MpBannerManageVo> pageInfo = new PageInfo<>(mpBannerManageVoList);
        return ApiResult.success(pageInfo);
    }

    @Override
    public ApiResult listforShow(@RequestBody BannerManageNoPageDto bannerManageDto) {
        String name = bannerManageDto.getBannerBelongMenu();
        if (null == name || "".equals(name)) {
            return ApiResult.error("请输入banner所属菜单名称");
        }
        MpBannerManageMenueVo mpBannerManageMenueVo = new MpBannerManageMenueVo();
        List<MpBannerBelongModuleVo> mpBannerBelongModuleVoList = mpBannerManageTMapper.listforShow(bannerManageDto);
        if (null != mpBannerBelongModuleVoList && mpBannerBelongModuleVoList.size() > 0) {
            List<MpBannerBelongModuleVo> mpBannerBelongModuleVoListT = new ArrayList<>();
            for (MpBannerBelongModuleVo entity : mpBannerBelongModuleVoList) {
                MpBannerBelongModuleVo rtvo = new MpBannerBelongModuleVo();
                BeanCopy.copy(entity, rtvo);
                List<MpAttachmentInfoVo> attachmentInfoVoList = mpBannerFileMapper.selectByBusinessId(entity.getId());
                rtvo.setMpAttachmentInfoVo(attachmentInfoVoList);
                mpBannerBelongModuleVoListT.add(rtvo);
            }
            mpBannerManageMenueVo.setMpBannerBelongModuleVos(mpBannerBelongModuleVoListT);
            mpBannerManageMenueVo.setName(mpBannerBelongModuleVoList.get(0).getName());
        }
        return ApiResult.success(mpBannerManageMenueVo);
    }

    @Override
    public ApiResult delete(CommonupTypeDto dto) {
        MpBannerManage mpBannerManage = new MpBannerManage();
        List<Long> ids = dto.getIds();
        if (ids != null && ids.size() > 0) {
            for (Long id : ids) {
                mpBannerManage.setId(id);
                mpBannerManage.setDelFlag(CommonEnum.DELETE.getCode());
                int i = mpBannerManageMapper.updateByPrimaryKeySelective(mpBannerManage);
                if (i != 1) {
                    throw new BusinessException("banner图管理删除失败！");
                }
            }
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult upType(@RequestBody CommonupTypeDto dto) {
        MpBannerManage mpBannerManage = new MpBannerManage();
        mpBannerManage.setId(dto.getId());
        mpBannerManage.setUpType(dto.getUpType().intValue());
        int i = mpBannerManageMapper.updateByPrimaryKeySelective(mpBannerManage);
        if (i != 1) {
            throw new BusinessException("banner图管理上下线更新失败！");
        }
        return ApiResult.success();
    }


}
