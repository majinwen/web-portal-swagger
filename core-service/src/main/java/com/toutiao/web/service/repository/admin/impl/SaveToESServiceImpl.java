package com.toutiao.web.service.repository.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.service.repository.admin.SaveToESService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.VersionType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Service
public class SaveToESServiceImpl implements SaveToESService {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${plot.index}")
    private String index;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;

    @Override
    public void saveParent(VillageEntityES village) {
        TransportClient client = esClientTools.init();
        VillageEntity villageEntity = new VillageEntity();
//        String jsonStr  = JSONObject.toJSONString(village);
//        JSONObject json = JSONObject.parseObject(jsonStr);
        BeanUtils.copyProperties(village,villageEntity);
        JSONObject json = (JSONObject) JSONObject.toJSON(villageEntity);
        String id = String.valueOf(village.getId());
        IndexRequest indexRequest = new IndexRequest(index, parentType,id)
                .version(village.getVersion())
                .versionType(VersionType.EXTERNAL.versionTypeForReplicationAndRecovery())
                .source(json);
//        UpdateRequest updateRequest = new UpdateRequest(index, parentType, id)
//                .doc(json)
//                .upsert(indexRequest);
        client.index(indexRequest).actionGet();
    }

    @Override
    public void saveChild(ProjHouseInfo projHouseInfo) {
        TransportClient client = esClientTools.init();
        VillageEntity village = new VillageEntity();
        village.setId(projHouseInfo.getHouseId());
        village.setAreaSize(projHouseInfo.getHouseArea());
        String jsonStr  = JSONObject.toJSONString(village);
        JSONObject json = JSONObject.parseObject(jsonStr);
//        BulkRequestBuilder bulkRequest = client.prepareBulk();
        String id = String.valueOf(village.getId());
        IndexRequest indexRequest = new IndexRequest(index, childType,id)
                .parent(String.valueOf(projHouseInfo.getHousePlotId()))
                .version(7)
                .versionType(VersionType.EXTERNAL.versionTypeForReplicationAndRecovery())
                .source(json);
//        UpdateRequest updateRequest = new UpdateRequest(index, childType, id)
//                .parent(String.valueOf(projHouseInfo.getHousePlotId()))
//                .doc(json)
//                .upsert(indexRequest);
//        bulkRequest.add(indexRequest);
//        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//        return bulkResponse.hasFailures();
        client.index(indexRequest).actionGet();
    }
}
