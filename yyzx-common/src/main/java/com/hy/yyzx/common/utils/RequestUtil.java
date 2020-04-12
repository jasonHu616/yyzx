package com.hy.yyzx.common.utils;



import com.hy.yyzx.common.modules.MapEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * {@link HttpServletRequest}扩展工具 RequestUtil
 *
 * @author Aidy
 * @version v1.0
 * @date 2016年5月12日
 */
public class RequestUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private static final String HEAD_HOST = "Host";
    private static final String HEAD_USERAGENT = "User-Agent";
    private static final String HEAD_PROTO = "X-Forwarded-Proto";
    private static final String HEAD_PORT = "X-Forwarded-Port";
    private static final String HEAD_REFERER = "Referer";

    private MapEntity postMap;
    private MapEntity getMap;

    public RequestUtil(HttpServletRequest request) {
        this.request = request;
        postMap = new MapEntity();
        getMap = new MapEntity();
        initRequestMap();
    }

    /**
     * 取得String值
     * String getString
     *
     * @param key    String requestname
     * @param defval String 默认值
     * @return String
     * @Description:从request从取String值
     * @author: Aidy
     * @createTime: 2016年5月12日下午4:35:39
     */
    public String getString(String key, String defval) {
        return request.getParameter(key) == null ? defval : request.getParameter(key);
    }

    /**
     * 取得Integer参数
     *
     * @param key
     * @param defval
     * @return
     */
    public Integer getInteger(String key, Integer defval) {
        if (request.getParameter(key) == null || !ValidateUtils.isInteger(request.getParameter(key).toString())) {
            return defval;
        }
        return Integer.parseInt(request.getParameter(key).toString());
    }


    /**
     * 取得Double参数
     *
     * @param key
     * @param defval
     * @return
     */
    public Double getDouble(String key, Double defval) {
        if (request.getParameter(key) == null || !ValidateUtils.isDouble(request.getParameter(key).toString())) {
            return defval;
        }
        return Double.valueOf(request.getParameter(key).toString());
    }

    /**
     * 取得Timestamp参数
     *
     * @param key
     * @param pattern 时间格式字串(yyyy-MM-dd HH:mm:ss)
     * @param defval
     * @return
     */
    public java.sql.Timestamp getTimestamp(String key, String pattern, java.sql.Timestamp defval) {
        if (request.getParameter(key) == null) {
            return defval;
        }
        pattern = pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String str = getString(key, "");
        if (str != null && !str.trim().equals("")) {
            Date date;
            try {
                date = sdf.parse(str.trim());
                return new java.sql.Timestamp(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return defval;
    }


    /**
     * 取主机名 String getHost
     *
     * @return
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:16:21
     */
    public String getHost() {
        return request.getHeader(HEAD_HOST);
    }

    /**
     * 取客户端UA String getUserAgent
     *
     * @return
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:16:43
     */
    public String getUserAgent() {
        return request.getHeader(HEAD_USERAGENT);
    }

    /**
     * 取服务端口 int getPort
     *
     * @return
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:17:28
     */
    public int getPort() {
        String port = request.getHeader(HEAD_PORT);
        if (ValidateUtils.isInteger(port)) {
            return Integer.parseInt(port);
        }
        return 80;
    }

    /**
     * 取来源页面 String getReferer
     *
     * @return
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:18:49
     */
    public String getReferer() {
        return request.getHeader(HEAD_REFERER);
    }

    /**
     * 取客户端IP
     * String getClientIp
     *
     * @return
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:20:40
     */
    public String getClientIp() {
        /*String cip = null;
        try {
            cip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") > -1) {
            String[] ips = ip.split(",");
            for (String s : ips) {
                if (s != null && !"".equals(s.trim()) && !"unknown".equals(s.trim())) {
                    return s.trim();
                }
            }
        }
        return ip;
    }

    public String getIPAddress() {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 取协议类型 String getProto
     *
     * @return
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:17:09
     */
    public String getProto() {
        return request.getHeader(HEAD_PROTO);
    }

    /**
     * 初始读取 void initRequestMap
     *
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:13:04
     */
    private void initRequestMap() {
        Enumeration<String> keys = request.getParameterNames();
        if (keys != null) {
            String queryString = "&" + request.getQueryString();
            String name = "";
            while (keys.hasMoreElements()) {
                name = keys.nextElement();
                if (queryString.indexOf("&" + name + "=") > -1) {
                    getMap.put(name, request.getParameter(name));
                } else {
                    postMap.put(name, request.getParameter(name));
                }
            }
        }
    }

    public String getBody() {
        try {
            return new BodyReaderHttpServletRequestWrapper(request).getBodyString(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取所有Post参数 MapEntity getPostParams
     *
     * @return {@link MapEntity}
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:11:51
     */
    public MapEntity getPostParams() {
        return postMap;
    }

    /**
     * // * 取所有Get参数 MapEntity getGetParams
     *
     * @return {@link MapEntity}
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:12:00
     */
    public MapEntity getGetParams() {
        return getMap;
    }

    /**
     * 取所有参数 MapEntity getAllParams
     *
     * @return {@link MapEntity}
     * @Description:
     * @author: Aidy
     * @createTime: 2016年5月12日下午3:12:20
     */
    public MapEntity getAllParams() {
        MapEntity map = new MapEntity();
        map.putAll(getMap);
        map.putAll(postMap);
        return map;
    }

}
