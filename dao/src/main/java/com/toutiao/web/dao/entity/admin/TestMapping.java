package com.toutiao.web.dao.entity.admin;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import org.apache.commons.lang.math.RandomUtils;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestMapping {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        //testIk(client, "house123", "house1234");
        //buildRobotMapping(client);
        saveRobot("robot_index", "robot_type", client);
        //queryByHouseId( "a", "b",1, client);
        //buildIndexMapping(client);
        //buildIndexMappingIk(client);
        //save("house123","house1234",client);
        //queryList("a", "b", client, null);
        //System.out.println("=============================");
        //queryRang("projhouseinfo", "projhouse", client, null);
        //query2("projhouseinfo", "projhouse", client, "房山v");
        /*Map<String, Object> map = queryByHouseId("a", "b", 12, client);
        Set<String> set = map.keySet();
		for (int i=0;i<set.size();i++){

			System.out.println(map.get(i));

		}


		System.out.println(map.get("data_house"));
		System.out.println(map.size());*/
    }

    protected static void buildIndexMapping(TransportClient client) throws Exception {
        /**
         .startObject("product_name").field("type", "string")
         .field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
         */
        client.admin().indices().prepareCreate("a").execute().actionGet();
        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject().startObject("b")
                .startObject("houseId").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("houseTitle").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("houseArea").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseType").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseTypeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOrientation").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOrientationId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseTotalPrices").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseUnitCost").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLabel").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLabelId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseFloorId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseFloor").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseYear").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePurposeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePurpose").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLiftId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseLift").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseHeatingId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseHeating").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOwnershipId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseOwnership").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseRecommendInfo").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePhoto").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePlotPhoto").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBudget").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseTrafficInfo").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseUpdateTime").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseManagementTypeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseManagementType").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBuildingTypeId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBuildingType").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseRank").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseProxyPhoto").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseProxyName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseProxyPhone").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseRecommend").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePlotId").field("type", "integer")
                .field("index", "not_analyzed").endObject().startObject("housePlotLocation").field("type", "geo_point")
                .field("index", "not_analyzed").endObject().startObject("housePlotName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("housePlotInfo").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBusinessNameId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseBusinessName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("areaId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("areaName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayLineId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayLineName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayStationId").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("subwayStationName").field("type", "string")
                .field("index", "not_analyzed").endObject().startObject("houseToSubwayDistance").field("type", "object")
                .endObject().endObject().endObject().endObject();

        PutMappingRequest mappingRequest = Requests.putMappingRequest("a").type("b")
                .source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    protected static void buildIndexMappingIk(TransportClient client) throws Exception {
        /**
         .startObject("product_name").field("type", "string")
         .field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
         */
        client.admin().indices().prepareCreate("house123").execute().actionGet();
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("house1234").startObject("properties")
                .startObject("areaName").field("type", "string").field("analyzer", "ik_smart").endObject()
                .startObject("houseBusinessName").field("type", "string").field("analyzer", "ik_smart").endObject()
                .startObject("housePlotName").field("type", "string").field("analyzer", "ik_smart").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("house123").type("house1234")
                .source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    public static void save(String index, String type, TransportClient client) throws Exception {
        ProjHouseInfo houseInfo = new ProjHouseInfo();
        Random random = new Random();
        int houseId = random.nextInt(20);
        // 房源id
        houseInfo.setHouseId(houseId);
        // 房源名称
        houseInfo.setHouseTitle("二手房名称");
        // 房源面积
        houseInfo.setHouseArea("100");
        // 房源户型
        houseInfo.setHouseType("二室二厅");
        // 房源户型id
        houseInfo.setHouseTypeId("1");
        // 房源朝向
        houseInfo.setHouseOrientation("东");
        // 房源朝向id
        houseInfo.setHouseOrientationId("1");
        // 房源总价
        houseInfo.setHouseTotalPrices("520");
        // 房源单价(52365元/㎡)
        houseInfo.setHouseUnitCost("52365");
        String[] lab = {"满二", "近地铁", "满五"};
        // 房源标签(满二)
        houseInfo.setHouseLabel(lab);
        String[] labId = {"1", "2", "3"};
        // 房源标签(满二)
        houseInfo.setHouseLabelId(labId);
        // 房源楼层
        houseInfo.setHouseFloorId("27");
        // 房源楼层
        houseInfo.setHouseFloor("中/27层");
        // 房源楼龄
        houseInfo.setHouseYear("5");
        // 房源用途id
        houseInfo.setHousePurposeId("1");
        // 房源用途
        houseInfo.setHousePurpose("住宅");
        // 房源电梯id
        houseInfo.setHouseLiftId("27");
        // 房源电梯
        houseInfo.setHouseLift("有");
        // 房源供暖id
        houseInfo.setHouseHeatingId("23");
        // 房源供暖
        houseInfo.setHouseHeating("自供暖");
        // 房源权属id
        houseInfo.setHouseOwnershipId("5");
        // 房源权属
        houseInfo.setHouseOwnership("商品房");
        // 房源推荐描述
        houseInfo.setHouseRecommendInfo("南北通透无遮挡 好视野 业主诚意售");
        String[] s = {"/ss", "/dff"};
        // 房源照片
        houseInfo.setHousePhoto(s);
        // 房源预算
        houseInfo.setHouseBudget("参考首付267万，月供5432元/月");
        // 房源交通信息
        houseInfo.setHouseTrafficInfo("距离地铁国贸站[1号线]0.6km");
        // 房源更新时间
        houseInfo.setHouseUpdateTime("2017.09.09");
        // 物业类型id
        houseInfo.setHouseManagementTypeId("1");
        // 物业类型
        houseInfo.setHouseManagementType("链家");
        // 建筑类别id
        houseInfo.setHouseBuildingTypeId("1");
        // 建筑类别
        houseInfo.setHouseBuildingType("板楼");
        // 房源级别(排序)
        houseInfo.setHouseRank("1");
        // 经纪人头像
        houseInfo.setHouseProxyPhone("15254445544");
        // 经纪人姓名
        houseInfo.setHouseProxyName("经纪人姓名");
        // 经纪人电话号
        houseInfo.setHouseProxyPhoto("/dds经纪人头像");
        // 房源介绍
        houseInfo.setHouseRecommend("主卧室南向");
        // 房源小区id
        houseInfo.setHousePlotId(1);
//        Double[] d = {34.0, 38.0};
        // 房源小区地理
        String la = "";
        houseInfo.setHousePlotLocation(la);

        // 房源小区名称
        houseInfo.setHousePlotName("版本");

        String[] photo = {"小区照片", "小区照片"};

        houseInfo.setHousePlotPhoto(photo);

        // 房源小区信息 首城国际
        houseInfo.setHousePlotInfo("啊啊");
        // 商圈名称
        houseInfo.setHouseBusinessNameId("1");

        // 商圈名称
        houseInfo.setHouseBusinessName("朝阳111");
        // 区域id
        houseInfo.setAreaId("12");
        // 区域名称(朝阳)
        houseInfo.setAreaName("东城1");
        String[] LineId = {"001", "003"};
        // 地铁线id
        houseInfo.setSubwayLineId(LineId);
        String[] LineName = {"一号线", "三号线"};
        // 地铁线名称
        houseInfo.setSubwayLineName(LineName);

        String[] StationId = {"001", "002"};
        // 地铁站id
        houseInfo.setSubwayStationId(StationId);
        // 地铁站名称
        String[] StationName = {"一号站", "三号站"};

        Map<String, String> ha = new HashMap<String, String>();
        ha.put("001,001", "距离您15km");
        ha.put("001,002", "距离您15km");
        ha.put("001,004", "距离您15km");
        houseInfo.setHouseToSubwayDistance(ha);
        houseInfo.setSubwayStationName(StationName);
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        JSONObject json = (JSONObject) JSONObject.toJSON(houseInfo);
        IndexRequestBuilder lrb = client.prepareIndex(index, type).setSource(json).setId(String.valueOf(houseId));
        bulkRequest.add(lrb);
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {

            bulkResponse.buildFailureMessage();
        }
        bulkRequest = client.prepareBulk();
    }


    public static void queryList(String index, String type, TransportClient client, ProjHouseInfoQuery houseInfo) {

        // 搜索，创建一个termQuery查询，该查询要求全部匹配才会出结果，如果只要包含关键字里面一部分，可以创建fieldQuery。

        if (houseInfo == null || "".equals(houseInfo)) {
            //SearchRequestBuilder requestBuilder = client.prepareSearch(index).setTypes(type);
            //requestBuilder.setQuery(QueryBuilders.matchAllQuery());
            SearchResponse response = client.prepareSearch(index)
                    .setTypes(type)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.matchAllQuery())
                    //.addSort("age", SortOrder.DESC)//排序
                    .setFrom(0).setSize(10)//分页
                    .execute()
                    .actionGet();
            //SearchResponse response = requestBuilder.execute().actionGet();
            for (SearchHit searchHit : response.getHits().getHits()) {
                System.out.println(searchHit.getSourceAsString());
            }
        }

    }


    public static void testIk(TransportClient client, String index, String type) {
        QueryBuilder queryBuilder = null;
        String content = "朝阳111";//朝阳111  首城国际111  东城1
        AnalyzeResponse response = client.admin().indices()
                .prepareAnalyze(content)//内容
                .setAnalyzer("ik_smart")//指定分词器
                //.setTokenizer("standard")
                .execute().actionGet();//执行
        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
        BoolQueryBuilder ww = QueryBuilders.boolQuery();
        for (AnalyzeResponse.AnalyzeToken analyzeToken :
                tokens) {
            System.out.println(analyzeToken.getTerm());
//			queryBuilder =QueryBuilders.boolQuery()
//					.should(QueryBuilders.prefixQuery("areaName",analyzeToken.getTerm()))
//			     .should(QueryBuilders.prefixQuery("houseBusinessName",analyzeToken.getTerm()))
//			     .should(QueryBuilders.prefixQuery("housePlotName",analyzeToken.getTerm()));
            queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termsQuery("areaName", analyzeToken.getTerm()))
                    .should(QueryBuilders.termsQuery("houseBusinessName", analyzeToken.getTerm()))
                    .should(QueryBuilders.termsQuery("housePlotName", analyzeToken.getTerm()));

            ww.should(queryBuilder);
        }
        SearchResponse searchResponse = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(ww)
                .get();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
        /*IndicesAdminClient indicesAdminClient = ElasticFactory.getClient().admin().indices();
        AnalyzeRequestBuilder request = new AnalyzeRequestBuilder(indicesAdminClient,"cloud_repair","中华人民共和国国歌");
        // request.setAnalyzer("ik");
		request.setTokenizer("ik");
         // Analyzer（分析器）、Tokenizer（分词器）
		List listAnalysis = request.execute().actionGet().getTokens();
		System.out.println(listAnalysis);
        // listAnalysis中的结果就是分词的结果

		for (AnalyzeResponse.AnalyzeToken term : listAnalysis) {
			System.out.print(term.getTerm());
			System.out.print(',');
			queryBuilder.should(QueryBuilders.queryString(term.getTerm()).field("search_keys_ik"));
			//这里可以用must 或者 should 视情况而定
		}
		System.out.print('\n');*/

    }

    protected static void buildRobotMapping(TransportClient client) throws Exception {
        /**
         .startObject("product_name").field("type", "string")
         .field("analyzer","ik").field("search_analyzer","ik_smart").endObject()
         */

        client.admin().indices().prepareCreate("robot_index").execute().actionGet();
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("robot_type").startObject("properties")
                .startObject("newcode").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("n_or_e").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("projname").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("nickname").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("installment").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("address").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("address_info").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("housefeature").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("proj_feature").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("province").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("city_id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("district_id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("area_id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("round").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("roundstation").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("rounddirection").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("community_id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("projdesc").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("right_year").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("groundarea").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("purposearea").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("dimension").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("virescencerate").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("buildingdes").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("totaldoor").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("parkdesc").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("parkspace").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("car_rent_price").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("car_sell_price").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("propertyfee").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("propfeetype").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("propertyaddition").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("fixstatus").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("equipment").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("layout").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("work_schedule").field("type", "string").endObject()
                .startObject("startdate").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("finishdate").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("saledate").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("saledate_s").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("livindate").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("livindate_s").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("saletelphone").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("saleaddress").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("salecard").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("mortgage_bank").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("saling").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("sail_schedule").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("developer").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("investor").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("propertymanage").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("propertyadviser").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("landscape").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("construct").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("agent").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("sightdesign").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("conextend").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("media").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("is_approve").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("webaddress").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("websaleaddress").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("complete_fraction").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("coord_x").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("coord_y").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("saling_optime").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("adminstauts_time").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("deldate").field("type", "date").field("format", "yyyy-MM-dd").field("index", "not_analyzed").endObject()
                .startObject("delreason").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("tel400").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("pricerate").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("pricemax").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("pricemin").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("esf_district").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("esf_comarea").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("pinyin_name").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("pinyin_initials").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("esf_address").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("esf_operastion").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("esf_show").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("esf_is_approve").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("update_saledate_time").field("type", "date").field("format", "yyyy-MM-dd").endObject()
                .startObject("update_livindate_time").field("type", "date").field("format", "yyyy-MM-dd").endObject()
                .startObject("right_desc").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("zip_code").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("property_tele").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("property_address").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("creator_id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("updater_id").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("is_del").field("type", "integer").field("index", "not_analyzed").endObject()
                .startObject("price").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("total_price").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("esf_price").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("esf_total_price").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("heating_mode").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("lift_door_radio").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("park_radio").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("property_type").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("build_form").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("build_category").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("villa_style").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("price_unit").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("total_price_unit").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("esf_price_unit").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("esf_total_price_unit").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("residential_category").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("soufun_newcode").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("air_quality").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("plotPhoto").field("type", "string").field("index", "not_analyzed").endObject()
                .startObject("plotTotalPricesBegin").field("type", "double").field("index", "not_analyzed").endObject()
                .startObject("plotTotalPricesEnd").field("type", "double").field("index", "not_analyzed").endObject()
                .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("robot_index").type("robot_type")
                .source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }


    public static void saveRobot(String index, String type, TransportClient client) throws Exception {

        QueryFindByRobot queryFindByRobot = new QueryFindByRobot();
        Integer houseId = RandomUtils.nextInt(20);

        queryFindByRobot.setUserId(houseId);
        queryFindByRobot.setUserType(3);
        //序号
        queryFindByRobot.setNewcode(houseId);
        //新房/二手房(0-新房，1-二手房)
        queryFindByRobot.setN_or_e(RandomUtils.nextInt(2));
        //楼盘名称(小区名称)
        queryFindByRobot.setProjname("楼盘名称");
        //别名
        queryFindByRobot.setNickname("别名");
        //楼盘分期
        queryFindByRobot.setInstallment("楼盘分期1");
        //地址
        queryFindByRobot.setAddress("楼盘地址");
        //地址描述
        queryFindByRobot.setAddress_info("楼盘地址描述");
        //房源特色
        queryFindByRobot.setHousefeature("房源特色");
        //项目特色
        queryFindByRobot.setProj_feature("项目特色");
        //省
        queryFindByRobot.setProvince("省");
        //城市
        queryFindByRobot.setCity_id(1);
        //区县
        queryFindByRobot.setDistrict_id(1);
        //商圈
        queryFindByRobot.setArea_id(1);
        //环线
        queryFindByRobot.setRound("二环以外，三环以内");
        //环线方位
        queryFindByRobot.setRounddirection("二环向东");
        //环线位置
        queryFindByRobot.setRoundstation("环线位置");
        //所属社区
        queryFindByRobot.setCommunity_id(12);
        //项目描述
        queryFindByRobot.setProjdesc("项目描述");
        //产权年限
        queryFindByRobot.setRight_year(23);
        //占地面积(平方米)
        queryFindByRobot.setGroundarea(123.4);
        //建筑面积(平方米)
        queryFindByRobot.setPurposearea(2345.5);
        //绿化率(%)
        queryFindByRobot.setVirescencerate(678.3);
        //容积率(%)
        queryFindByRobot.setDimension(34.5);
        //楼层状况
        queryFindByRobot.setBuildingdes("楼层状况");
        //总户数
        queryFindByRobot.setTotaldoor(123);
        //停车位描述
        queryFindByRobot.setParkdesc("停车位描述");
        //停车位数量
        queryFindByRobot.setParkspace(45);
        //停车位租价（年租）
        queryFindByRobot.setCar_rent_price(234.5);
        //停车位售价
        queryFindByRobot.setCar_sell_price(4354.5);
        //物业管理费
        queryFindByRobot.setPropertyfee(123.4);
        //物业管理费价格单位
        queryFindByRobot.setPropfeetype("物业管理费价格单位");
        //物业管理费附加信息
        queryFindByRobot.setPropertyaddition("物业管理费附加信息");
        //装修状况
        queryFindByRobot.setFixstatus("装修中");
        //建材设备
        queryFindByRobot.setEquipment("钢材");
        //小区内部配套
        queryFindByRobot.setLayout("小区健身器材");

        queryFindByRobot.setWork_schedule("工程建设中");
        queryFindByRobot.setStartdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        queryFindByRobot.setFinishdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        queryFindByRobot.setSaledate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        queryFindByRobot.setSaledate_s("开始建设");
        queryFindByRobot.setLivindate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        queryFindByRobot.setLivindate_s("1");
        queryFindByRobot.setSaletelphone("123");
        queryFindByRobot.setSaleaddress("1");
        queryFindByRobot.setSalecard("注册证");
         queryFindByRobot.setMortgage_bank("中国银行");
        //销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
        queryFindByRobot.setSaling(2);
        queryFindByRobot.setSail_schedule(4);
        queryFindByRobot.setDeveloper("开发商");
        queryFindByRobot.setInvestor("投资商");
        queryFindByRobot.setPropertymanage("我爱我家");
        //物业顾问公司
        queryFindByRobot.setPropertyadviser("物业顾问公司");
        //建筑及园林设计单位
        queryFindByRobot.setLandscape("建筑及园林设计单位");
        //承建商
        queryFindByRobot.setConstruct("中建竣工");
        //代理商
        queryFindByRobot.setAgent("中建竣工");
        //景观设计单位
        queryFindByRobot.setSightdesign("景观设计单位");
        //整合推广单位
        queryFindByRobot.setConextend("整合推广单位");
        //平面媒体广告单位
        queryFindByRobot.setMedia("平面媒体广告单位");
        //审批状态（0-不通过，1-通过，2-错误）（0-未发布，1-已发布）
        queryFindByRobot.setIs_approve(4);
        //网站地址
        queryFindByRobot.setWebaddress("网站地址");
        //网上售楼中心地址
        queryFindByRobot.setWebsaleaddress("网上售楼中心地址");
        //完成度
        queryFindByRobot.setComplete_fraction("完成度");
        //x坐标
        queryFindByRobot.setCoord_x(3.0);
        //y坐标
        queryFindByRobot.setCoord_y(3.5);
        //楼盘置为售完的时间
       queryFindByRobot.setSaling_optime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //移交二手房或者从二手房转回的时间
        queryFindByRobot.setAdminstauts_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //创建时间
        queryFindByRobot.setCreate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //修改时间
        queryFindByRobot.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //删除时间
        queryFindByRobot.setDeldate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //删除说明
         queryFindByRobot.setDelreason("删除说明：2018-1-1");
        //400电话
        queryFindByRobot.setTel400("1222111");
        //价格增长率
        queryFindByRobot.setPricerate(456.8);
        //最高价格
        queryFindByRobot.setPricemax(3443.6);
        //最低价格
        queryFindByRobot.setPricemin(345.7);
        //二手房区县
       queryFindByRobot.setEsf_district("二手房区县");
        //二手房商圈
        queryFindByRobot.setEsf_comarea("二手房商圈");
        //名称全拼
        queryFindByRobot.setPinyin_name("名称全拼");
        //名称拼音首字母
        queryFindByRobot.setPinyin_initials("名称拼音首字母");
        //二手房地址
        queryFindByRobot.setEsf_address("名称拼音首字母");
        //二手房业态
        queryFindByRobot.setEsf_operastion("二手房业态");
        //二手房是否展示(0-否，1-是)
        queryFindByRobot.setEsf_show(4);
        //二手房审核状态（Y/N）
        queryFindByRobot.setEsf_is_approve("y");
        //开盘更新时间
        queryFindByRobot.setUpdate_saledate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //入住更新时间
       queryFindByRobot.setUpdate_livindate_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //产权年限（下拉：70，50,40）
        queryFindByRobot.setRight_desc("70");
        //邮编
        queryFindByRobot.setZip_code("100000");
        //物业电话
        queryFindByRobot.setProperty_tele("11111");
        //物业地址
        queryFindByRobot.setProperty_address("物业地址");
        //创建人ID
        queryFindByRobot.setCreator_id(43);
        //更新人ID
        queryFindByRobot.setUpdater_id(56);
        //删除标志位(0-否，1-是)
        queryFindByRobot.setIs_del(23);
        //平均单价-新房
        queryFindByRobot.setPrice(23.4);
        //平均总价-新房
        queryFindByRobot.setTotal_price(233.3);
        //平均单价-二手房
        queryFindByRobot.setEsf_price(321.4);
        //平均总价-二手房
        queryFindByRobot.setEsf_total_price(987.2);
        String  i[]={"1","3","2"};
        //供暖方式(0-未知，1-集中供暖，2-自供暖）
        queryFindByRobot.setHeating_mode(i);

        //梯户比
        queryFindByRobot.setLift_door_radio("1:2");
        //车位配比
        queryFindByRobot.setPark_radio("3:4");
        String w[]={"1","3","2"};
        //物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商）
        queryFindByRobot.setProperty_type(w);
        String q[]={"1","3","2"};
        //住宅建筑形式(数组)：1-低层，2-多层，3-小高层，4-高层，5-超高层，
        //6-联排、7-独栋、8-双拼、9-叠拼、10-空中花园、11-空中别墅
        queryFindByRobot.setBuild_form(q);
        String q1[]={"1","3","2"};
        //建筑类别(数组)：1-板楼，2-塔楼，3-板塔结合
         queryFindByRobot.setBuild_category(q1);
        String q2[]={"1","3","2"};
        //别墅建筑风格(数组)：1-中式、2-欧式、3-日式、4-美式、5-英式、6-澳式、
        //7-法式、8-西班牙式、9-东南亚式、10-地中海式、11-意大利式、12-现代
        queryFindByRobot.setVilla_style(q2);
        //新房平均单价单位
        queryFindByRobot.setPrice_unit("新房平均单价单位");
        //新房平均单价单位
        queryFindByRobot.setTotal_price_unit("新房平均单价单位");
        //二手房平均单价单位
        queryFindByRobot.setEsf_price_unit("二手房平均单价单位");
        //二手房平均总价单位
        queryFindByRobot.setEsf_total_price_unit("二手房平均总价单位");

        String y[]={"1","3","2"};
        //住宅类别(数组)：1-普通住宅，2-公寓，3-酒店式公寓，4-花园洋房，5-商住楼，6-独栋别墅，
        //7-联排别墅，8-经济适用房，9-廉租房，10-公共租赁房，11-定向安置房，
        //12-两限商品房，13-自住型商品房，14-其他
        queryFindByRobot.setResidential_category(y);
        //房天下楼盘Id
        queryFindByRobot.setSoufun_newcode("45");
        //空气质量
        queryFindByRobot.setAir_quality("质量不错");
        String photo[]={"/sec","/sx"};
        //小区照片
        queryFindByRobot.setPlotPhoto(photo);
        //小区总价最低
        queryFindByRobot.setPlotTotalPricesBegin(345.6);
        //小区总价最高
        queryFindByRobot.setPlotTotalPricesEnd(763.6);

        BulkRequestBuilder bulkRequest = client.prepareBulk();
        JSONObject json = (JSONObject) JSONObject.toJSON(queryFindByRobot);
        IndexRequestBuilder lrb = client.prepareIndex(index, type).setSource(json).setId(String.valueOf(houseId));
        bulkRequest.add(lrb);
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {

            bulkResponse.buildFailureMessage();
        }
        bulkRequest = client.prepareBulk();
    }


}
