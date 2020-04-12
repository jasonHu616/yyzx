package com.hy.yyzx.admin.service.config;

import com.hy.yyzx.admin.service.interceptor.ActionInterceptor;
import com.hy.yyzx.admin.service.interceptor.PermissionInterceptor;
import com.hy.yyzx.admin.service.interceptor.SourceCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:14
 * @Description: 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    SourceCheckInterceptor sourceCheckInterceptor() {
        return new SourceCheckInterceptor();
    }

    @Bean
    PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    ActionInterceptor actionInterceptor(){
        return new ActionInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        //registry.addInterceptor(sourceCheckInterceptor());
        registry.addInterceptor(actionInterceptor()).excludePathPatterns(loginExludePaths())
                .excludePathPatterns("/loginUser/getloginUsers").excludePathPatterns("/user/uppwd")
                .excludePathPatterns("/swagger-ui.html").excludePathPatterns("/swagger/api-docs")
                .excludePathPatterns("/webjars/**").excludePathPatterns("/swagger-resources/**","/socket/**");
        registry.addInterceptor(permissionInterceptor()).excludePathPatterns(loginExludePaths());
    }


    /**
     * 登录拦截器排除地址
     * @return {@link List <String>}
     */
    private List<String> loginExludePaths(){
        List<String> list = new ArrayList<String>();
        list.add("/auth");
        list.add("/auth/**");
        list.add("/swagger-ui.html");
        list.add("/swagger/api-docs");
        list.add("/webjars/**");
        list.add("/swagger-resources/**");
        list.add("/socket/**");
        return list;
    }

}
