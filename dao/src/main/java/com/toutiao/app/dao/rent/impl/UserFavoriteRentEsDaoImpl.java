package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.UserFavoriteRentEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Service
public class UserFavoriteRentEsDaoImpl implements UserFavoriteRentEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Value("${bdw.subwayLineStation.index}")
    private String bdwSubwayLineStationIndex;
    @Value("${bdw.subwayLineStation.type}")
    private String bdwSubwayLineStationType;

    @Override
    public SearchResponse queryRentListByUserFavorite(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize, String city, String sort) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if ("1".equals(sort)) {
            searchSourceBuilder.query(boolQueryBuilder).sort("update_time", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        } else if ("3".equals(sort)) {
            searchSourceBuilder.query(boolQueryBuilder).sort("rent_house_price", SortOrder.ASC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        } else if ("4".equals(sort)) {
            searchSourceBuilder.query(boolQueryBuilder).sort("rent_house_price", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        } else if ("6".equals(sort)) {
            searchSourceBuilder.query(boolQueryBuilder).sort("house_area", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        } else {
            searchSourceBuilder.query(boolQueryBuilder).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
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

    @Override
    public SearchResponse querySubwayLineHouse(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("subwayLine").field("subway_line_id").size(0)
                    .subAggregation(AggregationBuilders.terms("community").field("zufang_id")));
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
