package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.sellhouse.NearbySellHouseEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellHouseServiceImpl implements SellHouseService{

    @Autowired
    private AgentService agentService;
    @Autowired
    private SellHouseEsDao sellHouseEsDao;
    @Autowired
    private NearbySellHouseEsDao nearbySellHouseEsDao;
    @Autowired
    private AgentHouseEsDao agentHouseEsDao;
    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;
    @Autowired
    private FavoriteRestService favoriteRestService;
    private  final  Integer FAVORITE_ESF=2;
    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private CommunityRestService communityRestService;

    @Override
    public SellHouseDetailsDo getSellHouseByHouseId(String houseId) {

        //二手房房源详情
        SellAndClaimHouseDetailsDo sellAndClaimHouseDetailsDo = new SellAndClaimHouseDetailsDo();
        SellHouseDetailsDo sellHouseDetailsDo = new SellHouseDetailsDo();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if ("FS".equals(houseId.substring(0,2))){
            booleanQueryBuilder.must(QueryBuilders.termQuery("claimHouseId", houseId));
        }else {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel",0));
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        AgentBaseDo agentBaseDo = new AgentBaseDo();
        if (searchHists.length>0){
            UserBasic userBasic = UserBasic.getCurrent();
            for (SearchHit searchHit : searchHists) {
                String sourceAsString = searchHit.getSourceAsString();
                sellAndClaimHouseDetailsDo = JSON.parseObject(sourceAsString,SellAndClaimHouseDetailsDo.class);
                BeanUtils.copyProperties(sellAndClaimHouseDetailsDo,sellHouseDetailsDo);
                if (StringTool.isNotEmpty(sellAndClaimHouseDetailsDo.getUserId())&&sellAndClaimHouseDetailsDo.getIsClaim()==1){
                    //经纪人信息
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDetailsDo.getUserId().toString());

                    sellHouseDetailsDo.setTagsName(sellAndClaimHouseDetailsDo.getClaimTagsName());
                    sellHouseDetailsDo.setHouseTitle(sellAndClaimHouseDetailsDo.getClaimHouseTitle());
                    sellHouseDetailsDo.setHouseId(sellAndClaimHouseDetailsDo.getClaimHouseId());
                    sellHouseDetailsDo.setHousePhotoTitle(sellAndClaimHouseDetailsDo.getClaimHousePhotoTitle());
//                    Date date = new Date();
//                    if(StringTool.isNotEmpty(searchHit.getSource().get("price_increase_decline"))){
//                        if(Integer.valueOf(searchHit.getSource().get("price_increase_decline").toString())>0){
//                            int claimDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("claim_time").toString()));
//                            if(claimDays>=0 && claimDays<30){
//                                sellHouseDetailsDo.setHousePhotoTitleTags(Integer.valueOf(sellHouseDetailsDo.getPriceIncreaseDecline()));
//                            }
//                        }else {
//                            int importFlag = -1;
//                            if(StringTool.isNotEmpty(searchHit.getSource().get("import_time"))){
//                                int importDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("import_time").toString()));
//                                if(importDays>=0 && importDays<7){
//                                    importFlag = 3;
//                                    sellHouseDetailsDo.setHousePhotoTitleTags(importFlag);
//                                }else{
//                                    sellHouseDetailsDo.setHousePhotoTitleTags(importFlag);
//                                }
//                            }
//                        }
//                    }






                }else {
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
                }
                sellHouseDetailsDo.setTypeCounts(communityRestService.getCountByBuildTags());
                sellHouseDetailsDo.setAgentBaseDo(agentBaseDo);
            }

            if(StringTool.isNotEmpty(userBasic)){
                IsFavoriteDo isFavoriteDo=new IsFavoriteDo();
                isFavoriteDo.setUserId(Integer.valueOf(userBasic.getUserId()));
                isFavoriteDo.setHouseId(sellHouseDetailsDo.getHouseId());
                boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_ESF,isFavoriteDo);
                sellHouseDetailsDo.setIsFavorite(isFavorite);
            }
            if (sellHouseDetailsDo.getHouseHeating()==0){
                sellHouseDetailsDo.setHouseHeatingName("未知");
            }
             if (sellHouseDetailsDo.getHouseHeating()==1){
                sellHouseDetailsDo.setHouseHeatingName("集中供暖");
            }
             if (sellHouseDetailsDo.getHouseHeating()==2){
                sellHouseDetailsDo.setHouseHeatingName("自供暖");
            }
        }

        return sellHouseDetailsDo;
    }



    /**
     * 认领二手房房源经纪人
     * @param houseId
     * @return
     */
    @Override
    public AgentsBySellHouseDo getAgentByHouseId(Integer houseId){

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
        SearchResponse searchResponse = agentHouseEsDao.getAgentHouseByHouseId(boolQueryBuilder);
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits.length>0){
            long time = System.currentTimeMillis();
            long index = (time / 600000) % hits.length;
            String details = hits[(int) index].getSourceAsString();
            AgentsBySellHouseDo agentsBySellHouseDo = JSON.parseObject(details,AgentsBySellHouseDo.class);
            return agentsBySellHouseDo;
        }else{
            return null;
        }
    }

    /**
     * 二手房，房源列表
     * @param sellHouseQueryDo
     * @return
     */
    @Override
    public SellHouseDomain getSellHouseByChoose(SellHouseDoQuery sellHouseQueryDo) {

        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //默认列表暂时不做搜索，暂注掉
        //boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseQueryDo);
        //设置搜索规则
//        Date date = new Date();
//        String pastDateOfMonth = DateUtil.getPastDate(29)+" 00:00:00";
//        String pastDateOfWeek = DateUtil.getPastDate(6)+" 00:00:00";
//        String nowDate = DateUtil.format(date)+" 23:59:59";
//        BoolQueryBuilder queryBuilderOfMonth = QueryBuilders.boolQuery();


//        queryBuilderOfMonth.must(QueryBuilders.termQuery("is_claim",1));
//        queryBuilderOfMonth.must(QueryBuilders.termQuery("isRecommend",0));
//        queryBuilderOfMonth.must(QueryBuilders.termsQuery("isDel", "0"));
//        queryBuilderOfMonth.must(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("isCutPrice").gt(0)));
//        queryBuilderOfMonth.must(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("claim_time").gt(pastDateOfMonth).lte(nowDate)));
//        queryBuilderOfMonth.must(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("price_increase_decline").gt(0)));

//        boolQueryBuilder.should(queryBuilderOfMonth);

        BoolQueryBuilder queryBuilderOfWeek = QueryBuilders.boolQuery();
        queryBuilderOfWeek.must(QueryBuilders.termQuery("is_claim",0));
        queryBuilderOfWeek.must(QueryBuilders.termQuery("isNew",1));
        queryBuilderOfWeek.must(QueryBuilders.termsQuery("isDel", "0"));
//        queryBuilderOfWeek.must(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("import_time").gt(pastDateOfWeek).lte(nowDate)));
        boolQueryBuilder.should(queryBuilderOfWeek);
        FunctionScoreQueryBuilder query = getQuery(sellHouseQueryDo,boolQueryBuilder);

          //这个注释的方法有问题，暂时不删除
//        Date date = new Date();
//        String pastDateOfMonth = DateUtil.getPastDate(30)+" 00:00:00";
//        String pastDateOfWeek = DateUtil.getPastDate(7)+" 00:00:00";
//        String nowDate = DateUtil.format(date)+" 00:00:00";
//        //获取认领房源中30天内有价格变动的房源
//        BoolQueryBuilder queryBuilderOfMonth = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",1));
//        boolQueryBuilder.must(QueryBuilders.termQuery("isRecommend",0));
//        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
//        queryBuilderOfMonth.should(QueryBuilders.rangeQuery("claim_time").gt(pastDateOfMonth).lte(nowDate));
//        queryBuilderOfMonth.should(QueryBuilders.rangeQuery("price_increase_decline").gt(0));
//        boolQueryBuilder.must(queryBuilderOfMonth);
//        //获取7天内导入的，并被认领的
//        BoolQueryBuilder queryBuilderOfWeek = QueryBuilders.boolQuery();
//        queryBuilderOfWeek.should(QueryBuilders.rangeQuery("import_time").gt(pastDateOfWeek).lte(nowDate));
//        boolQueryBuilder.must(queryBuilderOfWeek);
//        FunctionScoreQueryBuilder query = getQuery(sellHouseQueryDo,boolQueryBuilder);
        sellHouseQueryDo.setPageSize(5);
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query,sellHouseQueryDo.getDistance(),sellHouseQueryDo.getKeyword(),sellHouseQueryDo.getPageNum(),sellHouseQueryDo.getPageSize());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHouseDo sellHouseDo = JSON.parseObject(details,SellHouseDo.class);
//                if(StringTool.isNotEmpty(searchHit.getSource().get("price_increase_decline"))){
//                    if(Integer.valueOf(searchHit.getSource().get("price_increase_decline").toString())>0){
//                        int claimDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("claim_time").toString()));
//                        if(claimDays>=0 && claimDays<30){
//                            sellHouseDo.setHousePhotoTitleTags(Integer.valueOf(sellHouseDo.getPriceIncreaseDecline()));
//                        }
//                    }else {
//                        int importFlag = -1;
//                        if(StringTool.isNotEmpty(searchHit.getSource().get("import_time"))){
//                            int importDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("import_time").toString()));
//                            if(importDays>=0 && importDays<7){
//                                importFlag = 3;
//                                sellHouseDo.setHousePhotoTitleTags(importFlag);
//                            }else{
//                                sellHouseDo.setHousePhotoTitleTags(importFlag);
//                            }
//                        }
//                    }
//                }

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(sellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHouseDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
                }

                sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags());
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);

            }
            sellHouseDomain.setSellHouseList(sellHouseDos);
            sellHouseDomain.setTotal((int)hits.getTotalHits());
        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }


        return sellHouseDomain;
    }

    public static void main(String[] args) {
        Date date = new Date();
        int claimDays = DateUtil.daysBetween(date,DateUtil.getStringToDate("2018-05-25 09:58:48"))-1;

        System.out.println(claimDays);
    }


    /**
     * 查询二手房推荐房源
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getRecommendSellHouse(SellHouseDoQuery sellHouseDoQuery) {


        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim",1));
        boolQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));
        FunctionScoreQueryBuilder query = getQuery(sellHouseDoQuery,boolQueryBuilder);
        SearchResponse searchResponse = sellHouseEsDao.getRecommendSellHouse(query,sellHouseDoQuery.getUid(),sellHouseDoQuery.getPageSize());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if(searchHists.length > 0){
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHouseDo sellHouseDo = JSON.parseObject(details,SellHouseDo.class);

//                if(StringTool.isNotEmpty(searchHit.getSource().get("price_increase_decline"))){
//                    if(Integer.valueOf(searchHit.getSource().get("price_increase_decline").toString())>0){
//                        int claimDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("claim_time").toString()));
//                        if(claimDays>=0 && claimDays<30){
//                            sellHouseDo.setHousePhotoTitleTags(Integer.valueOf(sellHouseDo.getPriceIncreaseDecline()));
//                        }
//                    }else {
//                        int importFlag = -1;
//                        if(StringTool.isNotEmpty(searchHit.getSource().get("import_time"))){
//                            int importDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("import_time").toString()));
//                            if(importDays>=0 && importDays<7){
//                                importFlag = 3;
//                                sellHouseDo.setHousePhotoTitleTags(importFlag);
//                            }else{
//                                sellHouseDo.setHousePhotoTitleTags(importFlag);
//                            }
//                        }
//                    }
//                }

                sellHouseDo.setUid(searchHit.getSortValues()[0].toString());
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(sellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHouseDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString());

                }else{
//                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
//                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
//                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());
//                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto").toString());
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSourceAsMap().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSourceAsMap().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                }
                sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags());
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);

            }
            sellHouseDomain.setSellHouseList(sellHouseDos);
            sellHouseDomain.setTotal((int)searchResponse.getHits().getTotalHits());
        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }


        return sellHouseDomain;
    }

    @Override
    public SellHouseSearchDomain getSellHouseList(SellHouseDoQuery sellHouseDoQuery) {

        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        SellHousesSearchDo  sellHousesSearchDo= new SellHousesSearchDo();


        //其他筛选条件
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);


        //过滤为删除
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
//        booleanQueryBuilder.mustNot(QueryBuilders.termsQuery("is_parent_claim", "1"));

        FunctionScoreQueryBuilder query = null;
        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

        Map<String,Double> map = new HashMap<>();
        map.put("lat",sellHouseDoQuery.getLat());
        map.put("lon",sellHouseDoQuery.getLon());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));
        //设置高斯函数
        GaussDecayFunctionBuilder functionBuilder = null;
        FunctionScoreQueryBuilder queryKmBuilder = null;
        if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
            functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("housePlotLocation",json,sellHouseDoQuery.getDistance()+"km",sellHouseDoQuery.getDistance()+"km");
            //获取5km内所有的二手房

        }
        queryKmBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);
        if (StringUtil.isNotNullString(sellHouseDoQuery.getKeyword())) {
            List<String> searchKeyword = filterSellHouseChooseService.filterKeyWords(sellHouseDoQuery.getKeyword());
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
            if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()+1];
            }else{
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                int searchAreasSize = searchKeyword.size();
                for(int i=0 ;i<searchKeyword.size();i++){
                    QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName",searchKeyword.get(i));
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize-i);
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))) {
                int searchDistrictsSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }else{
                int searchTermSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }


            if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }else{
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }else{
            if(StringTool.isNotEmpty(sellHouseDoQuery.getDistance())){
                query = QueryBuilders.functionScoreQuery(queryKmBuilder,functionBuilder).boost(10).maxBoost(100)
                        .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }else{
                query = QueryBuilders.functionScoreQuery(queryKmBuilder).boost(10).maxBoost(100)
                        .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }
        List<SellHousesSearchDo> sellHousesSearchDos =new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo=new ClaimSellHouseDo();
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query,sellHouseDoQuery.getDistance(),sellHouseDoQuery.getKeyword(),sellHouseDoQuery.getPageNum(),sellHouseDoQuery.getPageSize());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length > 0){
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details=searchHit.getSourceAsString();
                sellHousesSearchDo=JSON.parseObject(details,SellHousesSearchDo.class);
                claimSellHouseDo=JSON.parseObject(details,ClaimSellHouseDo.class);
                if (null!=claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim()==1)
                {   //将认领信息替换
                    sellHousesSearchDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    sellHousesSearchDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    sellHousesSearchDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    sellHousesSearchDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if(claimSellHouseDo.getIsClaim()==1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString());


//                    if(StringTool.isNotEmpty(searchHit.getSource().get("price_increase_decline"))){
//                        if(Integer.valueOf(searchHit.getSource().get("price_increase_decline").toString())>0){
//                            int claimDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("claim_time").toString()));
//                            if(claimDays>=0 && claimDays<30){
//                                sellHousesSearchDo.setHousePhotoTitleTags(Integer.valueOf(sellHousesSearchDo.getPriceIncreaseDecline()));
//                            }
//                        }else {
//                            int importFlag = -1;
//                            if(StringTool.isNotEmpty(searchHit.getSource().get("import_time"))){
//                                int importDays = DateUtil.daysBetween(date,DateUtil.getStringToDate(searchHit.getSource().get("import_time").toString()));
//                                if(importDays>=0 && importDays<7){
//                                    importFlag = 3;
//                                    sellHousesSearchDo.setHousePhotoTitleTags(importFlag);
//                                }else{
//                                    sellHousesSearchDo.setHousePhotoTitleTags(importFlag);
//                                }
//                            }
//                        }
//                    }


                }else{
//                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
//                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
//                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
//                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());

                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSourceAsMap().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSourceAsMap().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                }
                sellHousesSearchDo.setTypeCounts(communityRestService.getCountByBuildTags());
                sellHousesSearchDo.setAgentBaseDo(agentBaseDo);
                sellHousesSearchDos.add(sellHousesSearchDo);
                //增加地铁与房子之间的距离
                String keys="";
                if(null!=sellHouseDoQuery.getSubwayLineId())
                {
                    keys+=sellHouseDoQuery.getSubwayLineId().toString();
                }
                if (null!=sellHouseDoQuery.getSubwayStationId())
                {
                    keys+= "$"+sellHouseDoQuery.getSubwayStationId();
                }
                if (!"".equals(keys) && null!=sellHousesSearchDo.getSubwayDistince())
                {
                    sellHousesSearchDo.setSubwayDistanceInfo(sellHousesSearchDo.getSubwayDistince().get(keys).toString());
                }



            }
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int)searchResponse.getHits().getTotalHits());
        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }


        return sellHouseSearchDomain;

    }



    public FunctionScoreQueryBuilder getQuery(SellHouseDoQuery sellHouseDoQuery,BoolQueryBuilder boolQueryBuilder){
        FunctionScoreQueryBuilder query = null;
        List<String> searchKeyword = new ArrayList<>();
        if(StringUtil.isNotNullString(sellHouseDoQuery.getKeyword())){
            searchKeyword = filterSellHouseChooseService.filterKeyWords(sellHouseDoQuery.getKeyword());
        }

        if (StringTool.isNotBlank(sellHouseDoQuery.getKeyword())){
            if(searchKeyword!=null && searchKeyword.size() > 0){
                int searchTermSize = searchKeyword.size();
                FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchTermSize];
                if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                    for(int i=0 ;i<searchKeyword.size();i++){
                        QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName",searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize-i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }else if(StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))){
                    for(int i=0 ;i<searchKeyword.size();i++){
                        QueryBuilder filter = QueryBuilders.termsQuery("area",searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize-i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }else {
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }
                query =QueryBuilders.functionScoreQuery(boolQueryBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FiltersFunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }else {
            query =QueryBuilders.functionScoreQuery(boolQueryBuilder);
        }
        return query;

    }



    @Override
    public SellHouseBeSureToSnatchDomain getBeSureToSnatchList(SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery) {
        SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDomain=new SellHouseBeSureToSnatchDomain();
        NearBySellHouseQueryDo nearBySellHouseQueryDo=new NearBySellHouseQueryDo();
        BeanUtils.copyProperties(sellHouseBeSureToSnatchDoQuery,nearBySellHouseQueryDo);
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterChoose(nearBySellHouseQueryDo);
        List<SellHouseBeSureToSnatchDo> sellHouseBeSureToSnatchDos=new ArrayList<>();
        FieldSortBuilder sortFile=null;
        Integer subscribeId=-1;
        if (null!=sellHouseBeSureToSnatchDoQuery.getIsNew())
        {
            booleanQueryBuilder.must(QueryBuilders.termQuery("isNew",1));
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("isMustRob",1));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim",0));
//        booleanQueryBuilder.must(QueryBuilders.termQuery("status",0));
        booleanQueryBuilder.must(QueryBuilders.termQuery("isDel",0));

        if (null!=sellHouseBeSureToSnatchDoQuery.getSortFile() && null!=sellHouseBeSureToSnatchDoQuery.getSort() )
        {
            if(sellHouseBeSureToSnatchDoQuery.getSort().equals(1))
            {
                sortFile= SortBuilders.fieldSort(sellHouseBeSureToSnatchDoQuery.getSortFile()).order(SortOrder.DESC);
            }
            else
            {
                sortFile= SortBuilders.fieldSort(sellHouseBeSureToSnatchDoQuery.getSortFile()).order(SortOrder.ASC);
            }
        }
        SearchResponse searchResponse= sellHouseEsDao.getBeSureToSnatchList(booleanQueryBuilder,sellHouseBeSureToSnatchDoQuery.getPageNum(),sellHouseBeSureToSnatchDoQuery.getPageSize(),sortFile);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit searchHit : searchHists)
        {
            String details = "";
            Object [] sort=searchHit.getSortValues();
            details=searchHit.getSourceAsString();
            SellHouseBeSureToSnatchDo sellHouseBeSureToSnatchDo=JSON.parseObject(details,SellHouseBeSureToSnatchDo.class);
            AgentBaseDo agentBaseDo = new AgentBaseDo();
            if(sellHouseBeSureToSnatchDo.getIsClaim().equals(1))
            {
                sellHouseBeSureToSnatchDo.setHouseId(sellHouseBeSureToSnatchDo.getClaimHouseId());
                sellHouseBeSureToSnatchDo.setHousePhotoTitle(sellHouseBeSureToSnatchDo.getClaimHousePhotoTitle());
                sellHouseBeSureToSnatchDo.setTagsName(sellHouseBeSureToSnatchDo.getClaimTagsName());
                sellHouseBeSureToSnatchDo.setHouseTitle(sellHouseBeSureToSnatchDo.getClaimHouseTitle());

                if (StringTool.isNotEmpty(sellHouseBeSureToSnatchDo.getUserId())){
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseBeSureToSnatchDo.getUserId().toString());
                }

            }else{
                agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
                agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
                agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto")==null?"":searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());
            }
            sellHouseBeSureToSnatchDo.setAgentBaseDo(agentBaseDo);

            sellHouseBeSureToSnatchDo.setTypeCounts(communityRestService.getCountByBuildTags());

            sellHouseBeSureToSnatchDo.setSort(sort[0]);
            sellHouseBeSureToSnatchDos.add(sellHouseBeSureToSnatchDo);
        }
        sellHouseBeSureToSnatchDomain.setData(sellHouseBeSureToSnatchDos);
        UserBasic userBasic = UserBasic.getCurrent();
        if (null!=userBasic)
        {
            UserSubscribeDetailDo userSubscribeDetailDo=new UserSubscribeDetailDo();
            BeanUtils.copyProperties(sellHouseBeSureToSnatchDoQuery,userSubscribeDetailDo);
            userSubscribeDetailDo.setTopicType(3);
            userSubscribeDetailDo.setBeginPrice((int) sellHouseBeSureToSnatchDoQuery.getBeginPrice());
            userSubscribeDetailDo.setEndPrice((int) sellHouseBeSureToSnatchDoQuery.getEndPrice());
            String districtIdsStr = "";
            Integer[] districtIds = sellHouseBeSureToSnatchDoQuery.getDistrictIds();
            if (StringTool.isNotEmpty(districtIds)) {
                //区域id排序，与订阅信息匹配
                Arrays.sort(districtIds);
                districtIdsStr = StringTool.IntegerArrayToString(districtIds);
            }
            userSubscribeDetailDo.setDistrictId(districtIdsStr);
            UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer.parseInt(userBasic.getUserId()));
            if (null!=userSubscribe)
            {
                subscribeId=userSubscribe.getId();
            }
        }
        sellHouseBeSureToSnatchDomain.setSubscribeId(subscribeId);
        sellHouseBeSureToSnatchDomain.setTotalCount(hits.totalHits);
         return sellHouseBeSureToSnatchDomain ;
    }

    /**
     * 获取推荐房源
     *
     * @param recommendEsf5DoQuery
     * @return
     */
    @Override
    public SellHouseSearchDomain getRecommendEsf5(RecommendEsf5DoQuery recommendEsf5DoQuery) {
        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        BoolQueryBuilder recommendEsf5 = filterSellHouseChooseService.getRecommendEsf5(recommendEsf5DoQuery);
        FieldSortBuilder sortFile = null;
        if (null != recommendEsf5DoQuery.getSortFile() && null != recommendEsf5DoQuery.getSort()) {
            if (recommendEsf5DoQuery.getSort().equals(1)) {
                sortFile = SortBuilders.fieldSort(recommendEsf5DoQuery.getSortFile()).order(SortOrder.DESC);
            } else {
                sortFile = SortBuilders.fieldSort(recommendEsf5DoQuery.getSortFile()).order(SortOrder.ASC);
            }
        }
        SearchResponse RecommendEsf5List = sellHouseEsDao.getBeSureToSnatchList(recommendEsf5,
                recommendEsf5DoQuery.getPageNum(), recommendEsf5DoQuery.getPageSize(), sortFile);
        SearchHits hits = RecommendEsf5List.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHousesSearchDo> sellHousesSearchDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHousesSearchDo sellHousesSearchDo = JSON.parseObject(details, SellHousesSearchDo.class);
                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (sellHousesSearchDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString());
                    //认领状态取认领数据
                    sellHousesSearchDo.setHouseId(searchHit.getSource().get("claimHouseId").toString());
                    sellHousesSearchDo.setHouseTitle(searchHit.getSource().get("claimHouseTitle").toString());
                    List<String> tags = (List<String>) searchHit.getSource().get("claimTagsName");
                    String[] tagsName = new String[tags.size()];
                    tags.toArray(tagsName);
                    sellHousesSearchDo.setTagsName(tagsName);
                    sellHousesSearchDo.setHousePhotoTitle(searchHit.getSource().get("claimHousePhotoTitle").toString());
                } else {
                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany").toString());
                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone").toString());
                }
                sellHousesSearchDo.setAgentBaseDo(agentBaseDo);
                sellHousesSearchDo.setTypeCounts(communityRestService.getCountByBuildTags());
                sellHousesSearchDos.add(sellHousesSearchDo);
            }
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int) RecommendEsf5List.getHits().getTotalHits());
        } else {
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND, "二手房列表为空");
        }
        return sellHouseSearchDomain;
    }

}
