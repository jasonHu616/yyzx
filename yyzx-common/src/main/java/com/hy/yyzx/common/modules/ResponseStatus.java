package com.hy.yyzx.common.modules;

/**
 * @author Aidy 2018/3/20 0020 14:35
 */
public class ResponseStatus {
    /**
     * 成功
     */
    public static final int SUCCESS = 0;
    /**
     * 请求成功，无数据
     */
    public static final int NODATA = 1000;
    /**
     * 验证失败
     */
    public static final int AUTHFAILD = 1001;
    /**
     * 错误
     */
    public static final int ERROR = 1002;

    /**
     *integer类型为null时返回给前端的默认参数
     */
    public static final int Integer_null = -999;
}
