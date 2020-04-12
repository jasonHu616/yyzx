package com.hy.yyzx.admin.service.config;

import com.hy.yyzx.common.modules.UploadDefine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 20:18
 * @Description: 上传文件配置
 */
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfig {
    private String rootDir;
    private UploadDefine noDefine;
    private Map<String, UploadDefine> define;

    public UploadConfig(){

    }

    /*public void test(){
        System.out.println("root:"+rootDir);
        for(String s : noDefine.getAllow()) {
            System.out.println("allow:" + s);
        }
        for(String s : noDefine.getDeny()) {
            System.out.println("deny:" + s);
        }
        System.out.println("maxSize:" + noDefine.getMaxFileSize());
        System.out.println("define:" + define);
    }*/


    /**
     * 取上传定义
     * @param define
     * @return
     */
    public UploadDefine getDefine(String define){
        if(getDefine().get(define)==null)return noDefine;
        return getDefine().get(define);
    }

    public UploadDefine getNoDefine() {
        return noDefine;
    }

    public void setNoDefine(UploadDefine noDefine) {
        this.noDefine = noDefine;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }


    public Map<String, UploadDefine> getDefine() {
        return define;
    }

    public void setDefine(Map<String, UploadDefine> define) {
        this.define = define;
    }
}
