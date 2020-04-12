package com.hy.yyzx.admin.service.security.handlers;

import com.hy.yyzx.admin.service.config.JwtConfig;
import com.hy.yyzx.admin.service.security.utils.JwtTokenUtil;
import com.hy.yyzx.common.modules.ResultMsg;
import com.hy.yyzx.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:29
 * @Description: 退出登录处理器
 */
public class JWTLogoutHandler implements LogoutHandler {
    private JwtConfig jwtConfig;
    private JwtTokenUtil tokenUtil;
    private Logger logger = LoggerFactory.getLogger(JWTLogoutHandler.class);

    public JWTLogoutHandler(JwtConfig jwtConfig, JwtTokenUtil tokenUtil) {
        this.jwtConfig = jwtConfig;
        this.tokenUtil = tokenUtil;
    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(jwtConfig.getHeader());
        if(authHeader!=null && !authHeader.equals("") && authHeader.length()>10) {
            tokenUtil.removeToken(authHeader);
        }
        PrintWriter out = null;
        try{
            response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            ResultMsg res = new ResultMsg(true, "注销登录成功");
            out.println(JSONUtils.toJSONString(res));
        }catch (IOException e){
            logger.error("注销登录出错", e);
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }
}
