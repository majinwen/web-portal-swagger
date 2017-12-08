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
    public void save(String index, String type, VillageEntity village) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        Client client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        JSONObject json = (JSONObject) JSONObject.toJSON(village);
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type)
                    .setSource(json);
            bulkRequest.add(lrb);
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
    }
}
