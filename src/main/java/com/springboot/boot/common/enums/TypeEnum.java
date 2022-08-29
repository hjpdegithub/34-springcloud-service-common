package com.springboot.boot.common.enums;

/**
 * 类型相关枚举
 */
public enum TypeEnum {
    /**
     * 类型
     */
    CLASS("Class", "课程管理"),
    /**
     * 考试规则
     *
     */
    SINGLE("single", "单选"),
    MULTIPLE ("multipe", "多选"),
    JUDGE ("judge", "判断"),
    ANALYSIS ("analysis", "分析"),
    ;


    TypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
