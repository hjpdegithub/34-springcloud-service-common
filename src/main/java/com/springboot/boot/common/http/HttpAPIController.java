package com.springboot.boot.common.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpAPIController {

    private static CloseableHttpClient httpClient;

    /**
     * 信任SSL证书
     */
    static {
        try {
            SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL)
                    .loadTrustMaterial((x, y) -> true).build();
            /**
             * 一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
             * 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
             */
            RequestConfig config = RequestConfig.custom().setConnectTimeout(500000).setSocketTimeout(500000).build();
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLContext(sslContext)
                    .setSSLHostnameVerifier((x, y) -> true).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private RequestConfig config;

    /**
     * @description GET---不含参
     * @param url
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:02
     */
    /*public HttpResultDTO doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 装载配置信息
        httpGet.setConfig(config);
        // 允许重定向
        httpGet.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        // 返回响应代码与内容
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }*/

    /**
     * @description GET---含请求头
     * @param url
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:04
     */
   /* public HttpResultDTO doGetWithHeaders(String url, Map<String,Object> headers) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 装载配置信息
        httpGet.setConfig(config);
        // 设置请求头
        if (headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key).toString();
                httpGet.addHeader(key,value);
            }
        }
        // 允许重定向
        httpGet.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        // 返回响应代码与内容
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }*/

    /**
     * @description GET---含Map请求参数
     * @param url
     * @param mapParams
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:06
     */
    /*public HttpResultDTO doGetWithParams(String url, Map<String, Object> mapParams) throws Exception {
        // 包装URL
        URIBuilder uriBuilder = new URIBuilder(url);
        if (mapParams != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
    }*/

    /**
     * @description GET---含Map请求参数且含请求头
     * @param url
     * @param mapParams
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:08
     */
    public HttpResultDTO doGetWithParamsAndHeaders(String url, Map<String, Object> mapParams , Map<String,Object> headers) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 允许重定向
        httpGet.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS,true);

        // 包装请求行
        if (mapParams != null) {
            // 包装URL
            URIBuilder uriBuilder = new URIBuilder(url);
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
            // 新的请求行
            url = uriBuilder.build().toString();
        }

        // 设置请求头
        if (headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key).toString();
                httpGet.addHeader(key,value);
            }
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 返回响应代码与内容
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }



    /**
     * @description POST--不含参
     * @param url
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:14
     */
    /*public HttpResultDTO doPost(String url) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 加入配置信息
        httpPost.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        // 返回响应代码与内容
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }*/

    /**
     * @description POST---含参
     * @param url
     * @param params
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:16
     */
    /*public HttpResultDTO doPostWithParams(String url, Map<String, Object> params) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (params != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        // 返回响应代码与内容
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }*/

    /**
     * @description POST---含请求头
     * @param url
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:17
     */
    /*public HttpResultDTO doPostWithHeaders(String url, Map<String, Object> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        // 设置请求头
        if (headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key).toString();
                httpPost.addHeader(key,value);
            }
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        // 返回响应代码与内容
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }*/


    /**
     * @description POST---含参(请求路径、Map参数、请求头)
     * @param url
     * @param mapParams
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:22
     */
    public HttpResultDTO doPostWithParamsAndHeaders(String url, Map<String, Object> mapParams , Map<String,Object> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (mapParams != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 设置请求头
        if (headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key).toString();
                httpPost.addHeader(key,value);
            }
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }



    /**
     * @description POST---含参(请求路径、JSON参数串、请求头)
     * @param url
     * @param jsonParam
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:20
     */
    public static HttpResultDTO doPostWithJsonParamAndHeaders(String url, String jsonParam, Map<String,Object> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        if (headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key).toString();
                httpPost.addHeader(key, value);
            }
        }

        // 设置以Json数据方式发送
        StringEntity stringEntity = new StringEntity(jsonParam, "utf-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);

        // 发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * @description PUT---含参(请求路径、JSON参数串、请求头)
     * @param url
     * @param jsonParam
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:32
     */
    public static HttpResultDTO doPutWithJsonAndHeaders(String url,String jsonParam,Map<String,String> headers) throws Exception{
        // 声明httpPut请求
        HttpPut httpPut = new HttpPut(url);

        //设置header
        httpPut.setHeader("Content-type", "application/json");
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(),entry.getValue());
            }
        }

        //组织请求参数
        if (StringUtils.isNotEmpty(jsonParam)) {
            StringEntity stringEntity = new StringEntity(jsonParam, "utf-8");
            httpPut.setEntity(stringEntity);
        }

        // 发起请求
        CloseableHttpResponse response = httpClient.execute(httpPut);

        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * @description DELETE---含参(请求路径、请求头)
     * @param url
     * @param headers
     * @return com.mark.dc2.DTO.HttpResultDTO
     * @author Mario
     * @date 2019/7/23 9:37
     */
    public static HttpResultDTO doDeleteWithHeaders(String url,Map<String,String> headers) throws Exception{
        // 声明httpDelete请求
        HttpDelete httpDelete = new HttpDelete(url);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDelete.setHeader(entry.getKey(),entry.getValue());
            }
        }
        // 发起请求
        CloseableHttpResponse response = httpClient.execute(httpDelete);

        return new HttpResultDTO(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));

    }
}