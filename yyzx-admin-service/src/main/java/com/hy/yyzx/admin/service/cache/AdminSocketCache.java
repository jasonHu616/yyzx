package com.hy.yyzx.admin.service.cache;

import com.hy.yyzx.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:08
 * @Description: sochet缓存
 */
@Component
public class AdminSocketCache {
    @Autowired
    RedisUtil redisUtil;


    private String getKey(){
        return "adminsocket";
    }

    /**
     * 保存socket用户
     * @return
     */
    public boolean setSession(String authtoken, Session session){
        if(authtoken==null || session==null){
            return false;
        }
        return redisUtil.set(getKey()+":"+authtoken,session,60*60*24*5);
    }

    /**
     * 根据authtoken 获取用户session
     * @param authtoken
     * @return
     */
    public Session getSession(String authtoken){
        if(authtoken==null){
            return null;
        }
        return redisUtil.get(getKey()+":"+authtoken,Session.class);
    }

    /**
     * 根据authtoken 获取用户session
     * @param authtoken
     * @return
     */
    public boolean removeSession(String authtoken){
        if(authtoken==null){
            return false;
        }
        return redisUtil.remove(getKey()+":"+authtoken);
    }

    public List<Session> getSessions(){
        List<Session> sessionList=redisUtil.getList(getKey(),Session.class);
        if (sessionList==null){
            return new ArrayList<>();
        }
        return sessionList;
    }

}
