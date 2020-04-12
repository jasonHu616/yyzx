package com.hy.yyzx.admin.service.security.authentication;

import com.hy.yyzx.admin.service.security.vo.UserDetail;
import com.hy.yyzx.common.model.SysUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:25
 * @Description: TODO
 */
public class SystemUserAuthToken extends AbstractAuthenticationToken {
    private Integer id;
    private String username;
    private String password;
    private UserDetail userDetail;
    private HttpServletRequest request;

    public SystemUserAuthToken(String username, String password){
        super((Collection)null);
        this.username = username;
        this.password = password;
        setAuthenticated(false);
    }

    public SystemUserAuthToken(Integer id, String username, SysUser user){
        super((Collection)null);
        this.id = id;
        this.username = username;
        this.userDetail = user==null ? null : new UserDetail(user);
        setAuthenticated(true);
    }

    public SystemUserAuthToken(String username, String password, HttpServletRequest request) {
        super((Collection)null);
        this.username = username;
        this.password = password;
        this.request = request;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return userDetail;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
