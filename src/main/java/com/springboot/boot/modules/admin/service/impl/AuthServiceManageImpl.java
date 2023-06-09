package com.springboot.boot.modules.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.Auth.MpAuthDto;

import com.springboot.boot.modules.admin.dto.Auth.MpNameIdsDto;
import com.springboot.boot.modules.admin.dto.exanmake.MakerPaperButtonDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.AuthManageService;

import com.springboot.boot.modules.admin.vo.auth.CertificateVo;
import com.springboot.boot.modules.admin.vo.auth.MpAuthHVo;
import com.springboot.boot.modules.admin.vo.test.MpUserAuthenticationVo;
import com.springboot.boot.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private MpExaminationMapper examinationMapper;
    @Resource
    private MpAuthHMapper mpAuthHMapper;
    @Resource
    private MpUserAuthExamMapper userAuthExamMapper;
    @Resource
    private MpUserAuthMapper userAuthMapper;
    @Resource
    private MpAttachmentInfoMapper mpAttachmentInfoMapper;
    @Resource
    private MpAuthMapper mpAuthMapper;
    @Resource
    private MpBusinessAttachmentInfoMapper mpBusinessAttachmentInfoMapper;
    @Resource
    private MpUserAuthenticationMapper mpUserAuthenticationMapper;
    @Resource
    private MpAuthCertificaseMapper mpAuthCertificaseMapper;
    /**
     * 分类的新增以及修改
     *
     * @param dto
     * @return {@link ApiResult}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult addOrUpdate(MpAuthDto dto) {
        Long fileid = dto.getFileId();
        if (fileid == null && fileid == 0) {
            throw new BusinessException("没有上传证书图样！");
        }
        //新增文件信息
        //雪花
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        //====================创建认证实体==================-=====
        MpAuth mpAuth = new MpAuth();
        //数据赋值
        BeanCopy.copy(dto, mpAuth);
        MpBusinessAttachmentInfo mpBusinessAttachmentInfo
                = new MpBusinessAttachmentInfo();
        mpBusinessAttachmentInfo.setId(snowFlakeUtil.nextId());
        mpBusinessAttachmentInfo.setBusinessId(mpAuth.getId());
        mpBusinessAttachmentInfo.setCreateUser(dto.getUserId());
        mpBusinessAttachmentInfo.setCreateTime(new Date());
        mpBusinessAttachmentInfo.setBusiness("AuthInfo");
        mpBusinessAttachmentInfo.setDelFlag(CommonEnum.USED.getCode());
        mpBusinessAttachmentInfo.setAttachmentId(dto.getFileId());
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
            mpAuth.setCreateUser(dto.getUserId());
            mpAuth.setDeleFlag(CommonEnum.USED.getCode());
            mpAuth.setUpType(CommonEnum.NO_UP.getCode());
            mpBusinessAttachmentInfo.setBusinessId(mpAuth.getId());
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

    public List<MpAuthHVo> searchNoPage(MpAuthDto dto) {
        List<MpAuthHVo> mpAuthHVos = mpAuthHMapper.selectAllMpAuthsList(dto);
        log.info("查询认证下拉菜单===================={}", dto);
        return mpAuthHVos;
    }


    /**
     * 分页查询认证前端
     *
     * @param dto
     * @return
     */
    public PageInfo<MpAuthHVo> searchForFront(MpAuthDto dto) {
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
        List<MpAuthHVo> mpAuthHVos = mpAuthHMapper.searchForFront(dto);
        log.info("分页查询认证===================={}", dto);
        PageInfo<MpAuthHVo> pageInfo = new PageInfo<>(mpAuthHVos);
        return pageInfo;
    }

    @Override
    public List<MpAuthHVo> myAuthSearch(MpAuthDto dto) {
        if (null == dto.getUserId()) {
            throw new BusinessException("请传入用户ID");
        }
        List<MpAuthHVo> mpAuthHVos = mpAuthHMapper.selectMyMpAuths(dto);
        log.info("分页查询认证===================={}", dto);
        return mpAuthHVos;
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
        //获取证书详情
        MpAttachmentInfo info = getfileInfoByCerId(dto.getId());
        vo.setFileInfo(info);
        return vo;
    }

    public MpAttachmentInfo getfileInfoByCerId(Long id) {
        Long idt = mpAuthHMapper.selectFileId(id);
        String fileName = null;
        String fileUrl = null;
        String filePath = null;
        MpAttachmentInfo info1 = null;
        if (null != idt) {
            //根据id找到文件信息
            info1 = mpAttachmentInfoMapper.selectByPrimaryKey(idt);
            fileName = info1.getFileName();
            fileUrl = info1.getFileUrl();
            filePath = info1.getFilePath();
            //String fileUrlLocal = aliyunOSSUtil.ossToLocalToShow(null, filePath, fileName);
            String fileUrlLocal = aliyunOSSUtil.picOSSUpdtoShow(info1.getDocumentid(), info1.getFileName());
            // String fileUrlLocal = aliyunOSSUtil.picOSSUpdtoShow(null, filePath, fileName);
            info1.setFileUrl(fileUrl);
            info1.setFilePath(filePath);
            info1.setFileurllocal(fileUrlLocal);
            info1.setFileName(fileName);
        }
        return info1;
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



    @Transactional
    @Override
    public ApiResult certificateGetAuto(MakerPaperButtonDto makerPaperButtonDto) {


        //证书绑定2
        MpAuthCertificaseExample example = new MpAuthCertificaseExample();
        example.createCriteria().andAuthIdEqualTo(makerPaperButtonDto.getAuthId()).andUserIdEqualTo(makerPaperButtonDto.getUserId())
                .andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAuthCertificase> lis = mpAuthCertificaseMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(lis)) {
            //证书绑定1
            MpUserAuth ent = new MpUserAuth();
            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
            ent.setId(snowFlakeUtil.nextId());
            ent.setCreateTime(new Date());
            ent.setDeleFlag(CommonEnum.USED.getCode());
            ent.setCrateUser(makerPaperButtonDto.getUserId());
            ent.setAuthId(makerPaperButtonDto.getAuthId());
            ent.setUserId(makerPaperButtonDto.getUserId());
            userAuthMapper.insertSelective(ent);
            MpAuthCertificase ent2 = new MpAuthCertificase();
            ent2.setId(snowFlakeUtil.nextId());
            ent2.setCreateTime(new Date());
            ent2.setDeleFlag(CommonEnum.USED.getCode());
            ent2.setCrateUser(makerPaperButtonDto.getUserId());
            ent2.setAuthId(makerPaperButtonDto.getAuthId());
            ent2.setUserId(makerPaperButtonDto.getUserId());
            mpAuthCertificaseMapper.insertSelective(ent2);
        }

        return ApiResult.success();
    }
    /**
     * 证书信息
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CertificateVo certificateGet(MpNameIdsDto dto) {
        //认证id
        Long id = dto.getId();
        //证书所有者Id
        Long cerUserId = dto.getCerUserId() == null ? dto.getUserId() : dto.getCerUserId();

        if (null == cerUserId) {
            throw new BusinessException("userId或者认证者Id都是空");
        }
        //获取证书模板信息
        MpAttachmentInfo info = getfileInfoByCerId(id);
        //获取试卷id
        MpAuth infoT = mpAuthMapper.selectByPrimaryKey(id);
        //根据该证书id 没有找到书卷
        if (null == infoT) {
            throw new BusinessException("根据该证书id没有找到试卷");
        }
        Long examId = infoT.getExamId();
        List<MpExamination> mpExaminations = mpExaminations(examId);
        //考试规定次数
        Integer testRuleCounts = null;
        if (null != mpExaminations && mpExaminations.size() > 0) {
            testRuleCounts = mpExaminations.get(0).getFrequencyCount();
        } else {
            throw new BusinessException("试卷不存在");
        }
        //查看用户考试信息
        MpUserAuthExamExample mpUserAuthExamExample = new MpUserAuthExamExample();
        MpUserAuthExamExample.Criteria criteria = mpUserAuthExamExample.createCriteria();
        criteria.andUserIdEqualTo(cerUserId);
        criteria.andAuthIdEqualTo(id);
        criteria.andExamIdEqualTo(examId);
        List<MpUserAuthExam> mpUserAuthExams = userAuthExamMapper.selectByExample(mpUserAuthExamExample);
        Integer frequencyCount = mpExaminations.get(0).getFrequencyCount();
        List<MpUserAuthExam> userCommonList = mpUserAuthExams.stream().filter(a -> a.getIfWhether().intValue() == 1).collect(Collectors.toList());
        //开始判断
        if (mpUserAuthExams.size() >= frequencyCount.intValue() || userCommonList.size() > 0) {
            //有合格的考试成绩
        } else {
            throw new BusinessException("该用户没有及格的考试成绩");
        }
        MpUserAuthentication userInfo =
                mpUserAuthenticationMapper.selectByPrimaryKey(dto.getCerUserId());

        //获取认证信息
        MpUserAuthenticationVo vo = new MpUserAuthenticationVo();
        BeanCopy.copy(userInfo, vo);
        CertificateVo revo = new CertificateVo();
        revo.setFileUrl(info.getFileUrl());
        revo.setFileLocalUrl(info.getFileurllocal());
        revo.setUserVo(vo);
        revo.setCertName(infoT.getName());
        //证书绑定1
        MpUserAuth ent = new MpUserAuth();
        BeanCopy.copy(dto, ent);
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
        ent.setId(snowFlakeUtil.nextId());
        ent.setCreateTime(new Date());
        ent.setDeleFlag(CommonEnum.USED.getCode());
        ent.setCrateUser(dto.getUserId() == null ? dto.getCerUserId() : dto.getUserId());
        ent.setAuthId(dto.getId());
        ent.setUserId(dto.getCerUserId());
        userAuthMapper.insertSelective(ent);
        //证书绑定2
        MpAuthCertificaseExample example = new MpAuthCertificaseExample();
        example.createCriteria().andAuthIdEqualTo(dto.getId()).andUserIdEqualTo(cerUserId)
                .andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpAuthCertificase> lis = mpAuthCertificaseMapper.selectByExample(example);
        if (null == lis && lis.size() > 0) {
            throw new IllegalArgumentException("考生已经领取过证书");
        }
        MpAuthCertificase ent2 = new MpAuthCertificase();
        BeanCopy.copy(dto, ent2);
        ent2.setId(snowFlakeUtil.nextId());
        ent2.setCreateTime(new Date());
        ent2.setDeleFlag(CommonEnum.USED.getCode());
        ent2.setCrateUser(dto.getUserId() == null ? dto.getCerUserId() : dto.getUserId());
        ent2.setAuthId(dto.getId());
        ent2.setUserId(dto.getCerUserId());
        mpAuthCertificaseMapper.insertSelective(ent2);
        revo.setCertificateType(infoT.getCertificateType());
        return revo;
    }

    /**
     * 证书信息展示
     *
     * @param dto
     * @return
     */
    public CertificateVo certificateShow(MpNameIdsDto dto) {
        //认证id
        Long id = dto.getId();
        //证书所有者Id
//        Long cerUserId = dto.getCerUserId() == null ? dto.getUserId() : dto.getCerUserId();
//        if (null == cerUserId) {
//            throw new BusinessException("认证者Id都是空");
//        }
        //获取证书模板信息
        MpAttachmentInfo info = getfileInfoByCerId(id);
        //获取试卷id
        MpAuth infoT = mpAuthMapper.selectByPrimaryKey(id);
//        MpUserAuthentication userInfo =
//                mpUserAuthenticationMapper.selectByPrimaryKey(dto.getCerUserId());
        //获取认证信息
//        MpUserAuthenticationVo vo = new MpUserAuthenticationVo();
//        BeanCopy.copy(userInfo, vo);
        CertificateVo revo = new CertificateVo();
        revo.setFileUrl(info.getFileUrl());
        revo.setFileLocalUrl(info.getFileurllocal());
        //  revo.setUserVo(vo);
        revo.setCertificateType(infoT.getCertificateType());
        return revo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<MpUserAuthenticationVo> certifiQuery(MpNameIdsDto dto) {
        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        String key = dto.getKey();
        String value = dto.getValue();
        if (null != key && !"".equals(key)) {
            if (!StringUtil.isNumeric(value)) {
                throw new BusinessException("编号或者手机号应该是数字");
            }
        }
        if ("1".equals(key)) {
            dto.setPhone(value);
        } else if ("2".equals(key)) {
            dto.setNumber(Integer.valueOf(dto.getValue()));
        } else {
            dto.setNumber(null);
            dto.setPhone(null);
        }
        List<MpUserAuthenticationVo>
                mpUserAuthenticationVoList =
                mpAuthHMapper.certifiQuery(dto);
        PageInfo<MpUserAuthenticationVo> pageInfo = new PageInfo<>(mpUserAuthenticationVoList);
        return pageInfo;
    }


    /**
     * 查看试卷次数函数
     *
     * @param
     * @return
     */
    public List<MpExamination> mpExaminations(Long examId) {
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria exampleCriteria = examinationExample.createCriteria();
        exampleCriteria.andIdEqualTo(examId);
        exampleCriteria.andUpTypeEqualTo(CommonEnum.UP.getCode());
        exampleCriteria.andExaminationTypeEqualTo(3);
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        return mpExaminations;
    }
}
