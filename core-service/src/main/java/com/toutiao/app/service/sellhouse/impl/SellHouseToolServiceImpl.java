package com.toutiao.app.service.sellhouse.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.sellhouse.HouseColorLable;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.sellhouse.SellHouseToolService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if (StringTool.isNotEmpty(totalRelativeWithBizcircle) && StringTool.isNotEmpty(totalRelativeWithCommunity)) {
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
    public List<String> getHouseBarrageSecondList(SearchHit searchHit) {
        //二手房弹幕第二行
        List<String> houseBarrageSecondList = new ArrayList<>();
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());
        Map<String, Object> searchMap = searchHit.getSourceAsMap();

        String forwardName = searchJson.getString("forwardName");
        if (StringTool.isNotEmpty(forwardName) && (forwardName.contains("东") || forwardName.contains("南"))) {
            houseBarrageSecondList.add("采光很好");
        }

        Integer rankInLowCommunityLayout = searchJson.getInteger("rankInLowCommunityLayout");
        if (StringTool.isNotEmpty(rankInLowCommunityLayout) && rankInLowCommunityLayout > 0) {
            houseBarrageSecondList.add("小区同户型低总价榜NO." + rankInLowCommunityLayout);
        }

        Double totalAbsoluteWithCommunity = searchJson.getDouble("totalAbsoluteWithCommunity");
        if (StringTool.isNotEmpty(totalAbsoluteWithCommunity) && totalAbsoluteWithCommunity > 0) {
            houseBarrageSecondList.add("总价低于小区同户型" + Math.abs(totalAbsoluteWithCommunity) + "万");
        }


        List<String> tagsNameList = (ArrayList<String>) searchMap.get("tagsName");
        for (String tag : tagsNameList) {
            houseBarrageSecondList.add(tag);
        }
        return houseBarrageSecondList;
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
                communityLableStr = "top50社区";
            } else if (recommendBuildTagNameList.size() > 0 && StringTool.isNotEmpty(typeCountsMap)) {
                if (recommendBuildTagNameList.contains("豪宅")) {
                    communityLableStr = areaName + typeCountsMap.get(4).get(areaId) + "大豪宅社区";
                } else if (recommendBuildTagNameList.contains("别墅")) {
                    communityLableStr = areaName + typeCountsMap.get(5).get(areaId) + "大别墅社区";
                } else if (recommendBuildTagNameList.contains("首次置业")) {
                    communityLableStr = areaName + typeCountsMap.get(2).get(areaId) + "大首置社区";
                } else if (recommendBuildTagNameList.contains("换房升级")) {
                    communityLableStr = areaName + typeCountsMap.get(3).get(areaId) + "大换房社区";
                } else if (recommendBuildTagNameList.contains("近公园")) {
                    communityLableStr = "近公园";
                }
            }
        }
        return communityLableStr;
    }

    @Override
    public String getNearbyDistanceBySubway(SearchHit searchHit) {
        String nearbyDistance = "";
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());
        Map<String, Object> searchMap = searchHit.getSourceAsMap();
        Integer subwayLineId = searchJson.getInteger("subwayLineId");
        List<Integer> subwayStationIdList = (ArrayList<Integer>) searchMap.get("subwayStationId");
        JSONObject subwayDistinceJson = searchJson.getJSONObject("subwayDistince");
        String keys = "";
        String[] trafficArr;

        //
        if (StringTool.isNotEmpty(subwayLineId)) {
            keys += subwayLineId.toString();

            // 选择地铁线，不选地铁站
            if (subwayDistinceJson.containsKey(keys) && StringTool.isEmpty(subwayStationIdList)) {
                trafficArr = subwayDistinceJson.getString(keys).split("\\$");
                if (trafficArr.length == 3) {
                    nearbyDistance = "距离" + trafficArr[1] + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                }

                // 选择地铁线，选地铁站
            } else if (!StringTool.isEmpty(subwayStationIdList)) {
                String subwayDistanceInfo = getSubwayDistanceInfoBySubway(searchHit);
                trafficArr = subwayDistanceInfo.split("\\$");
                if (trafficArr.length == 3) {
                    nearbyDistance = "距离" + trafficArr[1] + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";

                }
            }
        }
        return nearbyDistance;
    }

    @Override
    public String getSubwayDistanceInfoBySubway(SearchHit searchHit) {
        String subwayDistanceInfo = "";
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());
        Map<String, Object> searchMap = searchHit.getSourceAsMap();
        Integer subwayLineId = searchJson.getInteger("subwayLineId");
        List<Integer> subwayStationIdList = (ArrayList<Integer>) searchMap.get("subwayStationId");
        JSONObject subwayDistinceJson = searchJson.getJSONObject("subwayDistince");
        String keys = "";

        if (StringTool.isNotEmpty(subwayLineId)) {
            keys += subwayLineId.toString();
        }
        if (!StringTool.isEmpty(subwayStationIdList)) {
            Map<Integer, String> map = new HashMap<>();
            List<Integer> sortDistance = new ArrayList<>();
            for (Integer i : subwayStationIdList) {
                String stationKey = keys + "$" + i;
                if (StringTool.isNotEmpty(subwayDistinceJson.getString(stationKey))) {
                    String stationValue = subwayDistinceJson.getString(stationKey);
                    String[] stationValueSplit = stationValue.split("\\$");
                    Integer distance = Integer.valueOf(stationValueSplit[2]);
                    sortDistance.add(distance);
                    map.put(distance, stationKey);
                }
            }
            Integer minDistance = Collections.min(sortDistance);
            subwayDistanceInfo = subwayDistinceJson.getString(map.get(minDistance));
        }

        return subwayDistanceInfo;
    }

    /***
     *
     * @param searchHit
     * @param wapName
     * @param city
     * @param districtId 区域ID
     * @param districtName 区域名称
     * @return
     */
    @Override
    public List<HouseColorLable> getHouseColorLableListForESF(SearchHit searchHit, String wapName, String city, String districtId, String districtName) {
        List<HouseColorLable> houseColorLableList = new ArrayList<>();
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());

        Integer isMustRob = searchJson.getInteger("isMustRob");
        if (isMustRob == 1) {
            String houseRobCondition = StringTool.isNotEmpty(searchJson.getString("houseRobCondition")) ? "?" + searchJson.getString("houseRobCondition") : "";
            houseColorLableList.add(new HouseColorLable("F0FAFF", "2FB3FF", "抢手榜", wapName + "/" + city + "/topics/house/hot" + houseRobCondition));
        }

        int isLowPrice = searchJson.getInteger("isLowPrice");
        if (isLowPrice == 1) {
            String houseLowerCondition = StringTool.isNotEmpty(searchJson.getString("houseLowerCondition")) ? "?" + searchJson.getString("houseLowerCondition") : "";
            houseColorLableList.add(new HouseColorLable("FFF2F2", "FF6B6B", "捡漏榜", wapName + "/" + city + "/topics/house/low" + houseLowerCondition));
        }

        Integer isCutPrice = searchJson.getInteger("isCutPrice");
        if (isCutPrice == 1) {
            String houseCutCondition = StringTool.isNotEmpty(searchJson.getString("houseCutCondition")) ? "?" + searchJson.getString("houseCutCondition") : "";
            houseColorLableList.add(new HouseColorLable("EFFFEF", "47E24C", "降价榜", wapName + "/" + city + "/topics/house/reduction" + houseCutCondition));
        }

        houseColorLableList.addAll(getHouseColorLableListForESFDetails(searchHit, wapName, city, districtId, districtName));
        return houseColorLableList;
    }

    @Override
    public List<HouseColorLable> getHouseColorLableListForESFDetails(SearchHit searchHit, String wapName, String city, String districtId, String districtName) {
        List<HouseColorLable> houseColorLableList = new ArrayList<>();
        JSONObject searchJson = JSONObject.parseObject(searchHit.getSourceAsString());
        Map<String, Object> searchMap = searchHit.getSourceAsMap();
        List<String> recommendBuildTagNameList = (List<String>) searchMap.get("recommendBuildTagsName");
        Map<Integer, Map<String, Integer>> typeCountsMap = communityRestService.getCountByBuildTags(CityUtils.returnCityId(city));
        String districtIdCondition = StringTool.isNotEmpty(districtId) ? "?districtIds=" + districtId : "";
        String url = "";
        String communityLableStr = "";
        if (recommendBuildTagNameList != null && !recommendBuildTagNameList.isEmpty() && StringTool.isNotEmpty(typeCountsMap)) {
            if (recommendBuildTagNameList.contains("豪宅")) {
                communityLableStr = districtName + typeCountsMap.get(4).get(districtId) + "大豪宅社区";
                url = wapName + "/" + city + "/topics/plot/luxury" + districtIdCondition;
            } else if (recommendBuildTagNameList.contains("别墅")) {
                communityLableStr = districtName + typeCountsMap.get(5).get(districtId) + "大别墅社区";
                url = wapName + "/" + city + "/topics/plot/villa" + districtIdCondition;
            } else if (recommendBuildTagNameList.contains("首次置业")) {
                communityLableStr = districtName + typeCountsMap.get(2).get(districtId) + "大首置社区";
                url = wapName + "/" + city + "/topics/plot/first" + districtIdCondition;
            } else if (recommendBuildTagNameList.contains("换房升级")) {
                communityLableStr = districtName + typeCountsMap.get(3).get(districtId) + "大换房社区";
                url = wapName + "/" + city + "/topics/plot/improve" + districtIdCondition;
            } else if (recommendBuildTagNameList.contains("近公园")) {
                communityLableStr = "近公园";
                String nearpark = StringTool.isNotEmpty(searchJson.getString("nearPark")) ? "?strNearestPark=" + searchJson.getString("nearPark") : "";
                url = wapName + "/" + city + "/topics/nearpark" + nearpark;
            }
            if (StringTool.isNotEmpty(communityLableStr) && StringTool.isNotEmpty(url)) {
                houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", communityLableStr, url));
            }
        }

        Integer isCommunityTopHouse = searchJson.getInteger("isCommunityTopHouse");
        if (StringTool.isNotEmpty(isCommunityTopHouse) && isCommunityTopHouse == 1) {
            houseColorLableList.add(new HouseColorLable("FFF9E5", "E3AF00", "北京50大必看社区", wapName + "/" + city + "/topics/top50" + districtIdCondition));
        }

        return houseColorLableList;
    }
}
