package com.springboot.boot.modules.admin.dto.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class MpUserAuthenticationDto {
    private Long id;
    private String name;
    private String unitName;
    private String departmentName;
    private String phone;
    private Integer number;
    private Long userId;
    private Long departmentId;
    private Long unitId;


}