package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.SellHouseThemeEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.service.sellhouse.SellHouseThemeRestService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
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
import java.util.Date;
import java.util.List;


@Service
public class SellHouseThemeRestServiceImpl implements SellHouseThemeRestService {

    @Autowired
    private SellHouseThemeEsDao sellHouseThemeEsDao;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private SellHouseService sellHouseService;



    @Override
    public SellHouseThemeDomain getCutPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {
        SellHouseThemeDomain sellHouseThemeDomain = new SellHouseThemeDomain();
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);

        booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));

        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
        Integer pageSize = sellHouseThemeDoQuery.getPageSize();

        SearchResponse cpSellHouse = sellHouseThemeEsDao.getCutPriceShellHouse(booleanQueryBuilder, pageNum, pageSize, city);

        sellHouseThemeDomain = getSellHouseTheme(sellHouseThemeDoQuery,cpSellHouse,1);


        return sellHouseThemeDomain;
    }

    @Override
    public SellHouseThemeDomain getLowPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {

        SellHouseThemeDomain sellHouseThemeDomain = new SellHouseThemeDomain();
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);

        booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));

        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
        Integer pageSize = sellHouseThemeDoQuery.getPageSize();
        SearchResponse lowPriceShellHouse = sellHouseThemeEsDao.getLowPriceShellHouse(booleanQueryBuilder, pageNum, pageSize, city);
        sellHouseThemeDomain = getSellHouseTheme(sellHouseThemeDoQuery,lowPriceShellHouse,2);

        return sellHouseThemeDomain;
    }

    @Override
    public SellHouseThemeDomain getBeSureToSnatchShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {
        SellHouseThemeDomain sellHouseThemeDomain = new SellHouseThemeDomain();
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);

        booleanQueryBuilder.must(QueryBuilders.termQuery("isMustRob", 1));

        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
        Integer pageSize = sellHouseThemeDoQuery.getPageSize();
        SearchResponse beSureToSnatchShellHouse = sellHouseThemeEsDao.getBeSureToSnatchShellHouse(booleanQueryBuilder, pageNum, pageSize, city);
        sellHouseThemeDomain = getSellHouseTheme(sellHouseThemeDoQuery,beSureToSnatchShellHouse,3);

        return sellHouseThemeDomain;
    }

//    @Override
//    public SellHouseThemeDomain getSellHouseTheme(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {
//        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);
//
//        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
//        Integer pageSize = sellHouseThemeDoQuery.getPageSize();
//        SearchResponse beSureToSnatchShellHouse = sellHouseThemeEsDao.getBeSureToSnatchShellHouse(booleanQueryBuilder, pageNum, pageSize, city);
//        SellHouseThemeDomain sellHouseThemeDomain = getSellHouseTheme(sellHouseThemeDoQuery,beSureToSnatchShellHouse,sellHouseThemeDoQuery.getThemeType());
//
//        return sellHouseThemeDomain;
//    }


    /**
     * 专题数据
     * @param sellHouseThemeDoQuery
     * @param cpSellHouse
     * @return
     */
    public SellHouseThemeDomain getSellHouseTheme(SellHouseThemeDoQuery sellHouseThemeDoQuery,SearchResponse cpSellHouse, Integer topicType){

        SellHouseThemeDomain sellHouseThemeDomain = new SellHouseThemeDomain();
        SearchHits hits = cpSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseThemeDo> sellHouseThemeDoList = new ArrayList<>();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHouseThemeDo sellHouseThemeDo = JSON.parseObject(details, SellHouseThemeDo.class);

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHouseThemeDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, sellHouseThemeDo.getHousePhotoTitle());
                if(isDefault==1){
                    sellHouseThemeDo.setIsDefaultImage(1);
                }
                AgentBaseDo agentBaseDo = getAgentByUserId(sellHouseThemeDo,searchHit);


                //sellHouseThemeDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));

                sellHouseThemeDo.setAgentBaseDo(agentBaseDo);
                sellHouseThemeDoList.add(sellHouseThemeDo);
            }
        }
        if (!UserBasic.isLogin()) {
            sellHouseThemeDomain.setSubscribeId(-1);
        } else {
            UserSubscribe userSubscribe = getUserSubscribe(sellHouseThemeDoQuery,topicType);
            if (userSubscribe != null) {
                sellHouseThemeDomain.setSubscribeId(userSubscribe.getId());
            } else {
                sellHouseThemeDomain.setSubscribeId(-1);
            }
        }
        sellHouseThemeDomain.setData(sellHouseThemeDoList);
        sellHouseThemeDomain.setTotalCount(hits.totalHits);


        return sellHouseThemeDomain;
    }



    /**
     * 获取经纪人信息
     * @param sellHouseThemeDo
     * @param searchHit
     * @return
     */
    public AgentBaseDo getAgentByUserId(SellHouseThemeDo sellHouseThemeDo,SearchHit searchHit){
        AgentBaseDo agentBaseDo = new AgentBaseDo();
        if (sellHouseThemeDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseThemeDo.getUserId())) {
            agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseThemeDo.getUserId().toString(), "");
            //认领状态取认领数据
            sellHouseThemeDo.setHouseId(searchHit.getSourceAsMap().get("claimHouseId").toString());
            sellHouseThemeDo.setHouseTitle(searchHit.getSourceAsMap().get("claimHouseTitle").toString());
            List<String> tags = (List<String>) searchHit.getSourceAsMap().get("claimTagsName");
            String[] tagsName = new String[tags.size()];
            tags.toArray(tagsName);
            sellHouseThemeDo.setTagsName(tagsName);
            sellHouseThemeDo.setHousePhotoTitle(searchHit.getSourceAsMap().get("claimHousePhotoTitle").toString());
        }else if(sellHouseThemeDo.getIsClaim() == 0){
            if(StringUtil.isNotNullString(sellHouseThemeDo.getProjExpertUserId())){
                agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseThemeDo.getProjExpertUserId(), "");
            }else {
                agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone")==null?"":searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName")==null?"":searchHit.getSourceAsMap().get("houseProxyName").toString());
                agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany")==null?"":searchHit.getSourceAsMap().get("ofCompany").toString());
                agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
            }
        }
        return agentBaseDo;
    }


    /**
     * 专题筛选
     * @param sellHouseThemeDoQuery
     * @return
     */
    public BoolQueryBuilder filterSellHouseTheme(SellHouseThemeDoQuery sellHouseThemeDoQuery){
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel",0));

//        if(sellHouseThemeDoQuery.getThemeType()==1){
//            booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));
//        }else if (sellHouseThemeDoQuery.getThemeType()==2){
//            booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 2));
//        }else if (sellHouseThemeDoQuery.getThemeType()==3){
//            booleanQueryBuilder.must(QueryBuilders.termQuery("isMustRob", 3));
//        }

        if (StringTool.isNotEmpty(sellHouseThemeDoQuery.getIsNew())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", sellHouseThemeDoQuery.getIsNew()));
        }

        //区域
        Integer[] districtIds = sellHouseThemeDoQuery.getDistrictIds();
        if (StringTool.isNotEmpty(districtIds)) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("areaId", districtIds));
        }

        //价格区间
        double beginPrice = sellHouseThemeDoQuery.getBeginPrice();
        double endPrice = sellHouseThemeDoQuery.getEndPrice();
        if (beginPrice != 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice).lte(endPrice));
        } else if (beginPrice == 0 && endPrice != 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(endPrice));
        } else if (beginPrice != 0 && endPrice == 0) {
            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice));
        }
        //户型(室)
        if (StringTool.isNotEmpty(sellHouseThemeDoQuery.getLayoutId()) && sellHouseThemeDoQuery.getLayoutId().length!=0) {
            Integer[] layoutId = sellHouseThemeDoQuery.getLayoutId();
            booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
        }

        return booleanQueryBuilder;
    }




    /**
     * 是否订阅
     * @param sellHouseThemeDoQuery
     * @param topicType
     * @return
     */
    public UserSubscribe getUserSubscribe(SellHouseThemeDoQuery sellHouseThemeDoQuery, Integer topicType){

            UserBasic userBasic = UserBasic.getCurrent();
            UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
            userSubscribeDetailDo.setTopicType(topicType);
            String districtIdsStr = "";
            if (StringTool.isNotEmpty(sellHouseThemeDoQuery.getDistrictIds()) && sellHouseThemeDoQuery.getDistrictIds().length!=0) {
                //区域id排序，与订阅信息匹配
                Arrays.sort(sellHouseThemeDoQuery.getDistrictIds());
                districtIdsStr = StringTool.IntegerArrayToString(sellHouseThemeDoQuery.getDistrictIds());
            }
            userSubscribeDetailDo.setDistrictId(districtIdsStr);
            userSubscribeDetailDo.setBeginPrice(sellHouseThemeDoQuery.getBeginPrice());
            userSubscribeDetailDo.setEndPrice(sellHouseThemeDoQuery.getEndPrice());

            UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer.valueOf(userBasic.getUserId()));

        return userSubscribe;
    }


}
