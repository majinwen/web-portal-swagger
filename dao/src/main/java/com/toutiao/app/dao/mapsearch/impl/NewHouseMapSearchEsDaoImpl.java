package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.NewHouseMapSearchEsDao;
import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName NewHouseMapSearchEsDaoImpl
 * @Author jiangweilong
 * @Date 2018/11/24 7:23 PM
 * @Description:
 **/
@Service
public class NewHouseMapSearchEsDaoImpl implements NewHouseMapSearchEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse NewHouseMapSearchByDistrict(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder)
                .aggregation(AggregationBuilders.terms("buildCount").field("district_id").size(50)
                        .subAggregation(AggregationBuilders.terms("buildName").field("building_name_accurate"))
//                        .subAggregation(AggregationBuilders.terms("buildLatitude").field("latitude"))
//                        .subAggregation(AggregationBuilders.terms("buildLongitude").field("longitude"))
                );

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse NewHouseMapSearchByBuild(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(50)
                .fetchSource(new String[]{"district_id","district_name","property_type",
                        "building_name","average_price","sale_status_name","building_title_img"
                        ,"totalPrice","is_active","building_tags"},
                        null);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse getNewHouseMapByDbAvgPrice(BoolQueryBuilder boolQueryBuilder, String city) {
        Integer cityId = CityUtils.returnCityId(city);

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getDbAvgPriceIndex(CityConstant.ABBREVIATION_QUANGUO))
                .types(ElasticCityUtils.getDbAvgPriceType(CityConstant.ABBREVIATION_QUANGUO));

        boolQueryBuilder.must(QueryBuilders.termQuery("city_id", cityId));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("districtName").field("district_name"))
                .aggregation(AggregationBuilders.terms("latitude").field("district_latitude"))
                .aggregation(AggregationBuilders.terms("longitude").field("district_longitude"));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
