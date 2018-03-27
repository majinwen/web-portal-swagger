package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.AppRentDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.List;
import java.util.*;

@Service
public class AppRentDaoImpl implements AppRentDao {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;
    @Value("${tt.zufang.agent.index}")
    private String agentIndex;
    @Value("${tt.zufang.agent.type}")
    private String agentType;
    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布
    private static final Integer RENT = 0;//出租:1
    private static final Integer FOCUS_APARTMENT = 2;//公寓:2
    private static final Integer DISPERSED_APARTMENTS = 1;//公寓:2
    private static final String LAYOUT = "3";


    @Override
    public List queryHouseByPlotId(Integer plotId) {
        try{
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",plotId));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map<String, Object> source = hit.getSource();
                    source.put("total",searchResponse.getHits().getTotalHits());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map queryAgentByRentId(Integer rentId) {
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(agentIndex).setTypes(agentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",rentId));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                long time = new Date().getTime();
                long index = (time / 600000) % hits.length;
                Map result = hits[(int) index].getSource();
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map queryRentByRentId(Integer rentId) {
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //出租房源ID
            boolQueryBuilder.must(QueryBuilders.termQuery("house_id",rentId));
            //是否删除
            boolQueryBuilder.must(QueryBuilders.termQuery("is_del",IS_DEL));
            //发布状态
            boolQueryBuilder.must(QueryBuilders.termQuery("release_status",RELEASE_STATUS));
            SearchResponse response = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] searchHists = response.getHits().getHits();
            if(searchHists.length>0){
                Map source = searchHists[0].getSource();
                return source;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
