package com.hy.yyzx.admin.service.security.vo;

import com.hy.yyzx.common.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 17:27
 * @Description: 用户信息
 */
public class UserDetail implements UserDetails {
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private UserClaims claims;

    public UserDetail(){

    }
    public UserDetail(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.enabled = (user.getIsfreeze()==0);
        this.accountNonLocked = (user.getIsfreeze()==0);
        this.accountNonExpired = (user.getIsfreeze()==0);
        this.credentialsNonExpired = (user.getIsfreeze()==0);
    }

    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public UserClaims getClaims(){
        if(claims==null)claims = new UserClaims();
        claims.setId(this.id);
        claims.setUsername(this.username);
        return claims;
    }
}
