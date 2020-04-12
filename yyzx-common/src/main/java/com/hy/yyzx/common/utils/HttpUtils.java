package com.hy.yyzx.common.utils;

import com.hy.yyzx.common.utils.beanutils.RefObject;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final int readTimeout = 10000;

    private static final int connectionTimeout = 10000;

    private static final String DEFAULT_ENCODE = "UTF-8";

    public static String get(String url, Map<String, String> heards) throws Exception{
        if(url == null || "".equals(url)) throw new NullPointerException(url + " is null");
        URL http = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) http.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(readTimeout);
        connection.setConnectTimeout(connectionTimeout);
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Accept-Charset", "utf-8");
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        if (heards != null) {
            Iterator<Map.Entry<String, String>> it =heards.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.connect();
        int code = connection.getResponseCode();
        if(code != 200){
            throw new IOException("code" + code + connection.getResponseMessage());
        }
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }

    public static String get(String url, String param, Map<String, String> heards) throws Exception{
        StringBuffer buffer = new StringBuffer();
        buffer.append(url).append("?").append(param);
        String respMsg = get(buffer.toString(), heards);
        return respMsg;
    }

    public static String get(String url) throws Exception{
        if(url == null || "".equals(url)) throw new NullPointerException("url is null");
        String respMsg = get(url, null);
        return respMsg;
    }

    public static String post(String url, String param) throws Exception{
        if(url == null || "".equals(url)) throw new NullPointerException("url is null");
        return post(url, param, null);
    }

    public static String post(String url, String param, Map<String, String> heards) throws Exception{
        if(url == null || "".equals(url)) throw new NullPointerException("url is null");
        if(param == null || "".equals(url)) throw new NullPointerException("param is null");
        URL http = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) http.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(connectionTimeout);
        connection.setReadTimeout(readTimeout);
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Charset", "utf-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        if (heards != null) {
            Iterator<Map.Entry<String, String>> it =heards.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.connect();
        PrintWriter writer = new PrintWriter(connection.getOutputStream());
        writer.write(param);
        writer.flush();
        int code = connection.getResponseCode();
        if(code != 200){
            throw new RuntimeException("请求失败code=" + code + " msg=" + connection.getResponseMessage());
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        //关闭
        writer.close();
        in.close();
        connection.disconnect();
        return builder.toString();
    }

    public static String postHttpClient(String url, String reqData) throws Exception{
        if(url == null || "".equals(url)) throw new NullPointerException("url is null");
        HttpPost request = new HttpPost(url);
        if (reqData != null) {
            HttpEntity entity = EntityBuilder.create().setContentEncoding(DEFAULT_ENCODE).setBinary(reqData.getBytes(DEFAULT_ENCODE)).build();
            request.setEntity(entity);
        }

        RefObject<Object> ref_content = new RefObject((Object)null);
        accessUrl(request, null, null, String.class, ref_content, true);
        String respText = (String)ref_content.argvalue;
        return respText;
    }

    private static CloseableHttpResponse accessUrl(HttpUriRequest request, Map<String, String> reqHeads, Map<String, String> respHeads, Class<?> respType, RefObject<Object> ref_content, boolean isThrowException) throws IOException {
        CloseableHttpClient httpclient = null;
        int socketTimeout = 5000;
        int connectTimeout = 5000;
        int connectionRequestTimeout = 5000;
        int maxTotal = 10;
        int defaultMaxPerRoute = 10;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
        ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1L) {
                    keepAlive = 60000;
                }

                return keepAlive;
            }
        };
        httpclient = HttpClients.custom().addInterceptorLast(new HttpRequestInterceptor() {
            public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext) throws HttpException, IOException {
                paramHttpRequest.setHeader("Connection", "Keep-Alive");
                paramHttpRequest.setHeader("Keep-Alive", String.valueOf(60000));
            }
        }).setConnectionManager(cm).setKeepAliveStrategy(kaStrategy).setDefaultRequestConfig(requestConfig).build();
        CloseableHttpResponse response = null;

        CloseableHttpResponse var25;
        try {
            String respText;
            if (reqHeads != null) {
                Set<String> keys = reqHeads.keySet();
                Iterator var9 = keys.iterator();

                while(var9.hasNext()) {
                    String key = (String)var9.next();
                    respText = (String)reqHeads.get(key);
                    request.setHeader(key, respText);
                }
            }

            response = httpclient.execute(request);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = response.getStatusLine().getStatusCode();
            if (isThrowException && statusCode != 200) {
                throw new IOException("code=" + statusCode + ",reason=" + statusLine.getReasonPhrase());
            }

            HttpEntity entity = response.getEntity();
            if (respType != null) {
                if (respType.equals(String.class)) {
                    respText = EntityUtils.toString(entity, DEFAULT_ENCODE);
                    ref_content.argvalue = respText;
                } else if (respType.equals(byte[].class)) {
                    byte[] respbytes = EntityUtils.toByteArray(entity);
                    ref_content.argvalue = respbytes;
                }
            }

            if (respHeads != null) {
                Header[] headers = response.getAllHeaders();

                for(int i = 0; i < headers.length; ++i) {
                    respHeads.put(headers[i].getName(), headers[i].getValue());
                }
            }

            var25 = response;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var18) {
                    log.error("异常：", var18);
                }
            }

        }

        return var25;
    }


}
