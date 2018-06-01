package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RentEsDaoImpl implements RentEsDao {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;
    private static final Integer ZHENGZU = 1;
    private static final Integer HEZU = 2;


    @Override
    public SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder,Integer from) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).addSort("sortingScore", SortOrder.DESC).setFrom(from).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder){
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }
    @Override
    public SearchResponse queryNearHouseByLocation(BoolQueryBuilder boolQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort, Integer from, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).setFrom(from).setSize(size).setPostFilter(location).addSort(sort)
                .execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentNumByPlotId(BoolQueryBuilder boolQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder)
                .addAggregation(AggregationBuilders.filter("ZHENGZU", QueryBuilders.termQuery("rent_type", ZHENGZU)))
                .addAggregation(AggregationBuilders.filter("HEZU", QueryBuilders.termQuery("rent_type", HEZU)))
                .execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentList(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).addSort("sortingScore", SortOrder.DESC).setFrom(from).setSize(size)
                .setFetchSource(new String[]{"house_id","area_id","house_title","rent_house_price","rent_type_name","house_area","room","hall","forward",
                        "district_name","area_name","zufang_name","zufang_id","rent_house_tags_name","house_title_img","estate_agent","brokerage_agency","phone","agent_headphoto","userId","rent_type","rentHouseType","nearest_subway","rent_house_img"},null).execute().actionGet();

        return searchResponse;
    }

    @Override
    public SearchResponse queryRecommendRentList(BoolQueryBuilder boolQueryBuilder, String uid) {

        TransportClient client = esClientTools.init();

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(!uid.equals("0")){
            searchRequestBuilder.searchAfter(new String[]{uid});
        }
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).addSort("_uid", SortOrder.DESC).setSize(1)
                .setFetchSource(new String[]{"house_id","area_id","house_title","rent_house_price","rent_type_name","house_area","room","hall","forward",
                        "district_name","area_name","zufang_name","zufang_id","rent_house_tags_name","house_title_img","estate_agent","brokerage_agency","phone","agent_headphoto","userId","rent_type","rentHouseType","nearest_subway","rent_house_img"},null).execute().actionGet();

        return searchResponse;
    }

    @Override
    public SearchResponse queryNearRentHouse(FunctionScoreQueryBuilder query, Integer from) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(query).setFrom(from).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentSearchList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer pageNum, Integer pageSize) {

        TransportClient client = esClientTools.init();
        SearchResponse searchResponse = null;
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if((null!=keyword && !"".equals(keyword)) || null!=distance){
            searchResponse = searchRequestBuilder.setQuery(query).setFrom((pageNum - 1) * pageSize).setSize(pageSize)
                    .execute().actionGet();
        }else{
            searchResponse = searchRequestBuilder.setQuery(query).addSort("sortingScore", SortOrder.DESC).setFrom((pageNum - 1) * pageSize).setSize(pageSize)
                    .execute().actionGet();
        }
        return searchResponse;

    }
}
