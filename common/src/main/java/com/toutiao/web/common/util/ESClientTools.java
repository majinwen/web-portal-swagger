package com.toutiao.web.common.util;

import lombok.Data;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Data
public class ESClientTools {


    public ESClientTools(@Value("${es.cluster.name}")String esClusterName,@Value("${es.xpack.user}")String esXpackUser){
        settings = Settings.builder().put("cluster.name", esClusterName)
                .build();
    }

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ESClientTools.class);

    @Value("${es.server.ips}")
    private String esServerIps;//集群服务IP集合
    @Value("${es.server.port}")
    private String esServerPort;


    private volatile TransportClient client;


    Settings settings;

    public TransportClient init(){


        if(client == null){
            if (client == null){
                try {
                    client = new PreBuiltXPackTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName(esServerIps), Integer.valueOf(esServerPort)));

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    if (client != null) {
                        client.close();
                    }
                }
            }
        }
        return client;
    }


}
