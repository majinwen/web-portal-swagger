package com.toutiao.web.dao.entity.admin;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestMapping {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));

		//queryByHouseId( "a", "b",1, client);
		//buildIndexMapping(client);
		save("a","b",client);
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

	public static void save(String index, String type, TransportClient client) throws Exception {
		ProjHouseInfo houseInfo = new ProjHouseInfo();
		Random random=new Random();
		// 房源id
		houseInfo.setHouseId(random.nextInt(20));
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
		String[] lab={"满二","近地铁","满五"};
		// 房源标签(满二)
		houseInfo.setHouseLabel(lab);
		String[] labId={"1","2","3"};
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
		String[] s={"/ss","/dff"};
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
		Double[] d={34.0,38.0};
		// 房源小区地理
		houseInfo.setHousePlotLocation(d);

		// 房源小区名称
		houseInfo.setHousePlotName("首城国际");

		String[] photo={"小区照片","小区照片"};

		houseInfo.setHousePlotPhoto(photo);

		// 房源小区信息
		houseInfo.setHousePlotInfo("首城国际");
		// 商圈名称
		houseInfo.setHouseBusinessNameId("1");

		// 商圈名称
		houseInfo.setHouseBusinessName("朝阳");
		// 区域id
		houseInfo.setAreaId("12");
		// 区域名称(朝阳)
		houseInfo.setAreaName("东城");
		String[] LineId={"001","003"};
		// 地铁线id
		houseInfo.setSubwayLineId(LineId);
		String[] LineName={"一号线","三号线"};
		// 地铁线名称
		houseInfo.setSubwayLineName(LineName);

		String[] StationId={"001","002"};
		// 地铁站id
		houseInfo.setSubwayStationId(StationId);
		// 地铁站名称
		String[] StationName={"一号站","三号站"};

		Map<String,String> ha= new HashMap<String,String>();
		ha.put("001,001","距离您15km");
		ha.put("001,002","距离您15km");
		ha.put("001,004","距离您15km");
		houseInfo.setHouseToSubwayDistance(ha);
		houseInfo.setSubwayStationName(StationName);
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		JSONObject json = (JSONObject) JSONObject.toJSON(houseInfo);
		IndexRequestBuilder lrb = client.prepareIndex(index, type).setSource(json);
		bulkRequest.add(lrb);
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {

			bulkResponse.buildFailureMessage();
		}
		bulkRequest = client.prepareBulk();
	}


	public static void queryList(String index, String type, TransportClient client, ProjHouseInfoQuery houseInfo) {

		// 搜索，创建一个termQuery查询，该查询要求全部匹配才会出结果，如果只要包含关键字里面一部分，可以创建fieldQuery。

		if (houseInfo==null || "".equals(houseInfo)) {
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


//	public static void queryRang(String index, String type, TransportClient client, ProjHouseInfoRequest houseInfo) {
//		// 搜索，创建一个termQuery查询，该查询要求全部匹配才会出结果，如果只要包含关键字里面一部分，可以创建fieldQuery。
//		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//				.must(QueryBuilders.termQuery("subwayLineName", "三3号线"))
//				.must(QueryBuilders.termQuery("subwayStationName", "海淀4"));
//		SearchResponse searchResponse = client.prepareSearch(index)
//				.setTypes(type)
//				.setQuery(queryBuilder)
//				.get();
//		for(SearchHit searchHit : searchResponse.getHits().getHits()) {
//			System.out.println(searchHit.getSourceAsString());
//		}
//
//
//	}



	public static void query2(String index, String type, TransportClient client,String info) {
		QueryBuilder queryBuilder=null;
		if (info==null) {
			queryBuilder = QueryBuilders.boolQuery()
					//.must(QueryBuilders.termQuery("areaName1", info));
					.must(QueryBuilders.termQuery("areaName", "西城ss"));

		}else {
			queryBuilder = QueryBuilders.boolQuery()
					.must(QueryBuilders.termQuery("areaName1", info))
					.must(QueryBuilders.termQuery("areaName", "西城ss"));
		}
		SearchResponse searchResponse = client.prepareSearch(index)
				.setTypes(type)
				.setQuery(queryBuilder)
				.get();
		for(SearchHit searchHit : searchResponse.getHits().getHits()) {
			System.out.println(searchHit.getSourceAsString());
		}

	}


	/**
	 * 通过二手房id查找房源信息
	 * @param houseId
	 * @return
	 */
	public static Map<String, Object> queryByHouseId(String index, String type,Integer houseId,TransportClient client) {

		BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
		booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
		SearchResponse searchresponse = client.prepareSearch(index).setTypes(type)
				.setQuery(booleanQueryBuilder)
				.execute().actionGet();
		SearchHits hits = searchresponse.getHits();
		SearchHit[] searchHists = hits.getHits();
		Map<String, Object> result = new HashMap<>();
		result.put("data_house", searchHists);
		result.put("total_house", hits.getTotalHits());
		return result;
	}


	public Map<String, Object> queryProjHouseByhouseIdandLocation(String projhouseIndex, String projhousetype,double lat, double lon,TransportClient client) {

		SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhousetype);
		//从该坐标查询距离为distance      housePlotLocation
		GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("housePlotLocation").point(lat, lon).distance("30000", DistanceUnit.METERS);
		srb.setPostFilter(location1);
		// 获取距离多少公里 这个才是获取点与点之间的距离的
		GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("30000", lat, lon);
		sort.unit(DistanceUnit.METERS);
		sort.order(SortOrder.ASC);
		sort.point(lat, lon);
		srb.addSort(sort).setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto", "houseType", "houseArea", "housePlotName"}, null).execute().actionGet();
		SearchResponse searchResponse = srb.execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		String[] house = new String[(int) hits.getTotalHits()];
		System.out.println("附近的房源(" + hits.getTotalHits() + "个)：");
		ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();
		SearchHit[] searchHists = hits.getHits();
		for (SearchHit hit : searchHists) {
			Map<String, Object> buildings = hit.getSource();
			buildinglist.add(buildings);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("data_plot", buildinglist);
		result.put("total_plot", hits.getTotalHits());
		return result;
	}


}
