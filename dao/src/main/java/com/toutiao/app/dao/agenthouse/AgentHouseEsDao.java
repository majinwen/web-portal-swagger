package com.toutiao.app.dao.agenthouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface AgentHouseEsDao {

    /**
     * 根据房源id获取认领房源经纪人信息
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getAgentHouseByHouseId(BoolQueryBuilder booleanQueryBuilder);

    SearchResponse getAgentRentByRentId(BoolQueryBuilder booleanQueryBuilder);

}
