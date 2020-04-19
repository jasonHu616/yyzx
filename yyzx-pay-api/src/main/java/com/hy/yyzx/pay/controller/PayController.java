package com.hy.yyzx.pay.controller;

import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.pay.alipay.service.AlipayService;
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

    @Autowired
    private AlipayService alipayService;

    @PostMapping(value = "/wx")
    @ApiOperation(value = "微信支付",notes = "微信支付")
    public ResultData<MapEntity> wxPay(HttpServletRequest request,Integer id,Integer total,String serialnum){
        if(id==null || total==null || serialnum==null || serialnum.equals("")){
            return ResultData.failed("参数错误！请检查请求参数");
        }
        try {
            return wxPayService.doUnifiedOrder(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failed("拉起微信支付失败");
        }
    }


    @PostMapping(value = "/alipay")
    @ApiOperation(value = "支付宝支付",notes = "支付宝支付")
    public ResultData<MapEntity> aliPay(HttpServletRequest request){
        try {
            return alipayService.doUnifiedOrder(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failed("拉起支付宝支付失败");
        }
    }

}
