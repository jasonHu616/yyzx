package com.hy.yyzx.admin.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: Jason
 * @DateTime: 2020/4/10 10:36
 * @Description: jwt配置
 */
@Component
@ConfigurationProperties(prefix="jwt")
public class JwtConfig implements Serializable {
    private static final long serialVersionUID = 4781890204133126408L;
    private String header;
    private String secret;
    private long expiration;
    private String tokenPrefix;
    private String authUrlPrefix;
    private String loginUrl;
    private String refreshUrl;
    private String logoutUrl;
    private int tokenLength;
    private boolean singleLogin;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getAuthUrlPrefix() {
        return authUrlPrefix;
    }

    public void setAuthUrlPrefix(String authUrlPrefix) {
        this.authUrlPrefix = authUrlPrefix;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public void setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public int getTokenLength() {
        return tokenLength;
    }

    public void setTokenLength(int tokenLength) {
        this.tokenLength = tokenLength;
    }

    public boolean isSingleLogin() {
        return singleLogin;
    }

    public void setSingleLogin(boolean singleLogin) {
        this.singleLogin = singleLogin;
    }

}
