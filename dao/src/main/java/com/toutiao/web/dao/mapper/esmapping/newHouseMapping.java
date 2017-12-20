package com.toutiao.web.dao.mapper.esmapping;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.entity.esobject.NewHouseBuildings;
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
        save("newhouse","building");
//        getss();
    }

    public static Map save(String index, String type) throws Exception{

        NewHouseBuildings newHouseBuildings = new NewHouseBuildings();
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));

        Map<String,Object> json = new HashMap<>();
        json.put("city_id",newHouseBuildings.getCityId());
        json.put("city_name",newHouseBuildings.getCityName());
        json.put("district_id",newHouseBuildings.getDistrictId());
        json.put("district_name",newHouseBuildings.getDistrictName());
        json.put("area_id",newHouseBuildings.getAreaId());
        json.put("area_name",newHouseBuildings.getAreaName());
        json.put("subway_line_id",newHouseBuildings.getSubwayLineId());
        json.put("subway_line",newHouseBuildings.getSubwayLine());
        json.put("subway_station_id",newHouseBuildings.getSubwayStationId());
        json.put("subway_station",newHouseBuildings.getSubwayStationId());
        json.put("property_type_id",newHouseBuildings.getPropertyTypeId());
        json.put("property_type",newHouseBuildings.getPropertyType());
        json.put("elevator_flag",newHouseBuildings.getElevatorFlag());
        json.put("building_type_id",newHouseBuildings.getBuildingTypeId());
        json.put("building_type",newHouseBuildings.getBuildingTypeId());
        json.put("sale_status_id",newHouseBuildings.getSaleStatusId());
        json.put("sale_status_name",newHouseBuildings.getSaleStatusName());
        json.put("building_feature_id",newHouseBuildings.getBuildingFeatureId());
        json.put("building_feature",newHouseBuildings.getBuildingFeature());
        json.put("redecorate_type_id",newHouseBuildings.getRedecorateTypeId());
        json.put("redecorate_type",newHouseBuildings.getRedecorateType());
        json.put("building_name_id",newHouseBuildings.getBuildingNameId());
        json.put("building_name",newHouseBuildings.getBuildingName());
        json.put("average_price",newHouseBuildings.getAveragePrice());
        json.put("building_tags_id",newHouseBuildings.getBuildingTagsId());
        json.put("building_tags",newHouseBuildings.getBuildingTags());
        json.put("activity_desc",newHouseBuildings.getActivityDesc());
        json.put("building_imgs",newHouseBuildings.getBuildingImgs());
        json.put("building_nickname",newHouseBuildings.getBuildingNickname());
        json.put("building_address",newHouseBuildings.getBuildingAddress());
        json.put("traffic_condition",newHouseBuildings.getTrafficCondition());
        json.put("opened_time",newHouseBuildings.getOpenedTime());
        json.put("deliver_time",newHouseBuildings.getDeliverTime());
        json.put("developers",newHouseBuildings.getDevelopers());
        json.put("sell_licence",newHouseBuildings.getSellLicence());
        json.put("building_life",newHouseBuildings.getBuildingLife());
        json.put("park_radio",newHouseBuildings.getParkRadio());
        json.put("location",newHouseBuildings.getLocation());
        json.put("roundstation",newHouseBuildings.getRoundstation());
        json.put("sale_address",newHouseBuildings.getSaleAddress());
        json.put("ground_area",newHouseBuildings.getGroundArea());
        json.put("purpose_area",newHouseBuildings.getPurposeArea());
        json.put("dimension",newHouseBuildings.getDimension());
        json.put("virescencerate",newHouseBuildings.getVirescencerate());
        json.put("totaldoor",newHouseBuildings.getTotaldoor());
        json.put("park_space",newHouseBuildings.getParkSpace());
        json.put("propertymanage",newHouseBuildings.getPropertymanage());
        json.put("propertyfee",newHouseBuildings.getPropertyfee());
        json.put("heating_type",newHouseBuildings.getHeatingType());
        json.put("heating_type_id",newHouseBuildings.getHeatingTypeId());


        json.put("house_min_area",newHouseBuildings.getHouseMinArea());
        json.put("house_max_area",newHouseBuildings.getHouseMaxArea());
        json.put("nearbysubway",newHouseBuildings.getNearbysubway());
        json.put("building_level",newHouseBuildings.getBuildingLevel());

        IndexResponse indexResponse =  client.prepareIndex(index,type,"1").setSource(json, XContentType.JSON).get();
        RestStatus status = indexResponse.status();

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
        }


    }


}
