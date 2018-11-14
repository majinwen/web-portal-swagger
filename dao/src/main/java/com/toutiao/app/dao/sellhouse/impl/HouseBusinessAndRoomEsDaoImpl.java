package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.HouseBusinessAndRoomEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 商圈+户型房源专题页数据层实现类
 */
@Service
public class HouseBusinessAndRoomEsDaoImpl implements HouseBusinessAndRoomEsDao{

    @Autowired
    private ESClientTools esClientTools;

    /**
     * 索引名称
     */
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;

    /**
     * 索引类
     */
    @Value("${tt.projhouse.type}")
    private String projhouseType;

    /**
     * 商圈户型均价索引名称
     */
    @Value("${tt.areaRoom.index}")
    private String areaRoomIndex;

    /**
     * 商圈户型均价索引类
     */
    @Value("${tt.areaRoom.type}")
    private String areaRoomType;

    @Override
    public SearchResponse getHouseBusinessAndRoomHouses(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getEsfHouseIndex(city)).setTypes(ElasticCityUtils.getEsfHouseTpye(city));
        srb.addSort("houseTotalPrices", SortOrder.ASC);
//        srb.addSort("_uid", SortOrder.DESC);
        SearchResponse searchResponse = srb.setQuery(query).setFrom((pageNum - 1) * pageSize).setSize(pageSize).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse getHouseBusinessAndRoomAveragePrice(BoolQueryBuilder query, String city) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getAreaRoomIndex(city)).setTypes(ElasticCityUtils.getAreaRoomType(city));

        SearchResponse searchResponse = srb.setQuery(query).execute().actionGet();
        return searchResponse;
    }
}
