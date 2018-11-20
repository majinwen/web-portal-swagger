package com.toutiao.app.dao.newhouse.impl;

import com.toutiao.app.dao.newhouse.NewHouseLayoutEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewHouseLayoutEsDaoImpl implements NewHouseLayoutEsDao{


    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${tt.newhouse.index}")
    private String newHouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newHouseType;//索引类型
    @Value("${tt.newlayout.type}")
    private String layoutType;//子类索引类型

    /**
     * 根据新房id获取该id下所有的户型以及数量
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getLayoutCountByNewHouseId(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseChildType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).aggregation(AggregationBuilders.terms("roomCount").field("room").order(BucketOrder.count(true)));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;

        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }

    /**
     *
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getLayoutListByNewHouseIdAndRoomCount(BoolQueryBuilder booleanQueryBuilder, String city) {

//        TransportClient client = esClientTools.init();
//        SearchResponse searchresponse = client.prepareSearch(ElasticCityUtils.getNewHouseIndex(city)).setTypes(ElasticCityUtils.getNewHouseChildType(city))
//                .setQuery(booleanQueryBuilder).setSize(1000)
//                .execute().actionGet();

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseChildType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).size(1000);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }

    /**
     * 根据新房id获取户型价格范围
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getLayoutPriceByNewHouseId(BoolQueryBuilder booleanQueryBuilder, String city) {


        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseChildType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).size(0)
                .aggregation(AggregationBuilders.min("minPrice").field("total_price"))
                .aggregation(AggregationBuilders.max("maxPrice").field("total_price"));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
