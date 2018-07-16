package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.HouseBusinessAndRoomEsDao;
import com.toutiao.web.common.util.ESClientTools;
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
    public SearchResponse getHouseBusinessAndRoomHouses(BoolQueryBuilder query, Integer pageNum, Integer pageSize) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        srb.addSort("buildArea", SortOrder.DESC);
        srb.addSort("_uid", SortOrder.DESC);
        return srb.setQuery(query).setFrom((pageNum - 1) * pageSize).setSize(pageSize).execute().actionGet();
    }

    @Override
    public SearchResponse getHouseBusinessAndRoomAveragePrice(BoolQueryBuilder query) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(areaRoomIndex).setTypes(areaRoomType);
        return srb.setQuery(query).execute().actionGet();
    }
}
