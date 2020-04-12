package com.hy.yyzx.admin.service.config;

import com.hy.yyzx.admin.service.security.filters.JWTLoginFilter;
import com.hy.yyzx.admin.service.security.filters.JWTLogoutFilter;
import com.hy.yyzx.admin.service.security.filters.JwtAuthenticationFilter;
import com.hy.yyzx.admin.service.security.handlers.JWTLogoutHandler;
import com.hy.yyzx.admin.service.security.provider.LoginAuthenticationProvider;
import com.hy.yyzx.admin.service.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:21
 * @Description: web安全性配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    @Autowired
    LoginAuthenticationProvider loginAuthenticationProvider;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public WebSecurityConfig() {
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.js",
                "/**/*.css","/v2/api-docs","/static/**",
                "/webjars/**","/swagger-resources/**", "/swagger-ui.html",
                "/images/**", "/druid/**","/swagger/**", "/**/*.html","/socket/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();//解决编辑器 iframe 提交报错
        JWTLoginFilter loginFilter = new JWTLoginFilter(jwtConfig, jwtTokenUtil, authenticationManager());
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), jwtConfig, jwtTokenUtil);
        LogoutSuccessHandler logoutSuccessHandler = new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                System.out.println("##############LOGOUT SUCCESS####################");
            }
        };
        JWTLogoutFilter logoutFilter = new JWTLogoutFilter(jwtConfig, jwtTokenUtil, logoutSuccessHandler, new JWTLogoutHandler(jwtConfig, jwtTokenUtil));

        http.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.POST, jwtConfig.getLoginUrl(), jwtConfig.getLogoutUrl()).permitAll()
                .and()
                .addFilter(loginFilter)
                .addFilter(jwtAuthenticationFilter)
                .addFilter(logoutFilter);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider);
        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
