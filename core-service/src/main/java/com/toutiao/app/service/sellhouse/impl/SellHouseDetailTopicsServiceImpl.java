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
import java.util.Arrays;
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
        if(StringTool.isNotEmpty(sellHouseDoQuery.getSortFields()) && StringTool.isNotEmpty(sellHouseDoQuery.getUid())){
            searchAfterIds[0]=sellHouseDoQuery.getSortFields();
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

                sellHouseDo.setSortFields(searchHit.getSortValues()[0].toString());
                sellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(sellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHouseDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{

                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("tagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    sellHouseDo.setClaimTagsName(tagsName);
                    sellHouseDo.setClaimHouseId(searchHit.getSource().get("houseId").toString());
                    sellHouseDo.setClaimHousePhotoTitle(searchHit.getSource().get("housePhotoTitle").toString());
                    sellHouseDo.setClaimHouseTitle(searchHit.getSource().get("houseTitle").toString());


                }
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);
            }
        }

        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal((int)hits.getTotalHits());

        return sellHouseDomain;
    }


    /**
     * 获取降价专题房源详情
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getCutPriceTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery) {

        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        //降价房源
        boolQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", "1"));

        String[] searchAfterIds = new String[2];

        if(StringTool.isNotEmpty(sellHouseDoQuery.getSortFields()) && StringTool.isNotEmpty(sellHouseDoQuery.getUid())){
            searchAfterIds[0]=sellHouseDoQuery.getSortFields();
            searchAfterIds[1]="esf_type#"+sellHouseDoQuery.getUid();
        }

        SearchResponse searchResponse = sellHouseDetailTopicsEsDao.getCutPriceTopicsSellHouse(sellHouseDoQuery, boolQueryBuilder, searchAfterIds);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                SellHouseDo sellHouseDo = JSON.parseObject(searchHit.getSourceAsString(),SellHouseDo.class);
                sellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                sellHouseDo.setSortFields(searchHit.getSortValues()[0].toString());
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(StringTool.isNotEmpty(sellHouseDo.getUserId()) && sellHouseDo.getIsClaim()==1){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());

                    sellHouseDo.setClaimHousePhotoTitle(searchHit.getSource().get("housePhotoTitle").toString());
                    sellHouseDo.setClaimHouseTitle(searchHit.getSource().get("houseTitle").toString());
                    sellHouseDo.setClaimHouseId(searchHit.getSource().get("houseId").toString());

                    List<String> tags = (List<String>) searchHit.getSource().get("tagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    sellHouseDo.setClaimTagsName(tagsName);
                }
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);
            }
        }

        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal((int)hits.getTotalHits());

        return sellHouseDomain;
    }

    /**
     * 获取洼地专题房源详情
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getLowPriceTopicsSellHouse(SellHouseDoQuery sellHouseDoQuery) {


        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        //降价房源
        boolQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", "1"));
        String[] searchAfterIds = new String[2];

        if(StringTool.isNotEmpty(sellHouseDoQuery.getSortFields()) && StringTool.isNotEmpty(sellHouseDoQuery.getUid())){
            searchAfterIds[0]=sellHouseDoQuery.getSortFields();
            searchAfterIds[1]="esf_type#"+sellHouseDoQuery.getUid();
        }
        SearchResponse searchResponse = sellHouseDetailTopicsEsDao.getLowPriceTopicsSellHouse(sellHouseDoQuery, boolQueryBuilder, searchAfterIds);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                SellHouseDo sellHouseDo = JSON.parseObject(searchHit.getSourceAsString(),SellHouseDo.class);
                sellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                sellHouseDo.setSortFields(searchHit.getSortValues()[0].toString());

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(StringTool.isNotEmpty(sellHouseDo.getUserId()) && sellHouseDo.getIsClaim()==1){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());
                }else{
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());

                    sellHouseDo.setClaimHousePhotoTitle(searchHit.getSource().get("housePhotoTitle").toString());
                    sellHouseDo.setClaimHouseTitle(searchHit.getSource().get("houseTitle").toString());
                    sellHouseDo.setClaimHouseId(searchHit.getSource().get("houseId").toString());

                    List<String> tags = (List<String>) searchHit.getSource().get("tagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    sellHouseDo.setClaimTagsName(tagsName);
                }
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);
            }
        }

        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal((int)hits.getTotalHits());

        return sellHouseDomain;
    }

    /**
     * 获取逢出毕抢专题房源详情
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getMustRobTopicsSellHouseDetail(SellHouseDoQuery sellHouseDoQuery) {



        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        //逢出毕抢
        boolQueryBuilder.must(QueryBuilders.termQuery("isMustRob", "1"));
        String[] searchAfterIds = new String[2];

        if(StringTool.isNotEmpty(sellHouseDoQuery.getSortFields()) && StringTool.isNotEmpty(sellHouseDoQuery.getUid())){
            searchAfterIds[0]=sellHouseDoQuery.getSortFields();
            searchAfterIds[1]="esf_type#"+sellHouseDoQuery.getUid();
        }

        SearchResponse searchResponse = sellHouseDetailTopicsEsDao.getMustRobTopicsSellHouse(sellHouseDoQuery, boolQueryBuilder, searchAfterIds);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                SellHouseDo sellHouseDo = JSON.parseObject(searchHit.getSourceAsString(),SellHouseDo.class);
                sellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                sellHouseDo.setSortFields(searchHit.getSortValues()[0].toString());

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(StringTool.isNotEmpty(sellHouseDo.getUserId()) && sellHouseDo.getIsClaim()==1){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());

                    sellHouseDo.setClaimHousePhotoTitle(searchHit.getSource().get("housePhotoTitle").toString());
                    sellHouseDo.setClaimHouseTitle(searchHit.getSource().get("houseTitle").toString());
                    sellHouseDo.setClaimHouseId(searchHit.getSource().get("houseId").toString());

                    List<String> tags = (List<String>) searchHit.getSource().get("tagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    sellHouseDo.setClaimTagsName(tagsName);

                }
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);
            }
        }

        sellHouseDomain.setSellHouseList(sellHouseDos);
        sellHouseDomain.setTotal((int)hits.getTotalHits());

        return sellHouseDomain;
    }

    /**
     * 商圈户型房源详情
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getAreaRoomTopicsSellHouseDetail(SellHouseDoQuery sellHouseDoQuery) {

        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        //逢出毕抢
        boolQueryBuilder.must(QueryBuilders.termQuery("isMustRob", "1"));
        String[] searchAfterIds = new String[2];

        if(StringTool.isNotEmpty(sellHouseDoQuery.getSortFields()) && StringTool.isNotEmpty(sellHouseDoQuery.getUid())){
            searchAfterIds[0]=sellHouseDoQuery.getSortFields();
            searchAfterIds[1]="esf_type#"+sellHouseDoQuery.getUid();
        }
        SearchResponse searchResponse = sellHouseDetailTopicsEsDao.getAreaRoomTopicsSellHouse(sellHouseDoQuery, boolQueryBuilder, searchAfterIds);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                SellHouseDo sellHouseDo = JSON.parseObject(searchHit.getSourceAsString(),SellHouseDo.class);
                sellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                sellHouseDo.setSortFields(searchHit.getSortValues()[0].toString());

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(StringTool.isNotEmpty(sellHouseDo.getUserId()) && sellHouseDo.getIsClaim()==1){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("tagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    sellHouseDo.setClaimTagsName(tagsName);
                    sellHouseDo.setClaimHousePhotoTitle(searchHit.getSource().get("housePhotoTitle").toString());
                    sellHouseDo.setClaimHouseTitle(searchHit.getSource().get("houseTitle").toString());
                    sellHouseDo.setClaimHouseId(searchHit.getSource().get("houseId").toString());
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
