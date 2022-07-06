package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.TypeEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.classifyDto.ClassifyDelSecondDto;
import com.springboot.boot.modules.admin.dto.curriculum.CurriculumAddOrUpdateDto;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.CurriculumMapper;
import com.springboot.boot.modules.admin.mapper.MpCurriculumMapper;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.ClassifyService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CurriculumServiceImpl
 * @Description TODO 课程管理的业务
 * @Author jhzhou
 * @Date 2022/3/14 0014 10:19
 * @Version 1.0
 **/
@Service
@Slf4j
public class CurriculumServiceImpl implements CurriculumService {

    @Resource
    private MpCurriculumMapper mapper;
    @Resource
    private CurriculumMapper curriculumMapper;
    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private AttachmentService attachmentService;
    /**
     * 课程的新增以及修改
     * 枚举 {@link TypeEnum}
     * 1.通过主键id判断为新增还是修改 {@code dto}
     * 2.新增或者编辑附件业务表{@link AttachmentService#addBusinessFile(Long , String, Long)}
     * @param dto
     * @return
     */
    @Override
    public ApiResult addOrUpdate(CurriculumAddOrUpdateDto dto) {
        //beanCopy
        MpCurriculum mpCurriculum = new MpCurriculum();
        BeanCopy.copy(dto, mpCurriculum);
        mpCurriculum.setUpdateTime(new Date());
        mpCurriculum.setUpdateUser(dto.getUserId());
        mpCurriculum.setDeleFlag(CommonEnum.USED.getCode());
         //根据主键id判断新增还是修改
         if (null != dto.getId()){
             MpCurriculumExample example = new MpCurriculumExample();
             MpCurriculumExample.Criteria criteria = example.createCriteria();
             criteria.andIdNotEqualTo(dto.getId());
             criteria.andCurriculumNameEqualTo(dto.getCurriculumName());
             List<MpCurriculum> mpCurriculumList = mapper.selectByExample(example);
             if (mpCurriculumList.size()>0){
                 return ApiResult.error(ApiCode.FAIL,"课程名称重复无法编辑");
             }
             int i = mapper.updateByPrimaryKey(mpCurriculum);
             if (i <= CommonEnum.UPDATE_ERROR.getCode()){
                 throw new BusinessException("课程编辑失败");
             }
         }else{
             //通过课程名称查询课程
             MpCurriculum curriculum = curriculumService.selectByName(dto.getCurriculumName());
             if (null != curriculum){
                 return ApiResult.error(ApiCode.FAIL,"课程名称重复无法新增");
             }
             SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
             mpCurriculum.setId(snowFlakeUtil.nextId());
             mpCurriculum.setCreateTime(new Date());
             mpCurriculum.setCreateUser(dto.getUserId());
             int i = mapper.insertSelective(mpCurriculum);
             if (i <= CommonEnum.ADD_ERROR.getCode()){
                 throw new BusinessException("课程新增失败");
             }
         }
        attachmentService.addBusinessFile(dto.getFileId(), TypeEnum.CLASS.getCode(),mpCurriculum.getId());
        return ApiResult.success();
    }

    /**
     * 分页查询课程相关信息
     * 1.查询课程相关信息并且查询分类相关信息
     * @param dto
     * @return
     */
    @Override
    public PageInfo<CurriculumVo> search(SearchCurriculumDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        List<CurriculumVo> mpCurricula = curriculumMapper.selectAll(dto);
        log.info("课程查询输出报文===================={}",mpCurricula);
        if(!CollectionUtils.isEmpty(mpCurricula)){
        mpCurricula.forEach(e->{
            //2.0新增认证分类===========================================
            if (null!=e.getPropertyType()&&e.getPropertyType().intValue() == CommonEnum.CURR_AUTH.getCode()){
                MpFirstClassify mpFirstClassify = classifyService.searchFirstClassify(e.getAuthFirstClassifyId());
                MpSecondClassify secondClassify = classifyService.searchSecondClassifyById(e.getAuthSencondClassifyId());
                if (null != mpFirstClassify){
                    e.setFirstClassifyName(mpFirstClassify.getFirstClassifyName());
                }
                if (null != secondClassify){
                    e.setSecondClassifyName(secondClassify.getSecondClassifyName());
                }
            }else{
                if (null != e.getCustFirstClassifyId()){
                    MpFirstClassify mpFirstClassify = classifyService.searchFirstClassify(e.getCustFirstClassifyId());
                    MpSecondClassify secondClassify = classifyService.searchSecondClassifyById(e.getCustSecondClassifyId());
                    if (null != mpFirstClassify){
                        e.setCFirstClassifyName(mpFirstClassify.getFirstClassifyName());
                    }
                    if (null != secondClassify){
                        e.setCSecondClassifyName(secondClassify.getSecondClassifyName());
                    }

                }

                MpFirstClassify mpFirstClassify = classifyService.searchFirstClassify(e.getGenFirstClassifyId());
                MpSecondClassify secondClassify = classifyService.searchSecondClassifyById(e.getGenSecondClassifyId());
                if (null != mpFirstClassify){
                    e.setFirstClassifyName(mpFirstClassify.getFirstClassifyName());
                }
                if (null != secondClassify){
                    e.setSecondClassifyName(secondClassify.getSecondClassifyName());
                }

            }
        });}
        PageInfo<CurriculumVo> pageInfo = new PageInfo<>(mpCurricula);
        return pageInfo;
    }

    @Override
    public MpCurriculum searchById(Long id) {
        MpCurriculum mpCurriculum = mapper.selectByPrimaryKey(id);
        return mpCurriculum;
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    @Override
    public ApiResult delete(ClassifyDelSecondDto dto) {
        log.info("课程的删除=============id{}",dto.getIds());
        dto.getIds().forEach(e->{
            MpCurriculum mpCurriculum = new MpCurriculum();
            mpCurriculum.setId(e);
            mpCurriculum.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = mapper.updateByPrimaryKeySelective(mpCurriculum);
            if (i <= CommonEnum.DELETE_ERROR.getCode()){
                throw new BusinessException("课程删除失败");
            }
        });

        return ApiResult.success();
    }

    /**
     * 查询普通课程
     * @param id
     * @return
     */
    @Override
    public List<MpCurriculum> searchByClassId(List<Long> id) {
        MpCurriculumExample example = new MpCurriculumExample();
        example.createCriteria().andGenSecondClassifyIdIn(id).andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpCurriculum> mpCurricula = mapper.selectByExample(example);
        log.info("查询二级分类下的课程名称=============={}", JSONObject.toJSON(mpCurricula));
        return mpCurricula;
    }

    /**
     * 查询普通课程条件为{@code ClassFromat}
     * @param id
     * @return
     */
    @Override
    public List<MpCurriculum> searchByClassIdAndType(List<Long> id,Integer ClassFromat) {
        MpCurriculumExample example = new MpCurriculumExample();
        example.createCriteria().andGenSecondClassifyIdIn(id).andDeleFlagEqualTo(CommonEnum.USED.getCode()).andClassFormatEqualTo(ClassFromat);
        List<MpCurriculum> mpCurricula = mapper.selectByExample(example);
        log.info("查询二级分类下的课程名称=============={}",JSONObject.toJSON(mpCurricula));
        return mpCurricula;
    }

    @Override
    public List<MpCurriculum> searchByClassIdAndTypeAndName(List<Long> id, Integer ClassFromat, String className) {
        MpCurriculumExample example = new MpCurriculumExample();
        MpCurriculumExample.Criteria criteria = example.createCriteria();
        criteria.andGenSecondClassifyIdIn(id).andDeleFlagEqualTo(CommonEnum.USED.getCode()).andClassFormatEqualTo(ClassFromat);
        if (!className.isEmpty()){
            criteria.andCurriculumNameLike("%"+className+"%");
        }
        List<MpCurriculum> mpCurricula = mapper.selectByExample(example);
        log.info("查询二级分类下的课程名称=============={}",JSONObject.toJSON(mpCurricula));
        return mpCurricula;

    }

    /**
     * 定制课程
     * @param id andCustFirstClassifyIdIn
     * @return
     */
    @Override
    public List<MpCurriculum> searchByClassIdC(List<Long> id) {
        MpCurriculumExample example = new MpCurriculumExample();
        example.createCriteria().andCustSecondClassifyIdIn(id).andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpCurriculum> mpCurricula = mapper.selectByExample(example);
        log.info("查询二级分类下的课程名称=============={}",JSONObject.toJSON(mpCurricula));
        return mpCurricula;
    }

    /**
     * 定制课程 条件为{@code ClassFromat}
     * @param id andCustFirstClassifyIdIn
     * @return
     */
    @Override
    public List<MpCurriculum> searchByClassIdCAndType(List<Long> id,Integer ClassFromat) {
        MpCurriculumExample example = new MpCurriculumExample();
        example.createCriteria().andCustSecondClassifyIdIn(id).andDeleFlagEqualTo(CommonEnum.USED.getCode()).andClassFormatEqualTo(ClassFromat);;
        List<MpCurriculum> mpCurricula = mapper.selectByExample(example);
        log.info("查询二级分类下的课程名称=============={}",mpCurricula);
        return mpCurricula;
    }

    @Override
    public List<MpCurriculum> searchByClassIdCAndTypeAndName(List<Long> id, Integer ClassFromat, String className) {
        MpCurriculumExample example = new MpCurriculumExample();
        MpCurriculumExample.Criteria criteria = example.createCriteria();
        criteria.andCustSecondClassifyIdIn(id).andDeleFlagEqualTo(CommonEnum.USED.getCode()).andClassFormatEqualTo(ClassFromat);
        if (!className.isEmpty()){
            criteria.andCurriculumNameLike("%"+className+"%");
        }
        List<MpCurriculum> mpCurricula = mapper.selectByExample(example);
        log.info("查询二级分类下的课程名称=============={}",mpCurricula);
        return mpCurricula;
    }

    /**
     * 通过二级分类查询课程相关信息
     * @param id
     * @return
     */
    @Override
    public List<MpCurriculum> searchCurrBySecond(Long id,Integer classFormat,String className) {
        //查询出二级分类信息
        MpSecondClassify secondClassify = classifyService.searchSecondClassifyById(id);

        if (null == secondClassify){
            throw  new BusinessException("没有查询到该一级分类下的信息");
        }
        List<MpCurriculum> mpCurriculumList = new ArrayList<>();
        if (secondClassify.getSecondClassifyType() == CommonEnum.CTYPE.getCode()){
             mpCurriculumList = curriculumService.searchByClassIdCAndTypeAndName(Arrays.asList(id),classFormat,className);
        }else{
            mpCurriculumList = curriculumService.searchByClassIdAndTypeAndName(Arrays.asList(id),classFormat,className);
        }
        return mpCurriculumList;
    }

    @Override
    public MpCurriculum selectByName(String curriculumName) {
        MpCurriculumExample example = new MpCurriculumExample();
        example.createCriteria().andCurriculumNameEqualTo(curriculumName);
        List<MpCurriculum> mpCurriculumList = mapper.selectByExample(example);
        if (mpCurriculumList.size()>0){
            return mpCurriculumList.get(0);
        }
       return null;
    }
}
