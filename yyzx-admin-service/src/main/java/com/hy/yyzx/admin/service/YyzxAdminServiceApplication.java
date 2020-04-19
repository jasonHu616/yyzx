package com.hy.yyzx.admin.service;

import com.hy.yyzx.admin.service.config.RedisConfig;
import com.hy.yyzx.common.utils.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@MapperScan("com.hy.yyzx.admin.service.mapper")
public class YyzxAdminServiceApplication {

    @Bean
    public RedisUtil redisUtil(@Autowired RedisTemplate<String,?> redisTemplate, @Autowired RedisConfig redisConfig){
        return new RedisUtil(redisTemplate, redisConfig.getKeyPrefix());
    }

    public static void main(String[] args) {
        SpringApplication.run(YyzxAdminServiceApplication.class, args);
    }

}
