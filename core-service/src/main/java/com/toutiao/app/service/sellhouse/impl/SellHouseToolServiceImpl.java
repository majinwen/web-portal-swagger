package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.sellhouse.SellHouseToolService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SellHouseToolServiceImpl implements SellHouseToolService {

    @Autowired
    private CommunityRestService communityRestService;

    @Override
    public List<String> getHouseBarrageFirstList(SearchHit searchHit, String city) {
        List<String> houseBarrageFirstList = new ArrayList<>();
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());

        boolean titleTag = true;

        Integer isLowest = searchJson.getInteger("isLowest");
        if (StringTool.isNotEmpty(isLowest) && isLowest == 1) {
            houseBarrageFirstList.add("小区同户型总价最低");
        }

        Double totalAbsoluteWithBizcircle = searchJson.getDouble("totalAbsoluteWithBizcircle");
        if (StringTool.isNotEmpty(totalAbsoluteWithBizcircle) && totalAbsoluteWithBizcircle < 0) {
            houseBarrageFirstList.add("总价低于商圈同户型" + Math.abs(totalAbsoluteWithBizcircle) + "万");
            titleTag = false;
        }

        double priceFloat = searchJson.getDouble("priceFloat");
        Integer isCutPrice = searchJson.getInteger("isCutPrice");
        String houseCutPriceMsg = "";
        if (StringTool.isNotEmpty(isCutPrice) && StringTool.isNotEmpty(priceFloat) && isCutPrice == 1 && priceFloat < 0) {
            houseCutPriceMsg = "降" + Math.abs(priceFloat) + "万";
            houseBarrageFirstList.add(houseCutPriceMsg);
            titleTag = false;
        }

        Integer avgDealCycle = searchJson.getInteger("avgDealCycle");
        Integer isDealLayout = searchJson.getInteger("isDealLayout");
        if (StringTool.isNotEmpty(avgDealCycle) && StringTool.isNotEmpty(isDealLayout) && isDealLayout == 1 && avgDealCycle > 0) {
            houseBarrageFirstList.add("平均成交周期" + avgDealCycle + "天");
            titleTag = false;
        }

        if (titleTag && StringTool.isNotEmpty(searchJson.getString("houseTitle"))) {
            houseBarrageFirstList.add(searchJson.getString("houseTitle"));
        }

        //抢手 信息补充
        String communityLableStr = getCommunityLableStr(searchHit, city);

        //捡漏 信息补充
        Double totalRelativeWithBizcircle = searchJson.getDouble("totalRelativeWithBizcircle");
        Double totalRelativeWithCommunity = searchJson.getDouble("totalRelativeWithCommunity");
        String houseLower = "";
        Double totalRelative = null;
        if(StringTool.isNotEmpty(totalRelativeWithBizcircle) && StringTool.isNotEmpty(totalRelativeWithCommunity)){
            totalRelative = Math.min(totalRelativeWithBizcircle, totalRelativeWithCommunity);
        }

        int isLowPrice = searchJson.getInteger("isLowPrice");
        if (StringTool.isNotEmpty(totalRelative) && StringTool.isNotEmpty(isLowPrice) && isLowPrice == 1 && totalRelative < 0) {
            if (totalRelativeWithBizcircle < totalRelativeWithCommunity) {
                houseLower = "低于商圈同户型" + Math.abs(totalRelativeWithBizcircle) + "%";
            } else {
                houseLower = "低于小区同户型" + Math.abs(totalRelativeWithCommunity) + "%";
            }
        }

        //抢手
        Integer isMustRob = searchJson.getInteger("isMustRob");
        if (StringTool.isNotEmpty(isMustRob) && isMustRob == 1) {
            String houseRobLabel = StringTool.isNotEmpty(searchJson.getString("houseRobLabel")) ? searchJson.getString("houseRobLabel") : communityLableStr;
            houseBarrageFirstList.add(houseRobLabel);
        }

        //捡漏
        if (isLowPrice == 1) {
            String houseLowerLabel = StringTool.isNotEmpty(searchJson.getString("houseLowerLabel")) ? searchJson.getString("houseLowerLabel") : houseLower;
            houseBarrageFirstList.add(houseLowerLabel);
        }

        //降价
        if (isCutPrice == 1 && priceFloat < 0) {
            String houseCutLabel = StringTool.isNotEmpty(searchJson.getString("houseCutLabel")) ? searchJson.getString("houseCutLabel") : houseCutPriceMsg;
            houseBarrageFirstList.add(houseCutLabel);
        }
        return houseBarrageFirstList;
    }

    @Override
    public String getCommunityLableStr(SearchHit searchHit, String city) {
        String communityLableStr = "";
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());
        Map<String, Object> searchMap = searchHit.getSourceAsMap();
        List<String> recommendBuildTagNameList = (List<String>) searchMap.get("recommendBuildTagsName");
        String areaName = searchJson.getString("area");
        Map<Integer, Map<String, Integer>> typeCountsMap = communityRestService.getCountByBuildTags(CityUtils.returnCityId(city));

        //区域ID
        String areaId = searchJson.getString("areaId");

        if (searchJson.getInteger("isMustRob") == 1 && searchJson.getInteger("isMainLayout") == 1) {
            if (searchJson.getInteger("isCommunityTopHouse") == 1) {
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

        if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
            if (recommendBuildTagNameList.contains("豪宅")) {
                communityLableStr = areaName + typeCountsMap.get(4).get(areaId) + "大豪宅社区主力户型";
            }
            if (recommendBuildTagNameList.contains("别墅")) {
                communityLableStr = areaName + typeCountsMap.get(5).get(areaId) + "大别墅社区主力户型";
            }
            if (recommendBuildTagNameList.contains("首次置业")) {
                communityLableStr = areaName + typeCountsMap.get(2).get(areaId) + "大首置社区主力户型";
            }
            if (recommendBuildTagNameList.contains("换房升级")) {
                communityLableStr = areaName + typeCountsMap.get(3).get(areaId) + "大换房社区主力户型";
            }
            if (recommendBuildTagNameList.contains("近公园")) {
                communityLableStr = "近公园";
//                String nearpark = StringTool.isNotEmpty(sellHouseDetailsDo.getNearPark())?"?strNearestPark="+sellHouseDetailsDo.getNearPark():"";
            }
        }
        return communityLableStr;
    }


}
