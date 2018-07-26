package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.MustBuySellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDo;
import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.sellhouse.MustBuySellHouseRestService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
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
public class MustBuySellHouseRestServiceImpl implements MustBuySellHouseRestService {
    @Autowired
    private MustBuySellHouseEsDao mustBuySellHouseEsDao;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private CommunityRestService communityRestService;

    /**
     * 获取不买亏二手房Domain
     */
    @Override
    public MustBuyShellHouseDomain getMustBuySellHouse(MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery, Integer topicType) {
        MustBuyShellHouseDomain mustBuyShellHouseDomain = new MustBuyShellHouseDomain();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        Integer areaId = mustBuyShellHouseDoQuery.getDistrictId();
        if (topicType == 1) {
            //降价房
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));
        } else if (topicType == 2) {
            //捡漏房
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));
        }

        //区域
        if (areaId != null && areaId != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", areaId));
        }

        //新导入房源
        Integer isNew = mustBuyShellHouseDoQuery.getIsNew();
        if (isNew != null) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", isNew));
        }


        //价格区间
        double beginPrice = mustBuyShellHouseDoQuery.getBeginPrice();
        double endPrice = mustBuyShellHouseDoQuery.getEndPrice();
        if (beginPrice != 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice).lte(endPrice));
        } else if (beginPrice == 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(endPrice));
        } else if (beginPrice != 0 && endPrice == 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice));
        }
        Integer sort = mustBuyShellHouseDoQuery.getSort();
        Integer pageNum = mustBuyShellHouseDoQuery.getPageNum();
        Integer pageSize = mustBuyShellHouseDoQuery.getPageSize();
        SearchResponse cutPriceSellHouse = mustBuySellHouseEsDao.getMustBuySellHouse(booleanQueryBuilder, sort, pageNum, pageSize, topicType);

        SearchHits hits = cutPriceSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<MustBuyShellHouseDo> mustBuyShellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                MustBuyShellHouseDo mustBuyShellHouseDo = JSON.parseObject(details, MustBuyShellHouseDo.class);
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (mustBuyShellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(mustBuyShellHouseDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(mustBuyShellHouseDo.getUserId().toString());
                    //认领状态取认领数据
                    mustBuyShellHouseDo.setHouseId(searchHit.getSource().get("claimHouseId").toString());
                    mustBuyShellHouseDo.setHouseTitle(searchHit.getSource().get("claimHouseTitle").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("claimTagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    mustBuyShellHouseDo.setTagsName(tagsName);
                    mustBuyShellHouseDo.setHousePhotoTitle(searchHit.getSource().get("claimHousePhotoTitle").toString());
                } else {
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());
                }

                mustBuyShellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags());

                mustBuyShellHouseDo.setAgentBaseDo(agentBaseDo);
                mustBuyShellHouseDos.add(mustBuyShellHouseDo);
            }
        }

        //查询订阅Id
        if (!UserBasic.isLogin()) {
            mustBuyShellHouseDomain.setSubscribeId(-1);
        } else {
            UserBasic userBasic = UserBasic.getCurrent();
            UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
            userSubscribeDetailDo.setTopicType(topicType);
            userSubscribeDetailDo.setDistrictId(areaId);
            userSubscribeDetailDo.setBeginPrice((int) beginPrice);
            userSubscribeDetailDo.setEndPrice((int) endPrice);

            UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer.valueOf(userBasic.getUserId()));
            if (userSubscribe != null) {
                mustBuyShellHouseDomain.setSubscribeId(userSubscribe.getId());
            } else {
                mustBuyShellHouseDomain.setSubscribeId(-1);
        }
        }
        mustBuyShellHouseDomain.setData(mustBuyShellHouseDos);
        mustBuyShellHouseDomain.setTotalCount(hits.totalHits);
        return mustBuyShellHouseDomain;
    }
}
