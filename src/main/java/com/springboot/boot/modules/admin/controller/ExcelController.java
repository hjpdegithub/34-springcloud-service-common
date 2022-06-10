package com.springboot.boot.modules.admin.controller;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.springboot.boot.modules.admin.entity.Testoo;
import com.springboot.boot.modules.admin.entity.TestooExample;
import com.springboot.boot.modules.admin.mapper.TestooMapper;
import com.springboot.boot.modules.admin.service.ExcelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author zjh
 * @version 1.0.0
 * @ClassName TestController
 * @Description TODO 导出导入test
 * @createTime 2020.09.06 13:49
 */
@RequestMapping("/excel")
@RestController
@Slf4j
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Resource
    private TestooMapper testooMapper;
    /**
     * 导出用户信息
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/excelExport")
    public void excelExport(HttpServletResponse response) throws IOException {
        excelService.excelExport(response);
    }

    /**
     * 导入用户信息
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/user/excelImport")
    public String excelImport(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.excelImport(file);
        return "success";
    }


    @GetMapping("/exceltest")
    public void excel(HttpServletResponse response) throws IOException {
        String fileName = "黑名单导入模板_" +  ".xls";
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));// 设置文件头编码格式
        response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
//        response.setHeader("Cache-Control", "no-cache");// 设置头
//        response.setDateHeader("Expires", 0);// 设置日期头
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        TestooExample example = new TestooExample();
        List<Testoo> list = testooMapper.selectByExample(example);
        EasyExcel.write(response.getOutputStream(), Testoo.class).sheet("模板").doWrite(list);
    }

}
