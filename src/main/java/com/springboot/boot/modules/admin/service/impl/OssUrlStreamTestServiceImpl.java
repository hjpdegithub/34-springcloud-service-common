package com.springboot.boot.modules.admin.service.impl;


import com.springboot.boot.modules.admin.service.OssUrlStreamTestService;
import com.springboot.boot.utils.AliyunOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AppClassifyServiceImpl
 * @Description TODO 前端分页业务
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:15
 * @Version 1.0
 **/
@Service
@Slf4j
public class OssUrlStreamTestServiceImpl implements OssUrlStreamTestService {


    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Override
    public void StreamOut(HttpServletResponse response,String FileFullPath,String fileName) throws IOException {


        aliyunOSSUtil.uploadByStream(response,FileFullPath,fileName);


    }



    @Override
    public String ossToLocalToShow(HttpServletResponse response,String FileFullPath,String fileName) throws IOException {

        return aliyunOSSUtil.ossToLocalToShow(response,FileFullPath,fileName);


    }




}
