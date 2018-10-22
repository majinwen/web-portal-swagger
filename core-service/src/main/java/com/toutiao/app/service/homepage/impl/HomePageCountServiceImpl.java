package com.toutiao.app.service.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageCountEsDao;
import com.toutiao.app.domain.homepage.HomePageEsfCountDo;
import com.toutiao.app.domain.homepage.HomePageNewCountDo;
import com.toutiao.app.service.homepage.HomePageCountService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by CuiShihao on 2018/10/19
 */
@Service
public class HomePageCountServiceImpl implements HomePageCountService {

    @Autowired
    private HomePageCountEsDao countEsDao;

    //获取新房相应数量
    @Override
    public HomePageNewCountDo getNewCount(String city) {
        HomePageNewCountDo homePageNewCountDo = new HomePageNewCountDo();
        SearchResponse response = countEsDao.getNewCount(city);
        SearchHits searchHits = response.getHits();
        homePageNewCountDo.setBuildingCount(searchHits.totalHits);
        Map map = response.getAggregations().asMap();
        Terms terms = (Terms) map.get("buildingCount");
        Terms.Bucket onSaleBucket = terms.getBucketByKey("1");
        homePageNewCountDo.setOnsaleCount(onSaleBucket.getDocCount());
        Terms.Bucket forthBucket = terms.getBucketByKey("5");
        homePageNewCountDo.setForthcomingCount(forthBucket.getDocCount());
        Terms terms2 = (Terms) map.get("preferentialCount");
        Terms.Bucket activeBucket = terms2.getBucketByKey("1");
        homePageNewCountDo.setPreferentialCount(activeBucket.getDocCount());
        return homePageNewCountDo;
    }

    /**
     * 获取二手房相应数量
     *
     * @param city
     * @return
     */
    @Override
    public HomePageEsfCountDo getEsfCount(String city) {
        HomePageEsfCountDo homePageEsfCountDo = new HomePageEsfCountDo();
        SearchResponse response = countEsDao.getEsfCount(city);
        SearchHits searchHits = response.getHits();
        homePageEsfCountDo.setForSaleCount(searchHits.getTotalHits());
        Map map = response.getAggregations().asMap();
        Terms terms = (Terms) map.get("reduceCount");
        Terms.Bucket reduceBucket = terms.getBucketByKey("1");
        homePageEsfCountDo.setReduceCount(reduceBucket.getDocCount());
        Terms terms2 = (Terms) map.get("hotSaleCount");
        Terms.Bucket hotBucket = terms2.getBucketByKey("1");
        homePageEsfCountDo.setHotSaleCount(hotBucket.getDocCount());
        Terms terms3 = (Terms) map.get("missingCount");
        Terms.Bucket missBucket = terms3.getBucketByKey("1");
        homePageEsfCountDo.setMissingCount(missBucket.getDocCount());
        return homePageEsfCountDo;
    }
}
