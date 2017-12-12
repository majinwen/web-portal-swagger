package com.toutiao.web.service.repository.admin.impl;


import com.toutiao.web.service.repository.admin.SysBuildIndexMappingService;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
@Service
public class SysBuildIndexMappingServiceImpl implements SysBuildIndexMappingService {

    @Override
    public void buildIndexMapping(String index,String type) throws Exception {

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        //创建一个空索引，如没有索引，创建mapping时会报错
        client.admin().indices().prepareCreate(index).execute().actionGet();
        //创建mapping
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject(type).startObject("properties")
                .startObject("id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("rc").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("alias").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("photo").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("areaId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("area").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("areaNameId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("areaName").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("tradingAreaId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("tradingArea").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("address").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("location").field("type", "geo_point").field("index", "not_analyzed").endObject()
                .startObject("metroStationId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("metroStation").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("subwayLineId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("subwayLine").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("TrafficInformation").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("labelId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("label").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("saleHouse").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("avgPrice").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("sumPrice").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("abbreviatedAge").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("age").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("areaSize").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("sumBuilding").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("sumHousehold").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("avgGreeningRate").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("avgElevator").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("elevator").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("carPositionRatio").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("parkingRate").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("desc").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("characteristic").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("developers").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("property").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("propertyFee").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("propertyType").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("yopr").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("architectureTypeId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("architectureType").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("buildingStructure").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("too").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("residentialType").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("heatingMode").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("sdr").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("level").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("villageCharacteristics").field("type", "string").field("index", "not_analyzed").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest(index).type(type).source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }
}