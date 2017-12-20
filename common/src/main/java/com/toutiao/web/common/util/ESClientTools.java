package com.toutiao.web.common.util;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
@Data
public class ESClientTools {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ESClientTools.class);

    @Value("${es.cluster.name}")
    private String esClusterName = "elasticsearch";//集群名
    @Value("${es.server.ips}")
    private String esServerIps = "47.104.96.88:9300";//集群服务IP集合

    private volatile TransportClient client;

    Settings settings = Settings.builder().put("cluster.name", esClusterName).build();

    public TransportClient init(){

        if(client == null){
//            synchronized (TransportClient.class){}
            if (client == null){
                try {
                    String[] addresses = esServerIps.split(",");
                    for (String address : addresses) {
                        String[] hostAndPort = address.split(":");
                        client =  new PreBuiltTransportClient(settings)
                                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostAndPort[0]), Integer.valueOf(hostAndPort[1])));
                    }
                } catch (Exception e) {
                    if (client != null) {
                        try {
                            client.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        return client;
    }

}
