package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.EsfMapSearchEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

/**
 * @ClassName EsfMapSearchEsDaoImpl
 * @Author jiangweilong
 * @Date 2018/11/23 7:41 PM
 * @Description:
 **/

@Service
public class EsfMapSearchEsDaoImpl implements EsfMapSearchEsDao {


    @Override
    public SearchResponse esfMapSearchByDistrict(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        searchSourceBuilder.query(boolQueryBuilder).aggregation()


        return null;
    }
}
