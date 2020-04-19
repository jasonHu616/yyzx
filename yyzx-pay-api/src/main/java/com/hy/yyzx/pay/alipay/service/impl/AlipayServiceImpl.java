package com.hy.yyzx.pay.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.pay.alipay.AlipayUtils;
import com.hy.yyzx.pay.alipay.service.AlipayService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Jason
 * @DateTime: 2020/4/19 20:39
 * @Description: TODO
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    @Override
    public ResultData<MapEntity> doUnifiedOrder(HttpServletRequest request) throws AlipayApiException {
        AlipayUtils alipayUtils=new AlipayUtils();
        return alipayUtils.alipay();
    }
}
