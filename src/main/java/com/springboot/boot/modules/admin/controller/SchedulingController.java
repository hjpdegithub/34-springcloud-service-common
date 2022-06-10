package com.springboot.boot.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;

import com.aliyun.oss.model.GetObjectRequest;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.event.WindowFocusListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SchedulingController
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/12 0012 17:29
 * @Version 1.0
 **/
@Slf4j
@Component
public class SchedulingController {

//    @Value("${zuul.inside.server.url}")
//    private String filepath;
    /**
     * 文件删除
     *
     * @param
     * @return false、true
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public static Boolean delete() {

//        boolean b = FileSystemUtils.deleteRecursively(new File("D:\\test1"));
//        log.info("定时任务执行=================================================结果"+b);
        boolean result = deleteAllFile("D:\\test1");
        return result;
    }
    /**
     * 删除指定文件夹下所有子目录
     *
     * @param filepath 文件夹路径
     * @return 删除成功返回true,失败返回false
     */
    public static boolean deleteAllFile(String filepath) {
        boolean flag = false;
        File file = new File(filepath);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (filepath.endsWith(File.separator)) {
                temp = new File(filepath + tempList[i]);
            } else {
                temp = new File(filepath + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
                flag = true;
            }
            if (temp.isDirectory()) {
                deleteAllFile(temp.getPath());  // 先删除文件夹里面的文件
                temp.delete();  // 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        boolean result = deleteAllFile("D:/test1/test");
//        System.out.println(result);
    }
}
