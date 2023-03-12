package com.springboot.boot.modules.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Index.IndexDto;
import com.springboot.boot.modules.admin.dto.Index.IndexManageDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.IndexMangerService;
import com.springboot.boot.modules.admin.vo.Index.IndexManageVo;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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
public class IndexMangerServicelmpl implements IndexMangerService {

    @Resource
    private MpIndexMapper mpIndexMapper;
    @Resource
    private MpIndexManageMapper mpIndexManageMapper;


    /**
     * 自助列表2.0版本加入认证筛选条件
     *
     * @return
     **/
    @Override
    public ApiResult addOrUpdate(IndexManageDto indexManageDto) {
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();

        //无论是更新还是失败都要将以前的时候先逻辑删除
        List<IndexDto> indexDtos = indexManageDto.getIndexDtos();
        if (indexDtos != null && indexDtos.size() > 0) {
            for (IndexDto dto : indexDtos) {
                MpIndex mpIndex = new MpIndex();
                mpIndex.setId(dto.getId());
                mpIndex.setDelFlag(CommonEnum.DELETE.getCode());
                mpIndexMapper.updateByPrimaryKeySelective(mpIndex);
            }
        }
        //beanCopy
        MpIndexManage mpIndexManage = new MpIndexManage();
        BeanCopy.copy(indexManageDto, mpIndexManage);
        mpIndexManage.setDelFlag(CommonEnum.USED.getCode());
        mpIndexManage.setUpType(CommonEnum.UP.getCode());
        Long id =   null;
        //根据主键id判断新增还是修改
        if (null != indexManageDto.getId()) {
            //修改
            id  = indexManageDto.getId();
            mpIndexManage.setUpdateDate(new Date());
            mpIndexManage.setUpdateUser(null);
            //判断是否已经有了同名数据
            MpIndexManageExample example = new MpIndexManageExample();
            example.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode())
                    .andNameEqualTo(indexManageDto.getName()).andIdNotEqualTo(id);
            Long count = mpIndexManageMapper.countByExample(example);
            if (count.longValue() != 0) {
                throw new BusinessException("该指标名称已经存在不能重复添加");
            }
            int i = mpIndexManageMapper.updateByPrimaryKeySelective(mpIndexManage);
            if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("指标管理更新失败");
            }
        } else {
            id = snowFlakeUtil.nextId();
            mpIndexManage.setId(id);
            mpIndexManage.setCreateDate(new Date());
            mpIndexManage.setCreateUser(null);
            mpIndexManage.setUpdateDate(new Date());
            //判断是否已经有了同名数据
            MpIndexManageExample example = new MpIndexManageExample();
            example.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode())
                    .andNameEqualTo(indexManageDto.getName());
            Long count = mpIndexManageMapper.countByExample(example);
            if (count.longValue() != 0) {
                throw new BusinessException("该指标已经有管理数据请在原数据的基础上编辑！");
            }
            int i = mpIndexManageMapper.insertSelective(mpIndexManage);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("指标管理新增失败");
            }
        }
        for (IndexDto dto : indexDtos) {
            MpIndex index = new MpIndex();
            BeanCopy.copy(dto, index);
            index.setManageId(id);
            index.setId(snowFlakeUtil.nextId());
            index.setDelFlag(CommonEnum.USED.getCode());
            index.setCreateDate(new Date());
            index.setUpdateDate(new Date());
            mpIndexMapper.insertSelective(index);
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult list(@RequestBody IndexManageDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        MpIndexManageExample example = new MpIndexManageExample();
        if (null != dto.getName() && !("").equals(dto.getName())) {
            example.createCriteria().andNameLike(dto.getName())
                    .andDelFlagEqualTo(CommonEnum.USED.getCode());
        } else {
            example.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode());
        }
        List<MpIndexManage> mpIndexManageList = mpIndexManageMapper.selectByExample(example);
        List<IndexManageVo> indexManageVoList = new ArrayList<>();

        if (null != mpIndexManageList && mpIndexManageList.size() > 0)
            for (MpIndexManage entity : mpIndexManageList) {
                IndexManageVo vo = new IndexManageVo();
                BeanCopy.copy(entity, vo);
                Long id = entity.getId();
                MpIndexExample example1 = new MpIndexExample();
                example1.createCriteria().andDelFlagEqualTo(CommonEnum.USED.getCode()).andManageIdEqualTo(id);
                List<MpIndex> mpIndexList = mpIndexMapper.selectByExample(example1);
                vo.setIndexDtos(mpIndexList);
                indexManageVoList.add(vo);
            }
        PageInfo<IndexManageVo> pageInfo = new PageInfo<>(indexManageVoList);
        return ApiResult.success(pageInfo);

    }
    @Override
    public ApiResult delete(IndexManageDto dto) {
        List<Long>  ids =    dto.getIds();
        if(null!=ids&&ids.size()>0){
            for( Long id:  ids){
            MpIndexManage mpIndexManage = new MpIndexManage();
            mpIndexManage.setId(id);
            mpIndexManage.setDelFlag(CommonEnum.DELETE.getCode());
            mpIndexManage.setUpdateDate(new Date());
            int i =   mpIndexManageMapper.updateByPrimaryKeySelective(mpIndexManage);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("指标管理删除失败");
            }
        }}
        return ApiResult.success();
    }



}
