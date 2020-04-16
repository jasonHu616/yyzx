package com.hy.yyzx.pay.wx.util;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: Jason
 * @DateTime: 2020/4/15 9:42
 * @Description: 微信配置工具
 */
public class WXConfigUtil implements WXPayConfig {

    private byte[] certData;
    public static final String APP_ID = "wx8527454d6d88f670";
    public static final String KEY = "zgr3kIx9zqVn6gAHWmN3VOkgs7Y2jqsY";
    public static final String MCH_ID = "1533851931";

    public WXConfigUtil() throws Exception {
        String certPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/wx/apiclient_cert.p12";//从微信商户平台下载的安全证书存放的路径
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    @Override
    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
