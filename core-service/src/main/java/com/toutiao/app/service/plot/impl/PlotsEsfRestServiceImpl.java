package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDo;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * 小区下二手房服务实现类
 *
 */
@Service
public class PlotsEsfRestServiceImpl implements PlotsEsfRestService{

    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private AgentService agentService;
    @Autowired
    private CommunityRestService communityRestService;
    @Autowired
    private SellHouseService sellHouseService;

    /**
     * 根据小区id获取小区下房源数量
     * @param plotsId
     * @return
     */
    @Override
    public PlotsEsfRoomCountDomain queryPlotsEsfByPlotsId(Integer plotsId,  String city) {


        List<PlotsEsfRoomCountDo> plotsEsfRoomCountDoList = new ArrayList<>();

        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = new PlotsEsfRoomCountDomain();

        SearchResponse searchResponse = sellHouseEsDao.getSellHouseCountByPlotsId(plotsId,city);
        Map aggMap =searchResponse.getAggregations().asMap();
        StringTerms gradeTerms = (StringTerms) aggMap.get("roomCount");

        if(gradeTerms.getBuckets().size() == 0){
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_ESF_NOT_FOUND,"小区没有出售房源信息");
        }
        Iterator roomBucketIt = gradeTerms.getBuckets().iterator();
        while(roomBucketIt.hasNext()) {
            PlotsEsfRoomCountDo plotsEsfRoomCountDo = new PlotsEsfRoomCountDo();
            Terms.Bucket roomBucket = (Terms.Bucket) roomBucketIt.next();
            plotsEsfRoomCountDo.setRoom(roomBucket.getKey());
            plotsEsfRoomCountDo.setCount(roomBucket.getDocCount());
            plotsEsfRoomCountDoList.add(plotsEsfRoomCountDo);
        }
        plotsEsfRoomCountDomain.setRooms(plotsEsfRoomCountDoList);
        Long totalCount = 0L;
        if(null != plotsEsfRoomCountDoList && plotsEsfRoomCountDoList.size() > 0){
            for(int i=0; i < plotsEsfRoomCountDoList.size(); i++){
                totalCount = totalCount+ Integer.valueOf(String.valueOf(plotsEsfRoomCountDoList.get(i).getCount()));
            }
            plotsEsfRoomCountDomain.setTotalCount(totalCount);
        }
        return plotsEsfRoomCountDomain;
    }



    /**
     * 根据小区id，户型查询房源列表
     * @param plotsId
     * @param room
     * @return
     */
    @Override
    public List<SellAndClaimHouseDetailsDo> getEsfByPlotsIdAndRoom(Integer plotsId, Integer room, Integer pageNum, Integer pageSize,String city) {

        Date date = new Date();
        BoolQueryBuilder detailsBuilder = boolQuery();
        List<SellAndClaimHouseDetailsDo> sellHouseDoList = new ArrayList<>();
        detailsBuilder.must(termQuery("newcode",plotsId));
        detailsBuilder.must(termQuery("isDel",0));
        detailsBuilder.must(QueryBuilders.termsQuery("is_claim", "0"));
        if(room != 0){
            detailsBuilder.must(termQuery("room",room));
        }
        SearchResponse searchresponse = sellHouseEsDao.getEsfByPlotsIdAndRoom(detailsBuilder,pageNum,pageSize,city);

        if(searchresponse.getHits().totalHits==0){
            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_ESF_NOT_FOUND,"小区没有出售房源信息");
        }
        SearchHits layoutHits = searchresponse.getHits();
        SearchHit[] searchHists = layoutHits.getHits();

        String details = "";
        for (SearchHit hit : searchHists) {
            details = hit.getSourceAsString();
            SellAndClaimHouseDetailsDo sellHouseDo = JSON.parseObject(details,SellAndClaimHouseDetailsDo.class);

            //判断3天内导入，且无图片，默认上显示默认图
            String importTime = sellHouseDo.getImportTime();
            int isDefault = sellHouseService.isDefaultImage(importTime ,date, sellHouseDo.getHousePhotoTitle());
            if(isDefault==1){
                sellHouseDo.setIsDefaultImage(1);
            }


            if(hit.getSource().get("is_claim").toString().equals("1")){
                sellHouseDo.setHousePhotoTitle(hit.getSource().get("claimHousePhotoTitle").toString());
                sellHouseDo.setHouseId(hit.getSource().get("claimHouseId").toString());
            }

            AgentBaseDo agentBaseDo = new AgentBaseDo();
            if(sellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHouseDo.getUserId())){
                agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);




            }else{
                agentBaseDo.setAgentName(hit.getSource().get("houseProxyName")==null?"":hit.getSourceAsMap().get("houseProxyName").toString());
                agentBaseDo.setAgentCompany(hit.getSource().get("ofCompany")==null?"":hit.getSourceAsMap().get("ofCompany").toString());
                agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto")==null?"":hit.getSourceAsMap().get("houseProxyPhoto").toString());
                agentBaseDo.setDisplayPhone(hit.getSource().get("houseProxyPhone")==null?"":hit.getSourceAsMap().get("houseProxyPhone").toString());
            }
            sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
            sellHouseDo.setAgentBaseDo(agentBaseDo);
            sellHouseDoList.add(sellHouseDo);
        }
        return sellHouseDoList;
    }

    @Override
    public PlotsEsfRoomCountDomain queryHouseCountByPlotsId(Integer plotsId, String city) {
        List<PlotsEsfRoomCountDo> plotsEsfRoomCountDoList = new ArrayList<>();

        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = new PlotsEsfRoomCountDomain();

        SearchResponse searchResponse = sellHouseEsDao.getSellHouseCountByPlotsId(plotsId,city);
        Map aggMap =searchResponse.getAggregations().asMap();
        StringTerms gradeTerms = (StringTerms) aggMap.get("roomCount");


        Iterator roomBucketIt = gradeTerms.getBuckets().iterator();
        while(roomBucketIt.hasNext()) {
            PlotsEsfRoomCountDo plotsEsfRoomCountDo = new PlotsEsfRoomCountDo();
            Terms.Bucket roomBucket = (Terms.Bucket) roomBucketIt.next();
            plotsEsfRoomCountDo.setCount(roomBucket.getDocCount());
            plotsEsfRoomCountDo.setRoom(roomBucket.getKey());
            plotsEsfRoomCountDoList.add(plotsEsfRoomCountDo);
        }
        plotsEsfRoomCountDomain.setRooms(plotsEsfRoomCountDoList);
        Long totalCount = 0L;
        if(null != plotsEsfRoomCountDoList && plotsEsfRoomCountDoList.size() > 0){
            for(int i=0; i < plotsEsfRoomCountDoList.size(); i++){
                totalCount = totalCount+ Integer.valueOf(String.valueOf(plotsEsfRoomCountDoList.get(i).getCount()));
            }
            plotsEsfRoomCountDomain.setTotalCount(totalCount);
        }
        return plotsEsfRoomCountDomain;
    }
}
