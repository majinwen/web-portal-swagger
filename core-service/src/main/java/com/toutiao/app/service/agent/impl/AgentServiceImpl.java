package com.toutiao.app.service.agent.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.service.agent.AgentService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentHouseEsDao agentHouseEsDao;

    @Override
    public AgentBaseDo queryAgentInfoByUserId(String userId) {
        AgentBaseDo agentBaseDo = new AgentBaseDo();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("user_id", userId));
        SearchResponse searchResponse = agentHouseEsDao.getRentInfoByUserId(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            String sourceAsString = hits[0].getSourceAsString();
            agentBaseDo = JSON.parseObject(sourceAsString, AgentBaseDo.class);
        }
        return agentBaseDo;
    }
}
