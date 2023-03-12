package com.springboot.boot.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "oos")
public class ConstantProperties {
    private  String endpoint;
    private  String keyid;
    private  String keysecret;
    private  String bucketname;
    private  String filehost;
    //以下的三个参数和oos通用
    private  String oosFilePath;
    private  String oosFileShowPathPrex;
    private  String localShowPath;



}
