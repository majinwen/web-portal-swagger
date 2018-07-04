package com.toutiao.app.dao.sellhouse;


import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface SellHouseDetailTopicsEsDao {


    /**
     * 小区附近二手房
     * @param boolQueryBuilder
     * @param searchAfterIds
     * @param pageSize
     * @return
     */
    SearchResponse getNearbyTopicsSellHouse(BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds, Integer pageSize);


    /**
     * 降价房
     * @param sellHouseDoQuery
     * @param boolQueryBuilder
     * @param searchAfterIds
     * @return
     */
    SearchResponse getCutPriceTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds);


    /**
     * 洼地房
     * @param sellHouseDoQuery
     * @param boolQueryBuilder
     * @param searchAfterIds
     * @return
     */
    SearchResponse getLowPriceTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds);


    /**
     * 逢出毕抢
     * @param sellHouseDoQuery
     * @param boolQueryBuilder
     * @param searchAfterIds
     * @return
     */
    SearchResponse getMustRobTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds);
}



