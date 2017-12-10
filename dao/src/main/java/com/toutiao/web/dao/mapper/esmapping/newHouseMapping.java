package com.toutiao.web.dao.mapper.esmapping;

import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.*;


public class newHouseMapping {

    protected static void buildIndexMapping() throws Exception {

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        client.admin().indices().prepareCreate("beijing").execute().actionGet();
//        esClientTools.creatIndex("beijing",0,0);

        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("building")
                .startObject("properties")

                .startObject("city_id").field("type","integer").endObject()
                .startObject("city_name").field("type","text").field("analyzer","ik_max_word").field("search_analyzer","ik_max_word").endObject()
                .startObject("district_id").field("type","integer").endObject()
                .startObject("district_name").field("type","text").field("analyzer","ik_max_word").field("search_analyzer","ik_max_word").endObject()
                .startObject("area_id").field("type","integer").endObject()
                .startObject("area_name").field("type","text").field("analyzer","ik_max_word").field("search_analyzer","ik_max_word").endObject()
                .startObject("subway_line_id").field("type","integer").endObject()
                .startObject("subway_line").field("type","text").endObject()
                .startObject("subway_station_id").field("type","integer").endObject()
                .startObject("subway_station").field("type","text").endObject()
                .startObject("property_type_id").field("type","integer").endObject()
                .startObject("property_type").field("type","text").endObject()
                .startObject("elevator_flag").field("type","integer").endObject()
                .startObject("building_type_id").field("type","integer").endObject()
                .startObject("building_type").field("type","keyword").endObject()
                .startObject("sale_status_id").field("type","integer").endObject()
                .startObject("sale_status_name").field("type","text").endObject()
                .startObject("building_feature_id").field("type","integer").endObject()
                .startObject("building_feature").field("type","keyword").endObject()
                .startObject("redecorate_type_id").field("type","integer").endObject()
                .startObject("redecorate_type").field("type","keyword").endObject()
                .startObject("building_name_id").field("type","integer").endObject()
                .startObject("building_name").field("type","text").field("analyzer","ik_max_word").field("search_analyzer","ik_max_word").endObject()
                .startObject("average_price").field("type","double").endObject()
                .startObject("building_tags_id").field("type","integer").endObject()
                .startObject("building_tags").field("type","text").field("index","not_analyzed").endObject()
                .startObject("activity_desc").field("type","text").endObject()
                .startObject("building_imgs").field("type","keyword").endObject()


                .startObject("building_nickname").field("type","text").field("analyzer","ik_max_word").field("search_analyzer","ik_max_word").endObject()
                .startObject("building_address").field("type","keyword").endObject()
                .startObject("traffic_condition").field("type","keyword").endObject()
                .startObject("opened_time").field("type","date").field("format","yyyy-MM-dd").endObject()
                .startObject("deliver_time").field("type","date").field("format","yyyy-MM-dd").endObject()
                .startObject("developers").field("type","keyword").endObject()
                .startObject("sell_licence").field("type","keyword").endObject()
                .startObject("building_life").field("type","integer").endObject()
                .startObject("park_radio").field("type","keyword").endObject()
                .startObject("location").field("type", "geo_point").endObject()


                .startObject("roundstation").field("type","keyword").endObject()
                .startObject("sale_address").field("type","keyword").endObject()
                .startObject("ground_area").field("type","double").endObject()
                .startObject("purpose_area").field("type","double").endObject()
                .startObject("dimension").field("type","double").endObject()
                .startObject("virescencerate").field("type","double").endObject()
                .startObject("totaldoor").field("type","keyword").endObject()
                .startObject("park_space").field("type","integer").endObject()
                .startObject("propertymanage").field("type","keyword").endObject()
                .startObject("propertyfee").field("type","double").endObject()
                .startObject("heating_type").field("type","keyword").endObject()
                .startObject("heating_type_id").field("type","integer").endObject()
                .endObject()
                .endObject()
                .endObject();


        PutMappingRequest mappingRequest = Requests.putMappingRequest("beijing").type("building").source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();

    }

    public static void main(String[] args) throws Exception {

//       buildIndexMapping();
        save("beijing1","building1");
//        getss();
    }

    public static Map save(String index, String type) throws Exception{

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));

        Map<String,Object> json = new HashMap<>();
        json.put("city_id",12);
        json.put("city_name","北京");
        json.put("district_id",2);
        json.put("district_name","海淀");
        json.put("area_id",2);
        json.put("area_name","五道口");
        json.put("subway_line_id",new int[]{15,13});
        json.put("subway_line",new String[]{"15号线","13号线"});
        json.put("subway_station_id",new int[]{15,13});
        json.put("subway_station",new String[]{"五道口站","清华隔壁站"});
        json.put("property_type_id",2);
        json.put("property_type","高层");
        json.put("elevator_flag",1);
        json.put("building_type_id",2);
        json.put("building_type","塔楼");
        json.put("sale_status_id","1");
        json.put("sale_status_name","在售");
        json.put("building_feature_id","2");
        json.put("building_feature","特色就是好大");
        json.put("redecorate_type_id","2");
        json.put("redecorate_type","精装");
        json.put("building_name_id",121);
        json.put("building_name","五道口小区");
        json.put("average_price",22341);
        json.put("building_tags_id",new int[]{4,2,3});
        json.put("building_tags",new String[]{"别墅","花园","洋房"});
        json.put("activity_desc","优惠活动");
        json.put("building_imgs",new String[]{"11xxx.img","11eee.img"});
        json.put("building_nickname","五道口了啊");
        json.put("building_address","北京海淀五道口1号");
        json.put("traffic_condition","车多");
        json.put("opened_time","2017-12-01");
        json.put("deliver_time","2017-12-10");
        json.put("developers","s公司");
        json.put("sell_licence","许可证s文件");
        json.put("building_life",70);
        json.put("park_radio","1:1");
        json.put("location",new double[]{11.12,21.23});
        json.put("roundstation","3环到4环");
        json.put("sale_address","清华隔壁");
        json.put("ground_area",11121);
        json.put("purpose_area",333233);
        json.put("dimension",2.20);
        json.put("virescencerate",2222);
        json.put("totaldoor","123户");
        json.put("park_space",1221);
        json.put("propertymanage","s物业公司");
        json.put("propertyfee",100);
        json.put("heating_type","自采暖");
        json.put("heating_type_id",1);

        IndexResponse indexResponse =  client.prepareIndex(index,type,"1").setSource(json, XContentType.JSON).get();
        RestStatus status = indexResponse.status();
        System.out.println(status.getStatus());

        return null;

    }

    public static void getss() throws Exception{
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        //GetResponse response = client.prepareGet("newhouse", "xxxx99", "AWA28ARm1eqBE6YlBEsZ").get();
        SearchResponse searchresponse = new SearchResponse();
        MatchAllQueryBuilder booleanQueryBuilder = QueryBuilders.matchAllQuery();

//        booleanQueryBuilder.must(QueryBuilders.termsQuery("tagsId", new int[]{1110,22220,233}));
        searchresponse = client.prepareSearch("beijing").setTypes("building")
                .setQuery(booleanQueryBuilder).setFetchSource(new String[]{"area_name", "traffic_condition", "sender_mobileNo", "developers"}, null)
                .execute().actionGet();

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
