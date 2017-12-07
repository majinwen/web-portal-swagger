package com.toutiao.web.common.util;

import lombok.Data;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Data
public class ESClientTools {

    private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ESClientTools.class);

    @Value("${es.cluster.name}")
    private String esClusterName;//集群名
    @Value("${es.server.ips}")
    private String esServerIps;//集群服务IP集合

    private TransportClient client = null;
    private volatile boolean inited = false;

    public TransportClient get() {
        return this.client;
    }

    public synchronized void close() {
        if (this.client != null) {
            this.client.close();
        }
    }

    public synchronized void init() {
        if (!inited) {
            try {

                Settings settings = Settings.builder().put("cluster.name", esClusterName).build();
                TransportClient client = new PreBuiltTransportClient(settings);
                String[] addresses = esServerIps.split(",");
                for (String address : addresses) {
                    String[] hostAndPort = address.split(":");
                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostAndPort[0]), Integer.valueOf(hostAndPort[1])));
                }
                this.client = client;
                this.inited = true;
            } catch (UnknownHostException e) {
                LOGGER.error(String.format("init search client err:=>msg:[%s]", e.getMessage()), e);
                if (client != null) {
                    this.client.close();
                }
            }
        }
    }
}
