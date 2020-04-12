package com.hy.yyzx.admin.service.security.filters;

import com.hy.yyzx.admin.service.config.JwtConfig;
import com.hy.yyzx.admin.service.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:37
 * @Description: 退出登录过滤器
 */
public class JWTLogoutFilter extends LogoutFilter {
    private AuthenticationManager authenticationManager;

    JwtTokenUtil jwtTokenUtil;

    JwtConfig jwtConfig;


    public JWTLogoutFilter(JwtConfig jwtConfig, JwtTokenUtil tokenUtil, LogoutSuccessHandler successHandler, LogoutHandler logoutHandler) {
        super(jwtConfig.getLogoutUrl(), logoutHandler);
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.jwtTokenUtil = tokenUtil;
        setFilterProcessesUrl(jwtConfig.getLogoutUrl());
        RequestMatcher requestMatcher = new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest request) {
                String servletPath = request.getServletPath();
                String header = request.getHeader(jwtConfig.getHeader());
                if (servletPath.startsWith(jwtConfig.getLogoutUrl()) && header!=null) {
                    return true;
                }
                return false;
            }
        };
        setLogoutRequestMatcher(requestMatcher);
    }
}
