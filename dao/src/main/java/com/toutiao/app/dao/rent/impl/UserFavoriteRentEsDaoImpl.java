package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.UserFavoriteRentEsDao;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Service
public class UserFavoriteRentEsDaoImpl implements UserFavoriteRentEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse queryRentListByUserFavorite(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer pageNum, Integer pageSize, String city, GeoDistanceSortBuilder geoDistanceSort, String sort) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if ((null != keyword && !"".equals(keyword)) || (null != distance && distance > 0)) {
            //searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(geoDistanceSort);
            if ("1".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("update_time", SortOrder.DESC).sort(geoDistanceSort);
            } else if ("3".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("rent_house_price", SortOrder.ASC).sort(geoDistanceSort);
            } else if ("4".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("rent_house_price", SortOrder.DESC).sort(geoDistanceSort);
            } else if ("6".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("house_area", SortOrder.DESC).sort(geoDistanceSort);
            } else {
                if (StringTool.isNotEmpty(geoDistanceSort)) {
                    searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(geoDistanceSort);
                } else {
                    searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
                }
            }
        } else {
            //searchSourceBuilder.query(query).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            if ("1".equals(sort)) {
                searchSourceBuilder.query(query).sort("update_time", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else if ("3".equals(sort)) {
                searchSourceBuilder.query(query).sort("rent_house_price", SortOrder.ASC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else if ("4".equals(sort)) {
                searchSourceBuilder.query(query).sort("rent_house_price", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else if ("6".equals(sort)) {
                searchSourceBuilder.query(query).sort("house_area", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else {
                searchSourceBuilder.query(query).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            }
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
