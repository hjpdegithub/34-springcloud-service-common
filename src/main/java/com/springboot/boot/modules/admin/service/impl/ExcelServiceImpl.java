package com.springboot.boot.modules.admin.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import com.springboot.boot.common.ExcelListener;
import com.springboot.boot.modules.admin.dto.curriculum.SearchCurriculumDto;
import com.springboot.boot.modules.admin.entity.MpCurriculum;
import com.springboot.boot.modules.admin.entity.MpCurriculumExample;
import com.springboot.boot.modules.admin.entity.Testoo;
import com.springboot.boot.modules.admin.entity.TestooExample;
import com.springboot.boot.modules.admin.mapper.CurriculumMapper;
import com.springboot.boot.modules.admin.mapper.MpCurriculumMapper;
import com.springboot.boot.modules.admin.mapper.TestooMapper;
import com.springboot.boot.modules.admin.service.ExcelService;
import com.springboot.boot.modules.admin.vo.TestooVo;
import com.springboot.boot.modules.admin.vo.curriculum.CurriculumVo;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author xf
 * @version 1.0.0
 * @ClassName ExcelServiceImpl
 * @Description TODO
 * @createTime 2020.09.06 13:50
 */
@Service
public class ExcelServiceImpl  implements ExcelService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);


    @Resource
    private TestooMapper testooMapper;
    /**
     * 导出用户信息
     * @param response
     * @throws IOException
     */
    @Override
    public void excelExport(HttpServletResponse response) throws IOException {
        //准备导出的数据
        TestooExample example = new TestooExample();
        List<Testoo> list = testooMapper.selectByExample(example);
        logger.info("记录导出数据行数：{}",list.size());
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileProductName = URLEncoder.encode("店铺运营售后处理导出数据", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileProductName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Testoo.class).sheet("店铺运营售后处理导出数据").doWrite(list);
    }

    /**
     * 导入用户信息
     * @param file
     * @throws IOException
     */
    @Override
    public void excelImport(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //传入参数
        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        //读取信息
        excelReader.read(new Sheet(1, 1, TestooVo.class));
        //获取数据
        List<Object> list = listener.getDatas();
        List<TestooVo> originalList = new ArrayList<TestooVo>();
        logger.info("记录导入数据行数：{}",originalList.size());
        TestooVo catagory = new TestooVo();
        //转换数据类型
        for (int i = 0; i < list.size(); i++) {
            catagory = (TestooVo) list.get(i);
            originalList.add(catagory);
        }
        //对list进行去重并拿到新的list
//        List<MpCurriculum> lists = originalList.stream()
//                .filter(s -> StringUtils.isNotBlank(s.getPhoneNumber()))
//                .collect(Collectors.collectingAndThen(Collectors.toCollection(
//                        () -> new TreeSet<User>(Comparator.comparing(User::getPhoneNumber))), ArrayList::new));
        //执行批量插入
        if (originalList.size() > 0){
            logger.info("执行批量入库");
            originalList.forEach(e->{
                Testoo testoo = new Testoo();
                BeanCopy.copy(e,testoo);
                testoo.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
                testooMapper.insert(testoo);
            });

            return;
        }

        logger.info("解析数据为空");
    }

}
