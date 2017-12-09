package com.toutiao.web.dao.mapper.esmapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.entity.admin.SysUserEntity;
import com.toutiao.web.dao.entity.esobject.NewHouse;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class newHouseMapping {

    @Autowired
    private ESClientTools esClientTools;


    protected static void buildIndexMapping() throws Exception {

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        client.admin().indices().prepareCreate("newhouse").execute().actionGet();


        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("xxxx99")
                .startObject("properties")
                .startObject("districtId").field("type","text").field("index","not_analyzed").endObject()
                .startObject("districtName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("areaId").field("type","text").field("index","not_analyzed").endObject()
                .startObject("areaName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("subwayLine").field("type","integer").field("index","not_analyzed").endObject()
                .startObject("subwayStation").field("type","text").field("index","not_analyzed").endObject()
                .startObject("totalPrice").field("type","text").field("index","not_analyzed").endObject()
                .startObject("layoutId").field("type","text").field("index","not_analyzed").endObject()
                .startObject("layoutType").field("type","text").field("index","not_analyzed").endObject()
                .startObject("areaSize").field("type","text").field("index","not_analyzed").endObject()
                .startObject("tagsId").field("type","integer").field("index","not_analyzed").endObject()
                .startObject("tagsName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("buildFormId").field("type","text").field("index","not_analyzed").endObject()
                .startObject("buildingAge").field("type","text").field("index","not_analyzed").endObject()
                .startObject("purposeId").field("type","text").field("index","not_analyzed").endObject()
                .startObject("purposeName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("isLift").field("type","integer").field("index","not_analyzed").endObject()
                .startObject("ownershipId").field("type","text").field("index","not_analyzed").endObject()
                .startObject("projName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("nickName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("averagePrice").field("type","text").field("index","not_analyzed").endObject()
                .startObject("imgs").field("type","text").field("index","not_analyzed").endObject()
                .startObject("saledate").field("type","text").field("index","not_analyzed").endObject()
                .startObject("livindate").field("type","text").field("index","not_analyzed").endObject()
                .startObject("developer").field("type","text").field("index","not_analyzed").endObject()
                .startObject("developer").field("type","text").field("index","not_analyzed").endObject()
                .startObject("categoryName").field("type","text").field("index","not_analyzed").endObject()
                .startObject("rightYear").field("type","text").field("index","not_analyzed").endObject()
                .startObject("layoutList").field("type","object").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("newhouse").type("xxxx99").source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();

    }

    public static void main(String[] args) throws Exception {

//        buildIndexMapping();
//        save("newhouse","xxxx99");
        getss();
    }

    public static Map save(String index, String type) throws Exception{

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));


        NewHouse newHouse = new NewHouse();
        newHouse.setAreaId("1");
        newHouse.setAreaName("朝阳");
        newHouse.setAreaSize("500");
        newHouse.setAveragePrice("1000w");
        newHouse.setBuildCategory("超大");
        newHouse.setBuildingAge("70");
        newHouse.setBuildFormId("bf1");
        newHouse.setCategoryName("111");
        newHouse.setDeveloper("就是我");
        newHouse.setDistrictId("1");
        newHouse.setDistrictName("朝阳呢");
//        for(int i=0;i<2;i++){
//            newHouse.setImgs("qweqwe"+i);
//        }
        newHouse.setImgs(new String[]{"111","2222"});
        newHouse.setIsLift(1);
        newHouse.setLayoutId("111");
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(11);
        sysUserEntity.setPhone("177");
        List<SysUserEntity> ss = new ArrayList<>();
        ss.add(sysUserEntity);
        newHouse.setLayoutList(ss);
        newHouse.setLayoutType("lt1");
        newHouse.setLayoutId("111");
        newHouse.setLivindate("2017-12-12");
        newHouse.setNickName("就喜欢你");
        newHouse.setProjName("真大");
        newHouse.setOwnershipId("南");
        newHouse.setPurposeName("南1");
        newHouse.setRightYear("2018");
//        for(int i=0;i<2;i++){
//            NewHouse newHouse1 = new NewHouse();
//            newHouse.setTagsId(i);
//        }
        newHouse.setTagsId(new Integer[]{111,2222});
        newHouse.setTagsName(new String[]{"111","2222"});



        JSONObject aaa = (JSONObject) JSON.toJSON(newHouse);
        List list = new ArrayList();
        list.add(aaa);

        BulkRequestBuilder bulkRequest = client.prepareBulk();
        Map resultMap = new HashMap();


        for (Object object : list) {
            JSONObject json = JSONObject.parseObject(object.toString());
            //没有指定idName 那就让Elasticsearch自动生成

            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type)
                    .setSource(json);
            bulkRequest.add(lrb);

        }

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
            System.out.println(bulkResponse.getItems().toString());
            resultMap.put("500", "保存ES失败!");
            return resultMap;
        }
        bulkRequest = client.prepareBulk();
        resultMap.put("200", "保存ES成功!");
        System.out.print(JSON.toJSONString(resultMap));
        return null;

    }

    public static void getss() throws Exception{
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        //GetResponse response = client.prepareGet("newhouse", "xxxx99", "AWA28ARm1eqBE6YlBEsZ").get();
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.matchQuery("layoutType","lt1"));
//        booleanQueryBuilder.must(QueryBuilders.termsQuery("tagsId", new int[]{1110,22220,233}));
        searchresponse = client.prepareSearch("newhouse").setTypes("xxxx99").setQuery(booleanQueryBuilder).execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        long totalCount1 = hits.getTotalHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
//            String name = (String) hit.getSource().get("address");
//
//            Integer id = (Integer) hit.getSource().get("newcode");
//                String name = (String) JSON.toJSON(hit.getSource());
            System.out.println(hit.getSource());
        }


    }


}
