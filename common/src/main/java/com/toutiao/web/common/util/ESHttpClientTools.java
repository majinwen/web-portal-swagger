package com.toutiao.web.common.util;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ESHttpClientTools
 * @Author jiangweilong
 * @Date 2018/11/17 11:16 PM
 * @Description: elasticsearch http client setting
 **/

@Configuration
public class ESHttpClientTools implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ESHttpClientTools.class);

    //ES地址
    @Value("${elasticsearch.host}")
    private String host;
    //ES端口
    @Value("${elasticsearch.port}")
    private int port;
    //ES用户名
    @Value("${elasticsearch.username}")
    private String username;
    //ES密码
    @Value("${elasticsearch.password}")
    private String password;
    //ES链接协议
    @Value("${elasticsearch.schema}")
    private String schema;

    private static final int CONNECT_TIME_OUT = 5000;
    private static final int SOCKET_TIME_OUT = 40000;
    private static final int CONNECTION_REQUEST_TIME_OUT = 1000;

    private static final int MAX_CONNECT_NUM = 100;
    private static final int MAX_CONNECT_PER_ROUTE = 100;

    //使用高版本client必须依赖低版本的client
    private RestClient client;
    //高版本client
    private RestHighLevelClient restHighLevelClient;
    //ES节点嗅探器，暂时不启用
    //private Sniffer sniffer;


    @Override
    public void destroy() throws Exception {

        try {
            LOGGER.info("关闭ElasticSearch链接器");
            if (restHighLevelClient != null) {
//                LOGGER.info("Closing elasticSearch sniffer");
//                sniffer.close();
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            LOGGER.error("关闭ElasticSearch链接器异常: ", e);
        }
    }

    @Override
    public RestHighLevelClient getObject() throws Exception {
        return restHighLevelClient;
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }


    //初始化client
    protected void buildClient() {
        //验证，咱不启用
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClientBuilder builder =  RestClient.builder(new HttpHost(host, port, schema));

        Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        builder.setDefaultHeaders(defaultHeaders);


        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(CONNECT_TIME_OUT);
                requestConfigBuilder.setSocketTimeout(SOCKET_TIME_OUT);
                requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
                return requestConfigBuilder;
            }
        }).setMaxRetryTimeoutMillis(5*60*1000);

        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
                httpAsyncClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE);
                return httpAsyncClientBuilder;
            }
        });


        client = builder.build();
        //自动查找es节点，暂时不启用
        //sniffer = Sniffer.builder(client).setSniffIntervalMillis(60).build();
        restHighLevelClient = new RestHighLevelClient(builder);
    }
}
