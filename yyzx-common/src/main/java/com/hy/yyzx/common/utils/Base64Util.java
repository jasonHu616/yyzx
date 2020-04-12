package com.hy.yyzx.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class Base64Util {

    private static final Logger log = LoggerFactory.getLogger(Base64Util.class);

    private Base64Util(){}

    /**
     * base64转码
     * @param arg
     * @return
     * @throws Exception
     */
    public static String strToBase64(String arg){
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] bytes = arg.getBytes("UTF-8");
            return encoder.encodeToString(bytes);
        }catch (Exception e){
            log.error("base64转码异常：" + e.getMessage());
            return null;
        }
    }

    /**
     * base64解码
     * @param arg
     * @return
     * @throws Exception
     */
    public static String base64ToStr(String arg){
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            return new String(decoder.decode(arg),"UTF-8");
        }catch (Exception e){
            log.error("base64解码异常：" + e.getMessage());
            return null;
        }
    }
}
