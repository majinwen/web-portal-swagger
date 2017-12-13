/*
package com.toutiao.web.dao.mapper.admin;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class houseTest {

    protected static void buildIndexMapping() throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        client.admin().indices().prepareCreate("tes001").execute().actionGet();  //创建一个空索引，如没有索引，创建mapping时会报错
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("proj_house").startObject("properties")
                .startObject("houseId").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("houseTitle").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("houseArea").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("houseCategory").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("plotId").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("houseBudget").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("tagsId").field("type","string").field("index", "not_analyzed").endObject()
                .startObject("tagsName").field("type","string").field("index", "not_analyzed").endObject()
                .startObject("houseTotalPrice").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("housePrice").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("createTime").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("trafficInfor").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("agentImage").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("phone").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("houseDesc").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("businessAreaId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("businessAreaName").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("houseRank").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("areaId").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("areaName").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("metroStationId").field("type","string").field("index", "not_analyzed").endObject()
                .startObject("metroStation").field("type","string").field("index", "not_analyzed").endObject()
                .startObject("metroSubwayLineId").field("type","string").field("index", "not_analyzed").endObject()
                .startObject("metroSubwayLine").field("type","string").field("index", "not_analyzed").endObject()
                .startObject("houseImage").field("type","string").field("index", "not_analyzed").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("tes001").type("proj_house").source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }


    public static void main(String[] args) throws Exception {
        //创建mapping
       buildIndexMapping();
      //save("tes001","proj_house");

    }

    public static Map save(String index, String type) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        Client client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        Map resultMap = new HashMap();
        //创建对象
        ProjHouseInfo house = new ProjHouseInfo();
        house.setHouseId(1);
        house.setHouseTitle("aa");
        house.setHouseArea("1000");
        house.setHouseCategory("二室二厅");
        house.setPlotId(1);
        house.setHouseBudget("980万");
        String[] aId = {"1", "2", "3"};
        house.setTagsId(aId);
        String[] name = {"a", "b", "c"};
        house.setTagsName(name);
        house.setHouseTotalPrice(910);
        house.setHousePrice("52365元/㎡");
        house.setCreateTime("2017-09-01");
        house.setTrafficInfor("离什么地方最近");
        house.setAgentImage("/sre/image");
        house.setPhone("111111111");
        house.setPhone("环境优美");
        house.setBusinessAreaId("12");
        house.setBusinessAreaName("风光商业街");
        house.setHouseRecommend("欢迎参观");
        house.setHouseRank(2);
        house.setAreaId("5");
        house.setAreaName("ssdd");
        String[] metroId = {"1", "2", "3", "4"};
        house.setMetroStationId(metroId);
        String[] metroId1 = {"1", "2", "3", "4"};
        house.setMetroStation(metroId1);
        String[] metroName = {"四惠", "海淀", "房山"};
        String[] lineId = {"1", "2", "3"};
        String[] lineName = {"a", "b", "c"};
        house.setMetroSubwayLineId(lineId);
        house.setMetroSubwayLine(lineName);
        String[] image = {"/sdd", "/sss", "/sddf"};
        house.setHouseImage(image);
        JSONObject json = (JSONObject) JSONObject.toJSON(house);
//            没有指定idName 那就让Elasticsearch自动生成
        if (StringUtils.isBlank("")) {
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type)
                    .setSource(json);
            bulkRequest.add(lrb);
        } else {
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type, "003")
                    .setSource(json);
            bulkRequest.add(lrb);
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        return null;
    }
}
*/
