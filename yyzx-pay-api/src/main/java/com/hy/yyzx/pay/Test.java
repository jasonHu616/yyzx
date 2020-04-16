package com.hy.yyzx.pay;

import com.alipay.api.AlipayApiException;
import com.hy.yyzx.pay.alipay.Alipay;

/**
 * @Author: Jason
 * @DateTime: 2020/4/16 18:11
 * @Description: 测试
 */
public class Test {

    public static void main(String[] args) {
        Alipay alipay=new Alipay();
        try {
            alipay.alipay();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
