package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.LowerPriceSellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDo;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.sellhouse.LowerPriceSellHouseRestService;
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

/**
 * 捡漏房服务层
 *
 * @author zym
 */
@Service
public class LowerPriceSellHouseRestServiceImpl implements LowerPriceSellHouseRestService {
    @Autowired
    private LowerPriceSellHouseEsDao lowerPriceSellHouseEsDao;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private AgentService agentService;

    /**
     * 获取捡漏房List
     */
    @Override
    public LowerPriceShellHouseDomain getLowerPriceHouse(LowerPriceShellHouseDoQuery lowerPriceShellHouseDoQuery) {
        LowerPriceShellHouseDomain lowerPriceShellHouseDomain = new LowerPriceShellHouseDomain();

        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        Integer areaId = lowerPriceShellHouseDoQuery.getDistrictId();
        //捡漏房
        booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));

        //区域
        if (areaId != null && areaId != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", areaId));
        }

        //新导入房源
        Integer isNew = lowerPriceShellHouseDoQuery.getIsNew();
        if (isNew != null) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", isNew));
        }

        //价格区间
        double beginPrice = lowerPriceShellHouseDoQuery.getBeginPrice();
        double endPrice = lowerPriceShellHouseDoQuery.getEndPrice();
        if (beginPrice != 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice).lte(endPrice));
        } else if (beginPrice == 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(endPrice));
        } else if (beginPrice != 0 && endPrice == 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice));
        }

        Integer sort = lowerPriceShellHouseDoQuery.getSort();
        Integer pageNum = lowerPriceShellHouseDoQuery.getPageNum();
        Integer pageSize = lowerPriceShellHouseDoQuery.getPageSize();
        SearchResponse lowerPriceSellHouse = lowerPriceSellHouseEsDao.getLowerPriceSellHouse(booleanQueryBuilder, sort, pageNum, pageSize);

        SearchHits hits = lowerPriceSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<LowerPriceShellHouseDo> lowerPriceShellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                LowerPriceShellHouseDo lowerPriceShellHouseDo = JSON.parseObject(details, LowerPriceShellHouseDo.class);
//                lowerPriceShellHouseDo.setSortField(searchHit.getSortValues()[0].toString());
//                lowerPriceShellHouseDo.setUid(searchHit.getSortValues()[1].toString().split("#")[1]);
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                Integer userId = lowerPriceShellHouseDo.getUserId();
                if (lowerPriceShellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(userId)){
                    agentBaseDo = agentService.queryAgentInfoByUserId(userId.toString());

                    lowerPriceShellHouseDo.setHouseId(searchHit.getSource().get("claimHouseId").toString());
                    lowerPriceShellHouseDo.setHouseTitle(searchHit.getSource().get("claimHouseTitle").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("claimTagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    lowerPriceShellHouseDo.setTagsName(tagsName);

                    lowerPriceShellHouseDo.setHousePhotoTitle(searchHit.getSource().get("claimHousePhotoTitle").toString());




                } else {
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());
                }
                lowerPriceShellHouseDo.setAgentBaseDo(agentBaseDo);
                lowerPriceShellHouseDos.add(lowerPriceShellHouseDo);
            }
        }

        //查询订阅Id
        if (!UserBasic.isLogin()) {
            lowerPriceShellHouseDomain.setSubscribeId(-1);
        } else {
            UserBasic userBasic = UserBasic.getCurrent();
            UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
            userSubscribeDetailDo.setTopicType(2);
            userSubscribeDetailDo.setDistrictId(areaId);
            userSubscribeDetailDo.setBeginPrice((int) beginPrice);
            userSubscribeDetailDo.setEndPrice((int) endPrice);

            UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo,
                    Integer.valueOf(userBasic.getUserId()));
            if (userSubscribe != null) {
                lowerPriceShellHouseDomain.setSubscribeId(userSubscribe.getId());
            } else {
                lowerPriceShellHouseDomain.setSubscribeId(-1);
            }
        }
        lowerPriceShellHouseDomain.setData(lowerPriceShellHouseDos);
        lowerPriceShellHouseDomain.setTotalCount(hits.totalHits);
        return lowerPriceShellHouseDomain;
    }
}
