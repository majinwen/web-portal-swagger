package com.toutiao.app.service.mapSearch.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.mapsearch.NewHouseMapSearchEsDao;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.mapSearch.*;
import com.toutiao.app.service.mapSearch.NewHouseMapSearchRestService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.*;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @ClassName NewHouseMapSearchRestServiceImpl
 * @Author jiangweilong
 * @Date 2018/11/24 7:17 PM
 * @Description:
 **/
@Service
public class NewHouseMapSearchRestServiceImpl implements NewHouseMapSearchRestService {

    @Autowired
    private NewHouseMapSearchEsDao newHouseMapSearchEsDao;
    @Autowired
    private NewHouseEsDao newHouseEsDao;

    @Override
    public NewHouseMapSearchDistrictDomain newHouseMapSearchByDistrict(NewHouseMapSearchDoQuery newHouseMapSearchDoQuery, String city) {

        NewHouseMapSearchDistrictDomain newHouseMapSearchDistrictDomain = new NewHouseMapSearchDistrictDomain();
        List<NewHouseMapSearchDistrictDo> data = new ArrayList<>();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termQuery("is_approve", 1));
        boolQueryBuilder.must(termQuery("is_del", 0));
        boolQueryBuilder.must(termsQuery("property_type_id", new int[]{1, 2}));
        GeoPoint topRight = new GeoPoint(newHouseMapSearchDoQuery.getMaxLatitude(),newHouseMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(newHouseMapSearchDoQuery.getMinLatitude(),newHouseMapSearchDoQuery.getMinLongitude());
        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("location").setCornersOGC(bottomLeft, topRight);
        boolQueryBuilder.must(geoBoundingBoxQueryBuilder);
        SearchResponse build = newHouseEsDao.getBuildCount(boolQueryBuilder, city);
        long totalHits = build.getHits().totalHits;
        SearchResponse searchResponse = newHouseMapSearchEsDao.NewHouseMapSearchByDistrict(boolQueryBuilder, city);
        Terms buildCount = searchResponse.getAggregations().get("buildCount");
        List buckets= buildCount.getBuckets();
        for (Object bucket : buckets){

            NewHouseMapSearchDistrictDo newHouseMapSearchDistrictDo = new NewHouseMapSearchDistrictDo();
            newHouseMapSearchDistrictDo.setId(((ParsedLongTerms.ParsedBucket) bucket).getKeyAsNumber().intValue());
            newHouseMapSearchDistrictDo.setCount((int) ((ParsedLongTerms.ParsedBucket) bucket).getDocCount());
            BoolQueryBuilder builder = new BoolQueryBuilder();
            builder.must(termQuery("district_id", newHouseMapSearchDistrictDo.getId()));
            SearchResponse newHouseMapByDbAvgPrice = newHouseMapSearchEsDao.getNewHouseMapByDbAvgPrice(builder, city);
            Terms districtName = newHouseMapByDbAvgPrice.getAggregations().get("districtName");
            if(districtName.getBuckets().size() > 0){
                newHouseMapSearchDistrictDo.setName(districtName.getBuckets().get(0).getKeyAsString());
            }
            Terms latitude = newHouseMapByDbAvgPrice.getAggregations().get("latitude");
            if(latitude.getBuckets().size() > 0){
                newHouseMapSearchDistrictDo.setLatitude(latitude.getBuckets().get(0).getKeyAsNumber().doubleValue());
            }
            Terms longitude = newHouseMapByDbAvgPrice.getAggregations().get("longitude");
            if(longitude.getBuckets().size() > 0){
                newHouseMapSearchDistrictDo.setLongitude(longitude.getBuckets().get(0).getKeyAsNumber().doubleValue());
            }

            data.add(newHouseMapSearchDistrictDo);
        }
        newHouseMapSearchDistrictDomain.setHit("共"+totalHits+"个楼盘，拖动可查看全部");
        newHouseMapSearchDistrictDomain.setData(data);
        return newHouseMapSearchDistrictDomain;
    }

    @Override
    public NewHouseMapSearchBuildDomain newHouseMapSearchByBuild(NewHouseMapSearchDoQuery newHouseMapSearchDoQuery, String city) {

        List<NewHouseMapSearchBuildDo> data = new ArrayList<>();
        NewHouseMapSearchBuildDo newHouseMapSearchBuildDo = new NewHouseMapSearchBuildDo();
        NewHouseMapSearchBuildDomain newHouseMapSearchBuildDomain = new NewHouseMapSearchBuildDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termQuery("is_approve", 1));
        boolQueryBuilder.must(termQuery("is_del", 0));
        boolQueryBuilder.must(termsQuery("property_type_id", new int[]{1, 2}));

        QueryBuilder queryBuilder = null;
        if (StringUtil.isNotNullString(newHouseMapSearchDoQuery.getKeyword())) {
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(newHouseMapSearchDoQuery.getKeyword()))) {
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("district_name", newHouseMapSearchDoQuery.getKeyword()).analyzer("ik_smart")).tieBreaker(0.3f);
            } else {
                queryBuilder = QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("building_name", newHouseMapSearchDoQuery.getKeyword()).analyzer("ik_max_word").operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("building_nickname", newHouseMapSearchDoQuery.getKeyword()).fuzziness("AUTO").analyzer("ik_smart").operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("building_name_accurate", newHouseMapSearchDoQuery.getKeyword()).boost(2).operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("area_name", newHouseMapSearchDoQuery.getKeyword()).operator(Operator.AND))
                        .add(QueryBuilders.matchQuery("district_name", newHouseMapSearchDoQuery.getKeyword()).operator(Operator.AND)).tieBreaker(0.3f);
            }

            boolQueryBuilder.must(queryBuilder);
        }

        //区域
        if (newHouseMapSearchDoQuery.getDistrictId() != null && newHouseMapSearchDoQuery.getDistrictId() != 0) {
            boolQueryBuilder.must(termQuery("district_id", newHouseMapSearchDoQuery.getDistrictId()));
        }

        //地铁线id
        String keys = "";
        if (newHouseMapSearchDoQuery.getSubwayLineId() != null && newHouseMapSearchDoQuery.getSubwayLineId() != 0) {
            boolQueryBuilder.must(termsQuery("subway_line_id", new int[]{newHouseMapSearchDoQuery.getSubwayLineId()}));
            keys = newHouseMapSearchDoQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if (newHouseMapSearchDoQuery.getSubwayStationId() != null) {
            boolQueryBuilder.must(termsQuery("subway_station_id", newHouseMapSearchDoQuery.getSubwayStationId()));
        }
        //均价
        if(StringTool.isNotEmpty(newHouseMapSearchDoQuery.getBeginPrice()) && StringTool.isNotEmpty(newHouseMapSearchDoQuery.getEndPrice()) ){
            if (newHouseMapSearchDoQuery.getBeginPrice() != 0 && newHouseMapSearchDoQuery.getEndPrice() != 0) {
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseMapSearchDoQuery.getBeginPrice()).lte(newHouseMapSearchDoQuery.getEndPrice())));
            } else if (newHouseMapSearchDoQuery.getBeginPrice() == 0 && newHouseMapSearchDoQuery.getEndPrice() != 0) {
                newHouseMapSearchDoQuery.setBeginPrice(0.0);
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseMapSearchDoQuery.getBeginPrice()).lte(newHouseMapSearchDoQuery.getEndPrice())));
            } else if (newHouseMapSearchDoQuery.getEndPrice() == 0 && newHouseMapSearchDoQuery.getBeginPrice() != 0) {
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("average_price").gte(newHouseMapSearchDoQuery.getBeginPrice())));
            }
        }

        if(StringTool.isNotEmpty(newHouseMapSearchDoQuery.getBeginTotalPrice()) && StringTool.isNotEmpty(newHouseMapSearchDoQuery.getEndTotalPrice()) ){
            if (newHouseMapSearchDoQuery.getBeginTotalPrice() != 0 && newHouseMapSearchDoQuery.getEndTotalPrice() != 0) {
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseMapSearchDoQuery.getBeginTotalPrice()).lte(newHouseMapSearchDoQuery.getEndTotalPrice())));
            } else if (newHouseMapSearchDoQuery.getBeginTotalPrice() == 0 && newHouseMapSearchDoQuery.getEndTotalPrice() != 0) {
                newHouseMapSearchDoQuery.setBeginTotalPrice(0.0);
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseMapSearchDoQuery.getBeginTotalPrice()).lte(newHouseMapSearchDoQuery.getEndTotalPrice())));
            } else if (newHouseMapSearchDoQuery.getEndTotalPrice() == 0 && newHouseMapSearchDoQuery.getBeginTotalPrice() != 0) {
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseMapSearchDoQuery.getBeginTotalPrice())));
            }
        }


        //标签
        if (null != newHouseMapSearchDoQuery.getLabelId() && newHouseMapSearchDoQuery.getLabelId().length != 0) {
            Integer[] longs = newHouseMapSearchDoQuery.getLabelId();
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            for (int i = 0; i < longs.length; i++) {
                if (longs[i].equals(1)) {
                    builder.must(QueryBuilders.termQuery("has_subway", longs[i]));
                } else {
                    builder.must(QueryBuilders.termQuery("building_tags_id", longs[i]));
                }
            }
            boolQueryBuilder.must(builder);
        }

        //户型
        if (newHouseMapSearchDoQuery.getLayoutId() != null && newHouseMapSearchDoQuery.getLayoutId().length != 0) {
            Integer[] longs = newHouseMapSearchDoQuery.getLayoutId();
            boolQueryBuilder.must(JoinQueryBuilders.hasChildQuery(ElasticCityUtils.NEWHOUSE_CHILD_NAME, QueryBuilders.termsQuery("room", longs), ScoreMode.None));
        }

        //面积
        if(StringTool.isNotEmpty(newHouseMapSearchDoQuery.getBeginArea()) && StringTool.isNotEmpty(newHouseMapSearchDoQuery.getEndArea())){
            if (newHouseMapSearchDoQuery.getBeginArea() != 0 && newHouseMapSearchDoQuery.getEndArea() != 0) {
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseMapSearchDoQuery.getEndArea())));
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseMapSearchDoQuery.getBeginArea())));
            } else if (newHouseMapSearchDoQuery.getBeginArea() == 0 && newHouseMapSearchDoQuery.getEndArea() != 0) {

                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_max_area").lte(newHouseMapSearchDoQuery.getEndArea())));

            } else if (newHouseMapSearchDoQuery.getEndArea() == 0 && newHouseMapSearchDoQuery.getBeginArea() != 0) {
                boolQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("house_min_area").gte(newHouseMapSearchDoQuery.getBeginArea())));
            }
        }

        //销售状态
        if (StringTool.isNotEmpty(newHouseMapSearchDoQuery.getSaleStatusId()) && newHouseMapSearchDoQuery.getSaleStatusId().length > 0) {
            Integer[] longs = newHouseMapSearchDoQuery.getSaleStatusId();
            boolQueryBuilder.must(termsQuery("sale_status_id", longs));
        } else {
            boolQueryBuilder.must(termsQuery("sale_status_id", new int[]{0, 1, 5, 6}));
        }










        SearchResponse build = newHouseEsDao.getBuildCount(boolQueryBuilder, city);
        long totalHits = build.getHits().totalHits;
        GeoPoint topRight = new GeoPoint(newHouseMapSearchDoQuery.getMaxLatitude(),newHouseMapSearchDoQuery.getMaxLongitude());
        GeoPoint bottomLeft = new GeoPoint(newHouseMapSearchDoQuery.getMinLatitude(),newHouseMapSearchDoQuery.getMinLongitude());
        GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("location").setCornersOGC(bottomLeft, topRight);
        boolQueryBuilder.must(geoBoundingBoxQueryBuilder);

        SearchResponse searchResponse = newHouseMapSearchEsDao.NewHouseMapSearchByBuild(boolQueryBuilder, city);
        long searchCount = searchResponse.getHits().totalHits;
        if(searchResponse.getHits().totalHits > 0){
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            for(SearchHit searchHit : searchHists){
                String details = searchHit.getSourceAsString();
                newHouseMapSearchBuildDo = JSON.parseObject(details,NewHouseMapSearchBuildDo.class);
                if(newHouseMapSearchBuildDo.getAveragePrice() > 0){
                    newHouseMapSearchBuildDo.setPriceDesc("均价"+new BigDecimal(String.valueOf(newHouseMapSearchBuildDo.getAveragePrice())).stripTrailingZeros().toPlainString()+"元/平");
                } else if(newHouseMapSearchBuildDo.getTotalPrice() > 0){
                    newHouseMapSearchBuildDo.setPriceDesc("总价"+new BigDecimal(String.valueOf(newHouseMapSearchBuildDo.getTotalPrice())).stripTrailingZeros().toPlainString()+"元/套");
                } else {
                    newHouseMapSearchBuildDo.setPriceDesc("售价待定");
                }

                if(StringTool.isNotEmpty(newHouseMapSearchBuildDo.getBuildingTitleImg())){
                    newHouseMapSearchBuildDo.setBuildingTitleImg("http://s1.qn.toutiaofangchan.com/" + newHouseMapSearchBuildDo.getBuildingTitleImg() + "-dongfangdi1200x900");
                }
                String [] isActive = new String[1];
                if(newHouseMapSearchBuildDo.getIsActive()==1){
                    isActive[0] = "优惠楼盘";
                }
                String [] propertyType = new String[1];
                if(StringTool.isNotEmpty(newHouseMapSearchBuildDo.getPropertyType())){
                    propertyType[0] = newHouseMapSearchBuildDo.getPropertyType();
                }
                String [] aggArr = (String[]) ArrayUtils.addAll(isActive, propertyType);
                newHouseMapSearchBuildDo.setBuildingTags((String[]) ArrayUtils.addAll(aggArr, newHouseMapSearchBuildDo.getBuildingTags()));
                data.add(newHouseMapSearchBuildDo);
            }
        }
        newHouseMapSearchBuildDomain.setBuildDoList(data);
        newHouseMapSearchBuildDomain.setHit("可视范围内"+searchCount+"个楼盘，共"+totalHits+"个，拖动可查看全部");
        return newHouseMapSearchBuildDomain;
    }
}
