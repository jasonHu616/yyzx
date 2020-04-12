package com.hy.yyzx.admin.service.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:58
 * @Description: 设备检测拦截类
 */
public class SourceCheckInterceptor extends BaseInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SourceCheckInterceptor.class);

    @Override
    public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(!requestCheck(request)){
            outputMessage(response,"设备认证出错！","noPermission",0);
            return false;
        }
        return true;
    }
}
