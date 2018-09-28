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
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
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
import java.util.Arrays;
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
    public MustBuyShellHouseDomain getMustBuySellHouse(MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery, Integer topicType, String city) {
        MustBuyShellHouseDomain mustBuyShellHouseDomain = new MustBuyShellHouseDomain();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        if (topicType == 1) {
            //降价房
            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));
        } else if (topicType == 2) {
            //捡漏房
            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));
        }

        //区域
        Integer[] districtIds = mustBuyShellHouseDoQuery.getDistrictIds();
        if (StringTool.isNotEmpty(districtIds)) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("areaId", districtIds));
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

        //排序
        Integer sort = mustBuyShellHouseDoQuery.getSort();
        if (sort == null) {
            //降价房按更新时间降序，捡漏房按总价升序
            if (topicType == 1) {
                sort = 0;
            } else if (topicType == 2) {
                sort = 1;
            }
        }
        Integer pageNum = mustBuyShellHouseDoQuery.getPageNum();
        Integer pageSize = mustBuyShellHouseDoQuery.getPageSize();
        SearchResponse cutPriceSellHouse = mustBuySellHouseEsDao.getMustBuySellHouse(booleanQueryBuilder, sort, pageNum, pageSize, topicType, city);

        SearchHits hits = cutPriceSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<MustBuyShellHouseDo> mustBuyShellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                MustBuyShellHouseDo mustBuyShellHouseDo = JSON.parseObject(details, MustBuyShellHouseDo.class);
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (mustBuyShellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(mustBuyShellHouseDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(mustBuyShellHouseDo.getUserId().toString(), city);
                    //认领状态取认领数据
                    mustBuyShellHouseDo.setHouseId(searchHit.getSource().get("claimHouseId").toString());
                    mustBuyShellHouseDo.setHouseTitle(searchHit.getSource().get("claimHouseTitle").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("claimTagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    mustBuyShellHouseDo.setTagsName(tagsName);
                    mustBuyShellHouseDo.setHousePhotoTitle(searchHit.getSource().get("claimHousePhotoTitle").toString());
                }else if(mustBuyShellHouseDo.getIsClaim() == 0){
                    if(StringUtil.isNotNullString(mustBuyShellHouseDo.getProjExpertUserId())){
                        agentBaseDo = agentService.queryAgentInfoByUserId(mustBuyShellHouseDo.getProjExpertUserId(), city);
                    }else {
                        agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
                    }
                }


                mustBuyShellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));

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
            String districtIdsStr = "";
            if (StringTool.isNotEmpty(districtIds)) {
                //区域id排序，与订阅信息匹配
                Arrays.sort(districtIds);
                districtIdsStr = StringTool.IntegerArrayToString(districtIds);
            }
            userSubscribeDetailDo.setDistrictId(districtIdsStr);
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
