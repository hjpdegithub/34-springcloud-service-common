package com.springboot.boot.modules.admin.vo.curriculum;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CurriculumVo
 * @Description TODO 课程返回
 * @Author jhzhou
 * @Date 2022/3/21 0021 15:01
 * @Version 1.0
 **/
@Data
public class CurriculumVo {
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long id;

    private String curriculumName;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long genFirstClassifyId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long genSecondClassifyId;

    private Date studyTime;

    private Integer classHour;

    private Integer classFormat;

    private String authorName;

    private Integer applyType;

    private Integer num;

    private Integer customizedType;
    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long custFirstClassifyId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long custSecondClassifyId;

    private Integer openClassType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long createUser;

    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;


    private String firstClassifyName;

    private String secondClassifyName;

    private String cFirstClassifyName;

    private String cSecondClassifyName;
    private  String  fileUrl;
    private  Long  readCount;

    //版本2.0新增字段===================

    private Integer propertyType;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authFirstClassifyId;

    @JsonSerialize(using = ToStringSerializer.class)//解决long精度丢失问题
    private Long authSencondClassifyId;


}
