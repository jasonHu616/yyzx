package com.hy.yyzx.pay.controller;

import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.pay.wx.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Jason
 * @DateTime: 2020/4/15 13:52
 * @Description: 支付api接口
 */
@RestController
@RequestMapping(value = "/yyzx/pay")
@Api(value = "支付api信息",tags = "支付api信息")
public class PayController {

    @Autowired
    private WxPayService wxPayService;

    @PostMapping(value = "/wx")
    @ApiOperation(value = "微信支付",notes = "微信支付")
    public ResultData<MapEntity> hisPay(HttpServletRequest request){
        try {
            return wxPayService.doUnifiedOrder(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failed("拉起微信支付失败");
        }
    }

}
