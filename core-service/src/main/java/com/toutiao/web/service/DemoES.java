package com.toutiao.web.service;

import com.github.pagehelper.PageHelper;
import com.toutiao.web.dao.entity.admin.ProjInfo;
import com.toutiao.web.dao.mapper.admin.ProjInfoMapper;
import com.toutiao.web.domain.query.HousingProjectQuery;
import jdk.internal.dynalink.beans.StaticClass;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.List;

public class DemoES {



    public static void main(String[] args) throws Exception {

//        try {
//            // 设置集群名称
//            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//            // 创建client
//            TransportClient client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
//
////                         AsyncShardFetch.List<String> jsonData = DataFactory.getInitJsonData();
//
//
//            String jsonString = "{" +
//                    "\"user\":\"kimchy1\"," +
//                    "\"postDate\":\"2017-01-30\"," +
//                    "\"message\":\"trying out1 Elasticsearch\"" +
//                    "}";
//            IndexResponse response = client.prepareIndex("flow1", "data1","11111").setSource(jsonString).get();
//            System.out.println("成功创建了一个索引，索引名为："+response.getIndex()+",类别为："+response.getType()+",文档ID为："+response.getId());
//
//
//            // 关闭client
//            client.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




    }
}
