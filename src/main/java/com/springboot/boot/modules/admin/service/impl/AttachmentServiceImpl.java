package com.springboot.boot.modules.admin.service.impl;

import com.springboot.boot.common.constant.CommonConstant;

import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.modules.admin.dto.file.FileDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.AttachmentInfoMapper;
import com.springboot.boot.modules.admin.mapper.MpAttachmentInfoMapper;
import com.springboot.boot.modules.admin.mapper.MpBusinessAttachmentInfoMapper;
import com.springboot.boot.modules.admin.mapper.MpCurriculumMapper;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.CurriculumService;
import com.springboot.boot.modules.admin.service.FileService;
import com.springboot.boot.modules.admin.service.ReadingService;
import com.springboot.boot.modules.admin.vo.file.AttachmentInfoViewVo;
import com.springboot.boot.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    public AliyunOSSUtil aliyunOSSUtil;

    @Resource
    public MpAttachmentInfoMapper mpAttachmentInfoMapper;

    @Resource
    public AttachmentInfoMapper attachmentInfoMapper;
    @Resource
    public MpBusinessAttachmentInfoMapper businessAttachmentInfoMapper;

    @Autowired
    public FileService fileService;

    @Resource
    private CurriculumService curriculumService;

    @Resource
    private ReadingService readingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult attachmentDeal(@RequestParam("file")
                                            MultipartFile uploadFile) {


        MpAttachmentInfo mpAttachmentInfo = new MpAttachmentInfo();

        try {
//            BusinessAttachmentInfo businessAttachmentInfo = BeanCopy.copy(attachmentInfo, new BusinessAttachmentInfo());
            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
            //文件的ID主键
            mpAttachmentInfo.setId(snowFlakeUtil.nextId());
            //文件附件的ID主键
//            businessAttachmentInfo.setId(snowFlakeUtil.nextId());
            //    String rootPath = CommonConstant.rootPath;

//            String fileTypePath = attachmentInfoDto.getBusiness() + "/";
            String fileName = System.currentTimeMillis() + uploadFile.getOriginalFilename();
            //String fileFullPath = rootPath  + fileName;
            //String fileShowPatten = CommonConstant.fileShowPatten;
            //String fileIpAdress = CommonConstant.fileIpAdress;
            //String fileUrl = fileIpAdress + fileShowPatten + fileName;
            mpAttachmentInfo.setFileName(fileName);
            //attachmentInfo.setFileUrl(fileUrl);
            mpAttachmentInfo.setCreateDate(new Date());
            mpAttachmentInfo.setUpdateDate(new Date());
            // 业务表关联文件ID
//           businessAttachmentInfo.setAttachmentId(attachmentInfo.getId());
//            //设定文件的业务类型名称（例子:课程业务字符串)
//            businessAttachmentInfo.setBusiness(attachmentInfoDto.getBusiness());
//            //设定文件的业务ID（例子:192883:JAVA课程)
//            businessAttachmentInfo.setBusinessId(attachmentInfoDto.getBusinessId());
//            //相同业务的排序（比如JAVA课程的第一个文件）
//            businessAttachmentInfo.setSort(attachmentInfoDto.getSort());
//            businessAttachmentInfo.setCreateTime(new Date());
//            businessAttachmentInfo.setUpdateTime(null);
//            businessAttachmentInfo.setDelFlag(0);
//            businessAttachmentInfo.setCreateUser(null);
//            AttachmentInfoExample attachmentInfoExample = new AttachmentInfoExample();
//            attachmentInfoExample.createCriteria().andFileNameEqualTo(fileName);
//            List<AttachmentInfo> attachmentInfoList = attachmentInfoMapper.selectByExample(attachmentInfoExample);
//            if (attachmentInfoList != null && attachmentInfoList.size() > 0) {
//                return ApiResult.error(ApiCode.FAIL.getCode(), "该文件已经存在");
//            }
            //DTO设定
//            FileDto fileDto = new FileDto();
//            fileDto.setFileFullPath(fileFullPath);
//            fileDto.setFileName(fileName);
//            fileDto.setRootPath(rootPath);
//            fileDto.setFileTypePath(fileTypePath);
            //文件格式校验
            Boolean checkNotPass = false;
            File newFile = null;
            try {
                if (null != uploadFile) {
                    String filename = uploadFile.getOriginalFilename();
                    if (!"".equals(filename.trim())) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = format.format(new Date());
                        //创建文件路径
                        String fileKeyname = "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + filename);
                        newFile = new File(fileKeyname);
                        FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), newFile);
                        // FileOutputStream os = new FileOutputStream(newFile);
                        //   os.write(uploadFile.getBytes());
                        //    os.close();
                        //  uploadFile.transferTo(newFile);
                        //   newFile.getAbsolutePath();
                        checkNotPass = !aliyunOSSUtil.fileCheck(newFile);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (checkNotPass) {
                return ApiResult.error(ApiCode.FAIL.getCode(), "文件格式校验不通过");
            }
            //云服务上传
            Map<String, String> OSSMap = aliyunOSSUtil.picOSS(newFile);
            newFile.getAbsoluteFile();
            newFile.getAbsolutePath();
            newFile.delete();
            // fileService.upload(file, fileDto);
            mpAttachmentInfo.setFileUrl(OSSMap.get("url"));
            mpAttachmentInfo.setFilePath(OSSMap.get("fileKey"));
            //fileService.upload(file, fileDto);
            mpAttachmentInfoMapper.insert(mpAttachmentInfo);
//            businessAttachmentInfoMapper.insert(businessAttachmentInfo);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ApiResult.error(ApiCode.FAIL.getCode(), e.getMessage());
        }
        return ApiResult.success(mpAttachmentInfo);

    }


    @Override
    public ApiResult addBusinessFile(Long id,String business,Long businessId) {
        MpBusinessAttachmentInfoExample example = new MpBusinessAttachmentInfoExample();
        MpBusinessAttachmentInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andBusinessIdEqualTo(businessId);
        //businessAttachmentInfoMapper
        List<MpBusinessAttachmentInfo> businessAttachmentInfos = businessAttachmentInfoMapper.selectByExample(example);
        MpBusinessAttachmentInfo businessAttachmentInfo = new MpBusinessAttachmentInfo();
        if (businessAttachmentInfos.size()>0 ){
            MpBusinessAttachmentInfo info = businessAttachmentInfos.get(0);

            MpBusinessAttachmentInfo deleteInfo = new MpBusinessAttachmentInfo();
            deleteInfo.setId(info.getId());
            deleteInfo.setDelFlag(CommonEnum.DELETE.getCode());
            businessAttachmentInfoMapper.updateByPrimaryKeySelective(deleteInfo);
        }
        //业务表关联文件ID
        SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();

        businessAttachmentInfo.setId(snowFlakeUtil.nextId());
        businessAttachmentInfo.setAttachmentId(id);
        //设定文件的业务类型名称（例子:课程业务字符串)
        businessAttachmentInfo.setBusiness(business);
        //设定文件的业务ID（例子:192883:JAVA课程)
        businessAttachmentInfo.setBusinessId(businessId);
        //相同业务的排序（比如JAVA课程的第一个文件）
//            businessAttachmentInfo.setSort(attachmentInfoDto.getSort());
        businessAttachmentInfo.setCreateTime(new Date());
        businessAttachmentInfo.setUpdateTime(null);

        businessAttachmentInfo.setDelFlag(0);
        businessAttachmentInfo.setCreateUser(null);

        businessAttachmentInfoMapper.insert(businessAttachmentInfo);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult attachmentFileSelect(CommonDto CommonDto) {
        //查看课程的时候新增一个阅读量
        readingService.addUser(CommonDto);
        //查询课程信息
        MpCurriculum mpCurriculum = curriculumService.searchById(CommonDto.getBusinessId());
        if (null == mpCurriculum){
            return  ApiResult.error(500,"课程为空");
        }
        //判断学习年限
        String SystemDate = DateUtils.formatShortDate(new Date());
        String studyDate = DateUtils.formatShortDate(mpCurriculum.getStudyTime());

        if (studyDate.compareTo(SystemDate)<0){
            return ApiResult.error(ApiCode.FAIL.getCode(),"学习年限已过期！");
        }
        List<MpReading> mpReadings = readingService.selectByBusinessId(CommonDto);

        List<AttachmentInfoViewVo> attachmentInfoViewVo = attachmentInfoMapper.selectViewVoByPrimaryKey(CommonDto.getBusinessId());
        if (attachmentInfoViewVo.size()>0){
            AttachmentInfoViewVo attachmentInfoViewVo1 = attachmentInfoViewVo.get(0);
            attachmentInfoViewVo1.setStudyTime(mpCurriculum.getStudyTime());
            attachmentInfoViewVo1.setAuthorName(mpCurriculum.getAuthorName());
            attachmentInfoViewVo1.setReadingCount(mpReadings.size());
            return ApiResult.success(attachmentInfoViewVo1);

        }
        return ApiResult.error(ApiCode.FAIL.getCode(),ApiCode.FAIL.getMessage());

    }

//    @Override
//    public ApiResult attachmentFileListSelect(FileDto fileDto) {
//        return ApiResult.success(attachmentInfoMapper.selectViewVoList(fileDto));
//    }

//    @Override
//    public ApiResult attachmentFileDelete(CommonAllDto commonDto) {
//
//        BusinessAttachmentInfo businessAttachmentInfo = new BusinessAttachmentInfo();
//        businessAttachmentInfo.setId(commonDto.getId());
//        businessAttachmentInfo.setDelFlag(1);
//
//
//        return ApiResult.success(businessAttachmentInfoMapper.updateByPrimaryKeySelective(businessAttachmentInfo));
//    }


//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public ApiResult attachmentFileUpdate(MultipartFile file, AttachmentInfoDto attachmentInfoDto) {
//        try {
//
//            BusinessAttachmentInfoExample example = new BusinessAttachmentInfoExample();
//            BusinessAttachmentInfoExample.Criteria criteria = example.createCriteria();
//            criteria.andBusinessIdEqualTo(attachmentInfoDto.getBusinessId());
//            criteria.andDelFlagEqualTo(CommonEnum.USED.getCode());
//            List<BusinessAttachmentInfo> businessAttachmentInfos = businessAttachmentInfoMapper.selectByExample(example);
//            businessAttachmentInfos.forEach(e->{
//                BusinessAttachmentInfo businessAttachmentInfoForDelete = new BusinessAttachmentInfo();
//                businessAttachmentInfoForDelete.setId(e.getId());
//                businessAttachmentInfoForDelete.setDelFlag(1);
//                businessAttachmentInfoMapper.updateByPrimaryKeySelective(businessAttachmentInfoForDelete);
//            });
//
//            AttachmentInfo attachmentInfo = BeanCopy.copy(attachmentInfoDto, new AttachmentInfo());
//            BusinessAttachmentInfo businessAttachmentInfo = BeanCopy.copy(attachmentInfo, new BusinessAttachmentInfo());
//            SnowFlakeUtils snowFlakeUtil = SnowFlakeUtils.getFlowIdInstance();
//            //文件的ID主键
//            attachmentInfo.setId(snowFlakeUtil.nextId());
//            //文件附件的ID主键
//            businessAttachmentInfo.setId(snowFlakeUtil.nextId());
//            String rootPath = CommonConstant.rootPath;
//            //123
//            String fileTypePath = attachmentInfoDto.getBusiness() + "/";
//            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
//            String fileFullPath = rootPath  + fileName;
//            String fileShowPatten = CommonConstant.fileShowPatten;
//            String fileIpAdress = CommonConstant.fileIpAdress;
//            String fileUrl = fileIpAdress + fileShowPatten + fileName;
//
//            attachmentInfo.setFileName(fileName);
//            attachmentInfo.setFilePath(fileFullPath );
//            attachmentInfo.setFileUrl(fileUrl);
//            attachmentInfo.setCreateDate(new Date());
//            attachmentInfo.setUpdateDate(new Date());
//
//            //业务表关联文件ID
//            businessAttachmentInfo.setAttachmentId(attachmentInfo.getId());
//            //设定文件的业务类型名称（例子:课程业务字符串)
//            businessAttachmentInfo.setBusiness(attachmentInfoDto.getBusiness());
//            //设定文件的业务ID（例子:192883:JAVA课程)
//            businessAttachmentInfo.setBusinessId(attachmentInfoDto.getBusinessId());
//            //相同业务的排序（比如JAVA课程的第一个文件）
//            businessAttachmentInfo.setSort(attachmentInfoDto.getSort());
//            businessAttachmentInfo.setCreateTime(new Date());
//            businessAttachmentInfo.setUpdateTime(null);
//
//            businessAttachmentInfo.setDelFlag(0);
//            businessAttachmentInfo.setCreateUser(null);
//
//
////            AttachmentInfoExample attachmentInfoExample = new AttachmentInfoExample();
////            attachmentInfoExample.createCriteria().andFileNameEqualTo(fileName);
////            List<AttachmentInfo> attachmentInfoList = attachmentInfoMapper.selectByExample(attachmentInfoExample);
////            if (attachmentInfoList != null && attachmentInfoList.size() > 0) {
////                return ApiResult.error(ApiCode.FAIL.getCode(), "该文件已经存在");
////            }
//
//            //DTO设定
//            FileDto fileDto = new FileDto();
//            fileDto.setFileFullPath(fileFullPath);
//            fileDto.setFileName(fileName);
//            fileDto.setRootPath(rootPath);
////            fileDto.setFileTypePath(fileTypePath);
//
//            fileService.upload(file, fileDto);
//            attachmentInfoMapper.insert(attachmentInfo);
//            businessAttachmentInfoMapper.insert(businessAttachmentInfo);
//        } catch (Exception e) {
//
//            log.info(e.getMessage());
//            return ApiResult.error(ApiCode.FAIL.getCode(), e.getMessage());
//        }
//        return ApiResult.success();
//
//    }



}
