package com.hy.yyzx.admin.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hy.yyzx.admin.service.mapper")
public class YyzxAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyzxAdminServiceApplication.class, args);
    }

}
