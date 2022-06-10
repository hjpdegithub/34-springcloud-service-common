package com.springboot.boot.modules.admin.vo.classify;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName FirstClassifyVo
 * @Description TODO
 * @Author jhzhou
 * @Date 2022/3/15 0015 15:58
 * @Version 1.0
 **/
@Data
public class FirstClassifyVo {
    private Long id;

    private String label;

    private String firstClassifyDescr;

    private Integer firstClassifyType;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    private Integer deleFlag;

}
