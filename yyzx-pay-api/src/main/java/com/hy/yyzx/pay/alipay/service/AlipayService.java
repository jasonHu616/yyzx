package com.hy.yyzx.pay.alipay.service;

import com.alipay.api.AlipayApiException;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Jason
 * @DateTime: 2020/4/19 20:35
 * @Description: TODO 支付宝接口
 */
public interface AlipayService {
    /**
     * 支付宝下单接口
     * @param request
     * @return
     */
    ResultData<MapEntity> doUnifiedOrder(HttpServletRequest request) throws AlipayApiException;
}
