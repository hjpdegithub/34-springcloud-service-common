package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;
import com.springboot.boot.modules.admin.dto.Auth.MpNameDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDirectionDto;
import com.springboot.boot.modules.admin.dto.AuthClassify.MpAuthDomainDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AuthClassifyService;
import com.springboot.boot.modules.admin.service.AuthService;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.auth.MpAuthDomainVo;
import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.modules.admin.vo.test.MpExamAchievementMultiVo;
import com.springboot.boot.utils.ApiCode;
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
                if (null != eoldIdList && eoldIdList.size() > 0) {
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
                    ent.setCreateTime(new Date());
                    ent.setCreateUser(dto.getId());
                    mpAuthDomainMapper.insertSelective(ent);
                }
            }
            //需要逻辑删除
            for (MpAuthDomain eo : eoldList) {
                if (!enewIdList.contains(eo.getId())) {
                    eo.setUpdateUser(dto.getUserId());
                    eo.setUpdateTime(new Date());
                    eo.setDeleFlag(CommonEnum.DELETE.getCode());
                    mpAuthDomainMapper.updateByPrimaryKeySelective(eo);
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
    public PageInfo<MpAuthHVo> searchByAuthdirectionName(MpNameDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        String authdirectionName = dto.getName();
        List<MpAuthDirection> mpAuthDirections = null;
        MpAuthDirectionExample mpAuthDirectionExample = null;
        if (authdirectionName == null) {
            mpAuthDirectionExample = new MpAuthDirectionExample();
            mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());
            mpAuthDirections = mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);
        }
        if (null != authdirectionName) {
            mpAuthDirectionExample = new MpAuthDirectionExample();
            mpAuthDirectionExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode()).andNameEqualTo(authdirectionName);

            mpAuthDirections= mpAuthDirectionMapper.selectByExample(mpAuthDirectionExample);

        }

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
        PageInfo<MpAuthHVo> pageInfo = new PageInfo<>(mpAuthHVos);
        return pageInfo;
    }


    /**
     * 根据一级分类id查看二级分类信息
     *
     * @param id 一级分类id
     * @return
     */
    @Override
    public List<MpSecondClassify> searchByClassifyId(Long id) {
        MpSecondClassifyExample example = new MpSecondClassifyExample();
        example.createCriteria().andFirstClassifyIdEqualTo(id).andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpSecondClassify> mpSecondClassifies = secondClassifyMapper.selectByExample(example);

        log.info("根据一级分类id查看二级分类下的信息返回报文输出=================" + JSONObject.toJSONString(mpSecondClassifies));
        return mpSecondClassifies;
    }

    /**
     * 查询出一二级分类所有信息
     * 一级分类调用了 mapClassifyAll
     *
     * @return
     */
    @Override
    public List<ClassifyAllVo> searchClassifyAll(Integer classifyType) {

        List<ClassifyAllVo> result = new ArrayList<>();
        Map<Long, MpFirstClassify> firstClassifies = mapClassifyAll(classifyType);
        log.info("一级分类相关信息输出返回报文：--{}", JSONObject.toJSON(firstClassifies));
        //循环map查询出二级分类信息
        for (Long key : firstClassifies.keySet()) {//key为主键id，value为一级分类对象
            ClassifyAllVo classifyAllVo = new ClassifyAllVo();
            MpFirstClassify value = firstClassifies.get(key);
            BeanCopy.copy(value, classifyAllVo);
            classifyAllVo.setSecondClassifyName(value.getFirstClassifyName());
            //根据一级分类id查询出二级分类信息集合
            MpSecondClassifyExample example = new MpSecondClassifyExample();
            MpSecondClassifyExample.Criteria criteria = example.createCriteria();
            criteria.andFirstClassifyIdEqualTo(key);
            if (null != classifyType) {
                criteria.andSecondClassifyTypeEqualTo(classifyType);
            }
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            List<MpSecondClassify> mpSecondClassifies = secondClassifyMapper.selectByExample(example);
            log.info("二级分类信息集合输出======================》{}", JSONObject.toJSON(mpSecondClassifies));
            classifyAllVo.setClassifies(mpSecondClassifies);
            result.add(classifyAllVo);
        }
        return result;
    }

    /**
     * 查看出所有一级分类集合并封装成map
     */
    @Override
    public Map<Long, MpFirstClassify> mapClassifyAll(Integer classifyType) {
        //查看一级分类所有相关信息
        MpFirstClassifyExample firstClassifyExample = new MpFirstClassifyExample();
        final MpFirstClassifyExample.Criteria criteria = firstClassifyExample.createCriteria();
        if (null != classifyType) {
            criteria.andFirstClassifyTypeEqualTo(classifyType);

        }
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpFirstClassify> firstClassifies = firstClassifyMapper.selectByExample(firstClassifyExample);
        if (firstClassifies.isEmpty()) {
            throw new BusinessException("一级分类信息为空,无法展示分类信息");
        }
        log.info("一级分类相关信息输出返回报文：--{}", JSONObject.toJSON(firstClassifies));
        return firstClassifies.stream().collect(Collectors.toMap(MpFirstClassify::getId, firstClassify -> firstClassify));
    }

    @Override
    public ApiResult deleteByids(ClassifyDelSecondDto dto) {
        //区分是定制还是普通分类
        List<Long> ids = dto.getIds();
        MpSecondClassifyExample example = new MpSecondClassifyExample();
        example.createCriteria().andIdIn(ids);
        List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(example);
        secondClassifies.forEach(e -> {
            //查询二级分类下的课程信息
            List<MpCurriculum> mpCurricula = new ArrayList<>();
            if (e.getSecondClassifyType() == CommonEnum.CTYPE.getCode()) {
                mpCurricula = curriculumService.searchByClassIdC(Arrays.asList(e.getId()));

            } else {
                mpCurricula = curriculumService.searchByClassId(Arrays.asList(e.getId()));
            }
            if (!mpCurricula.isEmpty()) {
                StringBuilder name = new StringBuilder();
                mpCurricula.forEach(i -> {
                    name.append(i.getCurriculumName());

                });
                throw new BusinessException("该二级下存在课程名称为：" + name);
            }
        });

        ids.forEach(e -> {
            MpSecondClassify secondClassify = new MpSecondClassify();
            secondClassify.setId(e);
            secondClassify.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = secondClassifyMapper.updateByPrimaryKeySelective(secondClassify);
            if (i <= CommonEnum.DELETE_ERROR.getCode())
                throw new BusinessException("二级级分类删除失败");
        });

        return ApiResult.success();
    }

    @Override
    public ApiResult deleteById(ClassifyDelFirstDto dto) {
        List<MpSecondClassify> mpSecondClassifies = searchByClassifyId(dto.getId());
        if (!mpSecondClassifies.isEmpty()) {
            throw new BusinessException("该一级分类下存在二级分类信息无法删除");
        }
        MpFirstClassify mpFirstClassify = new MpFirstClassify();
        mpFirstClassify.setId(dto.getId());
        mpFirstClassify.setDeleFlag(CommonEnum.DELETE.getCode());
        int i = firstClassifyMapper.updateByPrimaryKeySelective(mpFirstClassify);
        if (i <= CommonEnum.DELETE_ERROR.getCode()) {
            throw new BusinessException("删除一级分类失败");
        }
        return ApiResult.success();
    }

    @Override
    public List<MpFirstClassify> searchFristClassify() {
        //查看一级分类所有相关信息
        MpFirstClassifyExample firstClassifyExample = new MpFirstClassifyExample();
        firstClassifyExample.createCriteria().andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpFirstClassify> firstClassifies = firstClassifyMapper.selectByExample(firstClassifyExample);
        if (firstClassifies.isEmpty()) {
            throw new BusinessException("一级分类信息为空,无法展示分类信息");
        }
        //判断一级分类下是否有二级分类没有删除一级分类
        firstClassifies.forEach(e -> {
            MpSecondClassifyExample mpSecondClassifyExample = new MpSecondClassifyExample();
            MpSecondClassifyExample.Criteria criteria = mpSecondClassifyExample.createCriteria();
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            criteria.andFirstClassifyIdEqualTo(e.getId());
            List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(mpSecondClassifyExample);
            if (secondClassifies.isEmpty()) {
                MpFirstClassify mpFirstClassify = new MpFirstClassify();
                mpFirstClassify.setId(e.getId());
                mpFirstClassify.setDeleFlag(CommonEnum.DELETE.getCode());
                mpFirstClassify.setUpdateTime(new Date());
                firstClassifyMapper.updateByPrimaryKeySelective(mpFirstClassify);
            }
        });
        log.info("一级分类相关信息输出返回报文：--{}", JSONObject.toJSON(firstClassifies));
        return firstClassifies;
    }

    @Override
    public PageInfo<MpSecondClassify> searchSecondClassify(ClassifySearchAllDto dto) {

        if (null == dto.getFirstClassifyId()) {
            throw new BusinessException("一级分类id为空");
        }
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        MpSecondClassifyExample example = new MpSecondClassifyExample();
        example.createCriteria()
                .andFirstClassifyIdEqualTo(dto.getFirstClassifyId())
                .andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpSecondClassify> mpSecondClassifies = secondClassifyMapper.selectByExample(example);
        log.info("根据一级分类id查询二级分类信息输出======================={}", JSONObject.toJSON(mpSecondClassifies));

        PageInfo<MpSecondClassify> pageInfo = new PageInfo<>(mpSecondClassifies);
        return pageInfo;
    }

    /**
     * 根据分类id查询一级分类信息
     *
     * @param id
     * @return
     */
    @Override
    public MpFirstClassify searchFirstClassify(Long id) {
        MpFirstClassify mpFirstClassify = firstClassifyMapper.selectByPrimaryKey(id);
        return mpFirstClassify;
    }

    @Override
    public MpSecondClassify searchSecondClassifyById(Long id) {
        MpSecondClassify secondClassify = secondClassifyMapper.selectByPrimaryKey(id);
        return secondClassify;
    }

    @Override
    public List<MpSecondClassify> searchSecondClassifyAll() {
        MpSecondClassifyExample example = new MpSecondClassifyExample();
        List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(example);
        return secondClassifies;
    }

    @Override
    public List<MpFirstClassify> searchFristClassifyNoAuth() {
        //查看一级分类所有相关信息
        MpFirstClassifyExample firstClassifyExample = new MpFirstClassifyExample();
        MpFirstClassifyExample.Criteria criteria1 = firstClassifyExample.createCriteria();
        criteria1.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        //2.0版本加入认证条件
        criteria1.andFirstClassifyTypeNotEqualTo(CommonEnum.AUTH.getCode());
        List<MpFirstClassify> firstClassifies = firstClassifyMapper.selectByExample(firstClassifyExample);
        if (firstClassifies.isEmpty()) {
            throw new BusinessException("一级分类信息为空,无法展示分类信息");
        }
        //判断一级分类下是否有二级分类没有删除一级分类
        firstClassifies.forEach(e -> {
            MpSecondClassifyExample mpSecondClassifyExample = new MpSecondClassifyExample();
            MpSecondClassifyExample.Criteria criteria = mpSecondClassifyExample.createCriteria();
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            criteria.andFirstClassifyIdEqualTo(e.getId());
            List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(mpSecondClassifyExample);
            if (secondClassifies.isEmpty()) {
                MpFirstClassify mpFirstClassify = new MpFirstClassify();
                mpFirstClassify.setId(e.getId());
                mpFirstClassify.setDeleFlag(CommonEnum.DELETE.getCode());
                mpFirstClassify.setUpdateTime(new Date());
                firstClassifyMapper.updateByPrimaryKeySelective(mpFirstClassify);
            }
        });
        log.info("一级分类相关信息输出返回报文：--{}", JSONObject.toJSON(firstClassifies));
        return firstClassifies;
    }

}
