package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageEsDao;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HomePageEsDaoImpl implements HomePageEsDao {

    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类

    @Autowired
    private

    @Override
    public SearchResponse getHomePageEsf(BoolQueryBuilder boolQueryBuilder) {
        Random random = new Random();
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);

            srb.addSort("sortingScore", SortOrder.DESC);
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(20).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                Map<String, Object> buildings = hit.getSource();
                Class<ProjHouseInfoResponse> entityClass = ProjHouseInfoResponse.class;
                ProjHouseInfoResponse instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                list.add(instance);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
