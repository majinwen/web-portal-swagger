package com.toutiao.web.common.httpUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :boot-demo
 * @Author : kewei@nash.work
 * @Date : 2017-09-27 下午9:14 星期三
 * @Version : v1.0
 **/
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private final static int SOCK_TIMEOUT = 5000;
    private final static int CONN_TIMEOUT = 10000;
    private static final int HTTP_SUCCESS = 200;
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String LOG_PREFIX = "[HttpUtils]";


    public static String get(String url) {
        return get(url, CHARSET_UTF8);
    }

    public static String get(String url, String encoding) {
        HttpGet method = new HttpGet(url);
        return executeMethod(method,encoding);
    }

    public static String get(String url, Map<String, ?> paramsMap) {
        return get(url,paramsMap,CHARSET_UTF8);
    }

    public static String get(String url, Map<String, ?> paramsMap,String encoding) {
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            StringBuilder sb = new StringBuilder(keySet.size() * 8);
            sb.append("?");
            for (String key : keySet) {
                try {
                    String value = URLEncoder.encode(String.valueOf(paramsMap.get(key)), encoding);
                    sb.append(key).append("=").append(value).append("&");
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            String params = sb.toString();
            url += params.substring(0, params.length() - 1);
        }
        HttpGet method = new HttpGet(url);
        return executeMethod(method,encoding);
    }

    public static String post(String url, Map<String, ?> paramsMap) {
        return post(url,paramsMap,CHARSET_UTF8);
    }

    public static String post(String url, Map<String, ?> paramsMap,String encoding) {
        HttpPost method = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>(paramsMap.size());
        Set<String> keySet = paramsMap.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }
        try {
            method.setEntity(new UrlEncodedFormEntity(nvps, encoding));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return executeMethod(method,encoding);
    }

    public static String postXml(String url, String body) {
        return postXml(url, body, CHARSET_UTF8);
    }

    public static String postXml(String url, String body,String encoding) {
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", encoding)));
        return executeMethod(method,encoding);
    }

    public static String postJson(String url, String body) {
        return postJson(url, body, CHARSET_UTF8);
    }

    public static String postJson(String url,String body,String encoding) {
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, ContentType.create("application/json", encoding)));
        return executeMethod(method,encoding);
    }


    private static final String executeMethod(HttpRequestBase method, String encoding) {
        return executeMethod(method,encoding,CONN_TIMEOUT,SOCK_TIMEOUT);
    }

    /**
     * 执行http post请求
     * @param url 请求url
     * @param body 请求参数
     * @param encoding 编码方式
     * @param connectTimeout 连接超时时间 单位为毫秒,必传
     * @param socketTimeout 响应超时时间 单位为毫秒,必传
     * @return 响应结果
     */
    public static String postXml(String url, String body,String encoding,int connectTimeout,int socketTimeout) {
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", encoding)));
        return executeMethod(method,encoding,connectTimeout,socketTimeout);
    }

    /**
     * 执行http请求
     * @param method
     * @param connectTimeout 连接超时时间 单位为毫秒
     * @param socketTimeout  响应超时时间 单位为毫秒
     * @param encoding 编码方式
     * @return 响应结果
     */
    private static final String executeMethod(HttpRequestBase method,String encoding,int connectTimeout,int socketTimeout) {
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig
                config =
                RequestConfig.custom().setSocketTimeout(socketTimeout)
                        .setConnectTimeout(connectTimeout).build();
        method.setConfig(config);
        try {
            HttpResponse response = client.execute(method);
            if (HTTP_SUCCESS == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), encoding);
            } else {
                logger.error("{}executeMethod返回失败,request={}, response={}",
                        LOG_PREFIX, method,EntityUtils.toString(response.getEntity(), encoding));
            }
        } catch (Exception e) {
            logger.warn("{}executeMethod异常,request={}, errorMsg={}",LOG_PREFIX, method,e.getMessage());
        } finally {
            method.releaseConnection();
            try {
                client.close();
            } catch (IOException e) {
                logger.error("{}连接关闭异常,errorMsg={}", LOG_PREFIX,e.getMessage());
            }
        }
        return null;
    }
}
