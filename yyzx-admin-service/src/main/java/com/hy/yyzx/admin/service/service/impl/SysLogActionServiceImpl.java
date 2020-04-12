/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service.impl;

import com.hy.yyzx.admin.service.mapper.SysLogActionMapper;
import com.hy.yyzx.admin.service.service.SysLogActionService;
import com.hy.yyzx.common.model.SysLogAction;
import com.hy.yyzx.common.model.SysLogActionExample;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.utils.JSONUtils;
import com.hy.yyzx.common.utils.RequestUtil;
import me.jason.generator.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Service
public class SysLogActionServiceImpl extends BaseServiceImpl<SysLogAction, SysLogActionExample, Integer> implements SysLogActionService {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SysLogActionServiceImpl.class);

    @Autowired
    public SysLogActionMapper sysLogActionMapper;

    @Autowired
    public void setMapper() {
        super.setMapper(sysLogActionMapper);
    }

    @Override
    public void saveLog(HttpServletRequest request, Integer id) {
        SysLogAction log=new SysLogAction();
        RequestUtil requestUtil = new RequestUtil(request);
        log.setUserid(id);
        log.setIp(requestUtil.getIPAddress());
        if("post".equals(request.getMethod().toLowerCase())){
            log.setPostdata(requestUtil.getPostParams()==null ? null : getParams(requestUtil.getPostParams()));
        }
        log.setPostdata(requestUtil.getPostParams()==null ? null : getParams(requestUtil.getPostParams()));
        if("put".equals(request.getMethod().toLowerCase())){
            String body = requestUtil.getBody();
            if(body.startsWith("[")){//list
                List<MapEntity> mapEntities= JSONUtils.parseList(body,MapEntity.class);
                for(int i=0;i<mapEntities.size();i++){
                    MapEntity map = mapEntities.get(i);
                    if(!map.getString("password").equals("")){
                        map.put("password", "******");
                    }
                    if(!map.getString("oldpwd").equals("")){
                        map.put("oldpwd", "******");
                    }
                    if(!map.getString("newpwd").equals("")){
                        map.put("newpwd", "******");
                    }
                    if(!map.getString("validpwd").equals("")){
                        map.put("validpwd", "******");
                    }
                    if(!map.getString("user.password").equals("")){
                        map.put("user.password", "******");
                    }
                    mapEntities.remove(i);
                    mapEntities.add(i,map);
                }
                log.setPostdata(JSONUtils.toJSONString(mapEntities));
            }else {
                log.setPostdata(body == null ? null : JSONUtils.toJSONString(body));
            }
        }
        if("get".equals(request.getMethod().toLowerCase())){
            log.setPostdata(requestUtil.getGetParams()==null ? null : getParams(requestUtil.getGetParams()));
        }
        if(request.getHeaders("referee")==null || request.getHeaders("referee").toString().trim().equals("")){
            log.setReferer("");
        }else{
            log.setReferer(request.getHeaders("referee").toString().trim());
        }
        log.setUa(requestUtil.getUserAgent());
        log.setUrl(request.getRequestURI());
        log.setCteatetime(new Timestamp(System.currentTimeMillis()));
        sysLogActionMapper.insertSelective(log);
    }

    private String getParams(MapEntity map){
        if(!map.getString("password").equals("")){
            map.put("password", "******");
        }
        if(!map.getString("oldpwd").equals("")){
            map.put("oldpwd", "******");
        }
        if(!map.getString("newpwd").equals("")){
            map.put("newpwd", "******");
        }
        if(!map.getString("validpwd").equals("")){
            map.put("validpwd", "******");
        }
        if(!map.getString("user.password").equals("")){
            map.put("user.password", "******");
        }
        return JSONUtils.toJSONString(map);
    }

}