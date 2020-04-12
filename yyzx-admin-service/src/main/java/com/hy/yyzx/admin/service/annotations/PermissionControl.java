package com.hy.yyzx.admin.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:53
 * @Description: 权限控制类u
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PermissionControl {
    /**
     *指定控制的权限操作
     */
    PermissionAction action() default PermissionAction.NONE;

    /**
     * ID参数名称，request请求过来的id参数名称。如获取的ID是Integer并且不等于0则为编辑权限，否则为新增权限
     */
    String idField() default "";

    /**
     * 控制权限对应的菜单keycode
     * @return
     */
    String menukey() default "";

    /**
     * 公用部分控制的权限方法名
     * @return
     */
    String methodName() default "";

    /**
     * 是否超级用户权限
     * @return
     */
    boolean superUser() default false;
    /**
     * 是否开发权限
     * @return
     */
    boolean devmode() default false;


}
