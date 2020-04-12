package com.hy.yyzx.common.utils;

//import com.tencent.cloud.CosStsClient;
//import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

/**
 * 腾讯云存储工具类
 */
public class CosUtils {


    /**
     * 获取腾讯云需要的key
     * @return
     */
  /*  public static JSONObject getKeyTemp(String secretId,String secretKey,String bucket){
        TreeMap<String, Object> config = new TreeMap<String, Object>();

        try {
            // 替换为您的 SecretId
            config.put("SecretId", secretId);
            // 替换为您的 SecretKey
            config.put("SecretKey", secretKey);

            // 临时密钥有效时长，单位是秒
            config.put("durationSeconds", 60*60);

            // 换成您的 bucket
            config.put("bucket", bucket);
            // 换成 bucket 所在地区
            config.put("region", "ap-chongqing");

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的目录，例子：* 或者 doc/* 或者 picture.jpg
            config.put("allowPrefix", "*");

            // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分片上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            return CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
        } catch (Exception e) {
            //失败抛出异常
            e.printStackTrace();
            return  null;
        }
    }*/
}
