package com.springboot.boot.modules.admin.vo.curriculum;

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
public class CollectStatusVo {
    private Long Amount;
    private Boolean collectStatus;


}
