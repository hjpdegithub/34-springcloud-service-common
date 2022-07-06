package com.springboot.boot.common.enums;

/**
 * @author by zhangbr
 * @date 2020/3/24.
 */
public enum CommonEnum {

    /**
     * 通用枚举
     */
    USED(0, "未删除"),
    DELETE(1, "删除"),
    ADD_ERROR(0,"新增错误"),
    UPDATE_ERROR(0,"编辑错误"),
    DELETE_ERROR(0,"删除错误"),

    //
    CTYPE(2,"定制分类"),

    EXAM(1,"考试类"),
    WENJUAN(2,"问卷类"),
    AUTH_EXAM(3,"认证类"),

    DATA_EXAM(1,"数据中台门户考试"),
    DATA_ACTIVIT(2,"能力开放平台活动"),

    SIGN_UP(1,"已报名"),
    NO_SING_UP(0,"未报名"),

    CANCEL_SIGN_UP(2,"取消报名"),
    CHLICK_SIGN_UP(1,"点击报名"),

    TRUE(1,"正确"),
    FALSE(0,"错误"),

    UP(1,"上线"),
    NO_UP(0,"下线"),

    IF_WHERE(1,"合格"),
    NOT_IF_WHERE(2,"不合格"),

    AUTH(3,"认证"),
    CURR_AUTH(2,"认证"),
    NOT_AUTH(1,"普通"),

    FINISH(1,"完成"),
    NOT_FINISH(2,"未完成"),

    NO(0,"否"),
    YES(1,"是"),
    INIT_AUTH(0,"认证预约流程"),
    STUDY_AUTH(1,"学习流程"),
    EXAM_AUTH(2,"考试流程"),
    SCER_AUTH(3,"证书流程"),
    FINISH_AUTH(4,"全部完成"),
    NO_FINISH_AUTH(5,"全部置灰"),
    ;


    CommonEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    private String name;
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
