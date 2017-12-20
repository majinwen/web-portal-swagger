package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class RingRoadMap {
    private static Map<String, String> ringRoadMap;

    static {
        ringRoadMap = new HashMap<>();
        ringRoadMap.put("1", "二环以内");
        ringRoadMap.put("2", "二至三环");
        ringRoadMap.put("3", "三至四环");
        ringRoadMap.put("4", "四至五环");
        ringRoadMap.put("5", "五至六环");
        ringRoadMap.put("6", "六环以外");

    }

    public static String getFitment(String ringRoadId) {
        String ringRoad = ringRoadMap.get(ringRoadId);
        if (ringRoad != null) {
            return ringRoad;
        }
        return null;
    }
}
