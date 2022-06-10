package com.springboot.boot.modules.admin.controller;


import com.springboot.boot.modules.admin.dto.file.AttachmentInfoDto;
import com.springboot.boot.modules.admin.dto.file.CommonAllDto;
import com.springboot.boot.modules.admin.dto.file.CommonDto;
import com.springboot.boot.modules.admin.dto.file.FileDto;
import com.springboot.boot.modules.admin.service.AttachmentService;
import com.springboot.boot.modules.admin.service.FileService;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/mp/file")
@Slf4j
@Api(tags = "1.0", description = "文件上传公共接口【周京昊】")
@CrossOrigin
public class FileController {



    @Autowired
    private AttachmentService attachmentService;

    @Resource
    private FileService fileService;

//    @ApiOperation("通过ftp上传文件")
//    @PostMapping(value = "/uploadByFtp",headers = "content-type=multipart/form-data")
//    public ApiResult uploadByFtp(@RequestParam("description") String description,
//                                 @RequestParam("userId") String userId,
//                                 @RequestParam("files") MultipartFile[] files) {
//        log.info("前端传参输出：description{}===userId{}========MultipartFile{}=====》", description,userId,files);
//        return fileService.uploadArrByFtp(files,description,Long.valueOf(userId));
//    }

    @PostMapping(value = "/attachmentupload")
    public ApiResult attachmentupload(@RequestParam("file") MultipartFile file
    ) {
        return attachmentService.attachmentDeal(file);
    }


    @CrossOrigin
    @PostMapping(value = "/attachmentFileSelectById")
    @ApiOperation(value = "按照id查询课件详细信息")
    public ApiResult attachmentFileSelectByBusinesId(
            @RequestBody CommonDto commonDto) {
        return attachmentService.attachmentFileSelect(commonDto);
    }



//
//    @PostMapping(value = "/attachmentFileListSelect")
//    public ApiResult attachmentFileListSelect(
//            @RequestBody FileDto FileDto) {
//        return attachmentService.attachmentFileListSelect(FileDto);
//
//    }
//
//
//    @ApiOperation(value = "通过附件主键id删除附件信息")
//    @PostMapping(value = "/attachmentFileDelete")
//    public ApiResult attachmentFileDelete(
//            @RequestBody CommonAllDto commonDto) {
//        return attachmentService.attachmentFileDelete(commonDto);
//
//    }


//    @PostMapping(value = "/attachmentFileUpdate")
//    public ApiResult attachmentFileUpdate(@RequestParam("file") MultipartFile file,
//                                          @Valid @ModelAttribute AttachmentInfoDto attachmentInfoDto) {
//        return attachmentService.attachmentFileUpdate(file, attachmentInfoDto);
//
//    }


    // 多个文件一起上传
    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {   //注意参数
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "./FILE/KING/";            // 文件路径
            File dest = new File(filePath);
            // 检测是否存在目录
            if (!dest.exists()) {
                dest.mkdirs();// 新建文件夹
            }
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename() + System.currentTimeMillis())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> " + e.getMessage();
                }
            } else {
                return "第 " + i + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "99d10454677af1f13cd9354920238c2e.mp4";// 文件名
        if (true) {
            //设置文件路径
            File file = new File("D:\\aim\\99d10454677af1f13cd9354920238c2e.mp4");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }
}
