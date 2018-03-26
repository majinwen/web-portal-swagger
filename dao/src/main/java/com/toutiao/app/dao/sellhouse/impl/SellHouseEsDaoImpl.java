package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SellHouseEsDaoImpl implements SellHouseEsDao{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${distance}")
    private Double distance;



    @Override
    public SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();

        return searchresponse;
    }

    @Override
    public SearchResponse getSellHouseByHouseIdAndLocation(BoolQueryBuilder booleanQueryBuilder,
                                                           ScriptSortBuilder scriptSortBuilder, GeoDistanceSortBuilder sort) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).addSort(scriptSortBuilder).addSort(sort).setSize(5)
                .setFetchSource(new String[]{"houseId","houseTitle","room","hall","toilet","housePhotoTitle","tagsName",
                        "houseTotalPrices","buildArea","forwardName","housetToPlotDistance"},null)
                .execute().actionGet();

        return searchResponse;
    }

}
