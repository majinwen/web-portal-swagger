package com.toutiao.app.service.sellhouse.impl;


import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.sellhouse.AgentsBySellHouseDo;
import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.StringUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;
    private static final String NEAR_HOUSE = "1.6";




    @Override
    public SellHouseDetailsDo getSellHouseByHouseId(Integer houseId) {

        //二手房房源详情
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        SellHouseDetailsDo sellHouseDetailsDo = JSON.parseObject(details,SellHouseDetailsDo.class);
        // TODO 小区信息

        // 附近好房
        Integer newcode = sellHouseDetailsDo.getNewcode();
        String location = sellHouseDetailsDo.getHousePlotLocation();
        Double lon = 0.0;
        Double lat = 0.0;
        if(StringUtil.isNotNullString(location)){
            String[] locationSplit = location.split(",");
            lat = Double.valueOf(locationSplit[0]);
            lon = Double.valueOf(locationSplit[1]);
        }
        List<NearBySellHousesDo> nearBySellHousesDos = getSellHouseByhouseIdAndLocation(newcode.toString() ,lat ,lon ,NEAR_HOUSE);
        if(nearBySellHousesDos!=null && nearBySellHousesDos.size()>0){
            sellHouseDetailsDo.setNearBySellHousesDo(nearBySellHousesDos);
        }

        //二手房房源经纪人
        AgentsBySellHouseDo agentsBySellHouseDo = getAgentByHouseId(houseId);
        if(agentsBySellHouseDo!=null){
            sellHouseDetailsDo.setAgentsBySellHouseDo(agentsBySellHouseDo);
        }
        return sellHouseDetailsDo;
    }

    @Override
    public List<SellHouseDetailsDo> queryEsfByPlotId(Integer plotId) {
        List<SellHouseDetailsDo> houseList = new ArrayList<>();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("newcode", plotId));
        SearchResponse sellHouseByPlotId = sellHouseEsDao.getSellHouseByPlotId(booleanQueryBuilder);
        SearchHit[] hits = sellHouseByPlotId.getHits().getHits();
        String details;
        for (SearchHit hit:hits){
            details =  hit.getSourceAsString();
            SellHouseDetailsDo sellHouseDetailsDo = JSON.parseObject(details, SellHouseDetailsDo.class);
            sellHouseDetailsDo.setTotalNum(Integer.valueOf((int) sellHouseByPlotId.getHits().getTotalHits()));
            houseList.add(sellHouseDetailsDo);
        }
        return houseList;
    }


    /**
     * 二手房附近好房列表
     * @param newhouse
     * @param lat
     * @param lon
     * @param distance
     * @return
     */
    public List<NearBySellHousesDo> getSellHouseByhouseIdAndLocation(String newhouse, double lat, double lon, String distance) {

        List<NearBySellHousesDo> nearBySh = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(termQuery("newcode",newhouse));
        boolQueryBuilder.must(QueryBuilders.geoDistanceQuery("housePlotLocation").point(lat, lon).distance(distance, DistanceUnit.KILOMETERS));
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("housePlotLocation", lat, lon);
        Script script = new Script("Math.random()");
        ScriptSortBuilder scriptSortBuilder = SortBuilders.scriptSort(script, ScriptSortBuilder.ScriptSortType.NUMBER);
        sort.unit(DistanceUnit.KILOMETERS);
        sort.geoDistance(GeoDistance.ARC);

        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseIdAndLocation(boolQueryBuilder,scriptSortBuilder,sort);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String details;
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
            BigDecimal geoDis = new BigDecimal((Double) searchHit.getSortValues()[1]);
            String range = geoDis.setScale(1, BigDecimal.ROUND_CEILING)+DistanceUnit.KILOMETERS.toString();
            NearBySellHousesDo nearBySellHousesDo = JSON.parseObject(details,NearBySellHousesDo.class);
            nearBySellHousesDo.setHousetToPlotDistance(range);
            nearBySh.add(nearBySellHousesDo);
        }
        return nearBySh;
    }

    /**
     * 认领房源经纪人
     * @param houseId
     * @return
     */
    public AgentsBySellHouseDo getAgentByHouseId(Integer houseId){

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
        SearchResponse searchResponse = agentHouseEsDao.getAgentHouseByHouseId(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            long time = new Date().getTime();
            long index = (time / 600000) % hits.length;
            String details = hits[(int) index].getSourceAsString();
            AgentsBySellHouseDo agentsBySellHouseDo = JSON.parseObject(details,AgentsBySellHouseDo.class);
            return agentsBySellHouseDo;
        }else{
            return null;
        }

    }
}
