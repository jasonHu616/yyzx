package com.hy.yyzx.pay.wx.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.common.utils.JSONUtils;
import com.hy.yyzx.pay.utils.RandomUtil;
import com.hy.yyzx.pay.wx.service.WxPayService;
import com.hy.yyzx.pay.wx.util.WXConfigUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @DateTime: 2020/4/15 9:54
 * @Description: 微信支付实现类
 */
@Service
public class WxPayServiceImpl implements WxPayService {
    public static final String SPBILL_CREATE_IP = "192.168.3.12";
    public static final String NOTIFY_URL = "http://localhost";
    public static final String TRADE_TYPE_APP = "APP";//app支付


    @Override
    public ResultData<MapEntity> doUnifiedOrder(HttpServletRequest request) throws Exception {
        try {
            WXConfigUtil config = new WXConfigUtil();
            WXPay wxpay = new WXPay(config);
            Map<String, String> data = new HashMap<>();
            //生成商户订单号，不可重复
            data.put("appid", config.getAppID());
            data.put("mch_id", config.getMchID());
            data.put("nonce_str", WXPayUtil.generateNonceStr());
            String body = "订单支付";
            data.put("body", body);
            data.put("out_trade_no", RandomUtil.serialNum("WX"));
            data.put("total_fee", "1");
            //自己的服务器IP地址
            data.put("spbill_create_ip", getIpAddress(request));
            //异步通知地址（请注意必须是外网）
            data.put("notify_url", NOTIFY_URL);
            //交易类型
            data.put("trade_type", TRADE_TYPE_APP);
            //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
            data.put("attach", "");
            data.put("sign", WXPayUtil.generateSignature(data, config.getKey(),
                    WXPayConstants.SignType.MD5));
            //使用官方API请求预付订单
            Map<String, String> response = wxpay.unifiedOrder(data);
            if ("SUCCESS".equals(response.get("return_code"))) {//主要返回以下5个参数
                MapEntity map=new MapEntity();
                Map<String, String> param = new HashMap<>();
                param.put("appid",config.getAppID());
                param.put("partnerid",response.get("mch_id"));
                param.put("prepayid",response.get("prepay_id"));
                param.put("package","Sign=WXPay");
                param.put("noncestr",WXPayUtil.generateNonceStr());
                param.put("timestamp",System.currentTimeMillis()/1000+"");
                param.put("sign",WXPayUtil.generateSignature(param, config.getKey(),
                        WXPayConstants.SignType.MD5));
                return ResultData.success("下单成功！", JSONUtils.toJSONString(param));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failed("下单失败");
        }
        return ResultData.failed("下单失败");
    }


    /**
     * 取得客户端IP地址
     * @return	String 客户端IP地址
     */
    private String getIpAddress(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if(ipAddress.indexOf(",")>-1){
            String[] ips = ipAddress.split(",");
            for(String s : ips){
                if(s!=null && !"".equals(s.trim()) && !"unknown".equals(s.trim())){
                    return s.trim();
                }
            }
        }
        return ipAddress;
    }
    /**
     * 获取当前时间
     * @return
     */
    protected Timestamp getCurrenttime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
