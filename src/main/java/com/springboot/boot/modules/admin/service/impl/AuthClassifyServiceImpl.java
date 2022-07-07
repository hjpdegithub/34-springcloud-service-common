package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDomainDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AuthClassifyService;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.auth.MpAuthDomainVo;
import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AuthClassifyServiceImpl implements AuthClassifyService {

    @Resource
    private MpFirstClassifyMapper firstClassifyMapper;
    @Resource
    private MpSecondClassifyMapper secondClassifyMapper;
    @Resource
    private MpAuthDirectionMapper mpAuthDirectionMapper;
    @Resource
    private MpAuthDomainMapper mpAuthDomainMapper;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private AuthService authService;

    /**
     * 分类的新增以及修改
     *
     * @param dto
     * @return {@link ApiResult}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult addOrUpdate(MpAuthDirectionAddOrUpdateDto dto) {
        //雪花
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //====================认证方向实体==================-=====
        MpAuthDirection ea = new MpAuthDirection();
        ea.setCreateTime(new Date());
        ea.setCreateUser(dto.getUserId());
        ea.setDescription(dto.getDescription());
        ea.setName(dto.getName());
        ea.setDeleFlag(CommonEnum.USED.getCode());
        ea.setId(snowFlakeUtil.nextId());
        if (null != dto.getMpAuthDomainDtoList() && dto.getMpAuthDomainDtoList().size() > 0) {
            dto.getMpAuthDomainDtoList().forEach(e -> {
                if (null == e.getName()) {
                    throw new BusinessException("认证领域名称请填写");
                }
            });
        }
        //判断方向新增还是修改
        if (null != dto.getId()) {
            //方向编辑
            ea.setId(dto.getId());
            int eaUpdate = mpAuthDirectionMapper.updateByPrimaryKeySelective(ea);
            if (eaUpdate <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("认证方向更新失败");
            }
            //领域编辑
            //旧信息
            MpAuthDomainExample example = new MpAuthDomainExample();
            example.createCriteria().andAuthDirectionIdEqualTo(dto.getId()).andDeleFlagEqualTo(CommonEnum.USED.getCode());
            List<MpAuthDomain> eoldList = mpAuthDomainMapper.selectByExample(example);

            //新信息
            List<MpAuthDomainDto> enewList = dto.getMpAuthDomainDtoList();
            //将所有的enew的主键作成一个list
            List<Long> enewIdList = new ArrayList<>();
            List<Long> eoldIdList = new ArrayList<>();
            {
                if (null != enewList && enewList.size() > 0) {
                    for (MpAuthDomainDto en : enewList) {
                        if (en.getId() != null) {
                            enewIdList.add(en.getId());
                        }
                    }
                }
                if (null != eoldList && eoldList.size() > 0) {
                    for (MpAuthDomain eold : eoldList) {
                        if (eold.getId() != null) {
                            eoldIdList.add(eold.getId());
                        }
                    }
                }
            }
            //新实体
            for (MpAuthDomainDto en : enewList) {
                MpAuthDomain ent = new MpAuthDomain();
                BeanCopy.copy(en, ent);
                //需要更新
                if (eoldIdList.contains(en.getId())) {
                    ent.setUpdateTime(new Date());
                    ent.setUpdateUser(dto.getUserId());
                    mpAuthDomainMapper.updateByPrimaryKeySelective(ent);
                }
                //需要新增
                if (!eoldIdList.contains(en.getId())) {
                    ent.setId(snowFlakeUtil.nextId());
                    ent.setAuthDirectionId(dto.getId());
                    ent.setDeleFlag(CommonEnum.USED.getCode());
                    ent.setCreateTime(new Date());
                    ent.setCreateUser(dto.getId());
                    mpAuthDomainMapper.insertSelective(ent);
                }
            }
            //需要逻辑删除
            if (null != eoldList && eoldList.size() > 0) {
                for (MpAuthDomain eo : eoldList) {
                    if (!enewIdList.contains(eo.getId())) {
                        eo.setUpdateUser(dto.getUserId());
                        eo.setUpdateTime(new Date());
                        eo.setDeleFlag(CommonEnum.DELETE.getCode());
                        mpAuthDomainMapper.updateByPrimaryKeySelective(eo);
                    }
                }
            }
            return ApiResult.success();
        } else {
            //方向新增
            mpAuthDirectionMapper.insertSelective(ea);
            //领域新增
            List<MpAuthDomainDto> dtos = dto.getMpAuthDomainDtoList();
            if (null != dtos && dtos.size() > 0) {
                for (MpAuthDomainDto edto : dtos) {
                    MpAuthDomain ebt = new MpAuthDomain();

                    BeanCopy.copy(edto, ebt);
                    ebt.setId(snowFlakeUtil.nextId());
                    ebt.setAuthDirectionId(ea.getId());
                    ebt.setCreateUser(dto.getUserId());
                    ebt.setDeleFlag(CommonEnum.USED.getCode());
                    ebt.setCreateTime(new Date());
                    mpAuthDomainMapper.insertSelective(ebt);
                }
            }
            return ApiResult.success();
        }
    }

    /**
     * 根据名称查看方向分类信息如果名称是空的话就查询所有的认证方向
     *
     * @param dto 认证方向名称
     * @return
     */
    @Override
    public PageInfo<MpAuthHVo> searchByAuthdirectionName(MpNameIdsDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        String authdirectionName = dto.getName();
        Long id = dto.getId();
        List<MpAuthDirection> mpAuthDirections = null;
        MpAuthDirectionExample mpAuthDirectionExample = null;
        if (authdirectionName == null || "".equals("")) {
            mpAuthDirectionExample = new MpAuthDirectionExample();
            mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());
            mpAuthDirections = mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);
        }
        if (null != authdirectionName && !"".equals("")) {
            mpAuthDirectionExample = new MpAuthDirectionExample();
            mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andNameEqualTo(authdirectionName);
            mpAuthDirections = mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);
        }
        if (null != id && !id.equals("")) {
            mpAuthDirectionExample = new MpAuthDirectionExample();
            mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andIdEqualTo(id);
            mpAuthDirections = mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);

        }
        List<MpAuthHVo> mpAuthHVos = new ArrayList<>();

        if (null != mpAuthDirections && mpAuthDirections.size() > 0) {

            for (MpAuthDirection e : mpAuthDirections) {
                Long eid = e.getId();
                MpAuthDomainExample ex = new MpAuthDomainExample();
                ex.createCriteria().andAuthDirectionIdEqualTo(eid).andDeleFlagEqualTo(CommonEnum.USED.getCode());
                List<MpAuthDomain> mpAuthDomainList = mpAuthDomainMapper.selectByExample(ex);

                List<MpAuthDomainVo> mpAuthDomainVoList = new ArrayList<>();
                if (null != mpAuthDomainList && mpAuthDomainList.size() > 0) {
                    for (MpAuthDomain e1 : mpAuthDomainList) {
                        MpAuthDomainVo vo1 = new MpAuthDomainVo();
                        BeanCopy.copy(e1, vo1);
                        mpAuthDomainVoList.add(vo1);
                    }
                }
                MpAuthHVo vo = new MpAuthHVo();
                BeanCopy.copy(e, vo);
                vo.setMpAuthDomainVos(mpAuthDomainVoList);
                mpAuthHVos.add(vo);
            }
        }
        PageInfo<MpAuthHVo> pageInfo = new PageInfo<>(mpAuthHVos);
        return pageInfo;
    }


    @Override
    public MpAuthHVo searchId(MpNameIdsDto dto) {
        MpAuthHVo vo = null;
        Long id = dto.getId();
        List<MpAuthDirection> mpAuthDirections = null;
        MpAuthDirectionExample mpAuthDirectionExample = null;
        if (null != id && !id.equals("")) {
            mpAuthDirectionExample = new MpAuthDirectionExample();
            mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andIdEqualTo(id);
        } else {
            return null;
        }
        mpAuthDirections = mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);
        for (MpAuthDirection e : mpAuthDirections) {
            Long eid = e.getId();
            MpAuthDomainExample ex = new MpAuthDomainExample();
            ex.createCriteria().andAuthDirectionIdEqualTo(eid).andDeleFlagEqualTo(CommonEnum.USED.getCode());
            List<MpAuthDomain> mpAuthDomainList = mpAuthDomainMapper.selectByExample(ex);
            List<MpAuthDomainVo> mpAuthDomainVoList = new ArrayList<>();

            if (null != mpAuthDomainList && mpAuthDomainList.size() > 0) {
                for (MpAuthDomain e1 : mpAuthDomainList) {
                    MpAuthDomainVo vo1 = new MpAuthDomainVo();
                    BeanCopy.copy(e1, vo1);
                    mpAuthDomainVoList.add(vo1);
                }
            }
            vo = new MpAuthHVo();
            BeanCopy.copy(e, vo);
            vo.setMpAuthDomainVos(mpAuthDomainVoList);

        }
        return vo;

    }

    /**
     * 根据名称查看方向分类信息如果名称是空的话就查询所有的认证方向
     *
     * @param
     * @return
     */
    @Override
    public List<MpAuthHVo> search() {
        List<MpAuthDirection> mpAuthDirections = null;
        MpAuthDirectionExample mpAuthDirectionExample = new MpAuthDirectionExample();
        mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());
        mpAuthDirections = mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);
        List<MpAuthHVo> mpAuthHVos = new ArrayList<>();
        for (MpAuthDirection e : mpAuthDirections) {
            Long eid = e.getId();
            MpAuthDomainExample ex = new MpAuthDomainExample();
            ex.createCriteria().andAuthDirectionIdEqualTo(eid).andDeleFlagEqualTo(CommonEnum.USED.getCode());
            List<MpAuthDomain> mpAuthDomainList = mpAuthDomainMapper.selectByExample(ex);

            List<MpAuthDomainVo> mpAuthDomainVoList = new ArrayList<>();
            for (MpAuthDomain e1 : mpAuthDomainList) {
                MpAuthDomainVo vo1 = new MpAuthDomainVo();
                BeanCopy.copy(e1, vo1);
                mpAuthDomainVoList.add(vo1);
            }
            MpAuthHVo vo = new MpAuthHVo();
            BeanCopy.copy(e, vo);
            vo.setMpAuthDomainVos(mpAuthDomainVoList);
            mpAuthHVos.add(vo);
        }

        return mpAuthHVos;
    }



    /**
     * 查询认证领域
     */
    @Override
    public List<MpAuthDomainVo> searchDomain(MpNameIdsDto dto) {

          Long id = dto.getId();


        MpAuthDomainExample ex  =null;

        if(id==null){
            ex = new MpAuthDomainExample();
            ex.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());

        }
        else{
            ex = new MpAuthDomainExample();
            ex.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andAuthDirectionIdEqualTo(id);
        }


        List<MpAuthDomain> mpAuthDomains = mpAuthDomainMapper.selectByExample(ex);
        List<MpAuthDomainVo> vos = new ArrayList<>();
        for (MpAuthDomain ent : mpAuthDomains) {
            MpAuthDomainVo vo = new MpAuthDomainVo();
            BeanCopy.copy(ent, vo);
            vos.add(vo);
        }
        return vos;

    }


    /**
     * 根据id删除认证方向以及认证方向下的领域
     */

    public Integer deleteByIds(MpNameIdsDto dto) {
        List<Long> ids = dto.getIds();
        if (null != ids && ids.size() > 0) {
            for (Long id : ids) {
                MpAuthDirection mpAuthDirection = mpAuthDirectionMapper.selectByPrimaryKey(id);
                mpAuthDirection.setUpdateUser(dto.getUserId());
                mpAuthDirection.setUpdateTime(new Date());
                mpAuthDirection.setDeleFlag(CommonEnum.DELETE.getCode());
                mpAuthDirectionMapper.updateByPrimaryKeySelective(mpAuthDirection);
                MpAuthDomainExample example = new MpAuthDomainExample();
                example.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode())
                        .andAuthDirectionIdEqualTo(mpAuthDirection.getId());
                List<MpAuthDomain> mpAuthDomains = mpAuthDomainMapper.selectByExample(example);
                for (MpAuthDomain e : mpAuthDomains) {
                    e.setDeleFlag(CommonEnum.DELETE.getCode());
                    mpAuthDomainMapper.updateByPrimaryKeySelective(e);
                }
            }
        }
        return 1;
    }

}
