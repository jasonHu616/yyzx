package com.hy.yyzx.pay.wx.service;

import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Jason
 * @DateTime: 2020/4/15 9:51
 * @Description: 微信支付接口
 */
public interface WxPayService {

    /**
     * 微信下单拉起支付接口
     * @return
     * @throws Exception
     */
    ResultData<MapEntity> doUnifiedOrder(HttpServletRequest request) throws Exception;
}
