package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class HouseLevelMap {
    private static Map<String, String> houseLevelMap;

    static {
        houseLevelMap = new HashMap<>();
        houseLevelMap.put("0","默认");
        houseLevelMap.put("1","最好");
        houseLevelMap.put("2","次之");
        houseLevelMap.put("3","再次");
    }

    public static String getHouseLevel(String houseLevelId) {
        String houseLevel = houseLevelMap.get(houseLevelId);
        if (houseLevel != null) {
            return houseLevel;
        }
        return null;
    }
}
