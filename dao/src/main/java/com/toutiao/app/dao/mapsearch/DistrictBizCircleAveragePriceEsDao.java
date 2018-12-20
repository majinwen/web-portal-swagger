package com.toutiao.app.dao.mapsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * @ClassName DistricrBizCircleAveragePriceEsDao
 * @Author jiangweilong
 * @Date 2018/11/25 12:54 PM
 * @Description:
 **/
public interface DistrictBizCircleAveragePriceEsDao {


    SearchResponse getLocationByDistrictId(BoolQueryBuilder boolQueryBuilder , String city);

    SearchResponse getLocationByBizCircleId(BoolQueryBuilder boolQueryBuilder , String city);
}
