package com.toutiao.app.dao.newhouse.impl;

import com.toutiao.app.dao.newhouse.NewHouseLayoutEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NewHouseLayoutEsDaoImpl implements NewHouseLayoutEsDao{


    @Autowired
    private ESClientTools esClientTools;
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
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = client.prepareSearch(ElasticCityUtils.getNewHouseIndex(city)).setTypes(ElasticCityUtils.getNewHouseChildType(city)).setQuery(booleanQueryBuilder)
                .addAggregation(AggregationBuilders.terms("roomCount").field("room").order(BucketOrder.count(true)))
                .execute().actionGet();
        return searchresponse;
    }

    /**
     *
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getLayoutListByNewHouseIdAndRoomCount(BoolQueryBuilder booleanQueryBuilder, String city) {

        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = client.prepareSearch(ElasticCityUtils.getNewHouseIndex(city)).setTypes(ElasticCityUtils.getNewHouseChildType(city))
                .setQuery(booleanQueryBuilder).setSize(1000)
                .execute().actionGet();
        return searchresponse;
    }

    /**
     * 根据新房id获取户型价格范围
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getLayoutPriceByNewHouseId(BoolQueryBuilder booleanQueryBuilder) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(newHouseIndex).setTypes(layoutType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).setSize(0)
                .addAggregation(AggregationBuilders.min("minPrice").field("total_price"))
                .addAggregation(AggregationBuilders.max("maxPrice").field("total_price"))
                .execute().actionGet();

        return searchResponse;
    }
}
