package com.toutiao.app.service.mapSearch.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.mapsearch.NewHouseMapSearchEsDao;
import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import com.toutiao.app.domain.mapSearch.*;
import com.toutiao.app.service.mapSearch.NewHouseMapSearchRestService;
import com.toutiao.web.common.util.StringTool;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

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
