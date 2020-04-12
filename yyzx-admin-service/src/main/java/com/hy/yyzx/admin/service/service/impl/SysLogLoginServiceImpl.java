/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service.impl;

import com.hy.yyzx.admin.service.mapper.SysLogLoginMapper;
import com.hy.yyzx.admin.service.service.SysLogLoginService;
import com.hy.yyzx.common.model.SysLogLogin;
import com.hy.yyzx.common.model.SysLogLoginExample;
import me.jason.generator.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLogin, SysLogLoginExample, Integer> implements SysLogLoginService {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SysLogLoginServiceImpl.class);

    @Autowired
    public SysLogLoginMapper sysLogLoginMapper;

    @Autowired
    public void setMapper() {
        super.setMapper(sysLogLoginMapper);
    }
}