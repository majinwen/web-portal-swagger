package com.toutiao.web.common.util;

import lombok.Data;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
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

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ESClientTools.class);

    @Value("${es.cluster.name}")
    private String esClusterName = "elasticsearch";//集群名
    @Value("${es.server.ips}")
    private String esServerIps = "47.104.96.88:9300";//集群服务IP集合

    private volatile TransportClient client;

//    private TransportClient client = null;
//    private volatile boolean inited = false;
//
//    public TransportClient get() {
//        return this.client;
//    }
//
//    public synchronized void close() {
//        if (this.client != null) {
//            this.client.close();
//        }
//    }
//
//    public synchronized void init() {
//        if (!inited) {
//            try {
//
//                Settings settings = Settings.builder().put("cluster.name", esClusterName).build();
//                TransportClient client = new PreBuiltTransportClient(settings);
//                String[] addresses = esServerIps.split(",");
//                for (String address : addresses) {
//                    String[] hostAndPort = address.split(":");
//                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostAndPort[0]), Integer.valueOf(hostAndPort[1])));
//                }
//                this.client = client;
//                this.inited = true;
//            } catch (UnknownHostException e) {
//                LOGGER.error(String.format("init search client err:=>msg:[%s]", e.getMessage()), e);
//                if (client != null) {
//                    this.client.close();
//                }
//            }
//        }
//    }

    Settings settings = Settings.builder().put("cluster.name", esClusterName).build();

    public TransportClient init(){

        if(client == null){
            synchronized (TransportClient.class){}
            if (client == null){
                try {
                    String[] addresses = esServerIps.split(",");
                    for (String address : addresses) {
                        String[] hostAndPort = address.split(":");
                            client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostAndPort[0]), Integer.valueOf(hostAndPort[1])));
                    }
                } catch (UnknownHostException e) {
                    if (client != null) {
                        client.close();
                    }
                }
            }
        }
        return client;
    }

    /**
     * 创建索引
     * @param indexName
     * @param shards
     * @param replicas
     * @return
     */
    public boolean creatIndex(String indexName, int shards, int replicas){

//        Settings settings = Settings.builder()
//                .put("index.number_of_shards",shards)
//                .put("index.number_of_replicas",replicas).build();

        CreateIndexResponse createIndexResponse = init().admin().indices()
                .prepareCreate(indexName.toLowerCase())
                .execute().actionGet();
        boolean isCreated = createIndexResponse.isAcknowledged();
        if(isCreated){
            System.out.println("创建"+indexName+"成功");
        }else {
            System.out.println("创建"+indexName+"失败");
        }
        return isCreated;

    }

//    public static void main(String[] args) {
//        TransportClient ss1 = init();
//        TransportClient ss2 = init();
//        TransportClient ss3 = init();
//        System.out.println(ss1);
//        System.out.println(ss2);
//        System.out.println(ss3);
//    }
}
