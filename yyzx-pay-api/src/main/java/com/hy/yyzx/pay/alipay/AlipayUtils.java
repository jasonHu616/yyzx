package com.hy.yyzx.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.springframework.util.ClassUtils;

/**
 * @Author: Jason
 * @DateTime: 2020/4/15 15:27
 * @Description: 支付宝支付
 */
public class AlipayUtils {
    private static final String appId="2021001155680610";//appid
    private static final String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCV2fV1p3jHBlQwlmVobSp3i4WU7DhzD0m+hla96zUQB/WK1EKeyPLBzCRjfkB7+EXQv5dtIVKZ+Ay3cJ4o8ZKNQOrD+JG4gx9MNmWqKHLTjBwOY/Up7IkgRYoY3JRd8pphLN3hI7R6cSt1l8g+45CEgKPDfIFI7qrT1p4rQN8FMCVfhXmguITteX3ZaIoKVi9M8kbiUrtKCQQWIFVsfnoQntApZWLm4LPZH8784OQcXO4fZyVUJnMTuFnxGi5FjsPQlvNOV/7+7HjhLk9bDdCB1c7cEwTScordzYjS/0CT7imzz7pGfVfkGbIHIGge4T7I7QQ9CNEmuQZ1G9+/VHA5AgMBAAECggEBAIc3JUYE1/a+vzkYfGi+GJPGUcMPY+Bu6xwAHEVC955/kBC1d2fP6zzz9+HPCmDSpiy2tOzEJMTKDq937z8CvDq5WR18LgFQT+11KEdiODqrMqXJjG9IAHQeS34ji706LBODaLK+hRv3AkAZtZcJwF3PtfWvYvlsYrjD7/UXlCYY6a1m3EJSqNu9eh/bpexTo59UwnNRa7w1+Coi4P0LL1YOuk7q1lHyDPhvbLCXkWDhdLr5VYnOBjglbshSgqyVR8uRuNdDjtjT1fKyIoW6fUo8AHD72IQnAnJDd1E9OHfrtHlnygPAOv8U63Tl0wUb4MS4HsAVM4x0BWEwdnEpAAECgYEAzlOC0DYPter/q87o1SPSjpWPI6pmr3ZWl9UvuJCkXWXn11kC+ptSHEBcDvfK5OtqA7zmsW+aUXL2T925rSyYaDS23mjVg8vIBGx+54PTh2HdYHgqNVR6xR5gFXTvfFHYDmOPqhszJAM38SFBzikr7lYck56tgGKwCTODnReXevkCgYEAue29WxvFtm2H2w6PeUx/rhJLcQlKY1FVqMo+I+yPqzT5ZQPBjbYv+24dFBs7ZBKHmepWRMeUX6lvTt1YbAuPV91tXHoHBraYD9spN/H5/tp1Vl5iZagVHKJPoCkxhoWID8skEM/RIm1HzZmasDcq5jHgFB2T43W74/PJMnC+r0ECgYBor26b/hISl12J6r2wBzD6L2AlFHxtYnmlKAHCf4El8mKjnQIp7UzIEM+kuI01gUyVAO9778g4sz/OXg2rlAjwNm7iggZzjJ27wODSzsww0LLmE5I25No10aemu2IXjd81zcZC5PIZv5hOvDULSVCE6SQuskAN+AShZoXM2v984QKBgCj96l6Y6+lBks9QEX/Lxuao7+sXAkiNWRZiqHqhIDe98TI9NsI4pbGIYG7bZie1EiJ0ENsPKX5By+GWWXVQwyS7E+5slPn87d+pERhdiCGRJoeJPaZt/WGXw3hrPJkM/aeFwQjX2KL/dvI0aG9ralDgNCgbMUzX2znxuK0fQFtBAoGBAMVq1PvmWZdEHGSS2c1r3fhhcb4BLrWqlrMoIBcmkmmPd/wsy2c1g6rjzE2/7sS5BAwckSkAn4RC6Lgmt6hzBoHrP73jMFN3CbGLGREsRjeXoU6Ewj//EasjsvpgF3LR5kCLvCjRTe/Awx1X2H4IjDoAbbUS1MxqzZn9DG7jZuYw";//私钥

    /**
     * 支付宝支付方法
     * @return
     * @throws AlipayApiException
     */
   public  String alipay() throws AlipayApiException {
       //构造client
       CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
       //设置网关地址
       certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");
       //设置应用Id
       certAlipayRequest.setAppId(appId);
       //设置应用私钥
       certAlipayRequest.setPrivateKey(privateKey);
       //设置请求格式，固定值json
       certAlipayRequest.setFormat("json");
       //设置字符集
       certAlipayRequest.setCharset("utf-8");
       //设置签名类型
       certAlipayRequest.setSignType("RSA2");
       //设置应用公钥证书路径
       String app_cert_path= ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/alipay/appCertPublicKey.crt";;
       certAlipayRequest.setCertPath(app_cert_path);
       //设置支付宝公钥证书路径
       String alipay_cert_path=ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/alipay/alipayCertPublicKey_RSA2.crt";;
       certAlipayRequest.setAlipayPublicCertPath(alipay_cert_path);
       //设置支付宝根证书路径
       String alipay_root_cert_path=ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/alipay/alipayRootCert.crt";;
       certAlipayRequest.setRootCertPath(alipay_root_cert_path);
       //构造client
       AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);

       //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
       AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
       //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
       AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
       model.setBody("我是测试数据");
       model.setSubject("App支付测试Java");
       model.setOutTradeNo("outtradeno");
       model.setTimeoutExpress("30m");
       model.setTotalAmount("0.01");
       model.setProductCode("QUICK_MSECURITY_PAY");
       request.setBizModel(model);
       request.setNotifyUrl("商户外网可以访问的异步地址");
       try {
           //这里和普通的接口调用不同，使用的是sdkExecute
           AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
           System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
       } catch (AlipayApiException e) {
           e.printStackTrace();
       }

       return null;

   }

}
