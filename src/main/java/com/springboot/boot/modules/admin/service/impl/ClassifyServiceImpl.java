package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelFirstDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifySearchAllDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.MpFirstClassifyMapper;
import com.springboot.boot.modules.admin.mapper.MpSecondClassifyMapper;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.classify.ClassifyAllVo;
import com.springboot.boot.modules.admin.vo.classify.FirstClassifyVo;
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
public class ClassifyServiceImpl implements ClassifyService {

    @Resource
    private MpFirstClassifyMapper firstClassifyMapper;
    @Resource
    private MpSecondClassifyMapper secondClassifyMapper;

    @Autowired
    private CurriculumService curriculumService;

    /**
     * 分类的新增以及修改
     *
     * @param dto
     * @return {@link ApiResult}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult addOrUpdate(ClassifyAddOrUpdateDto dto) {
        //雪花
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //====================一及分类实体==================-=====
        MpFirstClassify mpFirstClassify = new MpFirstClassify();
        mpFirstClassify.setUpdateTime(new Date());
        mpFirstClassify.setUpdateUser(dto.getUserId());
        mpFirstClassify.setFirstClassifyDescr(dto.getFirstClassifyDescr());
        mpFirstClassify.setFirstClassifyName(dto.getFirstClassifyName());
        mpFirstClassify.setFirstClassifyType(dto.getClassifyType());
        //=================二级分类实体============================
        MpSecondClassify mpSecondClassify = new MpSecondClassify();
        mpSecondClassify.setSecondClassifyType(dto.getClassifyType());
        mpSecondClassify.setUpdateTime(new Date());
        mpSecondClassify.setUpdateUser(dto.getUserId());
        //判断新增还是修改
        if (null != dto.getId()) {
            dto.getAddOrUpdateDtos().forEach(e->{
                if (null==e.getSecondClassifyName()){

                    throw new BusinessException("二级分类名称为空请填写");
                }
            });
            //编辑逻辑
            mpFirstClassify.setId(dto.getId());
            int firstUpdate = firstClassifyMapper.updateByPrimaryKeySelective(mpFirstClassify);
            if (firstUpdate <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("一级级分类修改失败");
            }
            //TODO 编辑一级分类的时候需要 编辑二级下所有分类的类型（后期优化）================================开始
            MpSecondClassifyExample example = new MpSecondClassifyExample();
            example.createCriteria().andFirstClassifyIdEqualTo(dto.getId());
            List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(example);
            secondClassifies.forEach(e->{
                MpSecondClassify allSecondClassify = new MpSecondClassify();
                allSecondClassify.setId(e.getId());
                allSecondClassify.setSecondClassifyType(dto.getClassifyType());
                int i = secondClassifyMapper.updateByPrimaryKeySelective(allSecondClassify);
                if (i <= CommonEnum.UPDATE_ERROR.getCode()){
                    throw new BusinessException("修改二级所有的分类类型修改失败！");
                }
            });
            //TODO ===================================================结束=============
            dto.getAddOrUpdateDtos().forEach(e -> {
                mpSecondClassify.setFirstClassifyId(dto.getId());
                mpSecondClassify.setSecondClassifyType(dto.getClassifyType());
                mpSecondClassify.setSecondClassifyName(e.getSecondClassifyName());
                mpSecondClassify.setSecondClassifyDescr(e.getSecondClassifyDescr());
                //判断二级分类编辑的时候id是否为空id为空的话编辑加入了新的二级分类需要进行新增
                if (null != e.getId()) {
                    mpSecondClassify.setId(e.getId());
                    int secondUpdate = secondClassifyMapper.updateByPrimaryKeySelective(mpSecondClassify);
                    if (secondUpdate <= CommonEnum.UPDATE_ERROR.getCode()) {
                        throw new BusinessException("二级级分类修改失败");
                    }
                } else {
                    mpSecondClassify.setId(snowFlakeUtil.nextId());
                    mpSecondClassify.setCreateUser(dto.getUserId());
                    mpSecondClassify.setCreateTime(new Date());
                    mpSecondClassify.setDeleFlag(CommonEnum.USED.getCode());
                    int secondAdd = secondClassifyMapper.insertSelective(mpSecondClassify);
                    if (secondAdd <= CommonEnum.ADD_ERROR.getCode()) {
                        throw new BusinessException("二级级分类修改失败");
                    }
                }
            });
        } else {
            List<MpFirstClassify> classifyApiResult = searchByClassifyName(dto.getFirstClassifyName());
            if (!classifyApiResult.isEmpty()){
                return ApiResult.error(ApiCode.FAIL.getCode(),"一级分类名称相同无法进行新增");
            }

            long firstId = snowFlakeUtil.nextId();//一级分类id
            //新增逻辑
            mpFirstClassify.setId(firstId);
            mpFirstClassify.setCreateTime(new Date());
            mpFirstClassify.setCreateUser(dto.getUserId());
            mpFirstClassify.setDeleFlag(0);
            int firstAdd = firstClassifyMapper.insertSelective(mpFirstClassify);
            if (firstAdd <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("一级级分类新增失败");
            }
            //添加二级分类
            dto.getAddOrUpdateDtos().forEach(e -> {
                mpSecondClassify.setId(snowFlakeUtil.nextId());
                mpSecondClassify.setCreateUser(dto.getUserId());
                mpSecondClassify.setCreateTime(new Date());
                mpSecondClassify.setDeleFlag(CommonEnum.USED.getCode());
                mpSecondClassify.setSecondClassifyDescr(e.getSecondClassifyDescr());
                mpSecondClassify.setSecondClassifyName(e.getSecondClassifyName());
                mpSecondClassify.setSecondClassifyType(dto.getClassifyType());
                mpSecondClassify.setFirstClassifyId(firstId);
                int secondAdd = secondClassifyMapper.insertSelective(mpSecondClassify);
                if (secondAdd <= CommonEnum.ADD_ERROR.getCode()) {
                    throw new BusinessException("二级分类新增失败");
                }
            });
        }
        return ApiResult.success();
    }

    /**
     * 根据名称查看一级分类信息
     *
     * @param firstClassifyName 一级分类名称
     * @return
     */
    @Override
    public List<MpFirstClassify> searchByClassifyName(String firstClassifyName) {
        MpFirstClassifyExample classifyExample = new MpFirstClassifyExample();
        classifyExample.createCriteria().andFirstClassifyNameEqualTo(firstClassifyName);
        List<MpFirstClassify> mpFirstClassifies = firstClassifyMapper.selectByExample(classifyExample);
        log.info("根据名称查看一级分类下的信息返回报文输出=================" + JSONObject.toJSONString(mpFirstClassifies));
        return mpFirstClassifies;
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
     * @return
     */
    @Override
    public List<ClassifyAllVo> searchClassifyAll(Integer classifyType) {

        List<ClassifyAllVo> result = new ArrayList<>();
        Map<Long, MpFirstClassify> firstClassifies = mapClassifyAll(classifyType);
        log.info("一级分类相关信息输出返回报文：--{}", JSONObject.toJSON(firstClassifies));
        //循环map查询出二级分类信息
        for(Long key:firstClassifies.keySet()){//key为主键id，value为一级分类对象
            ClassifyAllVo classifyAllVo = new ClassifyAllVo();
            MpFirstClassify value = firstClassifies.get(key);
            BeanCopy.copy(value,classifyAllVo);
            classifyAllVo.setSecondClassifyName(value.getFirstClassifyName());
            //根据一级分类id查询出二级分类信息集合
            MpSecondClassifyExample example = new MpSecondClassifyExample();
            MpSecondClassifyExample.Criteria criteria = example.createCriteria();
            criteria.andFirstClassifyIdEqualTo(key);
            if (null != classifyType){
                criteria.andSecondClassifyTypeEqualTo(classifyType);
            }
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            List<MpSecondClassify> mpSecondClassifies = secondClassifyMapper.selectByExample(example);
            log.info("二级分类信息集合输出======================》{}",JSONObject.toJSON(mpSecondClassifies));
            classifyAllVo.setClassifies(mpSecondClassifies);
            result.add(classifyAllVo);
        }
        return result;
    }
    /**
     *查看出所有一级分类集合并封装成map
     */
    @Override
    public Map<Long,MpFirstClassify > mapClassifyAll(Integer classifyType) {
        //查看一级分类所有相关信息
        MpFirstClassifyExample firstClassifyExample = new MpFirstClassifyExample();
        final MpFirstClassifyExample.Criteria criteria = firstClassifyExample.createCriteria();
        if (null != classifyType){
            criteria.andFirstClassifyTypeEqualTo(classifyType);

        }
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpFirstClassify> firstClassifies = firstClassifyMapper.selectByExample(firstClassifyExample);
        if (firstClassifies.isEmpty()){
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
        secondClassifies.forEach(e->{
            //查询二级分类下的课程信息
            List<MpCurriculum> mpCurricula = new ArrayList<>();
            if (e.getSecondClassifyType() == CommonEnum.CTYPE.getCode()){
                mpCurricula = curriculumService.searchByClassIdC(Arrays.asList(e.getId()));

            }else{
                mpCurricula = curriculumService.searchByClassId(Arrays.asList(e.getId()));
            }
            if (!mpCurricula.isEmpty()){
                StringBuilder name = new StringBuilder();
                mpCurricula.forEach(i->{
                    name.append(i.getCurriculumName());

                });
                throw new BusinessException("该二级下存在课程名称为："+name);
            }
        });

        ids.forEach(e->{
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
        if (!mpSecondClassifies.isEmpty()){
            throw new BusinessException("该一级分类下存在二级分类信息无法删除");
        }
        MpFirstClassify mpFirstClassify = new MpFirstClassify();
        mpFirstClassify.setId(dto.getId());
        mpFirstClassify.setDeleFlag(CommonEnum.DELETE.getCode());
        int i = firstClassifyMapper.updateByPrimaryKeySelective(mpFirstClassify);
        if (i <= CommonEnum.DELETE_ERROR.getCode()){
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
        if (firstClassifies.isEmpty()){
            throw new BusinessException("一级分类信息为空,无法展示分类信息");
        }
        //判断一级分类下是否有二级分类没有删除一级分类
        firstClassifies.forEach(e->{
            MpSecondClassifyExample mpSecondClassifyExample = new MpSecondClassifyExample();
            MpSecondClassifyExample.Criteria criteria = mpSecondClassifyExample.createCriteria();
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            criteria.andFirstClassifyIdEqualTo(e.getId());
            List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(mpSecondClassifyExample);
            if (secondClassifies.isEmpty()){
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

        if (null == dto.getFirstClassifyId()){
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
        log.info("根据一级分类id查询二级分类信息输出======================={}",JSONObject.toJSON(mpSecondClassifies));

        PageInfo<MpSecondClassify> pageInfo = new PageInfo<>(mpSecondClassifies);
        return pageInfo;
    }

    /**
     * 根据分类id查询一级分类信息
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
        if (firstClassifies.isEmpty()){
            throw new BusinessException("一级分类信息为空,无法展示分类信息");
        }
        //判断一级分类下是否有二级分类没有删除一级分类
        firstClassifies.forEach(e->{
            MpSecondClassifyExample mpSecondClassifyExample = new MpSecondClassifyExample();
            MpSecondClassifyExample.Criteria criteria = mpSecondClassifyExample.createCriteria();
            criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
            criteria.andFirstClassifyIdEqualTo(e.getId());
            List<MpSecondClassify> secondClassifies = secondClassifyMapper.selectByExample(mpSecondClassifyExample);
            if (secondClassifies.isEmpty()){
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
