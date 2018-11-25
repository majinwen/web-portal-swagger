package com.toutiao.app.dao.newhouse.impl;

import com.toutiao.app.dao.newhouse.NewHouseEsDao;
import org.elasticsearch.action.search.SearchRequest;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewHouseEsDaoImpl implements NewHouseEsDao {


    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city));
        searchRequest.types(ElasticCityUtils.getNewHouseParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  searchresponse;
    }



    @Override
    public SearchResponse getNewHouseList(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize,FieldSortBuilder levelSort,
                                          FieldSortBuilder buildingSort, String city, String sort) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder)
                /* .fetchSource(new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                 "district_id","district_name","area_id","area_name","building_title_img","sale_status_name","property_type",
                                 "location","house_min_area","house_max_area","nearbysubway","total_price","roundstation","deliver_time","park_radio","ringRoadName"},
                         null)*/.from((pageNum-1)*pageSize).size(pageSize);

        if (null!=levelSort && null!=buildingSort && "0".equals(sort)){//默认排序
            searchSourceBuilder.sort(levelSort).sort(buildingSort);
        }else if("1".equals(sort)){
            searchSourceBuilder.sort("totalPrice", SortOrder.ASC);
        }else if("2".equals(sort)){
            searchSourceBuilder.sort("totalPrice", SortOrder.DESC);
        }else if("3".equals(sort)){
            searchSourceBuilder.sort("average_price", SortOrder.ASC);
        }else if("4".equals(sort)){
            searchSourceBuilder.sort("average_price", SortOrder.DESC);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchresponse;
    }

    @Override
    public SearchResponse getDynamicByNewCode(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHosueDynamicIndex(city)).types(ElasticCityUtils.getNewHosueDynamicType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("create_time",SortOrder.DESC)
                .fetchSource(
                        new String[]{"title","time","link_url","detail","newcode","create_time","type","is_del"},null
                ).from((pageNum-1)*pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  searchresponse;

    }

    @Override
    public SearchResponse getOneNewHouseByRecommendCondition(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city));
        searchRequest.types(ElasticCityUtils.getNewHouseParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder.query(boolQueryBuilder));

        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  searchresponse;
    }


//    @Override
//    public SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder, String city) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getNewHouseIndex(city)).setTypes(ElasticCityUtils.getNewHouseParentType(city));
//        SearchResponse searchresponse=srb.setQuery(booleanQueryBuilder).execute().actionGet();
//        return searchresponse;
//    }
//
//    @Override
//    public SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder, String city) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getSearchEnginesIndex(city)).setTypes(ElasticCityUtils.getSearchEnginesType(city));
//        SearchResponse searchresponse=srb.setQuery(booleanQueryBuilder).execute().actionGet();
//        return searchresponse;
//    }

}
