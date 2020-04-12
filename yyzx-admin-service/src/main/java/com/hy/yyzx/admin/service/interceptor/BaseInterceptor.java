package com.hy.yyzx.admin.service.interceptor;

import com.hy.yyzx.admin.service.config.JwtConfig;
import com.hy.yyzx.admin.service.security.utils.JwtTokenUtil;
import com.hy.yyzx.admin.service.security.vo.UserClaims;
import com.hy.yyzx.admin.service.service.SysUserService;
import com.hy.yyzx.common.model.SysUser;
import com.hy.yyzx.common.modules.ResultMsg;
import com.hy.yyzx.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:42
 * @Description: 拦截器基础类
 */
public class BaseInterceptor {
    protected static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);


    @Autowired
    SysUserService sysUserService;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    protected UserClaims getLoginUserClaims(HttpServletRequest request){
        logger.info("获取用户基础信息开始");
        String token = request.getHeader(jwtConfig.getHeader());
        if(token!=null && token.length()>0) {
            return jwtTokenUtil.getUserClaimsFromToken(token);
        }
        logger.error("获取用户基础信息失败，token == null || token.length() < 0");
        return null;
    }
    protected SysUser getLoginUser(HttpServletRequest request){
        logger.info("获取用户信息开始");
        UserClaims claims = getLoginUserClaims(request);
        if(claims!=null){
            return sysUserService.selectByPrimaryKey(claims.getId());
        }
        logger.error("获取用户信息失败，UserClaims == null");
        return null;
    }


    /**
     * 判断来源是否合规
     * @param request request
     * @return {@link boolean 返回是否是微信端请求}
     */
    protected boolean requestCheck(HttpServletRequest request){
        String source=request.getHeader("SOURCE");
        if(source==null){
            return false;
        }
        return source.equalsIgnoreCase("engineering-admin");
    }




    /**
     * 获取登录的Authorization
     * @param request request
     * @return {@link String} 若获取不到，则返回null
     */
    protected String getAuthorization(HttpServletRequest request){
        String authorization=request.getHeader("Authorization");
        if(authorization==null || authorization.trim().equals("")){
            return null;
        }
        return authorization;
    }


    /**
     * 输出拦截信息返回给前端
     * @param response response
     * @param msg 输出信息
     */
    protected void outputMessage(HttpServletResponse response, String msg, String errorTips, Integer status){
        PrintWriter out = null;
        try{
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            out.println(JSONUtils.toJSONString(new ResultMsg(false, msg,errorTips,status)));
        }catch(Exception e){
            logger.error("输出权限控制信息出错", e);
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }
}
