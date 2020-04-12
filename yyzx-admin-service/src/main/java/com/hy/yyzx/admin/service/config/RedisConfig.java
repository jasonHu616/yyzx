package com.hy.yyzx.admin.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:15
 * @Description: TODO
 */
@Component
@ConfigurationProperties(prefix="spring.cache.redis")
public class RedisConfig implements Serializable {
    private static final long serialVersionUID = 4781890204133126408L;
    private String keyPrefix;

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
