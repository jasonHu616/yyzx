package com.hy.yyzx.admin.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:57
 * @Description: 公用权限控制类
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CommonPermissionControl {
    /**
     * 公共部分权限控制多为数组
     * @return
     */
    PermissionControl[] permissionControl();
}
