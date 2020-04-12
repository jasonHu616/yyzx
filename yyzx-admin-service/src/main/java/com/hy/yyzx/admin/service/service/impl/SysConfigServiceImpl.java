/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service.impl;

import com.hy.yyzx.admin.service.mapper.SysConfigMapper;
import com.hy.yyzx.admin.service.service.SysConfigService;
import com.hy.yyzx.common.model.SysConfig;
import com.hy.yyzx.common.model.SysConfigExample;
import me.jason.generator.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, SysConfigExample, Integer> implements SysConfigService {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SysConfigServiceImpl.class);

    @Autowired
    public SysConfigMapper sysConfigMapper;

    @Autowired
    public void setMapper() {
        super.setMapper(sysConfigMapper);
    }
}