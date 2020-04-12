package com.hy.yyzx.admin.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:13
 * @Description: 支付配置
 */
@Component
@ConfigurationProperties(prefix = "pays")
public class BasePaysConfig {
    private String catalog;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
