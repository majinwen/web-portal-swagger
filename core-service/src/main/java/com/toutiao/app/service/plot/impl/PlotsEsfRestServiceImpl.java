package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDo;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.constant.company.CompanyIconEnum;
import com.toutiao.web.common.constant.house.HouseLableEnum;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    @Value("${wap.domain.name}")
    private String wapName;

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
        ParsedStringTerms gradeTerms = (ParsedStringTerms) aggMap.get("roomCount");

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
    public SellAndClaimHouseDetailsDomain getEsfByPlotsIdAndRoom(Integer plotsId, Integer room, Integer pageNum, Integer pageSize, String city) {

        SellAndClaimHouseDetailsDomain sellAndClaimHouseDetailsDomain = new SellAndClaimHouseDetailsDomain();
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

//        if(searchresponse.getHits().totalHits==0){
//            throw new BaseException(PlotsInterfaceErrorCodeEnum.PLOTS_ESF_NOT_FOUND,"小区没有出售房源信息");
//        }
        SearchHits layoutHits = searchresponse.getHits();
        SearchHit[] searchHists = layoutHits.getHits();
        long totalHits = searchresponse.getHits().totalHits;
        if (searchHists.length > 0) {
            String details = "";
            for (SearchHit hit : searchHists) {
                details = hit.getSourceAsString();
                SellAndClaimHouseDetailsDo sellHouseDo = JSON.parseObject(details, SellAndClaimHouseDetailsDo.class);

                String nearbyDistance = StringTool.nullToString(sellHouseDo.getArea()) + " " + StringTool.nullToString(sellHouseDo.getHouseBusinessName());
                String traffic = sellHouseDo.getTraffic();
                String[] trafficArr = traffic.split("\\$");
                if (trafficArr.length == 3) {
                    int i = Integer.parseInt(trafficArr[2]);
                    if (i < 1000) {
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + trafficArr[2] + "米";
                    } else if (i < 2000) {
                        DecimalFormat df = new DecimalFormat("0.0");
                        nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[0] + trafficArr[1] + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                    }
                }


                if (StringTool.isNotEmpty(nearbyDistance)) {
                    sellHouseDo.setNearbyDistance(nearbyDistance);
                }

                //判断3天内导入，且无图片，默认上显示默认图
                String importTime = sellHouseDo.getImportTime();
                int isDefault = sellHouseService.isDefaultImage(importTime, date, sellHouseDo.getHousePhotoTitle());
                if (isDefault == 1) {
                    sellHouseDo.setIsDefaultImage(1);
                }
                ClaimSellHouseDo claimSellHouseDo = JSON.parseObject(details, ClaimSellHouseDo.class);
                if (null != claimSellHouseDo.getIsClaim() && claimSellHouseDo.getIsClaim() == 1) {   //将认领信息替换
                    sellHouseDo.setHouseId(claimSellHouseDo.getClaimHouseId());
                    sellHouseDo.setHouseTitle(claimSellHouseDo.getClaimHouseTitle());
                    sellHouseDo.setTagsName(claimSellHouseDo.getClaimTagsName());
                    sellHouseDo.setHousePhotoTitle(claimSellHouseDo.getClaimHousePhotoTitle());
                }
                String titlePhoto = sellHouseDo.getHousePhotoTitle();
                if (StringUtil.isNotNullString(titlePhoto) && !titlePhoto.startsWith("http")) {
                    titlePhoto = "http://s1.qn.toutiaofangchan.com/" + titlePhoto + "-dongfangdi400x300";
                }
                sellHouseDo.setHousePhotoTitle(titlePhoto);

                AgentBaseDo agentBaseDo = new AgentBaseDo();
                if (claimSellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);
                } else if (claimSellHouseDo.getIsClaim() == 0) {
                    if (StringUtil.isNotNullString(sellHouseDo.getProjExpertUserId())) {
                        agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getProjExpertUserId(), city);
                    } else {
                        agentBaseDo.setAgentName(hit.getSourceAsMap().get("houseProxyName") == null ? "" : hit.getSourceAsMap().get("houseProxyName").toString());
                        agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : hit.getSourceAsMap().get("houseProxyPhoto").toString());
                        agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("houseProxyPhone") == null ? "" : hit.getSourceAsMap().get("houseProxyPhone").toString());
                        agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("ofCompany") == null ? "" : hit.getSourceAsMap().get("ofCompany").toString());
                    }

                }
                sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                sellHouseDo.setAgentBaseDo(agentBaseDo);

                //设置房源公司图标
                String AgentCompany = agentBaseDo.getAgentCompany();
                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
                    sellHouseDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
                }

                //设置房源标签
                List<HouseLable> houseLableList = new ArrayList<>();
                int isCutPrice = sellHouseDo.getIsCutPrice();
                if (isCutPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.CUTPRICE);
                    houseLableList.add(houseLable);
                }
                int isMustRob = sellHouseDo.getIsMustRob();
                if (isMustRob == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.MUSTROB);
                    houseLableList.add(houseLable);
                }
                int isLowPrice = sellHouseDo.getIsLowPrice();
                if (isLowPrice == 1) {
                    HouseLable houseLable = new HouseLable(HouseLableEnum.LOWPRICE);
                    houseLableList.add(houseLable);
                }
                sellHouseDo.setHouseLableList(houseLableList);

                //设置房源专题
                List<HouseSubject> houseSubjectList = new ArrayList<>();

                //1.同户型小区均价最低
                if (sellHouseDo.getIsLowest() == 1) {
                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低", "");
                    houseSubjectList.add(houseSubject);
                }

                //2.总价低于小区/商圈同户型xx万
                String lowPriceStr = "";
                double totalAbsoluteWithBizcircle = sellHouseDo.getTotalAbsoluteWithBizcircle();
                double totalAbsoluteWithCommunity = sellHouseDo.getTotalAbsoluteWithCommunity();
                if (sellHouseDo.getIsLowPrice() == 1) {
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
                double priceFloat = sellHouseDo.getPriceFloat();
                if (sellHouseDo.getIsCutPrice() == 1 && priceFloat > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText("降" + priceFloat + "万");
                    sellHouseSubject.setUrl("/" + city + "/topics/house/reduction");
                    houseSubjectList.add(sellHouseSubject);
                }

                //4.平均成交天数
                Integer avgDealCycle = sellHouseDo.getAvgDealCycle();
                if (sellHouseDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
                    houseSubjectList.add(sellHouseSubject);
                }

                //5.商圈N大XX社区主力户型
                String communityLableStr = "";
                List recommendBuildTagNameList = sellHouseDo.getRecommendBuildTagsName();
                String areaName = sellHouseDo.getArea();
                Map<Integer, Map<String, Integer>> typeCountsMap = sellHouseDo.getTypeCounts();

                if (sellHouseDo.getIsMustRob() == 1 && sellHouseDo.getIsMainLayout() == 1) {
                    if (sellHouseDo.getIsCommunityTopHouse() == 1) {
                        communityLableStr = "top50社区主力户型";
                    } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                        if (recommendBuildTagNameList.contains("豪宅")) {
                            communityLableStr = areaName + typeCountsMap.get(4).get(sellHouseDo.getAreaId()) + "大豪宅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("别墅")) {
                            communityLableStr = areaName + typeCountsMap.get(5).get(sellHouseDo.getAreaId()) + "大别墅社区主力户型";
                        } else if (recommendBuildTagNameList.contains("首次置业")) {
                            communityLableStr = areaName + typeCountsMap.get(2).get(sellHouseDo.getAreaId()) + "大首置社区主力户型";
                        } else if (recommendBuildTagNameList.contains("换房升级")) {
                            communityLableStr = areaName + typeCountsMap.get(3).get(sellHouseDo.getAreaId()) + "大换房社区主力户型";
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
                Integer rankInLowCommunityLayout = sellHouseDo.getRankInLowCommunityLayout();
                if (rankInLowCommunityLayout > 0) {
                    HouseSubject sellHouseSubject = new HouseSubject();
                    sellHouseSubject.setText(sellHouseDo.getHouseBusinessName() + sellHouseDo.getRoom() + "居室低总价榜NO." + rankInLowCommunityLayout);
                    sellHouseSubject.setUrl("");
//                    houseSubjectList.add(sellHouseSubject);
                }

                //tagsName
                String[] tagsNameArray = sellHouseDo.getTagsName();
                if (tagsNameArray.length > 0) {
                    for (String tagsName : tagsNameArray) {
                        HouseSubject sellHouseSubject = new HouseSubject();
                        sellHouseSubject.setText(tagsName);
                        sellHouseSubject.setUrl("http://www.baidu.com");
                        houseSubjectList.add(sellHouseSubject);
                    }
                }
                sellHouseDo.setHouseSubjectList(houseSubjectList);





//                //判断3天内导入，且无图片，默认上显示默认图
//                String importTime = sellHouseDo.getImportTime();
//                int isDefault = sellHouseService.isDefaultImage(importTime, date, sellHouseDo.getHousePhotoTitle());
//                if (isDefault == 1) {
//                    sellHouseDo.setIsDefaultImage(1);
//                }
//
//
//                if (hit.getSourceAsMap().get("is_claim").toString().equals("1")) {
//                    sellHouseDo.setHousePhotoTitle(hit.getSourceAsMap().get("claimHousePhotoTitle").toString());
//                    sellHouseDo.setHouseId(hit.getSourceAsMap().get("claimHouseId").toString());
//                }
//
//                AgentBaseDo agentBaseDo = new AgentBaseDo();
//                if (sellHouseDo.getIsClaim() == 1 && StringTool.isNotEmpty(sellHouseDo.getUserId())) {
//                    agentBaseDo = agentService.queryAgentInfoByUserId(sellHouseDo.getUserId().toString(), city);
//                } else {
//                    agentBaseDo.setAgentName(hit.getSourceAsMap().get("houseProxyName") == null ? "" : hit.getSourceAsMap().get("houseProxyName").toString());
//                    agentBaseDo.setAgentCompany(hit.getSourceAsMap().get("ofCompany") == null ? "" : hit.getSourceAsMap().get("ofCompany").toString());
//                    agentBaseDo.setHeadPhoto(hit.getSourceAsMap().get("houseProxyPhoto") == null ? "" : hit.getSourceAsMap().get("houseProxyPhoto").toString());
//                    agentBaseDo.setDisplayPhone(hit.getSourceAsMap().get("houseProxyPhone") == null ? "" : hit.getSourceAsMap().get("houseProxyPhone").toString());
//                }
//
//                sellHouseDo.setPlotNameAccurate(sellHouseDo.getPlotName());
//                //设置房源公司图标
//                String AgentCompany = agentBaseDo.getAgentCompany();
//                if (!StringUtil.isNullString(AgentCompany) && CompanyIconEnum.containKey(AgentCompany)) {
//                    sellHouseDo.setCompanyIcon(CompanyIconEnum.getValueByKey(AgentCompany));
//                }
//
//                //设置房源专题
//                List<HouseSubject> houseSubjectList = new ArrayList<>();
//                //1.同户型小区均价最低
//                if (sellHouseDo.getIsLowest() == 1) {
//                    HouseSubject houseSubject = new HouseSubject("小区同户型总价最低", "");
//                    houseSubjectList.add(houseSubject);
//                }
//
//                //2.总价低于小区/商圈同户型xx万
//                String lowPriceStr = "";
//                double totalAbsoluteWithBizcircle = sellHouseDo.getTotalAbsoluteWithBizcircle();
//                double totalAbsoluteWithCommunity = sellHouseDo.getTotalAbsoluteWithCommunity();
//                if (sellHouseDo.getIsLowPrice() == 1) {
//                    if (totalAbsoluteWithBizcircle < 0 && totalAbsoluteWithCommunity < 0) {
//                        if (Math.abs(totalAbsoluteWithBizcircle) > Math.abs(totalAbsoluteWithCommunity)) {
//                            lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
//                        } else {
//                            lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
//                        }
//                    } else if (totalAbsoluteWithBizcircle < 0) {
//                        lowPriceStr = "总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万";
//                    } else if (totalAbsoluteWithCommunity < 0) {
//                        lowPriceStr = "总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万";
//                    }
//                }
//
//                if (StringTool.isNotEmpty(lowPriceStr)) {
//                    HouseSubject sellHouseSubject = new HouseSubject();
//                    sellHouseSubject.setText(lowPriceStr);
//                    sellHouseSubject.setUrl("http://www.baidu.com");
//                    houseSubjectList.add(sellHouseSubject);
//                }
//
//                //3.降/涨X万
//                double priceFloat = sellHouseDo.getPriceFloat();
//                if (sellHouseDo.getIsCutPrice() == 1 && priceFloat > 0) {
//                    HouseSubject sellHouseSubject = new HouseSubject();
//                    sellHouseSubject.setText("降" + priceFloat + "万");
//                    sellHouseSubject.setUrl("http://www.baidu.com");
//                    houseSubjectList.add(sellHouseSubject);
//                }
//
//                //4.平均成交天数
//                Integer avgDealCycle = sellHouseDo.getAvgDealCycle();
//                if (sellHouseDo.getIsDealLayout() == 1 && avgDealCycle > 0) {
//                    HouseSubject sellHouseSubject = new HouseSubject("本户型平均成交时间为" + avgDealCycle + "天", "");
//                    houseSubjectList.add(sellHouseSubject);
//                }
//                sellHouseDo.setHouseSubjectList(houseSubjectList);
                sellHouseDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
                //sellHouseDo.setAgentBaseDo(agentBaseDo);
                sellHouseDoList.add(sellHouseDo);
                sellAndClaimHouseDetailsDomain.setData(sellHouseDoList);
                sellAndClaimHouseDetailsDomain.setTotalNum((int) totalHits);
            }
        } else {
            sellAndClaimHouseDetailsDomain.setData(sellHouseDoList);
            sellAndClaimHouseDetailsDomain.setTotalNum(0);
        }
        return sellAndClaimHouseDetailsDomain;
    }

    @Override
    public PlotsEsfRoomCountDomain queryHouseCountByPlotsId(Integer plotsId, String city) {
        List<PlotsEsfRoomCountDo> plotsEsfRoomCountDoList = new ArrayList<>();

        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = new PlotsEsfRoomCountDomain();

        SearchResponse searchResponse = sellHouseEsDao.getSellHouseCountByPlotsId(plotsId,city);
        Map aggMap =searchResponse.getAggregations().asMap();
        ParsedStringTerms roomCount = (ParsedStringTerms) aggMap.get("roomCount");


        Iterator roomBucketIt = roomCount.getBuckets().iterator();
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
//
//    @Override
//    public int isDefaultImage(String importTime, Date today, String image) {
//        if (StringTool.isEmpty(image)) {
//            if (StringTool.isNotEmpty(importTime)) {
//                int betweenDays = DateUtil.daysBetween(today, DateUtil.parseV1(importTime));
//                if (betweenDays <= 3) {
//                    return 1;
//                } else {
//                    return 0;
//                }
//            }
//        } else {
//            return 0;
//        }
//        return 0;
//    }
}
