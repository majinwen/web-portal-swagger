package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.CutPriceSellHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CutPriceSellHouseEsDaoImpl implements CutPriceSellHouseEsDao {
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

    /***
     * 查询降价房列表
     */
    @Override
    public SearchResponse getCutPriceSellHouse(BoolQueryBuilder query, Integer sort, Integer pageNum, Integer pageSize) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        //排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-涨幅升, 4-涨幅降)
        if (sort == 0) {
            srb.addSort("updateTimeSort", SortOrder.DESC);
        } else if (sort == 1) {
            srb.addSort("houseTotalPrices", SortOrder.ASC);
        } else if (sort == 2) {
            srb.addSort("houseTotalPrices", SortOrder.DESC);
        } else if (sort == 3) {
            srb.addSort("priceFloat", SortOrder.ASC);
        } else if (sort == 4) {
            srb.addSort("priceFloat", SortOrder.DESC);
        }
        SearchResponse searchResponse = new SearchResponse();
        searchResponse = srb.setQuery(query).setFrom((pageNum - 1) * pageSize).setSize(pageSize).execute().actionGet();
        return searchResponse;
    }
}
