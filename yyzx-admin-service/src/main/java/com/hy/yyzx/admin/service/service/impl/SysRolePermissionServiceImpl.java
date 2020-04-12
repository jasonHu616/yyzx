/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service.impl;

import com.hy.yyzx.admin.service.mapper.SysRolePermissionMapper;
import com.hy.yyzx.admin.service.service.SysRolePermissionService;
import com.hy.yyzx.common.model.SysRolePermission;
import com.hy.yyzx.common.model.SysRolePermissionExample;
import me.jason.generator.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission, SysRolePermissionExample, Integer> implements SysRolePermissionService {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);

    @Autowired
    public SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    public void setMapper() {
        super.setMapper(sysRolePermissionMapper);
    }
}