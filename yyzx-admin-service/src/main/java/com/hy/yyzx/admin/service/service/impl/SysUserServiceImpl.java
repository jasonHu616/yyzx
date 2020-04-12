/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service.impl;

import com.hy.yyzx.admin.service.mapper.SysUserMapper;
import com.hy.yyzx.admin.service.service.SysUserService;
import com.hy.yyzx.common.model.SysUser;
import com.hy.yyzx.common.model.SysUserExample;
import me.jason.generator.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserExample, Integer> implements SysUserService {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    public SysUserMapper sysUserMapper;

    @Autowired
    public void setMapper() {
        super.setMapper(sysUserMapper);
    }
}