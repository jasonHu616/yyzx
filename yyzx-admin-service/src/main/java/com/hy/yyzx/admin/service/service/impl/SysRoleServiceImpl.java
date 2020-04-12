/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service.impl;

import com.hy.yyzx.admin.service.mapper.SysRoleMapper;
import com.hy.yyzx.admin.service.service.SysRoleService;
import com.hy.yyzx.common.model.SysRole;
import com.hy.yyzx.common.model.SysRoleExample;
import me.jason.generator.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleExample, Integer> implements SysRoleService {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    public SysRoleMapper sysRoleMapper;

    @Autowired
    public void setMapper() {
        super.setMapper(sysRoleMapper);
    }
}