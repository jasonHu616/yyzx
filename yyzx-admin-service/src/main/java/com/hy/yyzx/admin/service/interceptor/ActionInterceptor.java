package com.hy.yyzx.admin.service.interceptor;

import com.hy.yyzx.admin.service.service.SysLogActionService;
import com.hy.yyzx.common.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:41
 * @Description: 操作拦截
 */
public class ActionInterceptor extends BaseInterceptor implements HandlerInterceptor {

    @Autowired
    SysLogActionService sysLogActionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SysUser user = getLoginUser(request);
        logger.info("保存操作日志，request : " + request);
        if(user.getId().intValue()>-2) {
            if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod()) || "GET".equals(request.getMethod())) {
                sysLogActionService.saveLog(request, user.getId());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
