package com.hy.yyzx.common.utils;

public class OSUtil {

    public static String osType(){
        String os = System.getProperty("os.name");
        if(os.indexOf("win") > -1 || os.indexOf("Win") > -1)
            return "win";
        return "linux";
    }

    public static String path(){
        return System.getProperty("user.dir");
    }
}
