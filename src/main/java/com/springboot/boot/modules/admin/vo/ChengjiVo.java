package com.springboot.boot.modules.admin.vo;

import lombok.Data;

/**
 * @ClassName ChengjiVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/4/18 0018 14:15
 * @Version 1.0
 **/
@Data
public class ChengjiVo {
    private Integer sum;
    private Integer ifWhere;
    private Integer sigele;
    private Integer multple;
    private Integer jud;
    private Integer fenxi;
    //判断是否存在分析字段
    private Integer fenxiCount;
}
