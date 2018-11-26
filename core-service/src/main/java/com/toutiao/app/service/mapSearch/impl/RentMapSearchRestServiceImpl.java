package com.toutiao.app.service.mapSearch.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.mapsearch.RentMapSearchEsDao;
import com.toutiao.app.domain.mapSearch.RentMapSearchDo;
import com.toutiao.app.domain.mapSearch.RentMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.RentMapSearchDomain;
import com.toutiao.app.domain.mapSearch.RentOfPlotListDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.service.mapSearch.RentMapSearchRestService;
import com.toutiao.app.service.rent.NearRentHouseRestService;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.mapSearch.MapGroupUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class RentMapSearchRestServiceImpl implements RentMapSearchRestService {

    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布
    private static final Integer RENT = 0;//出租:1
    private static final Integer FOCUS_APARTMENT = 2;//公寓:2
    private static final Integer DISPERSED_APARTMENTS = 1;//公寓:2
    private static final String LAYOUT = "4";
    //租房标识
    private final Integer FAVORITE_RENT = 1;


    @Autowired
    private RentRestService rentRestService;
    @Autowired
    private NearRentHouseRestService nearRentHouseRestService;
    @Autowired
    private RentMapSearchEsDao rentMapSearchEsDao;

    @Override
    public RentMapSearchDomain rentMapSearch(RentMapSearchDoQuery rentMapSearchDoQuery, String city) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
        SearchSourceBuilder Num = new SearchSourceBuilder().size(0);
        BoolQueryBuilder boolQueryBuilder = getBoolQueryBuilder(rentMapSearchDoQuery);
        Num.query(boolQueryBuilder);
        SearchResponse rentMapSearchNum = rentMapSearchEsDao.getRentMapSearch(Num, city);
        Integer totalNum = 0;
        if (null!=rentMapSearchNum){
            totalNum = (int)rentMapSearchNum.getHits().getTotalHits();
        }


        //判断是矩形查询还是中心点半径查询
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())){
            FunctionScoreQueryBuilder query = null;
            //设置基础分(录入优先展示)(录入:1,导入1/3)
            FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("rentHouseTypeId")
                    .modifier(FieldValueFactorFunction.Modifier.RECIPROCAL).missing(10);

            GaussDecayFunctionBuilder functionBuilder = null;
            GeoDistanceSortBuilder sort = null;
            if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                double[] location = new double[]{rentMapSearchDoQuery.getCenterLon(), rentMapSearchDoQuery.getCenterLat()};

                //设置高斯函数(要保证5km内录入的排在导入的前面,录入房源的最低分需要大于导入的最高分)
                functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("location", location, "4km", "1km", 0.4);
                //根据坐标计算距离

                sort = SortBuilders.geoDistanceSort("location", rentMapSearchDoQuery.getCenterLat(), rentMapSearchDoQuery.getCenterLon());
                sort.unit(DistanceUnit.KILOMETERS);
                sort.geoDistance(GeoDistance.ARC);
            }

            //获取5km内的所有出租房源(函数得分进行加法运算,查询得分和函数得分进行乘法运算)
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder, fieldValueFactor).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);


            if (StringUtil.isNotNullString(rentMapSearchDoQuery.getKeyword())) {
                List<String> searchKeyword = nearRentHouseRestService.getAnalyzeByKeyWords(rentMapSearchDoQuery.getKeyword(), city);
                FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
                if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                    filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size() + 1];
                } else {
                    filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
                }
                if (StringUtil.isNotNullString(AreaMap.getAreas(rentMapSearchDoQuery.getKeyword()))) {
                    int searchAreasSize = searchKeyword.size();
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize - i);
                        QueryBuilder filter = QueryBuilders.termsQuery("area_name", searchKeyword.get(i));
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentMapSearchDoQuery.getKeyword()))) {
                    int searchDistrictsSize = searchKeyword.size();
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                        QueryBuilder filter = QueryBuilders.termsQuery("district_name", searchKeyword.get(i));
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                } else {
                    int searchTermSize = searchKeyword.size();
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                        QueryBuilder filter = QueryBuilders.termsQuery("zufang_name_search", searchKeyword.get(i));
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }

                if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                    filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                    query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
                } else {
                    query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
                }


            } else {
                if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                    query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, functionBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
                } else {
                    query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
                }
            }

            Integer pageSize = rentMapSearchDoQuery.getPageSize();
            Integer pageNum = rentMapSearchDoQuery.getPageNum();

            if(StringTool.isNotEmpty(rentMapSearchDoQuery.getKeyword())){
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(sort);
            }else{
                searchSourceBuilder.query(query).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            }

        }else if(ArrayUtils.isNotEmpty(rentMapSearchDoQuery.getSubwayStationId())&&rentMapSearchDoQuery.getSubwayStationId().length>1){
            //对地铁站做聚合
//            searchSourceBuilder.aggregation(AggregationBuilders.terms("").field("").)


        }else{
            GeoBoundingBoxQueryBuilder location = geoBoundingBoxQuery("location").setCorners(rentMapSearchDoQuery.getTopLeftLat(), rentMapSearchDoQuery.getTopLeftLon(), rentMapSearchDoQuery.getBottomRightLat(), rentMapSearchDoQuery.getBottomRightLon());
            boolQueryBuilder.must(location);
            searchSourceBuilder.query(boolQueryBuilder);
        }

        //groupTypeId 1：区县，2：商圈，3：社区
        Integer groupTypeId = null != MapGroupUtil.returnMapGrouId(rentMapSearchDoQuery.getGroupType()) ? MapGroupUtil.returnMapGrouId(rentMapSearchDoQuery.getGroupType()) : 0;
        if (groupTypeId == 1) {
            //聚合
            searchSourceBuilder.aggregation(AggregationBuilders.terms("id").field("district_id").size(200));
        }
        if (groupTypeId == 2) {
            //聚合
            searchSourceBuilder.aggregation(AggregationBuilders.terms("id").field("area_id").size(200));
        }
        if (groupTypeId == 3) {
            //聚合
            searchSourceBuilder.aggregation(AggregationBuilders.terms("id").field("zufang_id").size(200)
            .subAggregation(AggregationBuilders.terms("zufangName").field("zufang_name"))
            .subAggregation(AggregationBuilders.terms("lon").field("cummunity_longitude"))
            .subAggregation(AggregationBuilders.terms("lat").field("cummunity_latitude")));
        }

        SearchResponse rentMapSearch = rentMapSearchEsDao.getRentMapSearch(searchSourceBuilder, city);


        List<RentMapSearchDo> list = new ArrayList<>();
        Integer totalHits = 0;
        if (null!=rentMapSearch){
            totalHits = (int)rentMapSearch.getHits().getTotalHits();
            Terms ID = rentMapSearch.getAggregations().get("id");
            List buckets = ID.getBuckets();
            for (Object bucket : buckets){
                RentMapSearchDo rentMapSearchDo = new RentMapSearchDo();

                //区域
                if(groupTypeId == 1){
                    //id
                    int id = ((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue();
                    rentMapSearchDo.setId(id);
                    //数量
                    rentMapSearchDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());
                    Map distanceAndAreainfo = getDistanceAndAreainfo(id, 1);
                    if(null!=distanceAndAreainfo&&StringTool.isNotEmpty(distanceAndAreainfo.get("name"))&&StringTool.isNotEmpty(distanceAndAreainfo.get("lon"))&&StringTool.isNotEmpty(distanceAndAreainfo.get("lat"))){
                        rentMapSearchDo.setName(String.valueOf(distanceAndAreainfo.get("name")));
                        rentMapSearchDo.setLongitude(Double.valueOf(String.valueOf(distanceAndAreainfo.get("lon"))));
                        rentMapSearchDo.setLatitude(Double.valueOf(String.valueOf(distanceAndAreainfo.get("lat"))));
                    }
                }
                //商圈
                if(groupTypeId == 2){
                    //id
                    int id = ((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue();
                    rentMapSearchDo.setId(id);
                    //数量
                    rentMapSearchDo.setCount((int)((ParsedLongTerms.ParsedBucket) bucket).getDocCount());
                    Map distanceAndAreainfo = getDistanceAndAreainfo(id, 2);
                    if(null!=distanceAndAreainfo&&StringTool.isNotEmpty(distanceAndAreainfo.get("name"))&&StringTool.isNotEmpty(distanceAndAreainfo.get("lon"))&&StringTool.isNotEmpty(distanceAndAreainfo.get("lat"))){
                        rentMapSearchDo.setName(String.valueOf(distanceAndAreainfo.get("name")));
                        rentMapSearchDo.setLongitude(Double.valueOf(String.valueOf(distanceAndAreainfo.get("lon"))));
                        rentMapSearchDo.setLatitude(Double.valueOf(String.valueOf(distanceAndAreainfo.get("lat"))));
                    }
                }
                //社区
                if(groupTypeId == 3){
                    //id
                    int id = Integer.parseInt(((ParsedStringTerms.ParsedBucket) bucket).getKeyAsString());
                    rentMapSearchDo.setId(id);
                    //数量
                    rentMapSearchDo.setCount((int)((ParsedStringTerms.ParsedBucket) bucket).getDocCount());
                    //名称
                    Terms district_name = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("zufangName");
                    List buckets1 = district_name.getBuckets();
                    for (Object bucket1:buckets1){
                        String keyAsString = ((ParsedStringTerms.ParsedBucket) bucket1).getKeyAsString();
                        if(StringTool.isNotEmpty(keyAsString)){
                            rentMapSearchDo.setName(keyAsString);
                        }
                    }
                    //坐标
                    Terms lat = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("lat");
                    Terms lon = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("lon");
                    rentMapSearchDo.setLatitude(Double.valueOf(lat.getBuckets().get(0).getKeyAsString()));
                    rentMapSearchDo.setLongitude(Double.valueOf(lon.getBuckets().get(0).getKeyAsString()));
                }
                if(StringTool.isNotEmpty(rentMapSearchDo.getId())&&StringTool.isNotEmpty(rentMapSearchDo.getName())&&StringTool.isNotEmpty(rentMapSearchDo.getLatitude())&&StringTool.isNotEmpty(rentMapSearchDo.getLongitude())){
                    list.add(rentMapSearchDo);
                }
            }

        }
        RentMapSearchDomain rentMapSearchDomain = new RentMapSearchDomain();
        rentMapSearchDomain.setData(list);
        rentMapSearchDomain.setHit("可视范围内"+ String.valueOf(totalHits)+"套房源，共"+ String.valueOf(totalNum)+"房源");
        return rentMapSearchDomain;
    }

    @Override
    public Map getDistanceAndAreainfo(Integer id, Integer type) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Map map = new HashMap();
        //1:区域, 2:商圈
        if(type==1){
            boolQueryBuilder.must(QueryBuilders.termQuery("district_id",id));
            searchSourceBuilder.query(boolQueryBuilder);
            SearchResponse distanceAndAreainfo = rentMapSearchEsDao.getDistanceAndAreainfo(searchSourceBuilder);
            SearchHit[] hits = distanceAndAreainfo.getHits().getHits();
            if (hits.length>0){
                Map<String, Object> sourceAsMap = hits[0].getSourceAsMap();
                map.put("name",sourceAsMap.get("district_name"));
                map.put("lat",sourceAsMap.get("district_latitude"));
                map.put("lon",sourceAsMap.get("district_longitude"));
                map.put("price",sourceAsMap.get("district_avgprice"));
            }
        }
        if(type==2){
            boolQueryBuilder.must(QueryBuilders.termQuery("bizcircle_id",id));
            searchSourceBuilder.query(boolQueryBuilder);
            SearchResponse distanceAndAreainfo = rentMapSearchEsDao.getDistanceAndAreainfo(searchSourceBuilder);
            SearchHit[] hits = distanceAndAreainfo.getHits().getHits();
            if (hits.length>0){
                Map<String, Object> sourceAsMap = hits[0].getSourceAsMap();
                map.put("name",sourceAsMap.get("bizcircle_name"));
                map.put("lat",sourceAsMap.get("bizcircle_latitude"));
                map.put("lon",sourceAsMap.get("bizcircle_longitude"));
                map.put("price",sourceAsMap.get("bizcircle_avgprice"));
            }
        }


        return map;
    }

    @Override
    public RentOfPlotListDo getRentOfPlot(RentMapSearchDoQuery rentMapSearchDoQuery, String city) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = getBoolQueryBuilder(rentMapSearchDoQuery);

        FunctionScoreQueryBuilder query = null;
        //设置基础分(录入优先展示)(录入:1,导入1/3)
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("rentHouseTypeId")
                .modifier(FieldValueFactorFunction.Modifier.RECIPROCAL).missing(10);

        GaussDecayFunctionBuilder functionBuilder = null;
        GeoDistanceSortBuilder sort = null;
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
            double[] location = new double[]{rentMapSearchDoQuery.getCenterLon(), rentMapSearchDoQuery.getCenterLat()};

            //设置高斯函数(要保证5km内录入的排在导入的前面,录入房源的最低分需要大于导入的最高分)
            functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("location", location, "4km", "1km", 0.4);
            //根据坐标计算距离

            sort = SortBuilders.geoDistanceSort("location", rentMapSearchDoQuery.getCenterLat(), rentMapSearchDoQuery.getCenterLon());
            sort.unit(DistanceUnit.KILOMETERS);
            sort.geoDistance(GeoDistance.ARC);
        }

        //获取5km内的所有出租房源(函数得分进行加法运算,查询得分和函数得分进行乘法运算)
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder, fieldValueFactor).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);


        if (StringUtil.isNotNullString(rentMapSearchDoQuery.getKeyword())) {
            List<String> searchKeyword = nearRentHouseRestService.getAnalyzeByKeyWords(rentMapSearchDoQuery.getKeyword(), city);
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
            if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size() + 1];
            } else {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(rentMapSearchDoQuery.getKeyword()))) {
                int searchAreasSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentMapSearchDoQuery.getKeyword()))) {
                int searchDistrictsSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("district_name", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else {
                int searchTermSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("zufang_name_search", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }

            if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            } else {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, filterFunctionBuilders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            }


        } else {
            if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance())) {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder, functionBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            } else {
                query = QueryBuilders.functionScoreQuery(functionScoreQueryBuilder).scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
            }
        }

        Integer pageSize = rentMapSearchDoQuery.getPageSize();
        Integer pageNum = rentMapSearchDoQuery.getPageNum();

        if(StringTool.isNotEmpty(rentMapSearchDoQuery.getKeyword())){
            searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(sort);
        }else{
            searchSourceBuilder.query(query).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        }

        SearchResponse rentMapSearch = rentMapSearchEsDao.getRentMapSearch(searchSourceBuilder, city);
        RentOfPlotListDo rentOfPlotListDo = new RentOfPlotListDo();
        if(null!=rentMapSearch){
            long totalHits = rentMapSearch.getHits().getTotalHits();
            SearchHit[] hits = rentMapSearch.getHits().getHits();
            if (hits.length>0){

                List<RentDetailsFewDo> list = new ArrayList<>();
                for (SearchHit hit:hits){
                    String sourceAsString = hit.getSourceAsString();
                    RentDetailsFewDo rentDetailsFewDo = JSON.parseObject(sourceAsString, RentDetailsFewDo.class);
                    list.add(rentDetailsFewDo);
                }
                rentOfPlotListDo.setData(list);
                rentOfPlotListDo.setTotalNum((int) totalHits);
                rentOfPlotListDo.setNearSubwayLine((String) hits[0].getSourceAsMap().get("nearestSubwayLine"));
            }
        }
        return rentOfPlotListDo;
    }


    public BoolQueryBuilder getBoolQueryBuilder(RentMapSearchDoQuery rentMapSearchDoQuery) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("rentHouseType", "3"));//目前只展示导入的房源

        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getNewcode())){
            boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",rentMapSearchDoQuery.getNewcode()));
        }

        //关键字
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getKeyword())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentMapSearchDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("district_name_search", rentMapSearchDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
//                        .should(QueryBuilders.matchQuery("zufang_name_search", rentHouseDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart"));
            } else if (StringUtil.isNotNullString(AreaMap.getAreas(rentMapSearchDoQuery.getKeyword()))) {
                queryBuilder
                        .should(QueryBuilders.matchQuery("area_name_search", rentMapSearchDoQuery.getKeyword()).operator(Operator.AND).analyzer("ik_smart").boost(2));
            } else {
                queryBuilder
                        .should(QueryBuilders.matchQuery("zufang_name", rentMapSearchDoQuery.getKeyword()).operator(Operator.AND).boost(2))
                        .should(QueryBuilders.matchQuery("zufang_nickname", rentMapSearchDoQuery.getKeyword()).fuzziness("AUTO").operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("area_name_search", rentMapSearchDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("district_name_search", rentMapSearchDoQuery.getKeyword()).operator(Operator.AND))
                        .should(QueryBuilders.matchQuery("zufang_name_search", rentMapSearchDoQuery.getKeyword()).operator(Operator.AND));
            }
            boolQueryBuilder.must(queryBuilder);
        }

        //附近房源/楼盘
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistance()) && StringTool.isNotEmpty(rentMapSearchDoQuery.getCenterLat()) && StringTool.isNotEmpty(rentMapSearchDoQuery.getCenterLon())) {
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                    .point(rentMapSearchDoQuery.getCenterLat(), rentMapSearchDoQuery.getCenterLon())
                    .distance(rentMapSearchDoQuery.getDistance(), DistanceUnit.KILOMETERS);
            boolQueryBuilder.must(location);
        }

        //城市
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getCityId())) {
            boolQueryBuilder.must(termQuery("city_id", rentMapSearchDoQuery.getCityId()));
        }

        //区域
//        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getDistrictId())) {
//            boolQueryBuilder.must(termQuery("district_id", rentMapSearchDoQuery.getDistrictId()));
//        }

        //商圈
//        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getAreaId())) {
//            boolQueryBuilder.must(termsQuery("area_id", rentMapSearchDoQuery.getAreaId()));
//        }

        //地铁线id
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getSubwayLineId())) {
            boolQueryBuilder.must(termQuery("subway_line_id", rentMapSearchDoQuery.getSubwayLineId()));
        }

        //地铁站id
//        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getSubwayStationId())) {
//            boolQueryBuilder.must(termsQuery("subway_station_id", rentMapSearchDoQuery.getSubwayStationId()));
//        }

        //租金
        if (StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getBeginPrice()) && StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getEndPrice())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(rentMapSearchDoQuery.getBeginPrice()).lte(rentMapSearchDoQuery.getEndPrice()));
        } else if (StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getBeginPrice()) && StringTool.isDoubleEmpty(rentMapSearchDoQuery.getEndPrice())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").gte(rentMapSearchDoQuery.getBeginPrice()));
        } else if (StringTool.isDoubleEmpty(rentMapSearchDoQuery.getBeginPrice()) && StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getEndPrice())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("rent_house_price").lte(rentMapSearchDoQuery.getEndPrice()));
        }

//        //整租/合租
//        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getRentType())){
//            String[] split = rentMapSearchDoQuery.getRentType().split(",");
//            boolQueryBuilder.must(QueryBuilders.termsQuery("rent_type", split));
//        }
//        //几居
//        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getLayoutId())){
//            Integer[] split = rentMapSearchDoQuery.getLayoutId();
//            boolQueryBuilder.must(QueryBuilders.termsQuery("room", split));
//        }

        //户型
        if (StringTool.isNotBlank(rentMapSearchDoQuery.getElo()) && !StringTool.isNotBlank(rentMapSearchDoQuery.getJlo())) {

            if (rentMapSearchDoQuery.getElo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("erent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentMapSearchDoQuery.getElo().split(",");
                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("erent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("erent_layout", room));
                }
            }

        } else if (!StringTool.isNotBlank(rentMapSearchDoQuery.getElo()) && StringTool.isNotBlank(rentMapSearchDoQuery.getJlo())) {
            if (rentMapSearchDoQuery.getJlo().equals("0")) {
                boolQueryBuilder.must(rangeQuery("jrent_layout").gt(0));
            } else {
                String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
                String[] room = rentMapSearchDoQuery.getJlo().split(",");

                boolean roomflag = Arrays.asList(room).contains(LAYOUT);
                if (roomflag) {
                    String[] roomresult = (String[]) ArrayUtils.addAll(room, roommore);
                    boolQueryBuilder.must(termsQuery("jrent_layout", roomresult));
                } else {
                    boolQueryBuilder.must(termsQuery("jrent_layout", room));
                }
            }

        } else if (StringTool.isNotBlank(rentMapSearchDoQuery.getElo()) && StringTool.isNotBlank(rentMapSearchDoQuery.getJlo())) {
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            String[] roommore = new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            if (rentMapSearchDoQuery.getJlo().equals("0") && rentMapSearchDoQuery.getElo().equals("0")) {
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (rentMapSearchDoQuery.getElo().equals("0") && !rentMapSearchDoQuery.getJlo().equals("0")) {
                String[] jroom = rentMapSearchDoQuery.getJlo().split(",");
                boolQueryBuilder1.should(rangeQuery("erent_layout").gt(0));

                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else if (!rentMapSearchDoQuery.getElo().equals("0") && rentMapSearchDoQuery.getJlo().equals("0")) {
                String[] eroom = rentMapSearchDoQuery.getElo().split(",");
                boolQueryBuilder1.should(rangeQuery("jrent_layout").gt(0));

                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            } else {
                String[] eroom = rentMapSearchDoQuery.getElo().split(",");
                String[] jroom = rentMapSearchDoQuery.getJlo().split(",");

                //String[] roommore = new String[]{"4","5","6","7","8","9","10","11","12","13","14"};
                boolean jroomflag = Arrays.asList(jroom).contains(LAYOUT);
                boolean eroomflag = Arrays.asList(eroom).contains(LAYOUT);
                if (jroomflag) {
                    String[] jroomresult = (String[]) ArrayUtils.addAll(jroom, roommore);
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("jrent_layout", jroom));
                }
                if (eroomflag) {
                    String[] eroomresult = (String[]) ArrayUtils.addAll(eroom, roommore);
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroomresult));
                } else {
                    boolQueryBuilder1.should(termsQuery("erent_layout", eroom));
                }
                boolQueryBuilder.must(boolQueryBuilder1);
            }

        }

        //朝向
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getForwardId())) {
            Integer[] forword = rentMapSearchDoQuery.getForwardId();
            boolQueryBuilder.must(QueryBuilders.termsQuery("forward_type", forword));
        }

        //标签
        if (StringTool.isNotEmpty(rentMapSearchDoQuery.getLabelId())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("rent_house_tags_id", rentMapSearchDoQuery.getLabelId()));
        }

        //面积
        if (StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getBeginArea())&&StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getEndArea())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").lte(rentMapSearchDoQuery.getEndArea()).gte(rentMapSearchDoQuery.getBeginArea()));
        }else if (StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getBeginArea())&&StringTool.isDoubleEmpty(rentMapSearchDoQuery.getEndArea())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").gte(rentMapSearchDoQuery.getBeginArea()));
        }else if (StringTool.isDoubleEmpty(rentMapSearchDoQuery.getBeginArea())&&StringTool.isDoubleNotEmpty(rentMapSearchDoQuery.getEndArea())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("house_area").lte(rentMapSearchDoQuery.getEndArea()));
        }

        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", 0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", 1));

        return boolQueryBuilder;
    }
}
