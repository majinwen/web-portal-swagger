package com.toutiao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * zhangjinglei 2017/9/1 下午2:33
 */
@SpringBootApplication
public class WebApplicationServer {

    public static void main(String[] args) throws Exception {

        System.out.print(1);
        SpringApplication.run(WebApplicationServer.class, args);
    }
       /* try {
                         // 设置集群名称
                        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
                        // 创建client
                         TransportClient client = new PreBuiltTransportClient(settings)
                                 .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.0.112"), 9300));

//                         AsyncShardFetch.List<String> jsonData = DataFactory.getInitJsonData();

            String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
                               IndexResponse response = client.prepareIndex("flow", "data").setSource(jsonString).get();
                              System.out.println("成功创建了一个索引，索引名为："+response.getIndex()+",类别为："+response.getType()+",文档ID为："+response.getId());


                       // 关闭client
                       client.close();

                   } catch (Exception e) {
                        e.printStackTrace();
                    }
        }
*/





//        Settings settings = Settings.builder()
//                .put("cluster.name", "myClusterName")
//                .build();
//
//        TransportClient client = null;
//        try {
//            client = new PreBuiltTransportClient(settings);
//            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//
//        ImmutableList<DiscoveryNode> connectedNodes = (ImmutableList<DiscoveryNode>) client.connectedNodes();
//
//        for (DiscoveryNode node : connectedNodes) {
//            System.out.println(node.getHostAddress());
//        }
//        SpringApplication.run(WebApplicationServer.class, args);


//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("47.104.96.88", 9200, "http")));
//
//
//        IndexRequest request = new IndexRequest(
//                "posts",
//                "doc",
//                "1");
//        String jsonString = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//        request.source(jsonString, XContentType.JSON);
//        IndexResponse indexResponse = client.index(request);
//
//        String index = indexResponse.getIndex();
//        String type = indexResponse.getType();
//        String id = indexResponse.getId();
//        long version = indexResponse.getVersion();
//
//        System.out.print("index:"+index+" type:"+type+" id:"+id+" version:"+version);
//
//        client.close();
//        SpringApplication.run(WebApplicationServer.class, args);

}