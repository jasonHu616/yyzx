package com.hy.yyzx.admin.service.security.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 17:23
 * @Description: TODO
 */
@Component
@ConfigurationProperties(prefix = "base")
public class YyzxBaseConfig {
    /**平台微信公众号地址**/
    private String url;
    /**端口号**/
    private Integer port;
    /**网页url地址**/
    private String webappUrl;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getWebappUrl() {
        return webappUrl;
    }

    public void setWebappUrl(String webappUrl) {
        this.webappUrl = webappUrl;
    }
}
