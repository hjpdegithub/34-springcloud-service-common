package com.springboot.boot.modules.admin.dto.file;

import lombok.Data;

import java.util.List;

@Data
public class CommonupTypeDto {
    private Long id;
    private List<Long> ids;
    private Integer upType;

}