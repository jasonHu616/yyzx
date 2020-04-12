package com.hy.yyzx.admin.service.security.filters;

import com.hy.yyzx.admin.service.config.JwtConfig;
import com.hy.yyzx.admin.service.security.authentication.SystemUserAuthToken;
import com.hy.yyzx.admin.service.security.utils.JwtTokenUtil;
import com.hy.yyzx.admin.service.security.vo.UserClaims;
import com.hy.yyzx.common.modules.ResponseStatus;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.common.utils.BodyReaderHttpServletRequestWrapper;
import com.hy.yyzx.common.utils.JSONUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Jason
 * @DateTime: 2020/4/10 19:30
 * @Description: jwt认证处理器
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    JwtConfig jwtConfig;
    JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.jwtConfig = jwtConfig;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String servletPath = req.getServletPath();

        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(req);

        if (servletPath.startsWith(jwtConfig.getAuthUrlPrefix())) {
            // 防止出现日志拦截器读取数据后 controller 不能读取数据的问题
            chain.doFilter(requestWrapper, res);
//            chain.doFilter(req, res);
            return;
        }

        SystemUserAuthToken authentication = getAuthentication(req);
        if(authentication==null){
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json; charset=utf-8");
            PrintWriter out = res.getWriter();
            ResultData result = ResultData.create().message("Authentication Faild").status(ResponseStatus.AUTHFAILD);
            out.write(JSONUtils.toJSONString(result));
            out.flush();
            out.close();
        }else {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 防止出现日志拦截器读取数据后 controller 不能读取数据的问题
            chain.doFilter(requestWrapper, res);
//            chain.doFilter(req, res);
        }
    }

    private SystemUserAuthToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtConfig.getHeader());
        if (token != null) {
            try {
                // parse the token.
                UserClaims claims = jwtTokenUtil.getUserClaimsFromToken(token);
                if (claims == null) return null;
                if (claims.getUsername() != null && claims.getId()!=0) {
                    jwtTokenUtil.expireToken(token);
                    return new SystemUserAuthToken(claims.getId(), claims.getUsername(), null);
                }
            }catch (Exception e){
                logger.error("获取Token凭据失败", e);
                return null;
            }
            return null;
        }
        return null;
    }

}
