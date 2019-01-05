package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.report.ReportPipelineRecordEveryMonth;
import com.toutiao.app.dao.sellhouse.NearbySellHouseEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.domain.newhouse.CustomConditionDetailsDo;
import com.toutiao.app.domain.newhouse.CustomConditionDetailsDomain;
import com.toutiao.app.domain.newhouse.CustomConditionDistrictDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.plot.PlotMarketDo;
import com.toutiao.app.domain.plot.PlotMarketDomain;
import com.toutiao.app.domain.plot.PlotsHousesDomain;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.favorite.FavoriteRestService;
import com.toutiao.app.service.plot.PlotsHomesRestService;
import com.toutiao.app.service.plot.PlotsMarketService;
import com.toutiao.app.service.sellhouse.FilterSellHouseChooseService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.constant.company.CompanyIconEnum;
import com.toutiao.web.common.constant.house.HouseLableEnum;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import com.toutiao.web.dao.mapper.report.ReportPipelineRecordEveryMonthMapper;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.max.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.min.ParsedMin;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

@Service
public class SellHouseServiceImpl implements SellHouseService {

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
    private final Integer FAVORITE_ESF = 2;
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private ReportPipelineRecordEveryMonthMapper reportPipelineRecordEveryMonthMapper;
    @Autowired
    private CommunityRestService communityRestService;
    @Autowired
    private PlotEsDao plotEsDao;
    @Autowired
    private PlotsHomesRestService plotsHomesRestService;
    @Autowired
    private UserFavoriteEsHouseMapper userFavoriteEsHouseMapper;
    @Autowired
    private PlotsMarketService plotsMarketService;
    @Value("${wap.domain.name}")
    private String wapName;

    @Override
    public SellHouseDetailsDo getSellHouseByHouseId(String houseId, String city) {

        //二手房房源详情
        SellAndClaimHouseDetailsDo sellAndClaimHouseDetailsDo = new SellAndClaimHouseDetailsDo();
        SellHouseDetailsDo sellHouseDetailsDo = new SellHouseDetailsDo();
        Date date = new Date();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        if ("FS".equals(houseId.substring(0, 2))) {
            booleanQueryBuilder.must(termQuery("claimHouseId", houseId));
        } else {
            booleanQueryBuilder.must(termQuery("houseId", houseId));
        }
        booleanQueryBuilder.must(termQuery("isDel", 0));
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseByHouseId(booleanQueryBuilder, city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        AgentBaseDo agentBaseDo = new AgentBaseDo();
        List<HouseColorLable> plotLableList= new ArrayList<>();
        if (searchHists.length > 0) {

            for (SearchHit searchHit : searchHists) {
                String sourceAsString = searchHit.getSourceAsString();
                sellAndClaimHouseDetailsDo = JSON.parseObject(sourceAsString, SellAndClaimHouseDetailsDo.class);
                BeanUtils.copyProperties(sellAndClaimHouseDetailsDo, sellHouseDetailsDo);

                //房源列表标签
                List<HouseRankLable> houseRankLableList = new ArrayList<>();

                int isMustRob = sellHouseDetailsDo.getIsMustRob();
                if (isMustRob == 1) {
                    String houseRobCondition = StringTool.isNotEmpty(sellHouseDetailsDo.getHouseRobLabel())?"?"+sellHouseDetailsDo.getHouseRobLabel():"";
                    houseRankLableList.add(new HouseRankLable("", "", "抢手榜",sellHouseDetailsDo.getHouseRobLabel(), wapName + "/"+city+"/topics/house/hot"+houseRobCondition));
                }

                int isLowPrice = sellHouseDetailsDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    String houseLowerCondition = StringTool.isNotEmpty(sellHouseDetailsDo.getHouseLowerLabel())?"?"+sellHouseDetailsDo.getHouseLowerLabel():"";
                    houseRankLableList.add(new HouseRankLable("", "", "捡漏榜", sellHouseDetailsDo.getHouseLowerLabel(), wapName + "/"+city+"/topics/house/low"+houseLowerCondition));
                }

                int isCutPrice = sellHouseDetailsDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    String houseCutCondition = StringTool.isNotEmpty(sellHouseDetailsDo.getHouseCutLabel())?"?"+sellHouseDetailsDo.getHouseCutLabel():"";
                    houseRankLableList.add(new HouseRankLable("", "", "降价榜", sellHouseDetailsDo.getHouseCutLabel(), wapName + "/"+city+"/topics/house/reduction"+houseCutCondition));
                }
                sellHouseDetailsDo.setHouseRankLableList(houseRankLableList);

                //设置房源标签
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                if (sellHouseDetailsDo.getIsLowest() == 1) {
                    houseSubjectList.add(new HouseSubject("小区同户型总价最低",""));
                }

                if (sellHouseDetailsDo.getTotalAbsoluteWithBizcircle() < 0) {
                    houseSubjectList.add(new HouseSubject("总价低于商圈同户型" + Math.abs(sellHouseDetailsDo.getTotalAbsoluteWithBizcircle()) + "万",""));
                }

                double priceFloat = sellHouseDetailsDo.getPriceFloat();
                if (sellHouseDetailsDo.getIsCutPrice() == 1 && sellHouseDetailsDo.getPriceFloat() > 0) {
                    houseSubjectList.add(new HouseSubject("降" + priceFloat + "万",""));
                }

                Integer avgDealCycle = sellHouseDetailsDo.getAvgDealCycle();
                if (sellHouseDetailsDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    houseSubjectList.add(new HouseSubject("平均成交周期" + avgDealCycle + "天",""));
                }

                String traffic = sellHouseDetailsDo.getTraffic();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    String nearbyDistance;
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
                        nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                    } else{
                        DecimalFormat df = new DecimalFormat("0.0");
                        nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                    houseSubjectList.add(new HouseSubject(nearbyDistance,""));
                }

                //tagsName
                String[] tagsNameArray = sellHouseDetailsDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        if(tagsName.equals("近公园")){
                            continue;
                        }
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }
                sellHouseDetailsDo.setHouseSubjectList(houseSubjectList);

                //小区标签
                String communityLableStr = "";
                List recommendBuildTagNameList = sellHouseDetailsDo.getRecommendBuildTagsName();
                String areaName = sellHouseDetailsDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = sellHouseDetailsDo.getTypeCounts();
                //区域ID
                Integer areaId = sellHouseDetailsDo.getAreaId();

                if (sellHouseDetailsDo.getIsMustRob() == 1 && sellHouseDetailsDo.getIsMainLayout() == 1) {
                    if (sellHouseDetailsDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                        if (recommendBuildTagNameList.contains("豪宅")) {
                            communityLableStr = areaName + typeCountsMap.get(4).get(areaId) + "大豪宅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("别墅")) {
                            communityLableStr = areaName + typeCountsMap.get(5).get(areaId) + "大别墅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("首次置业")) {
                            communityLableStr = areaName + typeCountsMap.get(2).get(areaId) + "大首置社区主力户型";
                        } else if (recommendBuildTagNameList.contains("换房升级")) {
                            communityLableStr = areaName + typeCountsMap.get(3).get(areaId) + "大换房社区主力户型";
                        } else if (recommendBuildTagNameList.contains("近公园")) {
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }
                String districtIds = StringTool.isNotEmpty(areaId)?"?districtIds="+areaId:"";
                if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                    if (recommendBuildTagNameList.contains("豪宅")) {
                        communityLableStr = areaName + typeCountsMap.get(4).get(areaId) + "大豪宅社区主力户型";
                        plotLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/luxury"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("别墅")) {
                        communityLableStr = areaName + typeCountsMap.get(5).get(areaId) + "大别墅社区主力户型";
                        plotLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/villa"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("首次置业")) {
                        communityLableStr = areaName + typeCountsMap.get(2).get(areaId) + "大首置社区主力户型";
                        plotLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/first"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("换房升级")) {
                        communityLableStr = areaName + typeCountsMap.get(3).get(areaId) + "大换房社区主力户型";
                        plotLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/improve"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("近公园")) {
//                    communityLableStr = "近公园社区主力户型";
                        communityLableStr = "近公园";
                        String nearpark = StringTool.isNotEmpty(sellHouseDetailsDo.getNearPark())?"?strNearestPark="+sellHouseDetailsDo.getNearPark():"";
                        plotLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/nearpark"+nearpark));
                    }
                }

                if (sellHouseDetailsDo.getIsCommunityTopHouse() == 1) {
                    plotLableList.add(new HouseColorLable("FFF9E5", "E3AF00", "TOP50社区", wapName + "/"+city+"/topics/top50"+districtIds));
                }

                Integer rankLowInBizcircleLayout = sellHouseDetailsDo.getRankLowInBizcircleLayout();
                if (rankLowInBizcircleLayout > 0 && rankLowInBizcircleLayout < 10) {
                    String text = sellHouseDetailsDo.getHouseBusinessName() + "居室低总价榜NO." + rankLowInBizcircleLayout;
//                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", text, "http://www.baidu.com"));
                }




                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHouseDetailsDo.getImportTime();
                int isDefault = isDefaultImage(importTime, date, sellHouseDetailsDo.getHousePhotoTitle());
                if (isDefault == 1) {
                    sellHouseDetailsDo.setIsDefaultImage(1);
                }


                if (StringTool.isNotEmpty(sellAndClaimHouseDetailsDo.getUserId()) && sellAndClaimHouseDetailsDo.getIsClaim() == 1) {
                    //经纪人信息
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDetailsDo.getUserId().toString(), city);
                    if (StringTool.isNotEmpty(agentBaseDo.getAgentBusinessCard())) {
                        agentBaseDo.setAgentBusinessCard(agentBaseDo.getAgentBusinessCard().toString());
                    } else {
                        agentBaseDo.setAgentBusinessCard("");

                    }

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

                } else if (sellAndClaimHouseDetailsDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellAndClaimHouseDetailsDo.getProjExpertUserId())) {
                        //经纪人信息
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDetailsDo.getProjExpertUserId().toString(), city);
                        if (StringTool.isNotEmpty(agentBaseDo.getAgentBusinessCard())) {
                            agentBaseDo.setAgentBusinessCard(agentBaseDo.getAgentBusinessCard().toString());
                        } else {
                            agentBaseDo.setAgentBusinessCard("");

                        }

                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentBusinessCard(searchHit.getSourceAsMap().get("agentBusinessCard") == null ? "" : searchHit.getSourceAsMap().get("agentBusinessCard").toString());
                    }
                }

//                else {
//                    agentBaseDo.setAgentName(searchHit.getSource().get("houseProxyName")==null?"":searchHit.getSource().get("houseProxyName").toString());
//                    agentBaseDo.setAgentCompany(searchHit.getSource().get("ofCompany")==null?"":searchHit.getSource().get("ofCompany").toString());
//                    agentBaseDo.setHeadPhoto(searchHit.getSource().get("houseProxyPhoto")==null?"":searchHit.getSource().get("houseProxyPhoto").toString());
//                    agentBaseDo.setDisplayPhone(searchHit.getSource().get("houseProxyPhone")==null?"":searchHit.getSource().get("houseProxyPhone").toString());
//                    agentBaseDo.setAgentBusinessCard(searchHit.getSource().get("agentBusinessCard")==null?"":searchHit.getSource().get("agentBusinessCard").toString());
//                }
                sellHouseDetailsDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHouseDetailsDo.setAgentBaseDo(agentBaseDo);

                //公司图标
                String AgentCompany = sellHouseDetailsDo.getOfCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    sellHouseDetailsDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }
            }

            try {

                UserBasic userBasic = UserBasic.getCurrent();
                if (StringTool.isNotEmpty(userBasic)) {
                    IsFavoriteDo isFavoriteDo = new IsFavoriteDo();
                    isFavoriteDo.setUserId(Integer.valueOf(userBasic.getUserId()));
                    isFavoriteDo.setHouseId(sellHouseDetailsDo.getHouseId());
                    boolean isFavorite = favoriteRestService.getIsFavorite(FAVORITE_ESF, isFavoriteDo);
                    sellHouseDetailsDo.setIsFavorite(isFavorite);
                }
            } catch (BaseException e) {
                sellHouseDetailsDo.setIsFavorite(Boolean.FALSE);
            }

            if (sellHouseDetailsDo.getHouseHeating() == 0) {
                sellHouseDetailsDo.setHouseHeatingName("未知");
            }
            if (sellHouseDetailsDo.getHouseHeating() == 1) {
                sellHouseDetailsDo.setHouseHeatingName("集中供暖");
            }
            if (sellHouseDetailsDo.getHouseHeating() == 2) {
                sellHouseDetailsDo.setHouseHeatingName("自供暖");
            }
        }

        Integer plotId = sellHouseDetailsDo.getNewcode();

        if (null != plotId) {

            String details = "";
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(termQuery("id", plotId));
            SearchResponse plotSearchResponse = plotEsDao.queryPlotDetail(boolQueryBuilder, city);
            SearchHit[] plotHits = plotSearchResponse.getHits().getHits();

            for (SearchHit searchHit : plotHits) {
                details = searchHit.getSourceAsString();
            }

            if (StringUtils.isNotEmpty(details)) {
                PlotDetailsDo plotDetailsDo = JSON.parseObject(details, PlotDetailsDo.class);
                plotDetailsDo.setPlotLableList(plotLableList);
                ReportPipelineRecordEveryMonth reportPipelineRecordEveryMonth = reportPipelineRecordEveryMonthMapper.selectPlotRindRatio(String.valueOf(plotId),0);
                if (StringTool.isNotEmpty(reportPipelineRecordEveryMonth)){
                    plotDetailsDo.setRingRatio(reportPipelineRecordEveryMonth.getRingRatio());
                }
                PlotsHousesDomain plotsHousesDomain = plotsHomesRestService.queryPlotsHomesByPlotId(plotId, city);

                PlotMarketDo plotMarketDo = plotsMarketService.queryPlotMarketByPlotId(plotId);

                PlotMarketDomain plotMarketDomain = null;
                if (null != plotMarketDo) {
                    plotMarketDomain = new PlotMarketDomain();
                    org.springframework.beans.BeanUtils.copyProperties(plotMarketDo, plotMarketDomain);
                    plotMarketDomain.setDistrictName(plotDetailsDo.getArea());
                    plotDetailsDo.setPlotMarketDomain(plotMarketDomain);
                }

                plotsHousesDomain.setAvgPrice(plotDetailsDo.getAvgPrice());
                plotDetailsDo.setPlotsHousesDomain(plotsHousesDomain);

                if (plotDetailsDo.getPhoto().length > 0) {
                    String titlePhoto = plotDetailsDo.getPhoto()[0];
                    if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                        titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                    }
                    plotDetailsDo.setTitlePhoto(titlePhoto);
                }

                sellHouseDetailsDo.setPlotDetailsDo(plotDetailsDo);
            }
        }
        return sellHouseDetailsDo;
    }

    @Override
    public List<MessageSellHouseDo> querySellHouseByHouseId(String[] houseIds) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termQuery("isDel", 0));
//        if(houseIds.indexOf("FS")>-1){
//            boolQueryBuilder.must(QueryBuilders.termQuery("claimHouseId",houseIds));
//        }else {
        boolQueryBuilder.must(QueryBuilders.termsQuery("_id", houseIds));
//        }
        SearchResponse searchResponse = sellHouseEsDao.querySellHouseByHouseId(boolQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<MessageSellHouseDo> messageSellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String sourceAsString = searchHit.getSourceAsString();
                MessageSellHouseDo messageSellHouseDo = JSON.parseObject(sourceAsString, MessageSellHouseDo.class);
                messageSellHouseDos.add(messageSellHouseDo);
            }
        }
        return messageSellHouseDos;
    }

    @Override
    public List<MessageSellHouseDo> querySellHouseByHouseIdNew(String[] houseIds, String city) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termQuery("isDel", 0));
//        if(houseIds.indexOf("FS")>-1){
//            boolQueryBuilder.must(QueryBuilders.termQuery("claimHouseId",houseIds));
//        }else {
        boolQueryBuilder.must(QueryBuilders.termsQuery("_id", houseIds));
//        }
        SearchResponse searchResponse = sellHouseEsDao.querySellHouseByHouseIdNew(boolQueryBuilder, city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<MessageSellHouseDo> messageSellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String sourceAsString = searchHit.getSourceAsString();
                MessageSellHouseDo messageSellHouseDo = JSON.parseObject(sourceAsString, MessageSellHouseDo.class);
                messageSellHouseDos.add(messageSellHouseDo);
            }
        }
        return messageSellHouseDos;

    }


//    /**
//     * 认领二手房房源经纪人
//     * @param houseId
//     * @return
//     */
//    @Override
//    public AgentsBySellHouseDo getAgentByHouseId(Integer houseId){
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termQuery("corp_house_id",houseId));
//        SearchResponse searchResponse = agentHouseEsDao.getAgentHouseByHouseId(boolQueryBuilder);
//        SearchHit[] hits = searchResponse.getHits().getHits();
//        if (hits.length>0){
//            long time = System.currentTimeMillis();
//            long index = (time / 600000) % hits.length;
//            String details = hits[(int) index].getSourceAsString();
//            AgentsBySellHouseDo agentsBySellHouseDo = JSON.parseObject(details,AgentsBySellHouseDo.class);
//            return agentsBySellHouseDo;
//        }else{
//            return null;
//        }
//    }

    /**
     * 二手房，房源列表
     *
     * @param sellHouseQueryDo
     * @return
     */
    @Override
    public SellHouseDomain getSellHouseByChoose(SellHouseDoQuery sellHouseQueryDo, String city) {

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
        queryBuilderOfWeek.must(termQuery("is_claim", 0));
        queryBuilderOfWeek.must(termQuery("isNew", 1));
        queryBuilderOfWeek.must(QueryBuilders.termsQuery("isDel", "0"));

//        queryBuilderOfWeek.must(QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("import_time").gt(pastDateOfWeek).lte(nowDate)));
        boolQueryBuilder.should(queryBuilderOfWeek);
        FunctionScoreQueryBuilder query = getQuery(sellHouseQueryDo, boolQueryBuilder, city);

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
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query, sellHouseQueryDo.getDistance(),
                sellHouseQueryDo.getKeyword(), sellHouseQueryDo.getPageNum(), sellHouseQueryDo.getPageSize(), city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHouseDo sellHouseDo = JSON.parseObject(details, SellHouseDo.class);
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
                if (sellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);

                } else if (sellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHouseDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getProjExpertUserId().toString(), city);
                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                    }
                }

                sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);

            }
            sellHouseDomain.setData(sellHouseDos);
            sellHouseDomain.setTotalNum((int) hits.getTotalHits());
        } else {
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND, "二手房列表为空");
        }


        return sellHouseDomain;
    }

    /**
     * 二手房推荐列表V1
     *
     * @param userFavoriteConditionDoQuery
     * @param city
     * @return
     */
    @Override
    public SellHouseDomain getSellHouseByChooseV1(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        Date date = new Date();
        BoolQueryBuilder boolQueryBuilderT1 = QueryBuilders.boolQuery();
        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        List<SellHouseDo> list = new ArrayList<>();

        //无预设条件
        if (userFavoriteConditionDoQuery.getFlag() == 0) {
            SellHouseDomain sellHouseNoCondition = getSellHouseNoCondition(userFavoriteConditionDoQuery, city);
            return sellHouseNoCondition;
        }

        boolQueryBuilderT1.must(termQuery("isDel", 0));
        boolQueryBuilderT1.must(termQuery("is_claim", 0));

        //区域
        if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getDistrictId())) {
            boolQueryBuilderT1.must(QueryBuilders.termsQuery("areaId", userFavoriteConditionDoQuery.getDistrictId()));
        }
        //户型
        if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getLayoutId())) {
            boolQueryBuilderT1.must(QueryBuilders.termsQuery("room", userFavoriteConditionDoQuery.getLayoutId()));
        }
        //价格
        if (null != userFavoriteConditionDoQuery.getBeginPrice() && null != userFavoriteConditionDoQuery.getEndPrice() && userFavoriteConditionDoQuery.getEndPrice() > 0) {
            boolQueryBuilderT1.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()).lte(userFavoriteConditionDoQuery.getEndPrice()));
        } else if (null != userFavoriteConditionDoQuery.getBeginPrice() && null != userFavoriteConditionDoQuery.getEndPrice() && userFavoriteConditionDoQuery.getEndPrice() == 0) {
            boolQueryBuilderT1.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()));
        } else if (null != userFavoriteConditionDoQuery.getBeginPrice() && null == userFavoriteConditionDoQuery.getEndPrice()) {
            boolQueryBuilderT1.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()));
        }

        //使用functionnscore增加isNew为1的房源分数
        ScoreFunctionBuilder scoreFunctionBuilderT1 = ScoreFunctionBuilders.fieldValueFactorFunction("isNew").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(1).setWeight(1);
        FunctionScoreQueryBuilder functionScoreQueryBuilderT1 = QueryBuilders.functionScoreQuery(boolQueryBuilderT1, scoreFunctionBuilderT1).boostMode(CombineFunction.SUM);

        SearchResponse sellHouseByConditionT1 = sellHouseEsDao.getSellHouseByCondition(functionScoreQueryBuilderT1, userFavoriteConditionDoQuery.getPageNum(), userFavoriteConditionDoQuery.getPageSize(), city);
        SearchHit[] hitsT1 = sellHouseByConditionT1.getHits().getHits();
        List<SearchHit> searchHitList = new ArrayList<>();
        if (hitsT1.length > 0) {
            searchHitList.addAll(Arrays.asList(hitsT1));
        }
        int totalHits_T1 = (int) sellHouseByConditionT1.getHits().getTotalHits();

        //当T1条件分页结果不足每页大小,查询T2条件并补充
        if (searchHitList.size() < userFavoriteConditionDoQuery.getPageSize()) {
            BoolQueryBuilder boolQueryBuilderT2 = QueryBuilders.boolQuery();
            //获取pageNum和pageSize
            int pageNum_T1 = totalHits_T1 / userFavoriteConditionDoQuery.getPageSize();
            int pageNum_T2 = userFavoriteConditionDoQuery.getPageNum() - pageNum_T1;
            int pageSize_T2 = userFavoriteConditionDoQuery.getPageSize();
            if (pageNum_T2 == 1) {
                pageSize_T2 = userFavoriteConditionDoQuery.getPageSize() - totalHits_T1 % userFavoriteConditionDoQuery.getPageSize();
            }
            boolQueryBuilderT2.must(termQuery("is_claim", 0));
            boolQueryBuilderT2.must(termQuery("isDel", 0));

            //只符合价格条件的房源
            //价格
            if (null != userFavoriteConditionDoQuery.getBeginPrice() && null != userFavoriteConditionDoQuery.getEndPrice() && userFavoriteConditionDoQuery.getEndPrice() > 0) {
                boolQueryBuilderT2.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()).lte(userFavoriteConditionDoQuery.getEndPrice()));
                //区域
                if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getDistrictId())) {
                    boolQueryBuilderT2.mustNot(QueryBuilders.termsQuery("areaId", userFavoriteConditionDoQuery.getDistrictId()));
                }
                //户型
                if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getLayoutId())) {
                    boolQueryBuilderT2.mustNot(QueryBuilders.termsQuery("room", userFavoriteConditionDoQuery.getLayoutId()));
                }
            } else if (null != userFavoriteConditionDoQuery.getBeginPrice() && null != userFavoriteConditionDoQuery.getEndPrice() && userFavoriteConditionDoQuery.getEndPrice() == 0) {
                boolQueryBuilderT2.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()));
                //区域
                if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getDistrictId())) {
                    boolQueryBuilderT2.mustNot(QueryBuilders.termsQuery("areaId", userFavoriteConditionDoQuery.getDistrictId()));
                }
                //户型
                if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getLayoutId())) {
                    boolQueryBuilderT2.mustNot(QueryBuilders.termsQuery("room", userFavoriteConditionDoQuery.getLayoutId()));
                }
            } else if (null != userFavoriteConditionDoQuery.getBeginPrice() && null == userFavoriteConditionDoQuery.getEndPrice()) {
                boolQueryBuilderT2.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()));
                //区域
                if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getDistrictId())) {
                    boolQueryBuilderT2.mustNot(QueryBuilders.termsQuery("areaId", userFavoriteConditionDoQuery.getDistrictId()));
                }
                //户型
                if (ArrayUtils.isNotEmpty(userFavoriteConditionDoQuery.getLayoutId())) {
                    boolQueryBuilderT2.mustNot(QueryBuilders.termsQuery("room", userFavoriteConditionDoQuery.getLayoutId()));
                }
            }

            //使用functionnscore增加isNew为1的房源分数
            ScoreFunctionBuilder scoreFunctionBuilderT2 = ScoreFunctionBuilders.fieldValueFactorFunction("isNew").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(1).setWeight(1);
            FunctionScoreQueryBuilder functionScoreQueryBuilderT2 = QueryBuilders.functionScoreQuery(boolQueryBuilderT2, scoreFunctionBuilderT2).boostMode(CombineFunction.SUM);

            SearchResponse sellHouseByConditionT2 = sellHouseEsDao.getSellHouseByCondition(functionScoreQueryBuilderT2, pageNum_T2, pageSize_T2, city);
            SearchHit[] hitsT2 = sellHouseByConditionT2.getHits().getHits();
            int totalHits_T2 = (int) sellHouseByConditionT2.getHits().getTotalHits();
            if (hitsT2.length > 0) {
                searchHitList.addAll(Arrays.asList(hitsT2));
            }
//            if (hitsT2.length > 0) {
//                for (SearchHit hit : hitsT2) {
//                    String sourceAsString = hit.getSourceAsString();
//                    SellHouseDo sellHouseDo = JSON.parseObject(sourceAsString, SellHouseDo.class);
//                    //判断3天内导入，且无图片，默认上显示默认图
//                    String importTime = sellHouseDo.getImportTime();
//                    int isDefault = isDefaultImage(importTime, date, sellHouseDo.getHousePhotoTitle());
//                    if (isDefault == 1) {
//                        sellHouseDo.setIsDefaultImage(1);
//                    }
//                    //经纪人信息
//                    AgentBaseDo agentBaseDo = new AgentBaseDo();
//                    if (sellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
//                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);
//
//                    } else if (sellHouseDo.getIsClaim() == 0) {
//                        if (StringUtil.isNotNullString(sellHouseDo.getProjExpertUserId())) {
//                            agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getProjExpertUserId().toString(), city);
//                        } else {
//                            agentBaseDo.setAgentName(hit.getSourceAsMap().get("houseProxyName") == null ? "" : hit.getSourceAsMap().get("houseProxyName").toString());
//                            agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("ofCompany") == null ? "" : hit.getSourceAsMap().get("ofCompany").toString());
//                            agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : hit.getSourceAsMap().get("houseProxyPhoto").toString());
//                            agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("houseProxyPhone") == null ? "" : hit.getSourceAsMap().get("houseProxyPhone").toString());
//                        }
//                    }
//                    sellHouseDo.setAgentBaseDo(agentBaseDo);
//                    list.add(sellHouseDo);
//                }
//                sellHouseDomain.setData(list);
//                sellHouseDomain.setTotalNum(totalHits_T2);
//            }

            //当T2条件分页结果不足每页大小,查询T3条件并补充
            if (list.size() < userFavoriteConditionDoQuery.getPageSize()) {
                BoolQueryBuilder boolQueryBuilderT3 = QueryBuilders.boolQuery();
                //获取pageNum和pageSize
                pageNum_T2 = (totalHits_T2 + totalHits_T1) / userFavoriteConditionDoQuery.getPageSize();
                int pageNum_T3 = userFavoriteConditionDoQuery.getPageNum() - pageNum_T2;
                int pageSize_T3 = userFavoriteConditionDoQuery.getPageSize();
                if (pageNum_T3 == 1) {
                    pageSize_T3 = userFavoriteConditionDoQuery.getPageSize() - (totalHits_T2 + totalHits_T1) % userFavoriteConditionDoQuery.getPageSize();
                }
                boolQueryBuilderT3.must(termQuery("is_claim", 0));
                boolQueryBuilderT3.must(termQuery("isDel", 0));
                //展示全部房源 即所有条件为不限

                //使用functionnscore增加isNew为1的房源分数
                ScoreFunctionBuilder scoreFunctionBuilderT3 = ScoreFunctionBuilders.fieldValueFactorFunction("isNew").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(1).setWeight(1);
                FunctionScoreQueryBuilder functionScoreQueryBuilderT3 = QueryBuilders.functionScoreQuery(boolQueryBuilderT3, scoreFunctionBuilderT2).boostMode(CombineFunction.SUM);

                SearchResponse sellHouseByConditionT3 = sellHouseEsDao.getSellHouseByCondition(functionScoreQueryBuilderT3, pageNum_T3, pageSize_T3, city);
                SearchHit[] hitsT3 = sellHouseByConditionT3.getHits().getHits();
                int totalHits_T3 = (int) sellHouseByConditionT3.getHits().getTotalHits();
                if (hitsT3.length > 0) {
                    searchHitList.addAll(Arrays.asList(hitsT3));
                }
//                if (hitsT3.length > 0) {
//                    for (SearchHit hit : hitsT3) {
//                        String sourceAsString = hit.getSourceAsString();
//                        SellHouseDo sellHouseDo = JSON.parseObject(sourceAsString, SellHouseDo.class);
//                        //判断3天内导入，且无图片，默认上显示默认图
//                        String importTime = sellHouseDo.getImportTime();
//                        int isDefault = isDefaultImage(importTime, date, sellHouseDo.getHousePhotoTitle());
//                        if (isDefault == 1) {
//                            sellHouseDo.setIsDefaultImage(1);
//                        }
//                        //经纪人信息
//                        AgentBaseDo agentBaseDo = new AgentBaseDo();
//                        if (sellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
//                            agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);
//
//                        } else if (sellHouseDo.getIsClaim() == 0) {
//                            if (StringUtil.isNotNullString(sellHouseDo.getProjExpertUserId())) {
//                                agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getProjExpertUserId().toString(), city);
//                            } else {
//                                agentBaseDo.setAgentName(hit.getSourceAsMap().get("houseProxyName") == null ? "" : hit.getSourceAsMap().get("houseProxyName").toString());
//                                agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("ofCompany") == null ? "" : hit.getSourceAsMap().get("ofCompany").toString());
//                                agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : hit.getSourceAsMap().get("houseProxyPhoto").toString());
//                                agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("houseProxyPhone") == null ? "" : hit.getSourceAsMap().get("houseProxyPhone").toString());
//                            }
//                        }
//                        sellHouseDo.setAgentBaseDo(agentBaseDo);
//                        list.add(sellHouseDo);
//                    }
//                    sellHouseDomain.setData(list);
//                    sellHouseDomain.setTotalNum(totalHits_T3);
//                }
            }
        }

        list = getResultFromSearchHitList(city, list, searchHitList);
        sellHouseDomain.setData(list);
        sellHouseDomain.setTotalNum(totalHits_T1);
        return sellHouseDomain;
    }

    /**
     * 二手房推荐列表V1(无预设条件)
     *
     * @param userFavoriteConditionDoQuery
     * @param city
     * @return
     */
    @Override
    public SellHouseDomain getSellHouseNoCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        List<SellHouseDo> list = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //默认条件
        boolQueryBuilder.must(termQuery("is_claim", 0));
        boolQueryBuilder.must(termQuery("isDel", 0));

        //使用functionnscore增加isNew为1的房源分数
        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.fieldValueFactorFunction("isNew").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(1).setWeight(1);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder, scoreFunctionBuilder).boostMode(CombineFunction.SUM);

        SearchResponse sellHouseNoCondition = sellHouseEsDao.getSellHouseByCondition(functionScoreQueryBuilder, userFavoriteConditionDoQuery.getPageNum(), userFavoriteConditionDoQuery.getPageSize(), city);
        SearchHit[] hits = sellHouseNoCondition.getHits().getHits();
        List<SearchHit> searchHitList = new ArrayList<>(Arrays.asList(hits));
        if (hits.length > 0) {
            list = getResultFromSearchHitList(city, list, searchHitList);
            sellHouseDomain.setData(list);
            sellHouseDomain.setTotalNum((int) sellHouseNoCondition.getHits().getTotalHits());
        }

        return sellHouseDomain;
    }


    public static void main(String[] args) {
        Date date = new Date();
        int claimDays = DateUtil.daysBetween(date, DateUtil.getStringToDate("2018-05-25 09:58:48")) - 1;

        System.out.println(claimDays);
    }


    /**
     * 查询二手房推荐房源
     *
     * @param sellHouseDoQuery
     * @return
     */
    @Override
    public SellHouseDomain getRecommendSellHouse(SellHouseDoQuery sellHouseDoQuery, String city) {


        SellHouseDomain sellHouseDomain = new SellHouseDomain();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);
        boolQueryBuilder.must(termQuery("is_claim", 1));
        boolQueryBuilder.must(QueryBuilders.rangeQuery("isRecommend").gt(0));
        FunctionScoreQueryBuilder query = getQuery(sellHouseDoQuery, boolQueryBuilder, city);
        SearchResponse searchResponse = sellHouseEsDao.getRecommendSellHouse(query, sellHouseDoQuery.getUid(),
                sellHouseDoQuery.getPageSize(), city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHouseDo> sellHouseDos = new ArrayList<>();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = searchHit.getSourceAsString();
                SellHouseDo sellHouseDo = JSON.parseObject(details, SellHouseDo.class);

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
                if (sellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);

                } else if (sellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHouseDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getProjExpertUserId().toString(), city);
                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                    }

                }

                sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDos.add(sellHouseDo);

            }
            sellHouseDomain.setData(sellHouseDos);
            sellHouseDomain.setTotalNum((int) searchResponse.getHits().getTotalHits());
        } else {
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND, "二手房列表为空");
        }


        return sellHouseDomain;
    }


    @Override
    public SellHouseSearchDomain getSellHouseList(SellHouseDoQuery sellHouseDoQuery, String city) {

        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        SellHousesSearchDo sellHousesSearchDo = new SellHousesSearchDo();


        //其他筛选条件
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterSellHouseChoose(sellHouseDoQuery);


        //过滤为删除
        booleanQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        booleanQueryBuilder.must(termQuery("is_claim", "0"));
//        booleanQueryBuilder.mustNot(QueryBuilders.termsQuery("is_parent_claim", "1"));

        FunctionScoreQueryBuilder query = null;
        //条件is_claim标志设置权重
        FieldValueFactorFunctionBuilder fieldValueFactor = ScoreFunctionBuilders.fieldValueFactorFunction("is_claim")
                .modifier(FieldValueFactorFunction.Modifier.LN1P).factor(11).missing(0);

//        Map<String,Double> map = new HashMap<>();
//        map.put("lat",sellHouseDoQuery.getLat());
//        map.put("lon",sellHouseDoQuery.getLon());
//        JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));


        //设置高斯函数
        GaussDecayFunctionBuilder functionBuilder = null;
        FunctionScoreQueryBuilder queryKmBuilder = null;
        GeoDistanceSortBuilder geoDistanceSort = null;
        if (StringTool.isNotEmpty(sellHouseDoQuery.getDistance()) && sellHouseDoQuery.getDistance() > 0) {
            double[] location = new double[]{sellHouseDoQuery.getLon(), sellHouseDoQuery.getLat()};
            functionBuilder = ScoreFunctionBuilders.gaussDecayFunction("housePlotLocation", location, sellHouseDoQuery.getDistance() + "km", sellHouseDoQuery.getDistance() + "km");
            //获取5km内所有的二手房

            geoDistanceSort = SortBuilders.geoDistanceSort("housePlotLocation", sellHouseDoQuery.getLat(), sellHouseDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);

        }
        queryKmBuilder = QueryBuilders.functionScoreQuery(booleanQueryBuilder, fieldValueFactor);
        if (StringUtil.isNotNullString(sellHouseDoQuery.getKeyword())) {
            List<String> searchKeyword = filterSellHouseChooseService.filterKeyWords(sellHouseDoQuery.getKeyword(), city);
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[0];
            if (StringTool.isNotEmpty(sellHouseDoQuery.getDistance()) && sellHouseDoQuery.getDistance() > 0) {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size() + 1];
            } else {
                filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchKeyword.size()];
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                int searchAreasSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName", searchKeyword.get(i));
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchAreasSize - i);
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))) {
                int searchDistrictsSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchDistrictsSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("area", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            } else {
                int searchTermSize = searchKeyword.size();
                for (int i = 0; i < searchKeyword.size(); i++) {
                    ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                    QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                    filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                }
            }


            if (StringTool.isNotEmpty(sellHouseDoQuery.getDistance()) && sellHouseDoQuery.getDistance() > 0) {
                filterFunctionBuilders[searchKeyword.size()] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(functionBuilder);
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);

            } else {
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        } else {
            if (StringTool.isNotEmpty(sellHouseDoQuery.getDistance()) && sellHouseDoQuery.getDistance() > 0) {
                query = QueryBuilders.functionScoreQuery(queryKmBuilder, functionBuilder).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            } else {
                query = QueryBuilders.functionScoreQuery(queryKmBuilder).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        }
        List<SellHousesSearchDo> sellHousesSearchDos = new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo = new ClaimSellHouseDo();
        SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query, sellHouseDoQuery.getDistance(),
                sellHouseDoQuery.getKeyword(), sellHouseDoQuery.getPageNum(), sellHouseDoQuery.getPageSize(), city, geoDistanceSort, sellHouseDoQuery.getSort());
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details = searchHit.getSourceAsString();
                sellHousesSearchDo = JSON.parseObject(details, SellHousesSearchDo.class);

                String nearbyDistance = StringTool.nullToString(sellHousesSearchDo.getArea()) + " " + StringTool.nullToString(sellHousesSearchDo.getHouseBusinessName());
                String traffic = sellHousesSearchDo.getTraffic();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
//                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                    } else if (i < 2000) {
                        DecimalFormat df = new DecimalFormat("0.0");
//                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                }
                if (StringTool.isNotEmpty(sellHouseDoQuery.getDistance()) && sellHouseDoQuery.getDistance() > 0) {
                    BigDecimal geoDis;
                    if (sellHouseDoQuery.getSort().equals("0")) {
                        geoDis = new BigDecimal((Double) searchHit.getSortValues()[0]);
                    } else {
                        geoDis = new BigDecimal((Double) searchHit.getSortValues()[1]);
                    }
                    String distances = geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
//                    sellHousesSearchDo.setNearbyDistance(distance);
                    nearbyDistance = "距您" + distances;
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHousesSearchDo.getImportTime();
                int isDefault = isDefaultImage(importTime, date, sellHousesSearchDo.getHousePhotoTitle());
                if (isDefault == 1) {
                    sellHousesSearchDo.setIsDefaultImage(1);
                }
                claimSellHouseDo = JSON.parseObject(details, ClaimSellHouseDo.class);
                if (null != claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim() == 1) {   //将认领信息替换
                    sellHousesSearchDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    sellHousesSearchDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    sellHousesSearchDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    sellHousesSearchDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = sellHousesSearchDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dfdo400x300";
                }
                sellHousesSearchDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (claimSellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString(), city);
                } else if (claimSellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHousesSearchDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getProjExpertUserId(), city);
                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                    }

                }
                sellHousesSearchDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHousesSearchDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    sellHousesSearchDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isCutPrice = sellHousesSearchDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = sellHousesSearchDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                int isLowPrice = sellHousesSearchDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                sellHousesSearchDo.setHouseLableList(houseLableList);

                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低", "");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = sellHousesSearchDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = sellHousesSearchDo.getTotalAbsoluteWithCommunity();
                if (sellHousesSearchDo.getIsLowPrice() == 1) {
                    if (totalAbsoluteWithBizcircle < 0 && totalAbsoluteWithCommunity < 0) {
                        if (Math.abs(totalAbsoluteWithBizcircle) > Math.abs(totalAbsoluteWithCommunity)) {
                            lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                        } else {
                            lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                        }
                    } else if (totalAbsoluteWithBizcircle < 0) {
                        lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                    } else if (totalAbsoluteWithCommunity < 0) {
                        lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                    }
                }

                if (StringTool.isNotEmpty(lowPriceStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(lowPriceStr);
                    sellHouseSubject.setUrl("/" + city + "/topics/house/reduction");
                    houseSubjectList.add(sellHouseSubject);
                }

                //3.降/涨X万
                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("/" + city + "/topics/house/reduction");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List recommendBuildTagNameList = sellHousesSearchDo.getRecommendBuildTagsName();
                String areaName = sellHousesSearchDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = sellHousesSearchDo.getTypeCounts();

                if (sellHousesSearchDo.getIsMustRob() == 1 && sellHousesSearchDo.getIsMainLayout() == 1) {
                    if (sellHousesSearchDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                        if (recommendBuildTagNameList.contains("豪宅")) {
                            communityLableStr = areaName + typeCountsMap.get(4).get(sellHousesSearchDo.getAreaId()) + "大豪宅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("别墅")) {
                            communityLableStr = areaName + typeCountsMap.get(5).get(sellHousesSearchDo.getAreaId()) + "大别墅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("首次置业")) {
                            communityLableStr = areaName + typeCountsMap.get(2).get(sellHousesSearchDo.getAreaId()) + "大首置社区主力户型";
                        } else if (recommendBuildTagNameList.contains("换房升级")) {
                            communityLableStr = areaName + typeCountsMap.get(3).get(sellHousesSearchDo.getAreaId()) + "大换房社区主力户型";
                        } else if (recommendBuildTagNameList.contains("近公园")) {
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }

                if (StringTool.isNotEmpty(communityLableStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(communityLableStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //6同商圈同户型范围内做低价排名
                Integer rankInLowCommunityLayout = sellHousesSearchDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(sellHousesSearchDo.getHouseBusinessName() + sellHousesSearchDo.getRoom() + "居室低总价榜NO." + rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = sellHousesSearchDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }
                sellHousesSearchDo.setHouseSubjectList(houseSubjectList);

                List<HouseColorLable> houseColorLableList = new ArrayList<>();

                if (isMustRob == 1) {
                    String houseRobCondition = StringTool.isNotEmpty(sellHousesSearchDo.getHouseRobLabel())?"?"+sellHousesSearchDo.getHouseRobLabel():"";
                    houseColorLableList.add(new HouseColorLable("F0FAFF", "2FB3FF", "抢手榜", wapName + "/"+city+"/topics/house/hot"+houseRobCondition));
                }

                if (isLowPrice == 1) {
                    String houseLowerCondition = StringTool.isNotEmpty(sellHousesSearchDo.getHouseLowerLabel())?"?"+sellHousesSearchDo.getHouseLowerLabel():"";
                    houseColorLableList.add(new HouseColorLable("FFF2F2", "FF6B6B", "捡漏榜", wapName + "/"+city+"/topics/house/low"+houseLowerCondition));
                }

                if (isCutPrice == 1) {
                    String houseCutCondition = StringTool.isNotEmpty(sellHousesSearchDo.getHouseCutLabel())?"?"+sellHousesSearchDo.getHouseCutLabel():"";
                    houseColorLableList.add(new HouseColorLable("EFFFEF", "47E24C", "降价榜", wapName + "/"+city+"/topics/house/reduction"+houseCutCondition));
                }

                String districtIds = StringTool.isNotEmpty(sellHousesSearchDo.getAreaId())?"?districtIds="+sellHousesSearchDo.getAreaId():"";
                if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                    if (recommendBuildTagNameList.contains("豪宅")) {
                        communityLableStr = areaName + typeCountsMap.get(4).get(sellHousesSearchDo.getAreaId()) + "大豪宅社区主力户型";
                        houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/luxury"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("别墅")) {
                        communityLableStr = areaName + typeCountsMap.get(5).get(sellHousesSearchDo.getAreaId()) + "大别墅社区主力户型";
                        houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/villa"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("首次置业")) {
                        communityLableStr = areaName + typeCountsMap.get(2).get(sellHousesSearchDo.getAreaId()) + "大首置社区主力户型";
                        houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/first"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("换房升级")) {
                        communityLableStr = areaName + typeCountsMap.get(3).get(sellHousesSearchDo.getAreaId()) + "大换房社区主力户型";
                        houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/improve"+districtIds));
                    }
                    if (recommendBuildTagNameList.contains("近公园")) {
//                    communityLableStr = "近公园社区主力户型";
                        communityLableStr = "近公园";
                        String nearpark = StringTool.isNotEmpty(sellHousesSearchDo.getNearPark())?"?strNearestPark="+sellHousesSearchDo.getNearPark():"";
                        houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/nearpark"+nearpark));
                    }
                }

                if (sellHousesSearchDo.getIsCommunityTopHouse() == 1) {
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", "TOP50社区", wapName + "/"+city+"/topics/top50"+districtIds));
                }

                Integer rankLowInBizcircleLayout = sellHousesSearchDo.getRankLowInBizcircleLayout();
                if (rankLowInBizcircleLayout > 0 && rankLowInBizcircleLayout < 10) {
                    String text = sellHousesSearchDo.getHouseBusinessName() + "居室低总价榜NO." + rankLowInBizcircleLayout;
//                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", text, "http://www.baidu.com"));
                }
                sellHousesSearchDo.setHouseColorLableList(houseColorLableList);

                //增加地铁与房子之间的距离
                String keys = "";
                if (null != sellHouseDoQuery.getSubwayLineId() && sellHouseDoQuery.getSubwayLineId() > 0) {
                    keys += sellHouseDoQuery.getSubwayLineId().toString();
                    //增加地铁线选择，地铁站选择不限
                    if (StringTool.isNotEmpty(sellHousesSearchDo.getSubwayDistince().get(keys))) {
                        trafficArr = sellHousesSearchDo.getSubwayDistince().get(keys).toString().split("\\$");
                        if (trafficArr.length == 3) {
//                            nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                            nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                        }
                    }
                }

                if (StringTool.isNotEmpty(sellHouseDoQuery.getSubwayStationId()) && sellHouseDoQuery.getSubwayStationId().length > 0) {
                    Map<Integer, String> map = new HashMap<>();
                    List<Integer> sortDistance = new ArrayList<>();
                    for (int i = 0; i < sellHouseDoQuery.getSubwayStationId().length; i++) {
                        String stationKey = keys + "$" + sellHouseDoQuery.getSubwayStationId()[i];
                        if (StringTool.isNotEmpty(sellHousesSearchDo.getSubwayDistince().get(stationKey))) {
                            String stationValue = sellHousesSearchDo.getSubwayDistince().get(stationKey).toString();
                            String[] stationValueSplit = stationValue.split("\\$");
                            Integer distance = Integer.valueOf(stationValueSplit[2]);
                            sortDistance.add(distance);
                            map.put(distance, stationKey);
                        }
                    }
                    Integer minDistance = Collections.min(sortDistance);
                    sellHousesSearchDo.setSubwayDistanceInfo(sellHousesSearchDo.getSubwayDistince().get(map.get(minDistance)).toString());
                    trafficArr = sellHousesSearchDo.getSubwayDistanceInfo().split("\\$");
                    if (trafficArr.length == 3) {
//                        nearbyDistance = "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                        nearbyDistance = "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";

                    }
                }

                if (StringTool.isNotEmpty(nearbyDistance)) {
                    sellHousesSearchDo.setNearbyDistance(nearbyDistance);
                }

                //二手房弹幕第一行
                List<String> houseBarrageFirstList = new ArrayList<>();
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    houseBarrageFirstList.add("小区同户型总价最低");
                }
                boolean titleTag = true;
                if (sellHousesSearchDo.getTotalAbsoluteWithBizcircle() < 0) {
//                    String lowPriceStr = "总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万";
                    houseBarrageFirstList.add("总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万");
                    titleTag = false;
                }

//                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    houseBarrageFirstList.add("降" + priceFloat + "万");
                    titleTag = false;
                }
//                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
//                houseBarrageFirstList.add("本户型平均成交时间为" + avgDealCycle + "天");
                    titleTag = false;
                }
                if (titleTag) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseTitle());
                }

                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseCutLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseCutLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseLowerLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseLowerLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseRobLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseRobLabel());
                }
                sellHousesSearchDo.setHouseBarrageFirstList(houseBarrageFirstList);

                //二手房弹幕第二行
                List<String> houseBarrageSecondList = new ArrayList<>();

                if (sellHousesSearchDo.getForwardName().contains("东") || sellHousesSearchDo.getForwardName().contains("南")) {
                    houseBarrageSecondList.add("采光很好");
                }
                if (sellHousesSearchDo.getRankInLowCommunityLayout() > 0) {
                    houseBarrageSecondList.add("小区同户型低总价榜NO." + sellHousesSearchDo.getRankInLowCommunityLayout());
                }
                if (sellHousesSearchDo.getTotalAbsoluteWithCommunity() < 0) {
                    houseBarrageSecondList.add("总价低于小区同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithCommunity()) + "万");
                }

                for (String tag : sellHousesSearchDo.getTagsName()) {
                    houseBarrageSecondList.add(tag);
                }

                sellHousesSearchDo.setHouseBarrageSecondList(houseBarrageSecondList);

                sellHousesSearchDos.add(sellHousesSearchDo);
            }
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int) searchResponse.getHits().getTotalHits());
        } else {
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int) searchResponse.getHits().getTotalHits());
//            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }


        return sellHouseSearchDomain;

    }

    @Override
    public SellHouseSearchDomain getSimilarSellHouseList(SellHouseDoQuery sellHouseDoQuery, String city) {


        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        SellHousesSearchDo sellHousesSearchDo = new SellHousesSearchDo();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

        //过滤为删除
        booleanQueryBuilder.must(termQuery("isDel", "0"));
        booleanQueryBuilder.must(termQuery("is_claim", "0"));
        //商圈id
        if (StringTool.isNotEmpty(sellHouseDoQuery.getAreaId()) && sellHouseDoQuery.getAreaId().length != 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId", sellHouseDoQuery.getAreaId()));
        }
        //户型(室)
        if (StringTool.isNotEmpty(sellHouseDoQuery.getLayoutId()) && sellHouseDoQuery.getLayoutId().length > 0) {
            Integer[] layoutId = sellHouseDoQuery.getLayoutId();
            booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room", layoutId)));
        }
        //厅
        if (StringTool.isNotEmpty(sellHouseDoQuery.getHall()) && sellHouseDoQuery.getHall() != 0) {
            booleanQueryBuilder.must(termQuery("hall", sellHouseDoQuery.getHall()));
        }

        if (StringTool.isNotEmpty(sellHouseDoQuery.getHouseId())) {
            booleanQueryBuilder.mustNot(termQuery("houseId", sellHouseDoQuery.getHouseId()));
        }

//        if (StringTool.isDoubleNotEmpty(sellHouseDoQuery.getTotalPrice())) {
//            double beginPrice = sellHouseDoQuery.getTotalPrice() - (sellHouseDoQuery.getTotalPrice() * 0.1);
//            double endPrice = sellHouseDoQuery.getTotalPrice() + (sellHouseDoQuery.getTotalPrice() * 0.1);
//            booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice).lte(endPrice));
//        }

        GeoDistanceSortBuilder geoDistanceSort = null;
        if (StringTool.isDoubleNotEmpty(sellHouseDoQuery.getLat()) && StringTool.isDoubleNotEmpty(sellHouseDoQuery.getLon())) {
            geoDistanceSort = SortBuilders.geoDistanceSort("housePlotLocation", sellHouseDoQuery.getLat(), sellHouseDoQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.geoDistance(GeoDistance.ARC);
        }


        List<SellHousesSearchDo> sellHousesSearchDos = new ArrayList<>();
        ClaimSellHouseDo claimSellHouseDo = new ClaimSellHouseDo();
        //       SearchResponse searchResponse = sellHouseEsDao.getSellHouseList(query, sellHouseDoQuery.getDistance(),
//                sellHouseDoQuery.getKeyword(), sellHouseDoQuery.getPageNum(), sellHouseDoQuery.getPageSize(), city, geoDistanceSort, sellHouseDoQuery.getSort());

        SearchResponse searchResponse = sellHouseEsDao.getSimilarSellHouseList(booleanQueryBuilder, city, geoDistanceSort);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details = searchHit.getSourceAsString();
                sellHousesSearchDo = JSON.parseObject(details, SellHousesSearchDo.class);

                String nearbyDistance = "";

                if (sellHouseDoQuery.getBuildingId().equals(sellHousesSearchDo.getNewcode())) {
                    nearbyDistance = "小区同户型";
                } else {
                    BigDecimal geoDis = new BigDecimal((Double) searchHit.getSortValues()[1]);
                    String distances = geoDis.setScale(1, BigDecimal.ROUND_CEILING) + DistanceUnit.KILOMETERS.toString();
                    nearbyDistance = "距本房源" + distances;
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHousesSearchDo.getImportTime();
                int isDefault = isDefaultImage(importTime, date, sellHousesSearchDo.getHousePhotoTitle());
                if (isDefault == 1) {
                    sellHousesSearchDo.setIsDefaultImage(1);
                }
                claimSellHouseDo = JSON.parseObject(details, ClaimSellHouseDo.class);
                if (null != claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim() == 1) {   //将认领信息替换
                    sellHousesSearchDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    sellHousesSearchDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    sellHousesSearchDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    sellHousesSearchDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = sellHousesSearchDo.getHousePhotoTitle();
                if (StringTool.isNotEmpty(titlePhoto)&& !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                sellHousesSearchDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (claimSellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString(), city);
                } else if (claimSellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHousesSearchDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getProjExpertUserId(), city);
                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                    }

                }
                sellHousesSearchDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHousesSearchDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    sellHousesSearchDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isCutPrice = sellHousesSearchDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = sellHousesSearchDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                int isLowPrice = sellHousesSearchDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                sellHousesSearchDo.setHouseLableList(houseLableList);

                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低", "");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = sellHousesSearchDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = sellHousesSearchDo.getTotalAbsoluteWithCommunity();
                if (sellHousesSearchDo.getIsLowPrice() == 1) {
                    if (totalAbsoluteWithBizcircle < 0 && totalAbsoluteWithCommunity < 0) {
                        if (Math.abs(totalAbsoluteWithBizcircle) > Math.abs(totalAbsoluteWithCommunity)) {
                            lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                        } else {
                            lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                        }
                    } else if (totalAbsoluteWithBizcircle < 0) {
                        lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                    } else if (totalAbsoluteWithCommunity < 0) {
                        lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                    }
                }

                if (StringTool.isNotEmpty(lowPriceStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(lowPriceStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //3.降/涨X万
                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List recommendBuildTagNameList = sellHousesSearchDo.getRecommendBuildTagsName();
                String areaName = sellHousesSearchDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = sellHousesSearchDo.getTypeCounts();

                if (sellHousesSearchDo.getIsMustRob() == 1 && sellHousesSearchDo.getIsMainLayout() == 1) {
                    if (sellHousesSearchDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                        if (recommendBuildTagNameList.contains("豪宅")) {
                            communityLableStr = areaName + typeCountsMap.get(4).get(sellHousesSearchDo.getAreaId()) + "大豪宅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("别墅")) {
                            communityLableStr = areaName + typeCountsMap.get(5).get(sellHousesSearchDo.getAreaId()) + "大别墅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("首次置业")) {
                            communityLableStr = areaName + typeCountsMap.get(2).get(sellHousesSearchDo.getAreaId()) + "大首置社区主力户型";
                        } else if (recommendBuildTagNameList.contains("换房升级")) {
                            communityLableStr = areaName + typeCountsMap.get(3).get(sellHousesSearchDo.getAreaId()) + "大换房社区主力户型";
                        } else if (recommendBuildTagNameList.contains("近公园")) {
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }

                if (StringTool.isNotEmpty(communityLableStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(communityLableStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //6同商圈同户型范围内做低价排名
                Integer rankInLowCommunityLayout = sellHousesSearchDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(sellHousesSearchDo.getHouseBusinessName() + sellHousesSearchDo.getRoom() + "居室低总价榜NO." + rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = sellHousesSearchDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }
                sellHousesSearchDo.setHouseSubjectList(houseSubjectList);

                //增加地铁与房子之间的距离
                String keys = "";
                if (null != sellHouseDoQuery.getSubwayLineId() && sellHouseDoQuery.getSubwayLineId() > 0) {
                    keys += sellHouseDoQuery.getSubwayLineId().toString();
                }


                if (StringTool.isNotEmpty(nearbyDistance)) {
                    sellHousesSearchDo.setNearbyDistance(nearbyDistance);
                }

                //二手房弹幕第一行
                List<String> houseBarrageFirstList = new ArrayList<>();
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    houseBarrageFirstList.add("小区同户型总价最低");
                }
                boolean titleTag = true;
                if (sellHousesSearchDo.getTotalAbsoluteWithBizcircle() < 0) {
//                    String lowPriceStr = "总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万";
                    houseBarrageFirstList.add("总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万");
                    titleTag = false;
                }

//                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    houseBarrageFirstList.add("降" + priceFloat + "万");
                    titleTag = false;
                }
//                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
//                houseBarrageFirstList.add("本户型平均成交时间为" + avgDealCycle + "天");
                    titleTag = false;
                }
                if (titleTag) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseTitle());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseCutLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseCutLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseLowerLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseLowerLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseRobLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseRobLabel());
                }
                sellHousesSearchDo.setHouseBarrageFirstList(houseBarrageFirstList);

                //二手房弹幕第二行
                List<String> houseBarrageSecondList = new ArrayList<>();

                if (sellHousesSearchDo.getForwardName().contains("东") || sellHousesSearchDo.getForwardName().contains("南")) {
                    houseBarrageSecondList.add("采光很好");
                }
                if (sellHousesSearchDo.getRankInLowCommunityLayout() > 0) {
                    houseBarrageSecondList.add("小区同户型低总价榜NO." + sellHousesSearchDo.getRankInLowCommunityLayout());
                }
                if (sellHousesSearchDo.getTotalAbsoluteWithCommunity() < 0) {
                    houseBarrageSecondList.add("总价低于小区同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithCommunity()) + "万");
                }

                for (String tag : sellHousesSearchDo.getTagsName()) {
                    houseBarrageSecondList.add(tag);
                }

                sellHousesSearchDo.setHouseBarrageSecondList(houseBarrageSecondList);

                sellHousesSearchDos.add(sellHousesSearchDo);
            }
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int) searchResponse.getHits().getTotalHits());
        } else {
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int) searchResponse.getHits().getTotalHits());
//            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND,"二手房列表为空");
        }

        return sellHouseSearchDomain;
    }


    public FunctionScoreQueryBuilder getQuery(SellHouseDoQuery sellHouseDoQuery, BoolQueryBuilder boolQueryBuilder, String city) {
        FunctionScoreQueryBuilder query = null;
        List<String> searchKeyword = new ArrayList<>();
        if (StringUtil.isNotNullString(sellHouseDoQuery.getKeyword())) {
            searchKeyword = filterSellHouseChooseService.filterKeyWords(sellHouseDoQuery.getKeyword(), city);
        }

        if (StringTool.isNotBlank(sellHouseDoQuery.getKeyword())) {
            if (searchKeyword != null && searchKeyword.size() > 0) {
                int searchTermSize = searchKeyword.size();
                FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[searchTermSize];
                if (StringUtil.isNotNullString(AreaMap.getAreas(sellHouseDoQuery.getKeyword()))) {
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        QueryBuilder filter = QueryBuilders.termsQuery("houseBusinessName", searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                } else if (StringUtil.isNotNullString(DistrictMap.getDistricts(sellHouseDoQuery.getKeyword()))) {
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        QueryBuilder filter = QueryBuilders.termsQuery("area", searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                } else {
                    for (int i = 0; i < searchKeyword.size(); i++) {
                        QueryBuilder filter = QueryBuilders.termsQuery("plotName", searchKeyword.get(i));
                        ScoreFunctionBuilder scoreFunctionBuilder = ScoreFunctionBuilders.weightFactorFunction(searchTermSize - i);
                        filterFunctionBuilders[i] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(filter, scoreFunctionBuilder);
                    }
                }
                query = QueryBuilders.functionScoreQuery(boolQueryBuilder, filterFunctionBuilders).boost(10).maxBoost(100)
                        .scoreMode(FunctionScoreQuery.ScoreMode.MAX).boostMode(CombineFunction.MULTIPLY).setMinScore(0);
            }
        } else {
            query = QueryBuilders.functionScoreQuery(boolQueryBuilder);
        }
        return query;

    }


    @Override
    public SellHouseBeSureToSnatchDomain getBeSureToSnatchList(SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery, String city) {
        Date date = new Date();
        SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDomain = new SellHouseBeSureToSnatchDomain();
        NearBySellHouseQueryDo nearBySellHouseQueryDo = new NearBySellHouseQueryDo();
        BeanUtils.copyProperties(sellHouseBeSureToSnatchDoQuery, nearBySellHouseQueryDo);
        BoolQueryBuilder booleanQueryBuilder = filterSellHouseChooseService.filterChoose(nearBySellHouseQueryDo);
        List<SellHouseBeSureToSnatchDo> sellHouseBeSureToSnatchDos = new ArrayList<>();
        FieldSortBuilder sortFile = null;
        Integer subscribeId = -1;
        if (null != sellHouseBeSureToSnatchDoQuery.getIsNew()) {
            booleanQueryBuilder.must(termQuery("isNew", 1));
        }
        booleanQueryBuilder.must(termQuery("isMustRob", 1));
        booleanQueryBuilder.must(termQuery("is_claim", 0));
//        booleanQueryBuilder.must(QueryBuilders.termQuery("status",0));
        booleanQueryBuilder.must(termQuery("isDel", 0));

        if (null != sellHouseBeSureToSnatchDoQuery.getSortFile() && null != sellHouseBeSureToSnatchDoQuery.getSort()) {
            if (sellHouseBeSureToSnatchDoQuery.getSort().equals(1)) {
                sortFile = SortBuilders.fieldSort(sellHouseBeSureToSnatchDoQuery.getSortFile()).order(SortOrder.DESC);
            } else {
                sortFile = SortBuilders.fieldSort(sellHouseBeSureToSnatchDoQuery.getSortFile()).order(SortOrder.ASC);
            }
        }
        SearchResponse searchResponse = sellHouseEsDao.getBeSureToSnatchList(booleanQueryBuilder, sellHouseBeSureToSnatchDoQuery.getPageNum(),
                sellHouseBeSureToSnatchDoQuery.getPageSize(), sortFile, city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();

        for (SearchHit searchHit : searchHists) {
            String details = "";
            Object[] sort = searchHit.getSortValues();
            details = searchHit.getSourceAsString();
            SellHouseBeSureToSnatchDo sellHouseBeSureToSnatchDo = JSON.parseObject(details, SellHouseBeSureToSnatchDo.class);

            //判断3天内导入，且无图片，默认上显示默认图
            String importTime = sellHouseBeSureToSnatchDo.getImportTime();
            int isDefault = isDefaultImage(importTime, date, sellHouseBeSureToSnatchDo.getHousePhotoTitle());
            if (isDefault == 1) {
                sellHouseBeSureToSnatchDo.setIsDefaultImage(1);
            }

            AgentBaseDo agentBaseDo = new AgentBaseDo();
            if (sellHouseBeSureToSnatchDo.getIsClaim().equals(1)) {
                sellHouseBeSureToSnatchDo.setHouseId(sellHouseBeSureToSnatchDo.getClaimHouseId());
                sellHouseBeSureToSnatchDo.setHousePhotoTitle(sellHouseBeSureToSnatchDo.getClaimHousePhotoTitle());
                sellHouseBeSureToSnatchDo.setTagsName(sellHouseBeSureToSnatchDo.getClaimTagsName());
                sellHouseBeSureToSnatchDo.setHouseTitle(sellHouseBeSureToSnatchDo.getClaimHouseTitle());

                if (StringTool.isNotEmpty(sellHouseBeSureToSnatchDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseBeSureToSnatchDo.getUserId().toString(), city);
                }

            } else if (sellHouseBeSureToSnatchDo.getIsClaim() == 0) {
                if (StringUtil.isNotNullString(sellHouseBeSureToSnatchDo.getProjExpertUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseBeSureToSnatchDo.getProjExpertUserId(), city);
                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                }

            }

            sellHouseBeSureToSnatchDo.setAgentBaseDo(agentBaseDo);

            sellHouseBeSureToSnatchDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));

            sellHouseBeSureToSnatchDo.setSort(sort[0]);
            sellHouseBeSureToSnatchDos.add(sellHouseBeSureToSnatchDo);
        }
        sellHouseBeSureToSnatchDomain.setData(sellHouseBeSureToSnatchDos);
        UserBasic userBasic = UserBasic.getCurrent();
        if (null != userBasic) {
            UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
            BeanUtils.copyProperties(sellHouseBeSureToSnatchDoQuery, userSubscribeDetailDo);
            userSubscribeDetailDo.setTopicType(3);
            userSubscribeDetailDo.setBeginPrice(sellHouseBeSureToSnatchDoQuery.getBeginPrice());
            userSubscribeDetailDo.setEndPrice(sellHouseBeSureToSnatchDoQuery.getEndPrice());
            String districtIdsStr = "";
            Integer[] districtIds = sellHouseBeSureToSnatchDoQuery.getDistrictIds();
            if (StringTool.isNotEmpty(districtIds)) {
                //区域id排序，与订阅信息匹配
                Arrays.sort(districtIds);
                districtIdsStr = StringTool.IntegerArrayToString(districtIds);
            }
            userSubscribeDetailDo.setDistrictId(districtIdsStr);
            UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer.parseInt(userBasic.getUserId()));
            if (null != userSubscribe) {
                subscribeId = userSubscribe.getId();
            }
        }
        sellHouseBeSureToSnatchDomain.setSubscribeId(subscribeId);
        sellHouseBeSureToSnatchDomain.setTotalCount(hits.totalHits);
        return sellHouseBeSureToSnatchDomain;
    }

    /**
     * 获取推荐房源
     *
     * @param recommendEsf5DoQuery
     * @return
     */
    @Override
    public SellHouseSearchDomain getRecommendEsf5(RecommendEsf5DoQuery recommendEsf5DoQuery, String city) {
        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        BoolQueryBuilder recommendEsf5 = filterSellHouseChooseService.getRecommendEsf5(recommendEsf5DoQuery);
        FieldSortBuilder sortFile = null;
        SellHousesSearchDo sellHousesSearchDo = new SellHousesSearchDo();
        ClaimSellHouseDo claimSellHouseDo = new ClaimSellHouseDo();

        if ("3".equals(recommendEsf5DoQuery.getSort())) {
            sortFile = SortBuilders.fieldSort("houseTotalPrices").order(SortOrder.ASC);
        } else if ("6".equals(recommendEsf5DoQuery.getSort())) {
            sortFile = SortBuilders.fieldSort("buildArea").order(SortOrder.DESC);
        } else {
            sortFile = SortBuilders.fieldSort("updateTimeSort").order(SortOrder.DESC);
        }

        SearchResponse RecommendEsf5List = sellHouseEsDao.getBeSureToSnatchList(recommendEsf5,
                recommendEsf5DoQuery.getPageNum(), recommendEsf5DoQuery.getPageSize(), sortFile, city);
        SearchHits hits = RecommendEsf5List.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<SellHousesSearchDo> sellHousesSearchDos = new ArrayList<>();
        if (searchHists.length > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHists) {
                String details = "";
                details = searchHit.getSourceAsString();
                sellHousesSearchDo = JSON.parseObject(details, SellHousesSearchDo.class);

                String nearbyDistance = StringTool.nullToString(sellHousesSearchDo.getArea()) + " " + StringTool.nullToString(sellHousesSearchDo.getHouseBusinessName());


                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHousesSearchDo.getImportTime();
                int isDefault = isDefaultImage(importTime, date, sellHousesSearchDo.getHousePhotoTitle());
                if (isDefault == 1) {
                    sellHousesSearchDo.setIsDefaultImage(1);
                }
                claimSellHouseDo = JSON.parseObject(details, ClaimSellHouseDo.class);
                if (null != claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim() == 1) {   //将认领信息替换
                    sellHousesSearchDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    sellHousesSearchDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    sellHousesSearchDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    sellHousesSearchDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = sellHousesSearchDo.getHousePhotoTitle();
                if (StringTool.isNotEmpty(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                sellHousesSearchDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (claimSellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString(), city);
                } else if (claimSellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHousesSearchDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getProjExpertUserId(), city);
                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                    }

                }
                sellHousesSearchDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHousesSearchDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    sellHousesSearchDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isCutPrice = sellHousesSearchDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = sellHousesSearchDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                int isLowPrice = sellHousesSearchDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                sellHousesSearchDo.setHouseLableList(houseLableList);

                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低", "");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = sellHousesSearchDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = sellHousesSearchDo.getTotalAbsoluteWithCommunity();
                if (sellHousesSearchDo.getIsLowPrice() == 1) {
                    if (totalAbsoluteWithBizcircle < 0 && totalAbsoluteWithCommunity < 0) {
                        if (Math.abs(totalAbsoluteWithBizcircle) > Math.abs(totalAbsoluteWithCommunity)) {
                            lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                        } else {
                            lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                        }
                    } else if (totalAbsoluteWithBizcircle < 0) {
                        lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                    } else if (totalAbsoluteWithCommunity < 0) {
                        lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                    }
                }

                if (StringTool.isNotEmpty(lowPriceStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(lowPriceStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //3.降/涨X万
                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List recommendBuildTagNameList = sellHousesSearchDo.getRecommendBuildTagsName();
                String areaName = sellHousesSearchDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = sellHousesSearchDo.getTypeCounts();

                if (sellHousesSearchDo.getIsMustRob() == 1 && sellHousesSearchDo.getIsMainLayout() == 1) {
                    if (sellHousesSearchDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                        if (recommendBuildTagNameList.contains("豪宅")) {
                            communityLableStr = areaName + typeCountsMap.get(4).get(sellHousesSearchDo.getAreaId()) + "大豪宅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("别墅")) {
                            communityLableStr = areaName + typeCountsMap.get(5).get(sellHousesSearchDo.getAreaId()) + "大别墅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("首次置业")) {
                            communityLableStr = areaName + typeCountsMap.get(2).get(sellHousesSearchDo.getAreaId()) + "大首置社区主力户型";
                        } else if (recommendBuildTagNameList.contains("换房升级")) {
                            communityLableStr = areaName + typeCountsMap.get(3).get(sellHousesSearchDo.getAreaId()) + "大换房社区主力户型";
                        } else if (recommendBuildTagNameList.contains("近公园")) {
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }

                if (StringTool.isNotEmpty(communityLableStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(communityLableStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //6同商圈同户型范围内做低价排名
                Integer rankInLowCommunityLayout = sellHousesSearchDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(sellHousesSearchDo.getHouseBusinessName() + sellHousesSearchDo.getRoom() + "居室低总价榜NO." + rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = sellHousesSearchDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }
                sellHousesSearchDo.setHouseSubjectList(houseSubjectList);

                if (StringTool.isNotEmpty(nearbyDistance)) {
                    sellHousesSearchDo.setNearbyDistance(nearbyDistance);
                }

                //二手房弹幕第一行
                List<String> houseBarrageFirstList = new ArrayList<>();
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    houseBarrageFirstList.add("小区同户型总价最低");
                }
                boolean titleTag = true;
                if (sellHousesSearchDo.getTotalAbsoluteWithBizcircle() < 0) {
//                    String lowPriceStr = "总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万";
                    houseBarrageFirstList.add("总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万");
                    titleTag = false;
                }

//                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    houseBarrageFirstList.add("降" + priceFloat + "万");
                    titleTag = false;
                }
//                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
//                houseBarrageFirstList.add("本户型平均成交时间为" + avgDealCycle + "天");
                    titleTag = false;
                }
                if (titleTag) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseTitle());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseCutLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseCutLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseLowerLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseLowerLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseRobLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseRobLabel());
                }
                sellHousesSearchDo.setHouseBarrageFirstList(houseBarrageFirstList);

                //二手房弹幕第二行
                List<String> houseBarrageSecondList = new ArrayList<>();

                if (sellHousesSearchDo.getForwardName().contains("东") || sellHousesSearchDo.getForwardName().contains("南")) {
                    houseBarrageSecondList.add("采光很好");
                }
                if (sellHousesSearchDo.getRankInLowCommunityLayout() > 0) {
                    houseBarrageSecondList.add("小区同户型低总价榜NO." + sellHousesSearchDo.getRankInLowCommunityLayout());
                }
                if (sellHousesSearchDo.getTotalAbsoluteWithCommunity() < 0) {
                    houseBarrageSecondList.add("总价低于小区同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithCommunity()) + "万");
                }

                for (String tag : sellHousesSearchDo.getTagsName()) {
                    houseBarrageSecondList.add(tag);
                }

                sellHousesSearchDo.setHouseBarrageSecondList(houseBarrageSecondList);

                sellHousesSearchDos.add(sellHousesSearchDo);
            }
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum((int) RecommendEsf5List.getHits().getTotalHits());
        } else {
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND, "二手房列表为空");
        }
        return sellHouseSearchDomain;
    }

    @Override
    public int isDefaultImage(String importTime, Date today, String image) {
        if (StringTool.isEmpty(image)) {
            if (StringTool.isNotEmpty(importTime)) {
                int betweenDays = DateUtil.daysBetween(today, DateUtil.parseV1(importTime));
                if (betweenDays <= 3) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
        return 0;
    }

    /**
     * 二手房猜你喜欢
     *
     * @param sellHouseDoQuery
     * @param userId
     * @return
     */
    @Override
    public SellHouseSearchDomain queryGuessLikeSellHouseList(SellHouseDoQuery sellHouseDoQuery, Integer userId, String city) {

        SellHouseSearchDomain sellHouseSearchDomain = new SellHouseSearchDomain();
        SellHousesSearchDo sellHousesSearchDo = new SellHousesSearchDo();
        ClaimSellHouseDo claimSellHouseDo = new ClaimSellHouseDo();
        List<SellHousesSearchDo> sellHousesSearchDos = new ArrayList<>();
        List<SearchHit> searchHitList = new ArrayList<>();
        int totalCount = 0;
        // 如果用户登录，取用户收藏房源查询，如果未登录，客户端传用户浏览房源，如果无用户行为，取七天新上，标签数量排序，状态为待售

        if ((null == userId && (sellHouseDoQuery.getTotalPrice() == 0 && sellHouseDoQuery.getAreaId().length == 0 && sellHouseDoQuery.getLayoutId().length == 0 && sellHouseDoQuery.getHall() == 0)) ||
                (null != userId && (sellHouseDoQuery.getTotalPrice() == 0 && sellHouseDoQuery.getAreaId().length == 0 && sellHouseDoQuery.getLayoutId().length == 0 && sellHouseDoQuery.getHall() == 0))
                        && (userFavoriteEsHouseMapper.selectEsHouseFavoriteByUserIdAndCityId(Integer.valueOf(userId), CityUtils.returnCityId(city)) == 0)) {

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(termQuery("is_claim", 0));
            boolQueryBuilder.must(termQuery("isDel", 0));
            boolQueryBuilder.must(termQuery("isNew", 1));

//            Date date = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.datetimePattern);
//            String day = dateFormat.format(date);
//            String sevenDayBefore = DateUtil.beforeSevenDate(day);
//            boolQueryBuilder.must(QueryBuilders.rangeQuery("updateTimeSort").gte(sevenDayBefore).lte(day));

            SearchResponse searchResponse = sellHouseEsDao.getGuessLikeSellHouseList(boolQueryBuilder, city, sellHouseDoQuery.getPageNum(), sellHouseDoQuery.getPageSize());

            SearchHit[] hits = searchResponse.getHits().getHits();
            totalCount += (int) searchResponse.getHits().getTotalHits();
            if (hits.length > 0) {
                searchHitList.addAll(Arrays.asList(hits));
            }

        } else {


            if (null != userId) {
                SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery = new SellHouseFavoriteListDoQuery();
                sellHouseFavoriteListDoQuery.setUserId(userId);
                sellHouseFavoriteListDoQuery.setCityId(CityUtils.returnCityId(city));
                List<SellHouseFavoriteDo> sellHouseFavoriteDoList = userFavoriteEsHouseMapper.selectSellHouseFavoriteByUserIdAndCityId(sellHouseFavoriteListDoQuery);

                if (null != sellHouseFavoriteDoList && sellHouseFavoriteDoList.size() > 0) {
                    SellHouseFavoriteDo sellHouseFavoriteDo = sellHouseFavoriteDoList.get(0);
                    sellHouseDoQuery.setTotalPrice(sellHouseFavoriteDo.getHouseTotalPrices());
                    sellHouseDoQuery.setAreaName(sellHouseFavoriteDo.getAreaName());
                    List<Integer> roomList = new ArrayList<>(1);
                    roomList.add(sellHouseFavoriteDo.getRoom());
                    sellHouseDoQuery.setLayoutId(roomList.toArray(new Integer[1]));
                    sellHouseDoQuery.setHall(sellHouseFavoriteDo.getHall());
                }
            }


            // 规则：总价+-30，同商圈，同户型
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            //过滤为删除
            booleanQueryBuilder.must(termQuery("isDel", "0"));
            booleanQueryBuilder.must(termQuery("is_claim", "0"));

            //商圈
            if (null != sellHouseDoQuery.getAreaName() && !"".equals(sellHouseDoQuery.getAreaName())) {
                booleanQueryBuilder.must(
                        QueryBuilders.disMaxQuery()
                                .add(QueryBuilders.matchQuery("houseBusinessName", sellHouseDoQuery.getAreaName()).analyzer("ik_smart")).tieBreaker(0.3f));
            } else if (StringTool.isNotEmpty(sellHouseDoQuery.getAreaId()) && sellHouseDoQuery.getAreaId().length != 0) {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBusinessNameId", sellHouseDoQuery.getAreaId()));
            }

            //户型(室)
            if (StringTool.isNotEmpty(sellHouseDoQuery.getLayoutId()) && sellHouseDoQuery.getLayoutId().length > 0) {
                booleanQueryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("room", sellHouseDoQuery.getLayoutId())));
            }

            //厅
            if (StringTool.isNotEmpty(sellHouseDoQuery.getHall()) && sellHouseDoQuery.getHall() != 0) {
                booleanQueryBuilder.must(termQuery("hall", sellHouseDoQuery.getHall()));
            }

            // 总价浮动30
            if (StringTool.isDoubleNotEmpty(sellHouseDoQuery.getTotalPrice())) {
                double beginPrice = sellHouseDoQuery.getTotalPrice() - (sellHouseDoQuery.getTotalPrice() * 0.3);
                double endPrice = sellHouseDoQuery.getTotalPrice() + (sellHouseDoQuery.getTotalPrice() * 0.3);
                booleanQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(beginPrice).lte(endPrice));
            }


            SearchResponse searchResponseT1 = sellHouseEsDao.getGuessLikeSellHouseList(booleanQueryBuilder, city, sellHouseDoQuery.getPageNum(), sellHouseDoQuery.getPageSize());

            SearchHit[] hitsT1 = searchResponseT1.getHits().getHits();

            if (hitsT1.length > 0) {
                searchHitList.addAll(Arrays.asList(hitsT1));
            }
            int totalHits_T1 = (int) searchResponseT1.getHits().getTotalHits();
            totalCount += totalHits_T1;
            //当T1条件分页结果不足每页大小,查询T2条件并补充
            if (searchHitList.size() < sellHouseDoQuery.getPageSize()) {
                BoolQueryBuilder boolQueryBuilderT2 = QueryBuilders.boolQuery();
                //获取pageNum和pageSize
                int pageNum_T1 = totalHits_T1 / sellHouseDoQuery.getPageSize();
                int pageNum_T2 = sellHouseDoQuery.getPageNum() - pageNum_T1;
                int pageSize_T2 = sellHouseDoQuery.getPageSize();
                if (pageNum_T2 == 1) {
                    pageSize_T2 = sellHouseDoQuery.getPageSize() - totalHits_T1 % sellHouseDoQuery.getPageSize();
                }
                boolQueryBuilderT2.must(termQuery("is_claim", 0));
                boolQueryBuilderT2.must(termQuery("isDel", 0));
                boolQueryBuilderT2.must(termQuery("isNew", 1));
//
//                Date date = new Date();
//                SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.datetimePattern);
//                String day = dateFormat.format(date);
//                String sevenDayBefore = DateUtil.beforeSevenDate(day);
//                boolQueryBuilderT2.must(QueryBuilders.rangeQuery("updateTimeSort").gte(sevenDayBefore).lte(day));

                SearchResponse searchResponseT2 = sellHouseEsDao.getGuessLikeSellHouseList(boolQueryBuilderT2, city, pageNum_T2, pageSize_T2);

                SearchHit[] hitsT2 = searchResponseT2.getHits().getHits();
                int totalHits_T2 = (int) searchResponseT2.getHits().getTotalHits();
                totalCount += totalHits_T2;
                if (hitsT2.length > 0) {
                    searchHitList.addAll(Arrays.asList(hitsT2));
                }

            }
        }

        if (searchHitList.size() > 0) {
            Date date = new Date();
            for (SearchHit searchHit : searchHitList) {
                String details = "";
                details = searchHit.getSourceAsString();
                sellHousesSearchDo = JSON.parseObject(details, SellHousesSearchDo.class);

                String nearbyDistance = StringTool.nullToString(sellHousesSearchDo.getArea()) + " " + StringTool.nullToString(sellHousesSearchDo.getHouseBusinessName());

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHousesSearchDo.getImportTime();
                int isDefault = isDefaultImage(importTime, date, sellHousesSearchDo.getHousePhotoTitle());
                if (isDefault == 1) {
                    sellHousesSearchDo.setIsDefaultImage(1);
                }
                claimSellHouseDo = JSON.parseObject(details, ClaimSellHouseDo.class);
                if (null != claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim() == 1) {   //将认领信息替换
                    sellHousesSearchDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    sellHousesSearchDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    sellHousesSearchDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    sellHousesSearchDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = sellHousesSearchDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                sellHousesSearchDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (claimSellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHousesSearchDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getUserId().toString(), city);
                } else if (claimSellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHousesSearchDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHousesSearchDo.getProjExpertUserId(), city);
                    } else {
                        agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                    }

                }
                sellHousesSearchDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHousesSearchDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    sellHousesSearchDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isCutPrice = sellHousesSearchDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = sellHousesSearchDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                int isLowPrice = sellHousesSearchDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                sellHousesSearchDo.setHouseLableList(houseLableList);

                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低", "");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = sellHousesSearchDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = sellHousesSearchDo.getTotalAbsoluteWithCommunity();
                if (sellHousesSearchDo.getIsLowPrice() == 1) {
                    if (totalAbsoluteWithBizcircle < 0 && totalAbsoluteWithCommunity < 0) {
                        if (Math.abs(totalAbsoluteWithBizcircle) > Math.abs(totalAbsoluteWithCommunity)) {
                            lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                        } else {
                            lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                        }
                    } else if (totalAbsoluteWithBizcircle < 0) {
                        lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
                    } else if (totalAbsoluteWithCommunity < 0) {
                        lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
                    }
                }

                if (StringTool.isNotEmpty(lowPriceStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(lowPriceStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //3.降/涨X万
                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List recommendBuildTagNameList = sellHousesSearchDo.getRecommendBuildTagsName();
                String areaName = sellHousesSearchDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = sellHousesSearchDo.getTypeCounts();

                if (sellHousesSearchDo.getIsMustRob() == 1 && sellHousesSearchDo.getIsMainLayout() == 1) {
                    if (sellHousesSearchDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                        if (recommendBuildTagNameList.contains("豪宅")) {
                            communityLableStr = areaName + typeCountsMap.get(4).get(sellHousesSearchDo.getAreaId()) + "大豪宅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("别墅")) {
                            communityLableStr = areaName + typeCountsMap.get(5).get(sellHousesSearchDo.getAreaId()) + "大别墅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("首次置业")) {
                            communityLableStr = areaName + typeCountsMap.get(2).get(sellHousesSearchDo.getAreaId()) + "大首置社区主力户型";
                        } else if (recommendBuildTagNameList.contains("换房升级")) {
                            communityLableStr = areaName + typeCountsMap.get(3).get(sellHousesSearchDo.getAreaId()) + "大换房社区主力户型";
                        } else if (recommendBuildTagNameList.contains("近公园")) {
                            communityLableStr = "近公园社区主力户型";
                        }
                    }
                }

                if (StringTool.isNotEmpty(communityLableStr)) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(communityLableStr);
                    sellHouseSubject.setUrl("http://www.baidu.com");
                    houseSubjectList.add(sellHouseSubject);
                }

                //6同商圈同户型范围内做低价排名
                Integer rankInLowCommunityLayout = sellHousesSearchDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(sellHousesSearchDo.getHouseBusinessName() + sellHousesSearchDo.getRoom() + "居室低总价榜NO." + rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = sellHousesSearchDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }

                String traffic = sellHousesSearchDo.getTraffic();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
//                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                    } else if (i < 2000) {
                        DecimalFormat df = new DecimalFormat("0.0");
//                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1]  + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                }
                sellHousesSearchDo.setHouseSubjectList(houseSubjectList);

                //增加地铁与房子之间的距离
                String keys = "";
                if (null != sellHouseDoQuery.getSubwayLineId() && sellHouseDoQuery.getSubwayLineId() > 0) {
                    keys += sellHouseDoQuery.getSubwayLineId().toString();
                }


                if (StringTool.isNotEmpty(nearbyDistance)) {
                    sellHousesSearchDo.setNearbyDistance(nearbyDistance);
                }

                //二手房弹幕第一行
                List<String> houseBarrageFirstList = new ArrayList<>();
                if (sellHousesSearchDo.getIsLowest() == 1) {
                    houseBarrageFirstList.add("小区同户型总价最低");
                }
                boolean titleTag = true;
                if (sellHousesSearchDo.getTotalAbsoluteWithBizcircle() < 0) {
//                    String lowPriceStr = "总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万";
                    houseBarrageFirstList.add("总价低于商圈同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithBizcircle()) + "万");
                    titleTag = false;
                }

//                double priceFloat = sellHousesSearchDo.getPriceFloat();
                if (sellHousesSearchDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    houseBarrageFirstList.add("降" + priceFloat + "万");
                    titleTag = false;
                }
//                Integer avgDealCycle = sellHousesSearchDo.getAvgDealCycle();
                if (sellHousesSearchDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
//                houseBarrageFirstList.add("本户型平均成交时间为" + avgDealCycle + "天");
                    titleTag = false;
                }
                if (titleTag) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseTitle());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseCutLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseCutLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseLowerLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseLowerLabel());
                }
                if (StringTool.isNotEmpty(sellHousesSearchDo.getHouseRobLabel())) {
                    houseBarrageFirstList.add(sellHousesSearchDo.getHouseRobLabel());
                }
                sellHousesSearchDo.setHouseBarrageFirstList(houseBarrageFirstList);

                //二手房弹幕第二行
                List<String> houseBarrageSecondList = new ArrayList<>();

                if (sellHousesSearchDo.getForwardName().contains("东") || sellHousesSearchDo.getForwardName().contains("南")) {
                    houseBarrageSecondList.add("采光很好");
                }
                if (sellHousesSearchDo.getRankInLowCommunityLayout() > 0) {
                    houseBarrageSecondList.add("小区同户型低总价榜NO." + sellHousesSearchDo.getRankInLowCommunityLayout());
                }
                if (sellHousesSearchDo.getTotalAbsoluteWithCommunity() < 0) {
                    houseBarrageSecondList.add("总价低于小区同户型" + Math.abs(sellHousesSearchDo.getTotalAbsoluteWithCommunity()) + "万");
                }

                for (String tag : sellHousesSearchDo.getTagsName()) {
                    houseBarrageSecondList.add(tag);
                }

                sellHousesSearchDo.setHouseBarrageSecondList(houseBarrageSecondList);

                sellHousesSearchDos.add(sellHousesSearchDo);
            }
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum(totalCount);
        } else {
            sellHouseSearchDomain.setData(sellHousesSearchDos);
            sellHouseSearchDomain.setTotalNum(totalCount);
        }

        return sellHouseSearchDomain;

    }


    private List<SellHouseDo> getResultFromSearchHitList(String city, List<SellHouseDo> list, List<SearchHit> searchHitList) {
        for (SearchHit searchHit : searchHitList) {
            String details = searchHit.getSourceAsString();
            SellHouseDo sellHouseDo = JSON.parseObject(details, SellHouseDo.class);

            //判断3天内导入，且无图片，默认上显示默认图
            String importTime = sellHouseDo.getImportTime();
            int isDefault = isDefaultImage(importTime, new Date(), sellHouseDo.getHousePhotoTitle());
            if (isDefault == 1) {
                sellHouseDo.setIsDefaultImage(1);
            }
            //经纪人信息
            AgentBaseDo agentBaseDo = new AgentBaseDo();
            if (sellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
                agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);

            } else if (sellHouseDo.getIsClaim() == 0) {
                if (StringUtil.isNotNullString(sellHouseDo.getProjExpertUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getProjExpertUserId().toString(), city);
                } else {
                    agentBaseDo.setAgentName(searchHit.getSourceAsMap().get("houseProxyName") == null ? "" : searchHit.getSourceAsMap().get("houseProxyName").toString());
                    agentBaseDo.setAgentCompany(searchHit.getSourceAsMap().get("ofCompany") == null ? "" : searchHit.getSourceAsMap().get("ofCompany").toString());
                    agentBaseDo.setHeadPhoto(searchHit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhoto").toString());
                    agentBaseDo.setDisplayPhone(searchHit.getSourceAsMap().get("houseProxyPhone") == null ? "" : searchHit.getSourceAsMap().get("houseProxyPhone").toString());
                }
            }
            sellHouseDo.setAgentBaseDo(agentBaseDo);

            //设置房源公司图标
            String AgentCompany = agentBaseDo.getAgentCompany();
            if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                sellHouseDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
            }

            List<HouseColorLable> houseColorLableList = new ArrayList<>();

            int isMustRob = sellHouseDo.getIsMustRob();
            if (isMustRob == 1) {
                String houseRobCondition = StringTool.isNotEmpty(sellHouseDo.getHouseRobLabel())?"?"+sellHouseDo.getHouseRobLabel():"";
                houseColorLableList.add(new HouseColorLable("F0FAFF", "2FB3FF", "抢手榜", wapName + "/"+city+"/topics/house/hot"+houseRobCondition));
            }

            int isLowPrice = sellHouseDo.getIsLowPrice();
            if (isLowPrice == 1) {
                String houseLowerCondition = StringTool.isNotEmpty(sellHouseDo.getHouseLowerLabel())?"?"+sellHouseDo.getHouseLowerLabel():"";
                houseColorLableList.add(new HouseColorLable("FFF2F2", "FF6B6B", "捡漏榜", wapName + "/"+city+"/topics/house/low"+houseLowerCondition));

            }

            int isCutPrice = sellHouseDo.getIsCutPrice();
            if (isCutPrice == 1) {
                String houseCutCondition = StringTool.isNotEmpty(sellHouseDo.getHouseCutLabel())?"?"+sellHouseDo.getHouseCutLabel():"";
                houseColorLableList.add(new HouseColorLable("EFFFEF", "47E24C", "降价榜", wapName + "/"+city+"/topics/house/reduction"+houseCutCondition));
            }

            List recommendBuildTagNameList = sellHouseDo.getRecommendBuildTagsName();
            String areaName = sellHouseDo.getArea();
            Map<Integer, Map<String, Integer>> typeCountsMap = sellHouseDo.getTypeCounts();

            String districtIds = StringTool.isNotEmpty(sellHouseDo.getAreaId())?"?districtIds="+sellHouseDo.getAreaId():"";
            if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                String communityLableStr = "";
                if (recommendBuildTagNameList.contains("豪宅")) {
                    communityLableStr = areaName + typeCountsMap.get(4).get(sellHouseDo.getAreaId()) + "大豪宅社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/luxury"+districtIds));
                }
                if (recommendBuildTagNameList.contains("别墅")) {
                    communityLableStr = areaName + typeCountsMap.get(5).get(sellHouseDo.getAreaId()) + "大别墅社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/villa"+districtIds));
                }
                if (recommendBuildTagNameList.contains("首次置业")) {
                    communityLableStr = areaName + typeCountsMap.get(2).get(sellHouseDo.getAreaId()) + "大首置社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/first"+districtIds));
                }
                if (recommendBuildTagNameList.contains("换房升级")) {
                    communityLableStr = areaName + typeCountsMap.get(3).get(sellHouseDo.getAreaId()) + "大换房社区主力户型";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/plot/improve"+districtIds));
                }
                if (recommendBuildTagNameList.contains("近公园")) {
//                    communityLableStr = "近公园社区主力户型";
                    communityLableStr = "近公园";
                    String nearpark = StringTool.isNotEmpty(sellHouseDo.getNearPark())?"?strNearestPark="+sellHouseDo.getNearPark():"";
                    houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, wapName + "/"+city+"/topics/nearpark"+nearpark));
                }
            }

            if (sellHouseDo.getIsCommunityTopHouse() == 1) {
                houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", "TOP50社区", wapName + "/"+city+"/topics/top50"+districtIds));
            }

            Integer rankLowInBizcircleLayout = sellHouseDo.getRankLowInBizcircleLayout();
            if (rankLowInBizcircleLayout > 0 && rankLowInBizcircleLayout < 10) {
                String text = sellHouseDo.getHouseBusinessName() + "居室低总价榜NO." + rankLowInBizcircleLayout;
//                houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", text, "http://www.baidu.com"));
            }
            sellHouseDo.setHouseColorLableList(houseColorLableList);

            //二手房弹幕第一行
            List<String> houseBarrageFirstList = new ArrayList<>();
            if (sellHouseDo.getIsLowest() == 1) {
                houseBarrageFirstList.add("小区同户型总价最低");
            }
            boolean titleTag = true;
            if (sellHouseDo.getTotalAbsoluteWithBizcircle() < 0) {
                String lowPriceStr = "总价低于商圈同户型" + Math.abs(sellHouseDo.getTotalAbsoluteWithBizcircle()) + "万";
                houseBarrageFirstList.add(lowPriceStr);
                titleTag = false;
            }
            double priceFloat = sellHouseDo.getPriceFloat();
            if (sellHouseDo.getIsCutPrice() == 1 && priceFloat > 0) {
                houseBarrageFirstList.add("降" + priceFloat + "万");
                titleTag = false;
            }
            Integer avgDealCycle = sellHouseDo.getAvgDealCycle();
            if (sellHouseDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
                titleTag = false;
            }
            if (titleTag) {
                houseBarrageFirstList.add(sellHouseDo.getHouseTitle());
            }
            if (StringTool.isNotEmpty(sellHouseDo.getHouseCutLabel())) {
                houseBarrageFirstList.add(sellHouseDo.getHouseCutLabel());
            }
            if (StringTool.isNotEmpty(sellHouseDo.getHouseLowerLabel())) {
                houseBarrageFirstList.add(sellHouseDo.getHouseLowerLabel());
            }
            if (StringTool.isNotEmpty(sellHouseDo.getHouseRobLabel())) {
                houseBarrageFirstList.add(sellHouseDo.getHouseRobLabel());
            }
            sellHouseDo.setHouseBarrageFirstList(houseBarrageFirstList);

            //二手房弹幕第二行
            List<String> houseBarrageSecondList = new ArrayList<>();

            if (sellHouseDo.getForwardName().contains("东") || sellHouseDo.getForwardName().contains("南")) {
                houseBarrageSecondList.add("采光很好");
            }
            if (sellHouseDo.getRankInLowCommunityLayout() > 0) {
                houseBarrageSecondList.add("小区同户型低总价榜NO." + sellHouseDo.getRankInLowCommunityLayout());
            }
            if (sellHouseDo.getTotalAbsoluteWithCommunity() < 0) {
                houseBarrageSecondList.add("总价低于小区同户型" + Math.abs(sellHouseDo.getTotalAbsoluteWithCommunity()) + "万");
            }

            for (String tag : sellHouseDo.getTagsName()) {
                houseBarrageSecondList.add(tag);
            }

            sellHouseDo.setHouseBarrageSecondList(houseBarrageSecondList);

            list.add(sellHouseDo);
        }
        return list;
    }


    @Override
    public CustomConditionDetailsDomain getEsfCustomConditionDetails(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city) {
        CustomConditionDetailsDomain customConditionDetailsDomain = new CustomConditionDetailsDomain();
        List<CustomConditionDistrictDo> customConditionDistrictDos = new ArrayList<>();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringTool.isNotEmpty(userFavoriteConditionDoQuery.getDistrictId()) && userFavoriteConditionDoQuery.getDistrictId().length != 0) {
            boolQuery.must(termsQuery("district_id", userFavoriteConditionDoQuery.getDistrictId()));
        }
        SearchResponse avgPriceByDistrict = sellHouseEsDao.getAvgPriceByDistrict(boolQuery, city);
        Terms districtTerms = avgPriceByDistrict.getAggregations().get("districtId");
        List districtBucket = districtTerms.getBuckets();
        for (Object bucket : districtBucket) {
            CustomConditionDistrictDo customConditionDistrictDo = new CustomConditionDistrictDo();
            customConditionDistrictDo.setDistrictId(Integer.valueOf(((ParsedStringTerms.ParsedBucket) bucket).getKeyAsString()));

            Terms districtName = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("districtName");
            if (districtName.getBuckets().size() > 0) {
                customConditionDistrictDo.setDistrictName(districtName.getBuckets().get(0).getKeyAsString());
            }

            Terms latitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("latitude");
            if (latitude.getBuckets().size() > 0) {
                customConditionDistrictDo.setLatitude(latitude.getBuckets().get(0).getKeyAsNumber().doubleValue());
            }

            Terms longitude = ((ParsedStringTerms.ParsedBucket) bucket).getAggregations().get("longitude");
            if (longitude.getBuckets().size() > 0) {
                customConditionDistrictDo.setLongitude(longitude.getBuckets().get(0).getKeyAsNumber().doubleValue());
            }
            //房源
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(termQuery("isDel", "0"));
            boolQueryBuilder.must(termQuery("is_claim", "0"));

            if (StringTool.isNotEmpty(userFavoriteConditionDoQuery.getLayoutId()) && userFavoriteConditionDoQuery.getLayoutId().length != 0) {
                String[] layoutId = userFavoriteConditionDoQuery.getLayoutId();
                boolQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
            }
            if (StringTool.isDoubleNotEmpty(userFavoriteConditionDoQuery.getBeginPrice()) && StringTool.isDoubleEmpty(userFavoriteConditionDoQuery.getEndPrice())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()));
            } else if (StringTool.isDoubleNotEmpty(userFavoriteConditionDoQuery.getBeginPrice()) && StringTool.isDoubleNotEmpty(userFavoriteConditionDoQuery.getEndPrice())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").gte(userFavoriteConditionDoQuery.getBeginPrice()).lte(userFavoriteConditionDoQuery.getEndPrice()));
            } else if (StringTool.isDoubleEmpty(userFavoriteConditionDoQuery.getBeginPrice()) && StringTool.isDoubleNotEmpty(userFavoriteConditionDoQuery.getEndPrice())) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery("houseTotalPrices").lte(userFavoriteConditionDoQuery.getEndPrice()));
            }

            boolQueryBuilder.must(termQuery("areaId", customConditionDistrictDo.getDistrictId()));

            SearchResponse searchResponse = sellHouseEsDao.getEsfCustomConditionDetails(boolQueryBuilder, city);
            Terms terms = searchResponse.getAggregations().get("areaName");
            List buckets = terms.getBuckets();
            List<CustomConditionDetailsDo> customConditionDetailsDoList = new ArrayList<>();
            for (Object obhectBucket : buckets) {

                CustomConditionDetailsDo conditionDetailsDo = new CustomConditionDetailsDo();
                conditionDetailsDo.setBizcircleId(((ParsedLongTerms.ParsedBucket) obhectBucket).getKeyAsNumber().intValue());
                conditionDetailsDo.setHouseCount((int) ((ParsedLongTerms.ParsedBucket) obhectBucket).getDocCount());
                ParsedMin houseMinArea = ((ParsedLongTerms.ParsedBucket) obhectBucket).getAggregations().get("houseMinArea");

                Double minArea = houseMinArea.getValue();
                if (null != minArea) {
                    conditionDetailsDo.setHouseMinArea(new BigDecimal(minArea).setScale(1, RoundingMode.UP).doubleValue());
                }
                ParsedMax houseMaxArea = ((ParsedLongTerms.ParsedBucket) obhectBucket).getAggregations().get("houseMaxArea");
                Double maxArea = houseMaxArea.getValue();
                if (null != maxArea) {
                    conditionDetailsDo.setHouseMaxArea(new BigDecimal(maxArea).setScale(1, RoundingMode.UP).doubleValue());
                }

                conditionDetailsDo.setHouseAreaDesc(conditionDetailsDo.getHouseMinArea() + "-" + conditionDetailsDo.getHouseMaxArea() + "m²");
                Terms buildCount = ((ParsedLongTerms.ParsedBucket) obhectBucket).getAggregations().get("buildCount");
                if (buildCount.getBuckets().size() > 0) {
                    conditionDetailsDo.setBuildCount(buildCount.getBuckets().size());
                } else {
                    conditionDetailsDo.setBuildCount(0);
                }


                BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
                queryBuilder.must(QueryBuilders.termQuery("bizcircle_id", conditionDetailsDo.getBizcircleId()));
                SearchResponse avgPriceByBizcircle = sellHouseEsDao.getAvgPriceByBizcircle(queryBuilder, city);
                SearchHits layoutHits = avgPriceByBizcircle.getHits();
                SearchHit[] searchHists = layoutHits.getHits();
                if (searchHists.length > 0) {
                    for (SearchHit searchHit : searchHists) {
                        conditionDetailsDo.setBizcircleName(searchHit.getSourceAsMap().get("bizcircle_name") == null ? "" : searchHit.getSourceAsMap().get("bizcircle_name").toString());
                        conditionDetailsDo.setAveragePrice("");
                        if (searchHit.getSourceAsMap().get("bizcircle_avgprice") != null) {
                            BigDecimal bigDecimal = new BigDecimal(searchHit.getSourceAsMap().get("bizcircle_avgprice").toString());
                            Integer bizcircleAvgPrice = Integer.parseInt(bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
                            conditionDetailsDo.setAveragePrice(String.valueOf(bizcircleAvgPrice));
                        }
//                        conditionDetailsDo.setAveragePrice(searchHit.getSourceAsMap().get("bizcircle_avgprice")==null?"":searchHit.getSourceAsMap().get("bizcircle_avgprice").toString());
                        String location = searchHit.getSourceAsMap().get("bizcircle_location") == null ? "" : searchHit.getSourceAsMap().get("bizcircle_location").toString();
                        if (StringTool.isNotEmpty(location)) {
                            String[] lat_lon = location.split(",");
                            conditionDetailsDo.setLatitude(Double.valueOf(lat_lon[0]));
                            conditionDetailsDo.setLongitude(Double.valueOf(lat_lon[1]));
                        }
                    }
                }
                customConditionDetailsDoList.add(conditionDetailsDo);

            }
            customConditionDistrictDo.setData(customConditionDetailsDoList);
            customConditionDistrictDos.add(customConditionDistrictDo);

        }
        customConditionDetailsDomain.setData(customConditionDistrictDos);
        return customConditionDetailsDomain;
    }

}
