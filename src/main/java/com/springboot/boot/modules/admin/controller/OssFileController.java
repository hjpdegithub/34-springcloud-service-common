package com.springboot.boot.modules.admin.controller;

import com.springboot.boot.utils.AliyunOSSUtil;
import com.springboot.boot.utils.ConstantProperties;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @ClassName OssFileController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/22 0022 9:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/oss")
@Slf4j
@Api(tags = "1.9", description = "oss文件上传公共接口【周京昊】")
public class OssFileController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    @Autowired
    private ConstantProperties constantProperties;
    @GetMapping("/toUploadBlog")
    public String toUploadBlog(){
        return "oss/upload";
    }

    @PostMapping("/toUploadBlog")
    public String toUploadBlogPost(MultipartFile file){
        log.info("=========>文件上传");
        try {

            if(null != file){
                String filename = file.getOriginalFilename();
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    //上传到OSS
                    String uploadUrl = aliyunOSSUtil.upload(newFile);

                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "oss/index";
    }

    @GetMapping("/getObjectList")
    @ResponseBody
    public List<String> getObjectList(){
        String bucketName=constantProperties.getBucketname();
        System.out.println(bucketName);
        List<String> objectList = aliyunOSSUtil.getObjectList(bucketName);
        return objectList;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteBlog(@RequestParam("key")String key){
        aliyunOSSUtil.deleteBlog(key);
        return "删除成功";
    }
}
