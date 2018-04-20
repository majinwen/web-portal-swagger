package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
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

    @Override
    public SearchResponse getSellHouseByChoose(BoolQueryBuilder booleanQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort, String keyWord, Integer order,int pageSize,int pageNum) {

        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = null;
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        if(location!=null && sort!=null){
            srb.setPostFilter(location).addSort(sort);
        }
        if (order != null && order == 1) {
            searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.DESC)
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else if (order != null && order == 2) {
            searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.ASC)
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else {
            //如果含有关键字查询，优先显示关键字
            if (StringTool.isNotBlank(keyWord)){
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("_score",SortOrder.DESC).addSort("houseLevel", SortOrder.DESC).addSort("houseScore", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            } else {
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseLevel", SortOrder.DESC).addSort("houseScore", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            }
        }
        return searchresponse;
    }

    /**
     * 根据小区id获取小区的房源数量
     * @param plotsId
     * @return
     */
    @Override
    public SearchResponse getSellHouseCountByPlotsId(Integer plotsId) {

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", plotsId));
        TransportClient client = esClientTools.init();

        SearchRequestBuilder srb =client.prepareSearch(projhouseIndex).setTypes(projhouseType);
//        srb.setQuery(booleanQueryBuilder)
//                .addAggregation(AggregationBuilders.terms("roomCount").field("room"));

        SearchResponse searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType).setQuery(booleanQueryBuilder)
                .addAggregation(AggregationBuilders.terms("roomCount").field("layout"))
                .execute().actionGet();
        return searchResponse;
    }


    @Override
    public SearchResponse getEsfByPlotsIdAndRoom(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {

        TransportClient client = esClientTools.init();
        SearchResponse searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).setFrom((pageNum - 1) * pageSize).setSize(pageSize)
                .execute().actionGet();
        return searchResponse;
    }

}
