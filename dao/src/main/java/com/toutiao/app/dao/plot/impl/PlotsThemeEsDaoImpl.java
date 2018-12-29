package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotsThemeEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.ParentIdQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 小区专题数据层
 */
@Service
public class PlotsThemeEsDaoImpl implements PlotsThemeEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse getPlotsThemeList(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize, String city) {
        SearchResponse searchResponse = null;
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("house_count", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse getHouseAreaByPlotId(Integer plotId, String city) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("plotId", plotId));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del",0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));

        SearchResponse searchResponse = null;
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.max("maxHouse").field("sellHouseArea"))
                .aggregation(AggregationBuilders.min("minHouse").field("sellHouseArea"));
        searchRequest.source(searchSourceBuilder);
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse getHouseMaxAndMinArea(Integer plotId, String city) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(new ParentIdQueryBuilder(ElasticCityUtils.VILLAGES_CHILD_NAME,plotId.toString()));
        SearchResponse searchResponse = null;
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.max("max").field("sellHouseArea"))
                .aggregation(AggregationBuilders.min("min").field("sellHouseArea"));
        searchRequest.source(searchSourceBuilder);
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
