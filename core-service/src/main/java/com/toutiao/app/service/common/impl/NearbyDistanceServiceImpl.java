package com.toutiao.app.service.common.impl;

import com.toutiao.app.service.common.NearbyDistanceService;
import com.toutiao.web.common.util.StringTool;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class NearbyDistanceServiceImpl implements NearbyDistanceService {

    @Override
    public String getTrafficWithOneSubwayLine(Map subwayDistinceMap,Integer subwayLineId, Integer[] subwayStationIdArray) {
        String traffic = "";
        String keys = "";
        if (StringTool.isNotEmpty(subwayDistinceMap)) {

            if (StringTool.isNotEmpty(subwayLineId)) {
                keys += subwayLineId.toString();
                //地铁线已选择，地铁站选择不限
                if (subwayDistinceMap.containsKey(keys)) {
                    traffic = subwayDistinceMap.get(keys).toString();
                }
            }

            //地铁站有具体选择
            if (StringTool.isNotEmpty(subwayStationIdArray)) {
                Map<Integer, String> map = new HashMap<>();
                List<Integer> sortDistance = new ArrayList<>();
                for (Integer i : subwayStationIdArray) {
                    String stationKey = keys + "$" + i;
                    if (StringTool.isNotEmpty(subwayDistinceMap.get(stationKey))) {
                        String stationValue = subwayDistinceMap.get(stationKey).toString();
                        String[] stationValueSplit = stationValue.split("\\$");
                        Integer distance = Integer.valueOf(stationValueSplit[2]);
                        sortDistance.add(distance);
                        map.put(distance, stationKey);
                    }
                }
                Integer minDistance = Collections.min(sortDistance);
                traffic = subwayDistinceMap.get(map.get(minDistance)).toString();
            }
        }
        return traffic;
    }

    @Override
    public String getNearbyDistanceByTraffic(String traffic, String frontName) {
        String nearbyDistance = frontName;
        if (StringTool.isNotEmpty(traffic)) {
            String[] trafficArr = traffic.split("\\$");
            if (trafficArr.length == 3) {
                int i = Integer.parseInt(trafficArr[2]);
                if (i < 1000) {
                    nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1] + "(" + trafficArr[0] + ")" + trafficArr[2] + "m";
                } else {
                    DecimalFormat df = new DecimalFormat("0.0");
                    nearbyDistance = nearbyDistance + " " + "距离" + trafficArr[1] + "(" + trafficArr[0] + ")" + df.format(Double.parseDouble(trafficArr[2]) / 1000) + "km";
                }
            }
        }
        return nearbyDistance;
    }
}
