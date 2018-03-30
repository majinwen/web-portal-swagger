package com.toutiao.web.dao.advertisement;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface AggAdLandingDao {


    /**
     * 广告推荐
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getRecommendSellHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageSize);


//    SearchResponse getClaimSellHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum,Integer pageSize);


    SearchResponse getClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum,Integer pageSize);


    SearchResponse getUnClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum,Integer pageSize);



}
