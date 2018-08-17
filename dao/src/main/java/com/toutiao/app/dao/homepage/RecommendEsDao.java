package com.toutiao.app.dao.homepage;

import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   14:03
 * Theme:
 */
public interface RecommendEsDao {

//    /**
//     * 根据小区楼盘推荐标志查询
//     * @param boolQueryBuilder
//     * @return
//     */
//    SearchResponse getRecommendByRecommendBuildTags(BoolQueryBuilder boolQueryBuilder);
//
//    /**
//     * 根据房源标签查询
//     * @param boolQueryBuilder
//     * @return
//     */
//    SearchResponse getRecommendByRecommendHouseTags(BoolQueryBuilder boolQueryBuilder);



    /**
     * 根据小区楼盘推荐标志查询
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getRecommendByRecommendBuildTags(RecommendTopicDoQuery recommendTopicDoQuery,BoolQueryBuilder boolQueryBuilder);

    /**
     * 根据房源标签查询
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getRecommendByRecommendHouseTags(RecommendTopicDoQuery recommendTopicDoQuery,BoolQueryBuilder boolQueryBuilder);
}
