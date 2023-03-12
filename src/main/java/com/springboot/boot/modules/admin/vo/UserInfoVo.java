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
public class UserInfoVo {
   
    private String token;
    private String errCode;
    private String errMessage;
    private String url;
    private int result;
    private Long id;
    private String name;
    private String iscuserid;
    private String baseorgname;
    private String ip;
    private String isadmin;
    private String baseorgid;
    private String iscadcode;
    private String netscop;
    private String iscusersourceid;
    private Long logintime;

}
