package com.toutiao.app.dao.agenthouse.impl;

import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AgentHouseEsDaoImpl implements AgentHouseEsDao{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.esf.agent.index}")
    private String agentIndex;
    @Value("${tt.esf.agent.type}")
    private String agentType;

    @Override
    public SearchResponse getAgentHouseByHouseId(BoolQueryBuilder booleanQueryBuilder) {

        TransportClient client = esClientTools.init();
        SearchResponse searchResponse = client.prepareSearch(agentIndex).setTypes(agentType).setQuery(booleanQueryBuilder)
                .execute().actionGet();
        return searchResponse;
    }

}
