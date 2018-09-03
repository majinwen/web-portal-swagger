package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.HouseBusinessAndRoomEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.BusinessRoomAveragePriceDo;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDo;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDoQuery;
import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDomain;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.sellhouse.FilterBusinessRoomChooseService;
import com.toutiao.app.service.sellhouse.HouseBusinessAndRoomService;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 商圈+户型房源专题页服务层实现类
 */
@Service
public class HouseBusinessAndRoomServiceImpl implements HouseBusinessAndRoomService{
    @Autowired
    private HouseBusinessAndRoomEsDao houseBusinessAndRoomEsDao;

    @Autowired
    private FilterBusinessRoomChooseService filterBusinessRoomChooseService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private CommunityRestService communityRestService;

    /**
     * 获取商圈+户型
     */
    @Override
    public HouseBusinessAndRoomDomain getHouseBusinessAndRoomHouses(HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery,String city) {
        LinkedList<HouseBusinessAndRoomDo> houseBusinessAndRoomDos = new LinkedList<>();
        BoolQueryBuilder boolQueryBuilder = filterBusinessRoomChooseService.filterBusinessRoomChoose(houseBusinessAndRoomDoQuery);
        Integer pageNum = houseBusinessAndRoomDoQuery.getPageNum();
        Integer pageSize = houseBusinessAndRoomDoQuery.getPageSize();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        SearchResponse houseBusinessAndRoomHouses = houseBusinessAndRoomEsDao.getHouseBusinessAndRoomHouses(
                boolQueryBuilder, pageNum, pageSize);
        SearchHits hits = houseBusinessAndRoomHouses.getHits();
        SearchHit[] searchHists = hits.getHits();
        HouseBusinessAndRoomDomain houseBusinessAndRoomDomain = new HouseBusinessAndRoomDomain();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                HouseBusinessAndRoomDo houseBusinessAndRoomDo = JSON.parseObject(details, HouseBusinessAndRoomDo.class);
                String oldHouseId = houseBusinessAndRoomDo.getHouseId();
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (houseBusinessAndRoomDo.getIsClaim() == 1 && StringTool.isNotEmpty(houseBusinessAndRoomDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(houseBusinessAndRoomDo.getUserId().toString(),city);
                    //认领状态取认领数据
                    houseBusinessAndRoomDo.setHouseId(searchHit.getSource().get("claimHouseId").toString());
                    houseBusinessAndRoomDo.setHouseTitle(searchHit.getSource().get("claimHouseTitle").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("claimTagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    houseBusinessAndRoomDo.setTagsName(tagsName);
                    houseBusinessAndRoomDo.setHousePhotoTitle(searchHit.getSource().get("claimHousePhotoTitle").toString());
                } else {
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
                }
                houseBusinessAndRoomDo.setAgentBaseDo(agentBaseDo);
                houseBusinessAndRoomDo.setTypeCounts(communityRestService.getCountByBuildTags(city));
                String houseId = houseBusinessAndRoomDoQuery.getHouseId();
                if (StringTool.isNotEmpty(houseId) && houseId.equals(oldHouseId)) {
                    houseBusinessAndRoomDos.addFirst(houseBusinessAndRoomDo);
                } else {
                    houseBusinessAndRoomDos.add(houseBusinessAndRoomDo);
                }


            }
        }
        houseBusinessAndRoomDomain.setData(houseBusinessAndRoomDos);
        houseBusinessAndRoomDomain.setTotalCount(hits.totalHits);

        BoolQueryBuilder averagePriceBuilder = filterBusinessRoomChooseService.filterBusinessRoomAveragePriceChoose
                (houseBusinessAndRoomDoQuery);
        SearchResponse averagePrice = houseBusinessAndRoomEsDao.getHouseBusinessAndRoomAveragePrice(averagePriceBuilder);
        SearchHits averagePriceHits = averagePrice.getHits();
        SearchHit[] averagePriceSearchHists = averagePriceHits.getHits();
        if (averagePriceSearchHists.length > 0){
            String sourceAsString = averagePriceSearchHists[0].getSourceAsString();
            BusinessRoomAveragePriceDo averagePriceDo = JSON.parseObject(sourceAsString, BusinessRoomAveragePriceDo.class);
            houseBusinessAndRoomDomain.setAveragePrice(averagePriceDo.getAverage_price());
        }
        return houseBusinessAndRoomDomain;
    }
}
