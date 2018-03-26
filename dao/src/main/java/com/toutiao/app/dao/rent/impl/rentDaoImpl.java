package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.rentDao;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class rentDaoImpl implements rentDao {
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
    public Map queryHouseByPlotId(Integer plotId) {
        try{
            Map result = new HashMap();
            List list = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("zufang_id",plotId));
            SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).execute().actionGet();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length>0){
                for (SearchHit hit:hits){
                    Map<String, Object> source = hit.getSource();
                    list.add(source);
                }
                result.put("houseList",list);
                result.put("total",searchResponse.getHits().getTotalHits());
                return result;
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
