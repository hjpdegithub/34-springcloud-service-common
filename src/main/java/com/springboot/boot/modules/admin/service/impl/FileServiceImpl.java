package com.springboot.boot.modules.admin.service.impl;


import com.springboot.boot.modules.admin.dto.file.FileDto;
import com.springboot.boot.modules.admin.service.FileByDbService;
import com.springboot.boot.modules.admin.service.FileService;
import com.springboot.boot.utils.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${att.upload.docBase}")
    private String ftpPath;
    @Value("${att.upload.path}")
    private String downLoad;
    @Autowired
    private FtpUtil fileFtp;
    @Resource
    private FileByDbService fileByDbService;



    @Override
    public String upload(MultipartFile file, FileDto fileDto) {

        String fullPath = fileDto.getFileFullPath();
        String fileName = fileDto.getFileName();

        //注意参数
        try {
            if (file.isEmpty()) {
                return "上传失败";
            }
            // 获取文件名

            log.info("上传的文件名为：" + fileName);//写日志
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);//写日志
            // 设置文件存储路径         *************************************************

            File dest = new File(new File(fullPath).getAbsolutePath());// dist为文件，有多级目录的文件
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {//因此这里使用.getParentFile()，目的就是取文件前面目录的路径
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";


        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
//    /**
//     * ftp上传
//     * @param files 附件
//     * @param description 描述
//     * @param userId 用户id
//     * @return
//     */
//    @Override
//    public ApiResult uploadArrByFtp(MultipartFile[] files, String description, Long userId) {
//        log.info("-----ftp文件上传---description:{}--userId:{}----MultipartFile:{} files长度:--{}------",description,userId,files,files.length);
//        if (null ==  files){
//            ApiResult result = new ApiResult();
//            result.setCode(ApiCode.BUSINESS_FAIL.getCode());
//            result.setMsg(ApiCode.BUSINESS_FAIL.getMessage());
//            return result;
//        }else{
//            List<AttachmentInfoVo> attachList=new ArrayList<>();
//            String lastPath="";
//            LinkedList<String> newNameList = new LinkedList<String>();
//            LinkedList<InputStream> fileList = new LinkedList<InputStream>();
//            for (MultipartFile file : files) {
//                AttachmentInfoVo attachVo=new AttachmentInfoVo();
//                //获取上传的附件原名包括后缀
//                String realFileName=file.getOriginalFilename();
//                attachVo.setFileName(realFileName);
//                //获取后缀名，如.jpg.png
//                log.info("--------获取源文件名 realFileName--------"+realFileName);
//                String suffixes = realFileName.substring(realFileName.lastIndexOf("."));
//                List<String> extList = Arrays.asList(".jpg", ".png", ".jpeg", ".gif",".doc");
//                if (!extList.contains(suffixes)) {
//                    ApiResult result = new ApiResult();
//                    result.setCode(ApiCode.BUSINESS_FAIL.getCode());
//                    result.setMsg(ApiCode.BUSINESS_FAIL.getMessage());
//                    return result;
//                }
//                //图片名称重新命名包括后缀名
//                String newName = StringUtil.getRandomOrderNumber() + "_" + StringUtil.getRandomOrderNumberString() + suffixes;
////                    String newName= UUID.randomUUID().toString().replaceAll("\\-", "")+suffixes;
//                newNameList.add(newName);
//                try {
//                    fileList.add(file.getInputStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //存放图片的路径地址
//                StringBuilder sb = new StringBuilder(ftpPath).append("/");
//                String path = sb.toString();
//                lastPath=path;
////                fileFtp.uploadFile(path,newName,file.getInputStream());
//                //文件服务器地址**********************************************
//                String filePath=path+newName;
//                //赋值附件数据存储的信息**********************************************
//                attachVo.setCreateDate(new Date());
//                attachVo.setCreateUser(userId);
//                attachVo.setUpdateDate(new Date());
//                attachVo.setUpdateUser(userId);
//                attachVo.setDelFlag(CommonEnum.USED.getCode());
//                attachVo.setDescription(description);
//                attachVo.setFilePath(filePath);
//                attachVo.setFileUrl(downLoad+"/"+newName);
//                attachList.add(attachVo);
//            }
//            //ftp上传
//            try {
//                fileFtp.uploadArrFile(lastPath,newNameList,fileList);
//            } catch (Exception e) {
//                log.error("服务器上传附件异常----------",e);
//                e.printStackTrace();
//            }
//            //调用业务层，添加数据库附件信息**********************************************
//            AttachmentInfoListDto infoListDto=new AttachmentInfoListDto();
//            infoListDto.setAttachmentInfoDtoList(attachList);
//            //============================操作数据库==============
//            log.info("通过ftp上传操作数据库======================参数：{}"+ JSONObject.toJSONString(infoListDto));
//            ApiResult<List<AttachmentInfoVo>> listApiResult = fileByDbService.uploadByFtp(infoListDto);
//            return listApiResult;
//        }
//    }





}
