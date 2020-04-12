package com.hy.yyzx.admin.service.security.filters;

import com.hy.yyzx.admin.service.config.JwtConfig;
import com.hy.yyzx.admin.service.security.authentication.SystemUserAuthToken;
import com.hy.yyzx.admin.service.security.utils.JwtTokenUtil;
import com.hy.yyzx.admin.service.security.vo.UserDetail;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.common.utils.JSONUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:35
 * @Description: jwt登录拦截器
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    JwtTokenUtil jwtTokenUtil;

    JwtConfig jwtConfig;

    public JWTLoginFilter(JwtConfig jwtConfig, JwtTokenUtil tokenUtil, AuthenticationManager authenticationManager) {
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getLoginUrl(), "POST"));
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.jwtTokenUtil = tokenUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            String username = req.getParameter("username")==null ? "" : req.getParameter("username");
            String password = req.getParameter("password")==null ? "" : req.getParameter("password");
            if(username.equals("")) throw new BadCredentialsException("请输入用户名");
            if(password.equals("")) throw new BadCredentialsException("请输入登录密码");

            Authentication authentication = authenticationManager.authenticate(
                    new SystemUserAuthToken(username, password)
            );
            return authentication;
        }catch (AuthenticationException e){
            throw new UsernameNotFoundException(e.getMessage());
        }catch (Exception ex){
            logger.error("认证错误", ex);
            throw new BadCredentialsException(ex.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        //long expireTime = 1000 * 60 * 60 * 2;   //有效时间 2小时
        long expireTime = jwtConfig.getExpiration() * 1000;
        SystemUserAuthToken authToken = (SystemUserAuthToken)auth;

        UserDetail userDetail = (UserDetail)auth.getCredentials();
        String token = jwtTokenUtil.generateToken(userDetail.getClaims());

        response.addHeader(jwtConfig.getHeader(), token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        MapEntity tokenMap = new MapEntity();
        tokenMap.put("token", token);
        tokenMap.put("userid", userDetail.getId());
        tokenMap.put("expire", jwtConfig.getExpiration());
        ResultData result = ResultData.success("Login Success", tokenMap);
        out.write(JSONUtils.toJSONString(result));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONUtils.toJSONString(ResultData.failed(failed.getMessage())));
        out.flush();
        out.close();
    }
}
