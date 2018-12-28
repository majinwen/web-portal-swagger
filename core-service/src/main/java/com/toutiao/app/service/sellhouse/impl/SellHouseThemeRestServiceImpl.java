package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseThemeEsDao;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.service.sellhouse.SellHouseThemeRestService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
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
    private PlotEsDao plotEsDao;
//
//    @Autowired
//    private AgentService agentService;

    @Autowired
    private SellHouseService sellHouseService;



    @Override
    public CutPriceSellHouseThemeDomain getCutPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {
//        CutPriceSellHouseThemeDomain cutPriceSellHouseThemeDomain = new CutPriceSellHouseThemeDomain();
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);

        booleanQueryBuilder.must(QueryBuilders.termQuery("isCutPrice", 1));
        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
        Integer pageSize = sellHouseThemeDoQuery.getPageSize();

        SearchResponse cpSellHouse = sellHouseThemeEsDao.getCutPriceShellHouse(booleanQueryBuilder, pageNum, pageSize, city);

        CutPriceSellHouseThemeDomain cutPriceSellHouseThemeDomain = getCutPriceSellHouseTheme(cpSellHouse);

        if (!UserBasic.isLogin()) {
            cutPriceSellHouseThemeDomain.setSubscribeId(-1);
        } else {
            UserSubscribe userSubscribe = getUserSubscribe(sellHouseThemeDoQuery,1);
            if (userSubscribe != null) {
                cutPriceSellHouseThemeDomain.setSubscribeId(userSubscribe.getId());
            } else {
                cutPriceSellHouseThemeDomain.setSubscribeId(-1);
            }
        }
        return cutPriceSellHouseThemeDomain;
    }

    @Override
    public LowPriceSellHouseThemeDomain getLowPriceShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {

        //LowPriceSellHouseThemeDomain lowPriceSellHouseThemeDomain = new LowPriceSellHouseThemeDomain();
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);

        booleanQueryBuilder.must(QueryBuilders.termQuery("isLowPrice", 1));
        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
        Integer pageSize = sellHouseThemeDoQuery.getPageSize();
        SearchResponse lowPriceShellHouse = sellHouseThemeEsDao.getLowPriceShellHouse(booleanQueryBuilder, pageNum, pageSize, city);
        LowPriceSellHouseThemeDomain lowPriceSellHouseThemeDomain = getLowPriceSellHouseTheme(lowPriceShellHouse);
        if (!UserBasic.isLogin()) {
            lowPriceSellHouseThemeDomain.setSubscribeId(-1);
        } else {
            UserSubscribe userSubscribe = getUserSubscribe(sellHouseThemeDoQuery,2);
            if (userSubscribe != null) {
                lowPriceSellHouseThemeDomain.setSubscribeId(userSubscribe.getId());
            } else {
                lowPriceSellHouseThemeDomain.setSubscribeId(-1);
            }
        }
        return lowPriceSellHouseThemeDomain;
    }

    @Override
    public HotSellHouseThemeDomain getBeSureToSnatchShellHouse(SellHouseThemeDoQuery sellHouseThemeDoQuery, String city) {
        //HotSellHouseThemeDomain hotSellHouseThemeDomain = new HotSellHouseThemeDomain();
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseTheme(sellHouseThemeDoQuery);

        booleanQueryBuilder.must(QueryBuilders.termQuery("isMustRob", 1));
        Integer pageNum = sellHouseThemeDoQuery.getPageNum();
        Integer pageSize = sellHouseThemeDoQuery.getPageSize();
        SearchResponse beSureToSnatchShellHouse = sellHouseThemeEsDao.getBeSureToSnatchShellHouse(booleanQueryBuilder, pageNum, pageSize, city);
        HotSellHouseThemeDomain hotSellHouseThemeDomain = getHotSellHouseTheme(beSureToSnatchShellHouse);
        if (!UserBasic.isLogin()) {
            hotSellHouseThemeDomain.setSubscribeId(-1);
        } else {
            UserSubscribe userSubscribe = getUserSubscribe(sellHouseThemeDoQuery,3);
            if (userSubscribe != null) {
                hotSellHouseThemeDomain.setSubscribeId(userSubscribe.getId());
            } else {
                hotSellHouseThemeDomain.setSubscribeId(-1);
            }
        }
        return hotSellHouseThemeDomain;
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
     * @param cpSellHouse
     * @return
     */
    public CutPriceSellHouseThemeDomain getCutPriceSellHouseTheme(SearchResponse cpSellHouse){

        CutPriceSellHouseThemeDomain cutPrice = new CutPriceSellHouseThemeDomain();
        SearchHits hits = cpSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<CutPriceSellHouseThemeDo> cutPriceSellHouseThemeDoList = new ArrayList<>();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                CutPriceSellHouseThemeDo cutPriceSellHouseThemeDo = JSON.parseObject(details, CutPriceSellHouseThemeDo.class);
                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = cutPriceSellHouseThemeDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, cutPriceSellHouseThemeDo.getHousePhotoTitle());
                if(isDefault==1){
                    cutPriceSellHouseThemeDo.setIsDefaultImage(1);
                }

                String titlePhoto = cutPriceSellHouseThemeDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http://")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                cutPriceSellHouseThemeDo.setHousePhotoTitle(titlePhoto);
                cutPriceSellHouseThemeDoList.add(cutPriceSellHouseThemeDo);
            }
        }
        cutPrice.setData(cutPriceSellHouseThemeDoList);
        cutPrice.setTotalCount(hits.totalHits);
        return cutPrice;
    }


    public HotSellHouseThemeDomain getHotSellHouseTheme(SearchResponse cpSellHouse){

        HotSellHouseThemeDomain hot = new HotSellHouseThemeDomain();
        SearchHits hits = cpSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<HotSellHouseThemeDo> houseThemeDoList = new ArrayList<>();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                HotSellHouseThemeDo hotSellHouseThemeDo = JSON.parseObject(details, HotSellHouseThemeDo.class);
                if(StringTool.isNotEmpty(hotSellHouseThemeDo.getNewcode())){
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                    boolQueryBuilder.must(QueryBuilders.termQuery("id",hotSellHouseThemeDo.getNewcode().toString()));
                    SearchResponse communityRes = plotEsDao.queryTagsById(boolQueryBuilder, CityUtils.getCity());
                    SearchHits communityHit = communityRes.getHits();
                    SearchHit[] communityHits = communityHit.getHits();
                    for (SearchHit  tagsHits: communityHits){
                        hotSellHouseThemeDo.setRecommendBuildTagsName((List<String>) tagsHits.getSourceAsMap().get("recommendBuildTagsName"));
                        hotSellHouseThemeDo.setLabel((List<String>) tagsHits.getSourceAsMap().get("label"));
                        hotSellHouseThemeDo.setDistrictHotSort(Integer.valueOf(tagsHits.getSourceAsMap().get("districtHotSort")==null?"0":tagsHits.getSourceAsMap().get("districtHotSort").toString()));

                    }
                }
                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = hotSellHouseThemeDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, hotSellHouseThemeDo.getHousePhotoTitle());
                if(isDefault==1){
                    hotSellHouseThemeDo.setIsDefaultImage(1);
                }

                String titlePhoto = hotSellHouseThemeDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http://")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                hotSellHouseThemeDo.setHousePhotoTitle(titlePhoto);

                houseThemeDoList.add(hotSellHouseThemeDo);
            }
        }

        hot.setData(houseThemeDoList);
        hot.setTotalCount(hits.totalHits);
        return hot;
    }




    public LowPriceSellHouseThemeDomain getLowPriceSellHouseTheme(SearchResponse cpSellHouse){

        LowPriceSellHouseThemeDomain lowPrice = new LowPriceSellHouseThemeDomain();
        SearchHits hits = cpSellHouse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<LowPriceSellHouseThemeDo> lowPriceSellHouseThemeDoList = new ArrayList<>();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                LowPriceSellHouseThemeDo lowPriceSellHouseThemeDo = JSON.parseObject(details, LowPriceSellHouseThemeDo.class);
                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = lowPriceSellHouseThemeDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime ,date, lowPriceSellHouseThemeDo.getHousePhotoTitle());
                if(isDefault==1){
                    lowPriceSellHouseThemeDo.setIsDefaultImage(1);
                }
                String titlePhoto = lowPriceSellHouseThemeDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http://")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                lowPriceSellHouseThemeDo.setHousePhotoTitle(titlePhoto);
                lowPriceSellHouseThemeDoList.add(lowPriceSellHouseThemeDo);
            }
        }

        lowPrice.setData(lowPriceSellHouseThemeDoList);
        lowPrice.setTotalCount(hits.totalHits);
        return lowPrice;
    }



    /**
     * 获取经纪人信息
     * @param sellHouseThemeDo
     * @param searchHit
     * @return
     */
//    public AgentBaseDo getAgentByUserId(SellHouseThemeDo sellHouseThemeDo,SearchHit searchHit){
//        AgentBaseDo agentBaseDo = new AgentBaseDo();
//        if (sellHouseThemeDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseThemeDo.getUserId())) {
//            agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseThemeDo.getUserId().toString(), "");
//            //认领状态取认领数据
//            sellHouseThemeDo.setHouseId(searchHit.getSourceAsMap().get("claimHouseId").toString());
//            sellHouseThemeDo.setHouseTitle(searchHit.getSourceAsMap().get("claimHouseTitle").toString());
//            List<String> tags = (List<String>) searchHit.getSourceAsMap().get("claimTagsName");
//            String[] tagsName = new String[tags.size()];
//            tags.toArray(tagsName);
//            sellHouseThemeDo.setTagsName(tagsName);
//            sellHouseThemeDo.setHousePhotoTitle(searchHit.getSourceAsMap().get("claimHousePhotoTitle").toString());
//        }else if(sellHouseThemeDo.getIsClaim() == 0){
//            if(StringUtil.isNotNullString(sellHouseThemeDo.getProjExpertUserId())){
//                agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseThemeDo.getProjExpertUserId(), "");
//            }else {
//                agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone")==null?"":searchHit.getSourceAsMap().get("houseProxyPhone").toString());
//                agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName")==null?"":searchHit.getSourceAsMap().get("houseProxyName").toString());
//                agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany")==null?"":searchHit.getSourceAsMap().get("ofCompany").toString());
//                agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
//            }
//        }
//        return agentBaseDo;
//    }


    /**
     * 专题筛选
     * @param sellHouseThemeDoQuery
     * @return
     */
    public BoolQueryBuilder filterSellHouseTheme(SellHouseThemeDoQuery sellHouseThemeDoQuery){
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel",0));

        if (StringTool.isNotEmpty(sellHouseThemeDoQuery.getIsNew())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew", sellHouseThemeDoQuery.getIsNew()));
        }

        //区域
        Integer[] districtIds = sellHouseThemeDoQuery.getDistrictIds();
        if (StringTool.isNotEmpty(districtIds)) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("areaId", districtIds));
        }
        //商圈
        Integer[] areaIds = sellHouseThemeDoQuery.getAreaIds();
        if (StringTool.isNotEmpty(areaIds)) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId", areaIds));
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
