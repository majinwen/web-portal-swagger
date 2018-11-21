package com.toutiao.web.service.newhouse.impl;

import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
@Transactional
public class NewHouseServiceImpl implements NewHouseService{

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${tt.newhouse.index}")
    private String newhouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newhouseType;//索引类型
    @Value("${tt.newlayout.type}")
    private String layoutType;//子类索引类型
    @Value("${distance}")
    private Double distance;
    @Value("${tt.search.engines}")
    private String searchEnginesIndex ;
    @Value("${tt.search.engines.type}")
    private String searchEnginesType;

    private static final Integer IS_DEL = 0;//新房未删除
    private static final Integer IS_APPROVE = 1;//新房未下架

    /**
     * 根绝新房筛选新房
     * @param newHouseQuery
     * @return
     */
    @Override
    public Map<String,Object> getNewHouse(NewHouseQuery newHouseQuery) {

        //建立连接

//        TransportClient client = esClientTools.init();

//        BoolQueryBuilder bqbPlotName = QueryBuilders.boolQuery();
//        if (StringTool.isNotBlank(newHouseQuery.getKeyword())) {
//            SearchResponse searchResponse = null;
//            bqbPlotName.must(QueryBuilders.termQuery("building_name_accurate",newHouseQuery.getKeyword()));
//            searchResponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType).setQuery(bqbPlotName).execute().actionGet();
//            long total = searchResponse.getHits().getTotalHits();
//            out: if(total > 0l){
//                break out;
//            }else{
//                BoolQueryBuilder bqb = QueryBuilders.boolQuery();
//                bqb.must(QueryBuilders.multiMatchQuery(newHouseQuery.getKeyword(),"search_nickname").operator(Operator.AND).minimumShouldMatch("100%"));
//                searchResponse = client.prepareSearch(searchEnginesIndex).setTypes(searchEnginesType).setQuery(bqb).execute().actionGet();
//                if(searchResponse.getHits().getTotalHits()>0l){
//                    SearchHits hits = searchResponse.getHits();
//                    SearchHit[] searchHists = hits.getHits();
//                    outFor:for (SearchHit hit : searchHists) {
//                        newHouseQuery.setKeyword(hit.getSource().get("search_name").toString());
//                        break outFor ;
//                    }
//                }
//            }
//        }
        //
        SearchResponse searchresponse = new SearchResponse();
        //校验筛选条件，根据晒选条件展示列表
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;
        if(StringUtil.isNotNullString(newHouseQuery.getKeyword())){
            if(StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseQuery.getKeyword()))){
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name", newHouseQuery.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("area_name", newHouseQuery.getKeyword()).analyzer("ik_smart"))
                        .add(QueryBuilders.matchQuery("district_name", newHouseQuery.getKeyword()).analyzer("ik_smart")).tieBreaker(0.3f);
            } else {
            queryBuilder = QueryBuilders.disMaxQuery()
                    .add(QueryBuilders.matchQuery("building_name_accurate", newHouseQuery.getKeyword()).operator(Operator.AND).boost(2))
                    .add(QueryBuilders.matchQuery("building_nickname",newHouseQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                    .add(QueryBuilders.matchQuery("building_name", newHouseQuery.getKeyword()).operator(Operator.AND).analyzer("ik_max_word"))
                    .add(QueryBuilders.matchQuery("area_name", newHouseQuery.getKeyword()).operator(Operator.AND))
                    .add(QueryBuilders.matchQuery("district_name", newHouseQuery.getKeyword())).tieBreaker(0.3f);
            }

            booleanQueryBuilder.must(queryBuilder);
            //    booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("building_name_accurate", newHouseQuery.getKeyword()))).boost(2);
        }

        //城市
        if(newHouseQuery.getCityId()!=null && newHouseQuery.getCityId()!=0){
            booleanQueryBuilder.must(termQuery("city_id", newHouseQuery.getDistrictId()));
        }
        //区域
        if(newHouseQuery.getDistrictId()!=null && newHouseQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",newHouseQuery.getDistrictId()));
        }
        //商圈
        if(newHouseQuery.getAreaId()!=null && newHouseQuery.getAreaId()!=0){
            booleanQueryBuilder.must(termQuery("area_id", newHouseQuery.getAreaId()));
        }
        //地铁线id
        String keys = "";
        if(newHouseQuery.getSubwayLineId() !=null && newHouseQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseQuery.getSubwayLineId()}));
            keys = newHouseQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if(newHouseQuery.getSubwayStationId()!=null && newHouseQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{newHouseQuery.getSubwayStationId()}));
            keys = keys+"$"+newHouseQuery.getSubwayStationId().toString();
        }
        //总价
        if(newHouseQuery.getBeginPrice()!=null && newHouseQuery.getEndPrice()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseQuery.getBeginPrice()).lte(newHouseQuery.getEndPrice())));
        }

        //户型
        if(StringUtil.isNotNullString(newHouseQuery.getLayoutId())){
            String[] layoutId = newHouseQuery.getLayoutId().split(",");
            Long[] longs = new Long[layoutId.length];
            for (int i = 0; i < layoutId.length; i++) {
                longs[i] = Long.valueOf(layoutId[i]);
            }
            booleanQueryBuilder.must(JoinQueryBuilders.hasChildQuery("layout", QueryBuilders.termsQuery("room",longs), ScoreMode.None));

        }

        //面积
        if(StringUtil.isNotNullString(newHouseQuery.getHouseAreaSize())){
            String area = newHouseQuery.getHouseAreaSize().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length ; i=i+2) {
                if(i+1>layoutId.length){
                    break;
                }
                boolQueryBuilder.should(JoinQueryBuilders.hasChildQuery("layout",QueryBuilders.rangeQuery("building_area").gt(layoutId[i]).lte(layoutId[i+1]) , ScoreMode.None));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }

        //物业类型
        if(StringUtil.isNotNullString(newHouseQuery.getPropertyTypeId())){
            String[] py = newHouseQuery.getPropertyTypeId().split(",");
            String[] propertyType = new String[py.length];
            for(int i=0; i<py.length;i++){
                propertyType[i] = py[i];
            }
            booleanQueryBuilder.must(termsQuery("property_type_id",propertyType));
        }else{
            booleanQueryBuilder.must(termsQuery("property_type_id", new int[]{1,2}));
        }

        //电梯
        if(!StringUtils.isEmpty(newHouseQuery.getElevatorFlag())){
            booleanQueryBuilder.must(termQuery("elevator_flag", newHouseQuery.getElevatorFlag()));
        }
        //建筑类型
        if(StringUtil.isNotNullString(newHouseQuery.getBuildingType())){
            String[] py = newHouseQuery.getBuildingType().split(",");
            String[] BuildingType = new String[py.length];
            for(int i=0; i<py.length;i++){
                BuildingType[i] = py[i];
            }
            booleanQueryBuilder.must(termsQuery("building_type_id", BuildingType));
        }
        //销售状态
        if(StringUtil.isNotNullString(newHouseQuery.getSaleType())){
            booleanQueryBuilder.must(termQuery("sale_status_id", newHouseQuery.getSaleType()));
        }else{
            booleanQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        }
        //楼盘特色
        if(StringUtil.isNotNullString(newHouseQuery.getBuildingFeature())){

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] py = newHouseQuery.getBuildingFeature().split(",");
            boolean has_subway = Arrays.asList(py).contains("1");

            if(has_subway){
                String[] tagOther = new String[py.length-1];
                int idx = 0;
                for(int i=0;i<py.length;i++){
                    if(py[i].equals("1")){
                        boolQueryBuilder.should(QueryBuilders.termQuery("has_subway", py[i]));
                    } else {
                        tagOther[idx++] = py[i];
                    }
                }
                if(tagOther.length!=0){
                    boolQueryBuilder.should(QueryBuilders.termsQuery("building_tags_id", tagOther));
                }
                booleanQueryBuilder.must(boolQueryBuilder);
            }else{
                booleanQueryBuilder.must(QueryBuilders.termsQuery("building_tags_id", py));
            }
        }
        //装修
        if(StringUtil.isNotNullString(newHouseQuery.getDeliverStyle())){
            booleanQueryBuilder.must(termsQuery("redecorate_type_id", new String[]{newHouseQuery.getDeliverStyle()}));
        }

        //房源已发布
        booleanQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
        booleanQueryBuilder.must(termQuery("is_del", IS_DEL));


        int pageNum = 1;

        if(newHouseQuery.getPageNum()!=null && newHouseQuery.getPageNum()>1){
            pageNum = newHouseQuery.getPageNum();
        }

        SearchRequest searchRequest = new SearchRequest(newhouseIndex).types(newhouseType);

        //排序  0--默认（按楼盘级别（广告优先））--1均价升排序--2均价降排序--3开盘时间升排序--4开盘时间降排序
        if(newHouseQuery.getSort()!=null&& newHouseQuery.getSort()==1){
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(booleanQueryBuilder).sort("average_price", SortOrder.ASC).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                    .fetchSource( new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type","location","house_min_area","house_max_area","nearbysubway","total_price"},
                            null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
            searchRequest.source(searchSourceBuilder);
            try {
                searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(newHouseQuery.getSort()!=null && newHouseQuery.getSort()==2){
//            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                    .setQuery(booleanQueryBuilder).addSort("average_price", SortOrder.DESC).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
//                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                            null)
//                    .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                    .setSize(newHouseQuery.getPageSize())
//                    .execute().actionGet();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(booleanQueryBuilder).sort("average_price", SortOrder.DESC).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                    .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
                            null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
            searchRequest.source(searchSourceBuilder);
            try {
                searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(newHouseQuery.getSort()!=null&&newHouseQuery.getSort()==3){
//            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                    .setQuery(booleanQueryBuilder).addSort("opened_time", SortOrder.ASC).setFetchSource(
//                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                            null)
//                    .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                    .setSize(newHouseQuery.getPageSize())
//                    .execute().actionGet();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(booleanQueryBuilder).sort("opened_time", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                    .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
                            null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
            searchRequest.source(searchSourceBuilder);
            try {
                searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(newHouseQuery.getSort()!=null && newHouseQuery.getSort()==4){
//            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                    .setQuery(booleanQueryBuilder).addSort("opened_time", SortOrder.DESC).setFetchSource(
//                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                            null)
//                    .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                    .setSize(newHouseQuery.getPageSize())
//                    .execute().actionGet();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(booleanQueryBuilder).sort("opened_time", SortOrder.ASC)
                    .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
                            null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
            searchRequest.source(searchSourceBuilder);
            try {
                searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(newHouseQuery.getSort()!=null && newHouseQuery.getSort()==0){
//            searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                    .setQuery(booleanQueryBuilder).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
//                            new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                            null)
//                    .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                    .setSize(newHouseQuery.getPageSize())
//                    .execute().actionGet();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(booleanQueryBuilder).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                    .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                    "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                    "location","house_min_area","house_max_area","nearbysubway","total_price"},
                            null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
            searchRequest.source(searchSourceBuilder);
            try {
                searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            if(StringUtil.isNotNullString(newHouseQuery.getKeyword())){
                if(StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseQuery.getKeyword()))){
//                    searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                            .setQuery(booleanQueryBuilder).addSort("_score",SortOrder.DESC).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
//                                    new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                            "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                            "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                                    null)
//                            .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                            .setSize(newHouseQuery.getPageSize())
//                            .execute().actionGet();

                    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                    searchSourceBuilder.query(booleanQueryBuilder).sort("_score",SortOrder.DESC).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                            .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                            "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                            "location","house_min_area","house_max_area","nearbysubway","total_price"},
                                    null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
                    searchRequest.source(searchSourceBuilder);
                    try {
                        searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
//                    searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                            .setQuery(booleanQueryBuilder).addSort("_score",SortOrder.DESC).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
//                                    new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                            "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                            "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                                    null)
//                            .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                            .setSize(newHouseQuery.getPageSize())
//                            .execute().actionGet();
                    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                    searchSourceBuilder.query(booleanQueryBuilder).sort("_score",SortOrder.DESC).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                            .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                            "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                            "location","house_min_area","house_max_area","nearbysubway","total_price"},
                                    null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
                    searchRequest.source(searchSourceBuilder);
                    try {
                        searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }else{
//                searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                        .setQuery(booleanQueryBuilder).addSort("build_level", SortOrder.ASC).addSort("building_sort",SortOrder.DESC).setFetchSource(
//                                new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
//                                        "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
//                                        "location","house_min_area","house_max_area","nearbysubway","total_price"},
//                                null)
//                        .setFrom((pageNum-1)*newHouseQuery.getPageSize())
//                        .setSize(newHouseQuery.getPageSize())
//                        .execute().actionGet();

                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.query(booleanQueryBuilder).sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC)
                        .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                        "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                        "location","house_min_area","house_max_area","nearbysubway","total_price"},
                                null).from((pageNum-1)*newHouseQuery.getPageSize()).size(newHouseQuery.getPageSize());
                searchRequest.source(searchSourceBuilder);
                try {
                    searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        SearchHits hits = searchresponse.getHits();
//        List<String> buildinglist = new ArrayList<>();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String,String> fff = (Map<String, String>) hit.getSourceAsMap().get("nearbysubway");
            String ddd = fff.get(keys);
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildings.put("nearsubway",ddd);
            buildings.put("pageNum",newHouseQuery.getPageNum());
            buildinglist.add(buildings);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",buildinglist);
        result.put("total", hits.getTotalHits());
        return result;
    }

    @Override
    public Map<String, Object> getNewHouseDetails(Integer buildingId) {


        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("building_name_id", buildingId));
//        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                .setQuery(booleanQueryBuilder)
//                .execute().actionGet();

        SearchRequest searchRequest = new SearchRequest(newhouseIndex).types(newhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        BoolQueryBuilder booleanQueryBuilder1 = boolQuery();
//        booleanQueryBuilder1.must(JoinQueryBuilders.hasParentQuery(newhouseType,QueryBuilders.termQuery("building_name_id",buildingId) ,false));
//        SearchResponse searchresponse1 = client.prepareSearch(newhouseIndex).setTypes(layoutType)
//                .setQuery(booleanQueryBuilder1)
//                .execute().actionGet();

        SearchRequest searchRequest1 = new SearchRequest(newhouseIndex).types(layoutType);
        SearchSourceBuilder searchSourceBuilder1 = new SearchSourceBuilder();
        searchSourceBuilder1.query(booleanQueryBuilder);
        searchRequest1.source(searchSourceBuilder1);
        SearchResponse searchresponse1 = null;

        try {
            searchresponse1 = restHighLevelClient.search(searchRequest1, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SearchHits layouthits = searchresponse1.getHits();
        List<Map<String,Object>> layouts =new ArrayList<>();
        SearchHit[] searchLayoutHists = layouthits.getHits();
        for (SearchHit hit : searchLayoutHists) {
            Map<String,Object> item=hit.getSourceAsMap();
            layouts.add(item);
        }

        SearchHits hits = searchresponse.getHits();

        SearchHit[] searchHists = hits.getHits();
        String buildings = null;
//        List<Double> locations = new ArrayList<>();
        String locations = "";
        for (SearchHit hit : searchHists) {
            buildings = hit.getSourceAsString();
            locations = (String) hit.getSourceAsMap().get("location");

        }
        Map<String, Object> maprep = new HashMap<>();
        maprep.put("build",buildings);
        maprep.put("layout",layouts);
        String[] loca = locations.split(",");
        try {
            if(loca.length ==2){
                List<Map<String,Object>>nearBy = getNearBuilding(buildingId,newhouseIndex,newhouseType,Double.valueOf(loca[0]),Double.valueOf(loca[1]),distance);
                maprep.put("nearbybuild",nearBy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maprep;
    }
    /**
     * 获取新房全部描述
     * */
    @Override
    public List<Map<String,Object>> getNewHouseDiscript(Integer id) {


        BoolQueryBuilder booleanQueryBuilder = boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("building_name_id", id));

//        SearchResponse searchresponse1 = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
//                .setQuery(booleanQueryBuilder)
//                .execute().actionGet();

        SearchRequest searchRequest = new SearchRequest(newhouseIndex).types(newhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;

        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SearchHits layouthits = searchresponse.getHits();
        SearchHit[] searchHists = layouthits.getHits();
        List<Map<String,Object>> discripts =new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,Object> item=hit.getSourceAsMap();
            discripts.add(item);
        }

        return discripts;
    }


    @Override
    public Map<String, Object> getNewHouseLayoutDetails(Integer buildingId, Integer tags) {
//        TransportClient client = esClientTools.init();
        BoolQueryBuilder detailsBuilder = boolQuery();
//        BoolQueryBuilder booleanQueryBuilder1 = QueryBuilders.boolQuery();
        detailsBuilder.must(JoinQueryBuilders.hasParentQuery("buildings",QueryBuilders.termQuery("building_name_id",buildingId) ,false));
        if(tags > 0){
            detailsBuilder.must(QueryBuilders.termQuery("room",tags));
        }

        SearchRequest searchRequest = new SearchRequest(newhouseIndex).types(newhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(detailsBuilder).size(1000);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchrespons = null;

        try {
            searchrespons = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        SearchResponse searchresponse1 = client.prepareSearch(newhouseIndex).setTypes(layoutType)
//                .setQuery(detailsBuilder).setSize(1000)
//                .execute().actionGet();
        SearchHits layouthits = searchrespons.getHits();
        List<Map<String,Object>> layouts =new ArrayList<>();
        SearchHit[] searchLayoutHists = layouthits.getHits();
        for (SearchHit hit : searchLayoutHists) {
            Map<String,Object> item=hit.getSourceAsMap();
            layouts.add(item);
        }
        Map<String, Object> maprep = new HashMap<>();

        maprep.put("layouts",layouts);
        return maprep;
    }

    @Override
    public List<Map<String,Object>> getNewHouseLayoutCountByRoom(Integer buildingId) {

//        TransportClient client = esClientTools.init();
        BoolQueryBuilder sizeBuilder = QueryBuilders.boolQuery();
        sizeBuilder.must(JoinQueryBuilders.hasParentQuery("buildings",QueryBuilders.termQuery("building_name_id",buildingId) ,false));

//        SearchResponse searchresponse = client.prepareSearch(newhouseIndex).setTypes(layoutType).setQuery(sizeBuilder)
//                .addAggregation(AggregationBuilders.terms("roomCount").field("room"))
//                .execute().actionGet();

        SearchRequest searchRequest = new SearchRequest(newhouseIndex).types(layoutType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(sizeBuilder).aggregation(AggregationBuilders.terms("roomCount").field("room"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;

        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map aggMap =searchresponse.getAggregations().asMap();
        LongTerms gradeTerms = (LongTerms) aggMap.get("roomCount");
        Iterator roomBucketIt = gradeTerms.getBuckets().iterator();
        List<Map<String, Object>> roomCount = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
        while(roomBucketIt.hasNext())
        {
            Map<String, Object> map = new HashMap<>();
            Bucket roomBucket = (Bucket) roomBucketIt.next();

            // list.add(roomBucket.getKey()+","+roomBucket.getDocCount());
            map.put("room",roomBucket.getKey());
            map.put("count",roomBucket.getDocCount());
            roomCount.add(map);
        }

        return roomCount;
    }

    /**
     * 地理位置找房
     * @param buildingNameId
     * @param index
     * @param type
     * @param lat
     * @param lon
     * @param distance
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> getNearBuilding(int buildingNameId,String index, String type, double lat, double lon, Double distance) throws Exception {

//        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);

        SearchRequest searchRequest = new SearchRequest(index).types(type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //从该坐标查询距离为distance
        BoolQueryBuilder boolQueryBuilder =boolQuery();

        boolQueryBuilder.mustNot(termQuery("building_name_id",buildingNameId));
        boolQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
        boolQueryBuilder.must(termQuery("is_del", IS_DEL));
        boolQueryBuilder.filter(QueryBuilders.geoDistanceQuery("location").point(lat,lon).distance(distance, DistanceUnit.METERS));
        searchSourceBuilder.query(boolQueryBuilder);
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        sort.point(lat, lon);
        searchSourceBuilder.sort(sort).fetchSource(
                new String[]{"building_name_id","building_name","average_price","city_id",
                        "district_id","district_name","area_id","area_name","building_imgs","total_price"},
                null);

        searchSourceBuilder.sort("build_level", SortOrder.ASC).sort("building_sort",SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        List<Map<String,Object>> nearBy = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String,Object> mapitem=new HashMap() ;
            mapitem = hit.getSourceAsMap();
            nearBy.add(mapitem);
        }
        return nearBy;
    }

    /**
     * 新房搜索找房
     *
     *
     *  */
   /* @Override
    public ArrayList<HashMap<String,Object>> searchNewHouse(String text) {
        ArrayList<HashMap<String,Object>> houseList = new ArrayList();
        try {
            QueryBuilder queryBuilder = null;
            TransportClient client = esClientTools.init();
            AnalyzeResponse response = esClientTools.init().admin().indices()
                    .prepareAnalyze(text)//内容
                    .setAnalyzer("ik_smart")//指定分词器3`3
                    //.setTokenizer("standard")
                    .execute().actionGet();//执行
            List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
            BoolQueryBuilder ww = QueryBuilders.boolQuery();
            for (AnalyzeResponse.AnalyzeToken analyzeToken :tokens) {

                queryBuilder = QueryBuilders.boolQuery()
                       // .should(QueryBuilders.fuzzyQuery("keywords", analyzeToken.getTerm()))
                        .should(QueryBuilders.fuzzyQuery("building_name", analyzeToken.getTerm()))
                        .should(QueryBuilders.fuzzyQuery("area_name", analyzeToken.getTerm()));
                ww.should(queryBuilder);
            }

            *//*ww = QueryBuilders.boolQuery()
                    .must(QueryBuilders.fuzzyQuery("area_name", text));*//*
            SearchResponse searchResponse = client.prepareSearch("beijing1")
                    .setTypes("building1")
                    .setQuery(ww)
                   *//* .addSort("houseRank", SortOrder.DESC)*//*
                    .setFrom(0)
                    .setSize(10)
                    .execute().actionGet();

            for (SearchHit hit : searchResponse.getHits().getHits()) {
                Map<String,Object> buildings = hit.getSourceAsMap();
                houseList.add((HashMap<String, Object>) buildings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houseList;
    }
*/
//    @Override
//    public void saveBuildingParent(NewHouseBuildings newHouseBuildings) {
//
////        TransportClient client = esClientTools.init();
//
//        JSONObject json = (JSONObject) JSONObject.toJSON(newHouseBuildings);
//        String id = String.valueOf(newHouseBuildings.getBuildingNameId());
//        IndexRequest indexRequest = new IndexRequest(newhouseIndex, newhouseType, id)
//                .version(newHouseBuildings.getVersion())
//                .versionType(VersionType.EXTERNAL.versionTypeForReplicationAndRecovery())
//                .source(json);
//        client.index(indexRequest).actionGet();
//
//
//
//    }

}
