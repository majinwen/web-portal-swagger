package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class ForWardMap {
    private static Map<String, String> forWardMap;

    static {
        forWardMap = new HashMap<>();
        forWardMap.put("1","东");
        forWardMap.put("2","西");
        forWardMap.put("3","南");
        forWardMap.put("4","北");
        forWardMap.put("5","东南");
        forWardMap.put("6","西南");
        forWardMap.put("7","东北");
        forWardMap.put("8","西北");
        forWardMap.put("9","东西");
        forWardMap.put("10","南北");
    }
    public static String getForWard(String forWardId){
        String forWard = forWardMap.get(forWardId);
        if (forWard!=null){
            return forWard;
        }
        return null;
    }
}
