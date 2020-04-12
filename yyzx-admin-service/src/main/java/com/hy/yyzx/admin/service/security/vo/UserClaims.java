package com.hy.yyzx.admin.service.security.vo;

import java.io.Serializable;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 18:06
 * @Description: 用户验证
 */
public class UserClaims implements Serializable {
    private static final long serialVersionUID = -6367425306832985533L;
    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
