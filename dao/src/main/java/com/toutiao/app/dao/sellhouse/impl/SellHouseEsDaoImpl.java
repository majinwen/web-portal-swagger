package com.toutiao.app.dao.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SellHouseEsDaoImpl implements SellHouseEsDao{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    @Value("${distance}")
    private Double distance;
    @Value("${tt.esf.agent.index}")
    private String agentIndex;
    @Value("${tt.esf.agent.type}")
    private String agentType;


    @Override
    public int queryNearByProjHouseInfo(Integer houseId) {
        TransportClient client = esClientTools.init();
        GetResponse ss = client.prepareGet(projhouseIndex,projhouseType,houseId.toString()).execute().actionGet();
        JSONObject aa = JSON.parseObject(ss.getSourceAsString());
        System.out.println(aa);
        return 1;
    }
}
