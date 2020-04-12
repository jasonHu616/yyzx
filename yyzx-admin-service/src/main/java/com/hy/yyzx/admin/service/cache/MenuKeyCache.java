package com.hy.yyzx.admin.service.cache;

import com.hy.yyzx.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:06
 * @Description: 菜单缓存
 */
@Component
public class MenuKeyCache {
    @Autowired
    RedisUtil redisUtil;

    private String getKey(){
        return "User_Menu";
    }
    /**
     * 菜单缓存
     * @param userId 登录用户id
     * @param menukey
     * @return
     */
    public boolean setMenu(Integer userId,String menukey){
        if(userId==null || menukey==null || menukey.equals("")){
            return false;
        }
        return redisUtil.set(getKey()+":"+userId,menukey);
    }

    /**
     * 获取短信用户菜单缓存
     * @param userId
     * @return
     */
    public String getMenu(Integer userId){
        if(userId==null ){
            return null;
        }
        return redisUtil.get(getKey()+":"+userId);

    }

}
