package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotsThemeEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 小区专题数据层
 */
@Service
public class PlotsThemeEsDaoImpl implements PlotsThemeEsDao {
    @Value("${plot.index}")
    private String index;

    @Value("${plot.parent.type}")
    private String parentType;

    @Value("${plot.child.type}")
    private String childType;

    @Autowired
    private ESClientTools esClientTools;


    @Override
    public SearchResponse getPlotsThemeList(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize, String city) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotParentType(city));
        srb.addSort("house_count", SortOrder.DESC);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom((pageNum - 1) * pageSize)
                .setSize(pageSize).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse getHouseAreaByPlotId(Integer plotId, String city) {

        TransportClient client = esClientTools.init();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("plotId", plotId));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del",0));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status",1));
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotChildType(city));
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(0).addAggregation(AggregationBuilders.max("maxHouse").field("sellHouseArea"))
                .addAggregation(AggregationBuilders.min("minHouse").field("sellHouseArea")).execute().actionGet();

        return searchResponse;
    }
}
