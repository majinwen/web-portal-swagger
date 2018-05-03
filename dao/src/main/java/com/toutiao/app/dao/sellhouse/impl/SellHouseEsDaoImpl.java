package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder)
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



    @Override
    public SearchResponse getSellHouseList(FunctionScoreQueryBuilder query, Integer pageNum, Integer pageSize) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        SearchResponse searchresponse = srb.setQuery(query).addSort("sortingScore",SortOrder.DESC).setFrom((pageNum - 1) * pageSize).setSize(pageSize).setFetchSource(
                new String[] {"claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
                        "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
                        "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","is_claim","userId"} ,null)
                .execute().actionGet();
        return searchresponse;
    }


    @Override
    public SearchResponse getRecommendSellHouse(FunctionScoreQueryBuilder query, String uid, Integer pageSize) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);

        if(!uid.equals("0")){
            srb.searchAfter(new String[]{uid});
        }
        SearchResponse searchresponse = srb.setQuery(query).addSort("_uid",SortOrder.DESC).setSize(1).setFetchSource(
                new String[] {"claimHouseId","claimHouseTitle","claimHousePhotoTitle","price_increase_decline","houseTotalPrices",
                        "houseUnitCost","buildArea","claimTagsName","room","hall","forwardName","area","houseBusinessName",
                        "plotName","year","parkRadio","subwayDistince","housePlotLocation","newcode","housePhoto","is_claim","userId"} ,null)
                .execute().actionGet();
        return searchresponse;
    }

}
