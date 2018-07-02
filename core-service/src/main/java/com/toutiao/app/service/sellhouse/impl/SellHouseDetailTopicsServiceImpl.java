package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.SellHouseDetailTopicsEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDomain;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.SellHouseDetailTopicsService;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SellHouseDetailTopicsServiceImpl implements SellHouseDetailTopicsService{


    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;

    @Autowired
    private SellHouseDetailTopicsEsDao sellHouseDetailTopicsEsDao;

    @Autowired
    private AgentService agentService;

    /**
     * 获取专题附近小区房源详情
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getNearbyTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery) {


        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);

        String[] searchAfterIds = new String[2];
        if(StringTool.isNotEmpty(sellHouseDoQuery.getUpTimestamp() ) && StringTool.isNotEmpty(sellHouseDoQuery.getUid() ) ){
            searchAfterIds[0]=sellHouseDoQuery.getUpTimestamp();
            searchAfterIds[1]="esf_type#"+sellHouseDoQuery.getUid();
        }



        SearchResponse searchResponse = sellHouseDetailTopicsEsDao.getNearbyTopicsSellHouse(boolQueryBuilder,searchAfterIds,sellHouseDoQuery.getPageSize());

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHouseDo sellHouseDo = JSON.parseObject(details,SellHouseDo.class);

                sellHouseDo.setUpTimestamp(searchHit.getSortValues()[0].toString());
                sellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(sellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHouseDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{

                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
                }
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);
            }
        }

        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal((int)hits.getTotalHits());

        return sellHouseDomain;
    }
}
