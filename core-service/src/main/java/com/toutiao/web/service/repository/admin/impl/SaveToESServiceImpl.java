package com.toutiao.web.service.repository.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.service.repository.admin.SaveToESService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
@Service
public class SaveToESServiceImpl implements SaveToESService {
    @Override
    public Map save(String index, String type, VillageEntity village) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        Client client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        Map resultMap = new HashMap();
        JSONObject json = (JSONObject) JSONObject.toJSON(village);

//        IndexRequestBuilder irb = client.prepareIndex(index,
//                type, "1");
//        XContentBuilder xb = XContentFactory.jsonBuilder()
//                .startObject();
//        xb.latlon("location", 0, 0);
//        for (Object object : listData) {
//            JSONObject json = JSONObject.fromObject(object);
//            没有指定idName 那就让Elasticsearch自动生成
        if(StringUtils.isBlank("")){
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type)
                    .setSource(json);
            bulkRequest.add(lrb);
        }
        else{
//                String idValue = json.optString(idName);
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type,"003")
                    .setSource(json);
            bulkRequest.add(lrb);
        }
//        IndexRequestBuilder lrb = client
//                        .prepareIndex(index, type)
//                        .setSource(json);
//        bulkRequest.add(lrb);
////
//        }
//        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//        if (bulkResponse.hasFailures()) {
//            // process failures by iterating through each bulk response item
//            System.out.println(bulkResponse.getItems().toString());
//            resultMap.put("500", "保存ES失败!");
//            return resultMap;
//        }
//        bulkRequest = ESTools.client.prepareBulk();
//        resultMap.put("200", "保存ES成功!");
//        xb.endObject();
//        irb.setSource(xb);
//        BulkResponse bulkResponse = brb.execute().actionGet();
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        return null;
    }
}
