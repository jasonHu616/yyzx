package com.hy.yyzx.pay;

import com.alipay.api.AlipayApiException;
import com.hy.yyzx.pay.alipay.AlipayUtils;

/**
 * @Author: Jason
 * @DateTime: 2020/4/16 18:11
 * @Description: 测试
 */
public class Test {

    public static void main(String[] args) {
        AlipayUtils alipay=new AlipayUtils();
        try {
            alipay.alipay();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
