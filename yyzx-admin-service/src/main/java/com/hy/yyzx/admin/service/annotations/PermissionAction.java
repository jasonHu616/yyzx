package com.hy.yyzx.admin.service.annotations;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:55
 * @Description: 权限操作成员量
 */
public enum PermissionAction {
    /**
     * 控制操作：新增或删除，根据PermissionControl.idField是否为空确认操作类型
     */
    ADDOREDIT,
    /**
     * 控制操作：新增权限
     */
    ADD,
    /**
     * 控制操作：编辑权限
     */
    EDIT,
    /**
     * 控制操作：数据权限
     */
    LIST,
    /**
     * 控制操作：删除权限
     */
    DELETE,
    /**
     * 三项权限任意一项
     */
    ANY,
    /**
     * 不控制
     */
    NONE
}
