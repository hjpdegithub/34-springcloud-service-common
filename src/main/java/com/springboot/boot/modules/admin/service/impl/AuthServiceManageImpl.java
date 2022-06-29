package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;

import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.AuthManageService;

import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.*;
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
public class AuthServiceManageImpl implements AuthManageService {

    @Autowired
    public AliyunOSSUtil aliyunOSSUtil;

    @Resource
    private MpAttachmentInfoMapper mpAttachmentInfoMapper;

    @Resource
    private MpAuthMapper mpAuthMapper;

    @Resource
    private MpAuthHMapper mpAuthHMapper;

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
    public ApiResult addOrUpdate(MpAuthDto dto) {
        //新增文件信息
        //雪花
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //====================创建认证实体==================-=====
        MpAuth mpAuth = new MpAuth();
        //数据赋值
        BeanCopy.copy(dto, mpAuth);
        MpBusinessAttachmentInfo mpBusinessAttachmentInfo
                = new MpBusinessAttachmentInfo();
        mpBusinessAttachmentInfo.setBusinessId(mpAuth.getId());
        mpBusinessAttachmentInfo.setCreateUser(dto.getUserId());
        mpBusinessAttachmentInfo.setCreateTime(new Date());
        mpBusinessAttachmentInfo.setBusiness("AuthInfo");
        mpBusinessAttachmentInfo.setDelFlag(CommonEnum.USED.getCode());
        mpBusinessAttachmentInfo.setAttachmentId(dto.getFileId());
        mpBusinessAttachmentInfo.setId(snowFlakeUtil.nextId());
        //是修改
        if (null != dto.getId() && dto.getId() != 0 && !dto.getId().toString().equals("")) {
            //以下处理以下业务1删除掉 图片业务表的该认证的关联数据 。
            MpBusinessAttachmentInfoExample mpBusinessAttachmentInfoExample =
                    new MpBusinessAttachmentInfoExample();
            mpBusinessAttachmentInfoExample.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode())
                    .andBusinessIdEqualTo(dto.getId());
            List<MpBusinessAttachmentInfo> mpBusinessAttachmentInfolist =
                    mpBusinessAttachmentInfoMapper.selectByExample(mpBusinessAttachmentInfoExample);
            if (null != mpBusinessAttachmentInfolist && mpBusinessAttachmentInfolist.size() > 0) {
                for (MpBusinessAttachmentInfo e : mpBusinessAttachmentInfolist) {
                    e.setDelFlag(CommonEnum.DELETE.getCode());
                    e.setUpdateUser(dto.getUserId());
                    e.setUpdateTime(new Date());
                    mpBusinessAttachmentInfoMapper.updateByPrimaryKeySelective(e);
                }
            }

            mpBusinessAttachmentInfoMapper.insertSelective(mpBusinessAttachmentInfo);
            int i = mpAuthMapper.updateByPrimaryKeySelective(mpAuth);
            if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("更新认证信息失败！");
            }
        } else {
            //是新增
            mpAuth.setId(snowFlakeUtil.nextId());
            mpAuth.setUpType(0);
            mpAuth.setCreateUser(dto.getUserId());
            mpAuth.setDeleFlag(CommonEnum.USED.getCode());
            mpAuth.setUpType(CommonEnum.UP.getCode());

            mpBusinessAttachmentInfoMapper.insertSelective(mpBusinessAttachmentInfo);
            mpAuth.setCreateTime(new Date());
            int i = mpAuthMapper.insertSelective(mpAuth);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("新增认证信息失败！");
            }
        }
        return ApiResult.success(mpAuth);
    }

    /**
     * 分页查询认证
     *
     * @param dto
     * @return
     */
    @Override
    public PageInfo<MpAuthHVo> search(MpAuthDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        //java 取当天日期开始时点
        Date startTime = dto.getCertificateTime();
        if (null != startTime) {
            Date dateStart = DateUtils.getDayStart(startTime);
            Date dateEnd = DateUtils.getNextDay(startTime);
            dto.setDateCEnd(dateEnd);
            dto.setDateCStart(dateStart);
        }
        List<MpAuthHVo> mpAuthHVos = mpAuthHMapper.selectAllMpAuths(dto);
        log.info("分页查询认证===================={}", dto);
        PageInfo<MpAuthHVo> pageInfo = new PageInfo<>(mpAuthHVos);
        return pageInfo;
    }


    /**
     * 认证详情查询
     *
     * @param dto
     * @return
     */
    @Override
    public MpAuthHVo searchById(MpAuthDto dto) {

        MpAuthHVo vo = null;
        if (dto.getId() == null || dto.getId() == 0) {
            return null;
        }
        List<MpAuthHVo> mpAuthHVos = mpAuthHMapper.selectAllMpAuths(dto);
        if (null != mpAuthHVos && mpAuthHVos.size() > 0) {
            vo = mpAuthHVos.get(0);
        } else {
            return null;
        }

        //查询出对应的证书底板

        Long id = mpAuthHMapper.selectFileId(dto.getId());
        String fileName = null;
        String fileUrl = null;
        String filePath = null;
        MpAttachmentInfo info1 = null;
        if (null != id) {
            //根据id找到文件信息
            info1 = mpAttachmentInfoMapper.selectByPrimaryKey(id);

            fileName = info1.getFileName();
            fileUrl = info1.getFileUrl();
            filePath = info1.getFilePath();
        }

        String fileUrlLocal = aliyunOSSUtil.ossToLocalToShow(null, filePath, fileName);
        info1.setFileUrl(fileUrl);
        info1.setFilePath(filePath);
        info1.setFileUrlLocal(fileUrlLocal);
        info1.setFileName(fileName);
        vo.setFileInfo(info1);
        return vo;
    }

    /**
     * 认证信息上下线
     *
     * @param dto
     * @return
     */
    @Override
    public Integer onOffLine(MpAuthDto dto) {
        MpAuth ent = mpAuthMapper.selectByPrimaryKey(dto.getId());
        ent.setUpdateUser(dto.getUserId());
        ent.setUpdateTime(new Date());
        ent.setUpType(dto.getUpType());
        int i = mpAuthMapper.updateByPrimaryKey(ent);
        if (i <= CommonEnum.ADD_ERROR.getCode()) {
            throw new BusinessException("上下线切换失败！");
        }
        return i;
    }

    /**
     * 认证信息批量删除
     *
     * @param dto
     * @return
     */
    @Override

    public Integer deleteBatch(MpNameIdsDto dto) {

        List<Long> ids = dto.getIds();
        int i = 0;
        if (null != ids && ids.size() > 0) {
            for (Long id : ids) {
                MpAuth ent = mpAuthMapper.selectByPrimaryKey(id);
                ent.setDeleFlag(CommonEnum.DELETE.getCode());
                ent.setUpdateUser(dto.getUserId());
                ent.setUpdateTime(new Date());

                mpAuthMapper.updateByPrimaryKey(ent);
                i++;
            }
            if (i != ids.size()) {
                throw new BusinessException("认证批量删除失败！");
            }
        }
        return 1;

    }


}
