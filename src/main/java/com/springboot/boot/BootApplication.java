package com.springboot.boot;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.springboot.boot.modules.admin.mapper")
@ComponentScan(value = {"com.springboot"})
@EnableSwagger2
@EnableScheduling
//@EnableTransactionManagement
public class BootApplication {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.url”));
//        dataSource.setUsername(env.getProperty("spring.datasource.username”));
//        dataSource.setPassword(env.getProperty("spring.datasource.password”));
//        return dataSource;
//    }
//    /**
//     * 解决Jackson导致Long型数据精度丢失问题
//     * @return
//     */
//    @Bean("jackson2ObjectMapperBuilderCustomizer")
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
//            @Override
//            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//                jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
//                        .serializerByType(Long.TYPE, ToStringSerializer.instance);
//            }
//        };
//        return customizer;
//    }

}
