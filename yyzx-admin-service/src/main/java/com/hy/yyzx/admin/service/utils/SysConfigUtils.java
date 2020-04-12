package com.hy.yyzx.admin.service.utils;

import com.hy.yyzx.admin.service.service.SysConfigService;
import com.hy.yyzx.common.model.SysConfig;
import com.hy.yyzx.common.model.SysConfigExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:16
 * @Description: 获取系统配置的工具类
 */
public class SysConfigUtils implements Serializable {
    @Autowired
    SysConfigService sysConfigService;

    /**
     * 根据keycode获取配置项
     * @param keycode
     * @return
     */

    public SysConfig getConfigByKey(String keycode){
        SysConfigExample configExample=new SysConfigExample();
        configExample.createCriteria().andKeycodeEqualTo(keycode);
        return sysConfigService.selectByCondition(configExample);
    }
}
