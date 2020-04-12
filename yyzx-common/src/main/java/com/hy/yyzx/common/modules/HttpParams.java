package com.hy.yyzx.common.modules;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.security.*;

/**
 * Http请求参数对象
 * HttpParams
 *
 * @author Aidy 2016年9月19日
 * @version v1.0
 */
public class HttpParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private MapEntity params;
    private MapEntity headers;
    private String body;
    private Cookie[] cookies;
    private SSLConnectionSocketFactory sslFactory;
    private boolean encodeParams = true;

    /**
     * 创建新实例
     * HttpParams create
     *
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:17:51
     */
    public static HttpParams create() {
        return new HttpParams();
    }

    /**
     * 参数不进行编码
     *
     * @return
     */
    public HttpParams withoutEncodeParams() {
        this.encodeParams = false;
        return this;
    }

    /**
     * 取请求参数
     * MapEntity getParams
     *
     * @return {@link MapEntity} 参数Map
     * @author Aidy 2016年9月19日下午4:03:51
     */
    public MapEntity getParams() {
        return params;
    }

    /**
     * 设置请求参数
     * void setParams
     *
     * @param params {@link MapEntity} 参数Map
     * @author Aidy 2016年9月19日下午4:04:16
     */
    public void setParams(MapEntity params) {
        this.params = params;
    }

    /**
     * 取请求头
     * MapEntity getHeaders
     *
     * @return {@link MapEntity}
     * @author Aidy 2016年9月19日下午4:04:29
     */
    public MapEntity getHeaders() {
        return headers;
    }

    /**
     * 设置请求头
     * void setHeaders
     *
     * @param headers {@link MapEntity} 请求头Map
     * @author Aidy 2016年9月19日下午4:04:40
     */
    public void setHeaders(MapEntity headers) {
        this.headers = headers;
    }

    /**
     * 取数据体
     * String getBody
     *
     * @return {@link String}
     * @author Aidy 2016年9月19日下午4:05:00
     */
    public String getBody() {
        return body;
    }

    /**
     * 设置数据体
     * void setBody
     *
     * @param body {@link String} 数据体
     * @author Aidy 2016年9月19日下午4:05:20
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 取Cookie信息(暂无实现)
     * Cookie[] getCookies
     *
     * @return {@link Cookie}[]
     * @author Aidy 2016年9月19日下午4:12:31
     */
    public Cookie[] getCookies() {
        return cookies;
    }

    /**
     * 设置Cookie信息(暂无实现)
     * void setCookies
     *
     * @param cookies {@link Cookie}[] Cookie
     * @author Aidy 2016年9月19日下午4:13:05
     */
    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    /**
     * 取得SSL访问安全工厂
     * SSLConnectionSocketFactory getSslFactory
     *
     * @return {@link SSLConnectionSocketFactory}
     * @author Aidy 2016年10月20日下午1:56:19
     */
    public SSLConnectionSocketFactory getSslFactory() {
        return sslFactory;
    }

    /**
     * 设置SSL安全工厂
     * void setSslFactory
     *
     * @param sslFactory {@link SSLConnectionSocketFactory}
     * @author Aidy 2016年10月20日下午1:56:41
     */
    public void setSslFactory(SSLConnectionSocketFactory sslFactory) {
        this.sslFactory = sslFactory;
    }

    /**
     * 增加参数
     * HttpParams addParams
     *
     * @param inParams {@link MapEntity} 参数Map
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:06:36
     */
    public HttpParams addParams(MapEntity inParams) {
        if (inParams != null && !inParams.isEmpty()) {
            if (params == null) params = new MapEntity();
            params.putAll(inParams);
        }
        return this;
    }

    /**
     * 增加参数
     * HttpParams addParams
     *
     * @param key {@link String} 参数名称
     * @param val {@link String} 参数值
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:06:36
     */
    public HttpParams addParams(String key, String val) {
        if (params == null) params = new MapEntity();
        params.put(key, val);
        return this;
    }

    /**
     * 移除参数
     * HttpParams removeParams
     *
     * @param key {@link String} 参数名称
     * @author Aidy 2016年9月19日下午4:07:23
     * @return    {@link HttpParams}
     */
    public HttpParams removeParams(String key) {
        if (params != null && params.get(key) != null) {
            params.remove(key);
        }
        return this;
    }

    /**
     * 清除所有参数
     * HttpParams clearParams
     *
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:07:23
     */
    public HttpParams clearParams() {
        if (params != null) {
            params.clear();
        }
        return this;
    }

    /**
     * 添加请求头
     * HttpParams addHeader
     *
     * @param inHeaders {@link MapEntity} 请求头Map
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:09:30
     */
    public HttpParams addHeader(MapEntity inHeaders) {
        if (inHeaders != null && !inHeaders.isEmpty()) {
            if (headers == null) headers = new MapEntity();
            headers.putAll(inHeaders);
        }
        return this;
    }

    /**
     * 添加请求头
     * HttpParams addHeader
     *
     * @param name  {@link String} 头名称
     * @param value {@link String} 头内容
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:09:30
     */
    public HttpParams addHeader(String name, String value) {
        if (headers == null) headers = new MapEntity();
        headers.put(name, value);
        return this;
    }

    /**
     * 移除请求头
     * HttpParams removeHeader
     *
     * @param name {@link String} 头名称
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:11:05
     */
    public HttpParams removeHeader(String name) {
        if (headers != null && headers.get(name) != null) {
            headers.remove(name);
        }
        return this;
    }

    /**
     * 清除请求头
     * HttpParams clearHeaders
     *
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:12:01
     */
    public HttpParams clearHeaders() {
        if (headers != null) {
            headers.clear();
        }
        return this;
    }

    /**
     * 绑定数据流
     * HttpParams bindBytes
     *
     * @param inBody {@link String} 传入数据体
     * @return {@link HttpParams}
     * @author Aidy 2016年9月19日下午4:22:59
     */
    public HttpParams bindBody(String inBody) {
        if (inBody != null && inBody.length() > 0) {
            body = inBody;
        }
        return this;
    }

    /**
     * 从KeyStore设置安全连接
     * void loadSSLFactoryByKeyStore
     *
     * @param keyStore {@link KeyStore} 密钥库
     * @param password {@link String} 密钥库密码
     * @return HttpParams
     * @author Aidy 2016年10月20日下午1:59:25
     */
    public HttpParams loadSSLFactoryByKeyStore(KeyStore keyStore, String password) {
        SSLContext sslContext;
        try {
            sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, password.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            sslFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
        } catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isEncodeParams() {
        return encodeParams;
    }

    public void setEncodeParams(boolean encodeParams) {
        this.encodeParams = encodeParams;
    }
}
