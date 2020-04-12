package com.hy.yyzx.admin.service.cache;

import com.hy.yyzx.admin.service.service.SysConfigService;
import com.hy.yyzx.common.model.SysConfig;
import com.hy.yyzx.common.model.SysConfigExample;
import com.hy.yyzx.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:05
 * @Description: 配置缓存
 */
@Component
public class ConfigCache {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysConfigService sysConfigService;

    private String getKey(){
        return "config";
    }

    /**
     * 保存配置缓存
     * @param sysConfig
     * @return
     */
    public boolean setConfig(SysConfig sysConfig){
        if(sysConfig==null){
            return false;
        }
        return redisUtil.set(getKey()+":"+sysConfig.getKeycode(),sysConfig);
    }

    /**
     * 读起配置缓存
     * @param keycode
     * @param refresh 是否刷新缓存
     * @return
     */
    public SysConfig getConfig(String keycode,boolean refresh){
        if(keycode==null || keycode.equals("")){
            return null;
        }
        SysConfig config=redisUtil.get(getKey()+":"+keycode,SysConfig.class);
        if(refresh || config==null){
            SysConfigExample example = new SysConfigExample();
            example.createCriteria().andKeycodeEqualTo(keycode);
            config=sysConfigService.selectByCondition(example);
            setConfig(config);
        }
        return config;
    }
}
