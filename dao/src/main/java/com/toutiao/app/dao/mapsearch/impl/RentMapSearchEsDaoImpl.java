package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.RentMapSearchEsDao;
import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RentMapSearchEsDaoImpl  implements RentMapSearchEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${bdw.dbavgprice.index}")
    private String bdwDbavgpriceIndex;
    @Value("${bdw.dbavgprice.type}")
    private String bdwDbavgpriceType;
    @Value("${bdw.subwayhouse.index}")
    private String bdwSubwayhouseIndex;
    @Value("${bdw.subwayhouse.type}")
    private String bdwSubwayhouseType;
    @Value("${bdw.subwayLineStation.index}")
    private String bdwSubwayLineStationIndex;
    @Value("${bdw.subwayLineStation.type}")
    private String bdwSubwayLineStationType;

    @Override
    public SearchResponse getRentMapSearch(SearchSourceBuilder searchSourceBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentHouseIndex(city)).types(ElasticCityUtils.getRentHouseType(city));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }

    @Override
    public SearchResponse getDistanceAndAreainfo(SearchSourceBuilder searchSourceBuilder) {
        SearchRequest searchRequest = new SearchRequest(bdwDbavgpriceIndex).types(bdwDbavgpriceType);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }

    @Override
    public SearchResponse getSubwayLineAndSubwayStationinfo(SearchSourceBuilder searchSourceBuilder) {
        SearchRequest searchRequest = new SearchRequest(bdwSubwayLineStationIndex).types(bdwSubwayLineStationType);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }

    @Override
    public SearchResponse getSubwayInfo(SearchSourceBuilder searchSourceBuilder) {
        SearchRequest searchRequest = new SearchRequest(bdwSubwayhouseIndex).types(bdwSubwayhouseType);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }


    @Override
    public SearchResponse getSubwayStationinfo(BoolQueryBuilder boolQueryBuilder, String city) {
        Integer cityId = CityUtils.returnCityId(city);
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getSubwayHousePriceIndex(CityConstant.ABBREVIATION_QUANGUO))
                .types(ElasticCityUtils.getSubwayHousePriceType(CityConstant.ABBREVIATION_QUANGUO));
        boolQueryBuilder.must(QueryBuilders.termQuery("city_id", cityId));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(100).sort("sorting", SortOrder.ASC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }

    @Override
    public SearchResponse queryDistrictOrAreaRentCount(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentHouseIndex(city)).types(ElasticCityUtils.getRentHouseType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }
}
