package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.sellhouse.NearbySellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.NearSellHouseRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NearSellHouseRestServiceImpl implements NearSellHouseRestService {

    @Autowired
    private FilterSellHouseChooseService filterSellHouseChooseService;

    @Autowired
    private NearbySellHouseEsDao nearbySellHouseEsDao;

    @Autowired
    private AgentService agentService;

    @Autowired
    private CommunityRestService communityRestService;

    @Autowired
    private SellHouseService sellHouseService;

    @Override
    public NearBySellHouseDomain getSellHouseByHouseIdAndLocation(NearBySellHouseQueryDo nearBySellHouseQueryDo, String city) {

        NearBySellHouseDomain nearBySellHouseDomain = new NearBySellHouseDomain();
        NearBySellHousesDo nearBySellHousesDo = new NearBySellHousesDo();
        //其他筛选条件
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterChoose(nearBySellHouseQueryDo);
        //过滤附近5km
        if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getDistance()) && StringTool.isNotEmpty(nearBySellHouseQueryDo.getLat()) &&
                StringTool.isNotEmpty(nearBySellHouseQueryDo.getLon())) {
            GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                    .point(nearBySellHouseQueryDo.getLat(), nearBySellHouseQueryDo.getLon())
                    .distance(nearBySellHouseQueryDo.getDistance(), DistanceUnit.KILOMETERS);

            booleanQueryBuilder.must(location);
        }


        //过滤为删除
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
//        booleanQueryBuilder.mustNot(QueryBuilders.termsQuery("is_parent_claim", "1"));
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));

        FunctionScoreQueryBuilder query = null;
        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

//        Map<String, Double> map = new HashMap<>();
//        map.put("lat", nearBySellHouseQueryDo.getLat());
//        map.put("lon", nearBySellHouseQueryDo.getLon());
//        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));
        double[] json = new double[]{nearBySellHouseQueryDo.getLon(), nearBySellHouseQueryDo.getLat()};
        //设置高斯函数
        GaussDecayFunctionBuilder functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("housePlotLocation", json, "1km", "1km");
        //获取5km内所有的二手房
        FunctionScoreQueryBuilder query5kmBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);
        if (StringUtil.isNotNullString(nearBySellHouseQueryDo.getKeyword())) {
            List<String> searchKeyword = filterSellHouseChooseService.filterKeyWords(nearBySellHouseQueryDo.getKeyword(), city);
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size() + 1];
            if (StringUtil.isNotNullString(AreaMap.getAreas(nearBySellHouseQueryDo.getKeyword()))) {
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchAreasSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(nearBySellHouseQueryDo.getKeyword()))) {
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchDistrictsSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else {
                for (int i = 0; i < searchKeyword.size(); i++) {
                    int searchTermSize = searchKeyword.size();
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }
            filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
            query = QueryBuilders.functionScoreQuery(query5kmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                    .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
        } else {
            query = QueryBuilders.functionScoreQuery(query5kmBuilder, functionBuilder).boost(10).maxBoost(100)
                    .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
        }
        List<NearBySellHousesDo> nearBySellHouses = new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo = new ClaimSellHouseDo();
        SearchResponse searchResponse = nearbySellHouseEsDao.getNearbySellHouseByFilter(query, nearBySellHouseQueryDo.getPageNum(), nearBySellHouseQueryDo.getPageSize(), city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        AgentBaseDo agentBaseDo = new AgentBaseDo();
        Date date = new Date();
        for (SearchHit searchHit : searchHists) {
            String details = "";
            details = searchHit.getSourceAsString();
            nearBySellHousesDo = JSON.parseObject(details, NearBySellHousesDo.class);
            claimSellHouseDo = JSON.parseObject(details, ClaimSellHouseDo.class);
            if (null != claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim() == 1) {   //将认领信息替换
                nearBySellHousesDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                nearBySellHousesDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                nearBySellHousesDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                nearBySellHousesDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
            }

            if (claimSellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(nearBySellHousesDo.getUserId())) {
                agentBaseDo = agentService.queryAgentInfoByUserId(nearBySellHousesDo.getUserId().toString(), city);
            } else if (claimSellHouseDo.getIsClaim() == 0) {
                if (StringUtil.isNotNullString(nearBySellHousesDo.getProjExpertUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(nearBySellHousesDo.getProjExpertUserId(), city);
                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                }
            }

            //判断3天内导入，且无图片，默认上显示默认图
            String importTime = nearBySellHousesDo.getImportTime();
            int isDefault = sellHouseService.isDefaultImage(importTime, date, nearBySellHousesDo.getHousePhotoTitle());
            if (isDefault == 1) {
                nearBySellHousesDo.setIsDefaultImage(1);
            }


            nearBySellHousesDo.setAgentBaseDo(agentBaseDo);
            nearBySellHousesDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));

            /*List<HouseColorLable> houseColorLableList = new ArrayList<>();
            HouseColorLable houseColorLable = new HouseColorLable();
            houseColorLable.setBackColor("FFF2F2");
            houseColorLable.setFontColor("FF6B6B");
            houseColorLable.setText("捡漏房");
            houseColorLable.setUrl("http://www.baidu.com");
            houseColorLableList.add(houseColorLable);
            HouseColorLable houseColorLable2 = new HouseColorLable();
            houseColorLable2.setBackColor("F0FAFF");
            houseColorLable2.setFontColor("2FB3FF");
            houseColorLable2.setText("抢手房");
            houseColorLable2.setUrl("http://www.baidu.com");
            houseColorLableList.add(houseColorLable2);
            HouseColorLable houseColorLable3 = new HouseColorLable();
            houseColorLable3.setBackColor("EFFFEF");
            houseColorLable3.setFontColor("47E24C");
            houseColorLable3.setText("降价房");
            houseColorLable3.setUrl("http://www.baidu.com");
            houseColorLableList.add(houseColorLable3);
            HouseColorLable houseColorLable4 = new HouseColorLable();
            houseColorLable4.setBackColor("FFF9E5");
            houseColorLable4.setFontColor("E3AF00");
            houseColorLable4.setText("5大豪宅社区");
            houseColorLable4.setUrl("http://www.baidu.com");
            houseColorLableList.add(houseColorLable4);
            HouseColorLable houseColorLable5 = new HouseColorLable();
            houseColorLable5.setBackColor("FFF9E5");
            houseColorLable5.setFontColor("E3AF00");
            houseColorLable5.setText("TOP50社区");
            houseColorLable5.setUrl("http://www.baidu.com");
            houseColorLableList.add(houseColorLable5);
            nearBySellHousesDo.setHouseColorLableList(houseColorLableList);

            List<String> houseBarrageFirstList = new ArrayList<>();
            houseBarrageFirstList.add("小区同户型总价最低");
            houseBarrageFirstList.add("`总价低于商圈同户型5万");
            houseBarrageFirstList.add("`降10万");
            houseBarrageFirstList.add("`平均成交周期7天");
            nearBySellHousesDo.setHouseBarrageFirstList(houseBarrageFirstList);

            List<String> houseBarrageSecondList = new ArrayList<>();
            houseBarrageSecondList.add("小区同户型总价最低");
            houseBarrageSecondList.add("`总价低于商圈同户型5万");
            houseBarrageSecondList.add("`降10万");
            houseBarrageSecondList.add("`平均成交周期7天");
            nearBySellHousesDo.setHouseBarrageSecondList(houseBarrageSecondList);*/

            List<HouseColorLable> houseColorLableList = new ArrayList<>();

            int isMustRob = nearBySellHousesDo.getIsMustRob();
            if (isMustRob == 1) {
                houseColorLableList.add(new HouseColorLable("FFF2F2", "FF6B6B", "捡漏房", "http://www.baidu.com"));
            }

            int isLowPrice = nearBySellHousesDo.getIsLowPrice();
            if (isLowPrice == 1) {
                houseColorLableList.add(new HouseColorLable("F0FAFF", "2FB3FF", "抢手房", "http://www.baidu.com"));
            }

            int isCutPrice = nearBySellHousesDo.getIsCutPrice();
            if (isCutPrice == 1) {
                houseColorLableList.add(new HouseColorLable("EFFFEF", "47E24C", "降价房", "http://www.baidu.com"));
            }

            List recommendBuildTagNameList = nearBySellHousesDo.getRecommendBuildTagsName();
            String areaName = nearBySellHousesDo.getArea();
            Map<Integer, Map<String, Integer>> typeCountsMap = nearBySellHousesDo.getTypeCounts();

            if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                String communityLableStr = "";
                if (recommendBuildTagNameList.contains("豪宅")) {
                    communityLableStr = areaName + typeCountsMap.get(4).get(nearBySellHousesDo.getAreaId()) + "大豪宅社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, "http://www.baidu.com"));
                }
                if (recommendBuildTagNameList.contains("别墅")) {
                    communityLableStr = areaName + typeCountsMap.get(5).get(nearBySellHousesDo.getAreaId()) + "大别墅社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, "http://www.baidu.com"));
                }
                if (recommendBuildTagNameList.contains("首次置业")) {
                    communityLableStr = areaName + typeCountsMap.get(2).get(nearBySellHousesDo.getAreaId()) + "大首置社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, "http://www.baidu.com"));
                }
                if (recommendBuildTagNameList.contains("换房升级")) {
                    communityLableStr = areaName + typeCountsMap.get(3).get(nearBySellHousesDo.getAreaId()) + "大换房社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, "http://www.baidu.com"));
                }
                if (recommendBuildTagNameList.contains("近公园")) {
//                    communityLableStr = "近公园社区主力户型";
                    communityLableStr = "近公园";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, "http://www.baidu.com"));
                }
            }

            if (nearBySellHousesDo.getIsCommunityTopHouse() == 1 ) {
                houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", "TOP50社区", "http://www.baidu.com"));
            }

            Integer rankLowInBizcircleLayout = nearBySellHousesDo.getRankLowInBizcircleLayout();
            if (rankLowInBizcircleLayout > 0 &&  rankLowInBizcircleLayout<10){
                String text = nearBySellHousesDo.getHouseBusinessName()+"居室低总价榜NO."+rankLowInBizcircleLayout;
                houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", text, "http://www.baidu.com"));
            }

            //二手房弹幕第一行
            List<String> houseBarrageFirstList = new ArrayList<>();
            if (nearBySellHousesDo.getIsLowest() == 1 ){
                houseBarrageFirstList.add("小区同户型总价最低");
            }
            boolean titleTag = true;
            if(nearBySellHousesDo.getTotalAbsoluteWithBizcircle()<0){
                String lowPriceStr = "总价低于商圈同户型" + Math.abs(nearBySellHousesDo.getTotalAbsoluteWithBizcircle()) + "万";
                houseBarrageFirstList.add(lowPriceStr);
                titleTag = false;
            }
            double priceFloat = nearBySellHousesDo.getPriceFloat();
            if (nearBySellHousesDo.getIsCutPrice() == 1 && priceFloat > 0) {
                houseBarrageFirstList.add("降" + priceFloat + "万");
                titleTag = false;
            }
            Integer avgDealCycle = nearBySellHousesDo.getAvgDealCycle();
            if (nearBySellHousesDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
//                houseBarrageFirstList.add("本户型平均成交时间为" + avgDealCycle + "天");
                titleTag = false;
            }
            if(titleTag){
                houseBarrageFirstList.add(nearBySellHousesDo.getHouseTitle());
            }
            nearBySellHousesDo.setHouseBarrageFirstList(houseBarrageFirstList);

            //二手房弹幕第二行
            List<String> houseBarrageSecondList = new ArrayList<>();

            if(nearBySellHousesDo.getForwardName().contains("东") || nearBySellHousesDo.getForwardName().contains("南")){
                houseBarrageSecondList.add("采光很好");
            }
            if(nearBySellHousesDo.getRankInLowCommunityLayout()>0){
                houseBarrageSecondList.add("小区同户型低总价榜NO."+nearBySellHousesDo.getRankInLowCommunityLayout());
            }
            if (nearBySellHousesDo.getTotalAbsoluteWithCommunity() < 0) {
                houseBarrageSecondList.add( "总价低于小区同户型" + Math.abs(nearBySellHousesDo.getTotalAbsoluteWithCommunity()) + "万");
            }

            for (String tag : nearBySellHousesDo.getTagsName()){
                houseBarrageSecondList.add(tag);
            }

            nearBySellHousesDo.setHouseBarrageSecondList(houseBarrageSecondList);

            nearBySellHouses.add(nearBySellHousesDo);
            //增加地铁站与房源的距离
            String keys = "";
            if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getSubwayLineId()) && nearBySellHouseQueryDo.getSubwayLineId()>0) {
                keys += nearBySellHouseQueryDo.getSubwayLineId().toString();
            }
            if (StringTool.isNotEmpty(nearBySellHouseQueryDo.getSubwayStationId())) {
                keys += "$" + nearBySellHouseQueryDo.getSubwayStationId();
            }
            if (!"".equals(keys) && null != nearBySellHousesDo.getSubwayDistince()) {
                nearBySellHousesDo.setSubwayDistanceInfo(nearBySellHousesDo.getSubwayDistince().get(keys).toString());
            }
        }

        nearBySellHouseDomain.setData(nearBySellHouses);
        nearBySellHouseDomain.setTotalNum(searchResponse.getHits().getTotalHits());

        return nearBySellHouseDomain;
    }

}
