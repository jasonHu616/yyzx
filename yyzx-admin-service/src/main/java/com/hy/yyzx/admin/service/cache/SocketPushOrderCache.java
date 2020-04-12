package com.hy.yyzx.admin.service.cache;

import com.hy.yyzx.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author DGY 2019年12月2日 16:46:12
 * 查看redis中待推送的订单缓存
 */
@Component
public class SocketPushOrderCache {
    @Autowired
    RedisUtil redisUtil;


    private String getKey(){
        return "socket_push_order";
    }

  /*  *//**
     * 保存socket用户
     * @return
     *//*
    public boolean setPharmacyInfo(Integer pharmacyid, AdvPrescriptOrder phrmacyinfo){
       if(pharmacyid==null || phrmacyinfo==null){
           return false;
       }
       return redisUtil.set(getKey()+":"+pharmacyid.toString(),phrmacyinfo,60*60*24*5);
    }

    *//**
     * 根据authtoken 获取用户
     * @param pharmacyid
     * @return
     *//*
    public AdvPrescriptOrder getPharmacyInfo(Integer pharmacyid){
        if(pharmacyid==null){
            return null;
        }
        return redisUtil.get(getKey()+":"+pharmacyid.toString(),AdvPrescriptOrder.class);
    }

    *//**
     * 根据authtoken 获取用户session
     * @param pharmacyid
     * @return
     *//*
    public boolean removePharmacyInfo(Integer pharmacyid){
        if(pharmacyid==null){
            return false;
        }
        return redisUtil.remove(getKey()+":"+pharmacyid.toString());
    }

    public List<AdvPrescriptOrder> getPharmacyInfos(){
        return redisUtil.getList(getKey(),AdvPrescriptOrder.class);
    }*/

    /**
     * 获取药房ids
     * @return
     */
    public String getPharmacyIds(){
        List<Integer> keys = null;
        String ids =null;
        Set<String> set = redisUtil.keys(getKey());
        if (set!=null){
            keys=new ArrayList<>();
            ids="";
            for(String s:set){
                keys.add(Integer.parseInt(s));
                ids+=s+",";
            }
        }
        if (ids!=null){
            ids=ids.substring(0,ids.length()-1);
        }
        return ids;
    }

    public Set<String> getPharmacyIdSet(){
        Set<String> set = redisUtil.keys(getKey()+":*");
        return set;
    }

}
