package com.hy.yyzx.admin.service.cache;

import com.hy.yyzx.admin.service.service.SysRolePermissionService;
import com.hy.yyzx.common.model.SysRolePermission;
import com.hy.yyzx.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jason
 * @DateTime: 2020/4/11 10:03
 * @Description: 权限缓存
 */
@Component
public class PermissionCache {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    private String getKey(){
        return "rolePermission";
    }

    /**
     * 刷新权限缓存
     * @return
     */
    public boolean reload(){
        List<SysRolePermission> permissionList=sysRolePermissionService.selectByExample(null);
        return redisUtil.setList(getKey(),permissionList);
    }

    /**
     * 读取权限
     * @param roleid 用户组id
     * @return
     */
    public List<SysRolePermission> getPermissionByRoleid(int roleid){
        List<SysRolePermission> permissionList=redisUtil.getList(getKey(),SysRolePermission.class);
        List<SysRolePermission> resultList = new ArrayList<SysRolePermission>();
        if(permissionList!=null && permissionList.size()>0){
            for(SysRolePermission p : permissionList){
                if(p.getRoleid().intValue()==roleid){
                    resultList.add(p);
                }
            }
        }
        return resultList;
    }
}
