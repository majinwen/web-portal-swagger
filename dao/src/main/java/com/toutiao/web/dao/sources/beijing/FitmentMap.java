package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class FitmentMap {
    private static Map<String, String> fitmentMap;

    static {
        fitmentMap = new HashMap<>();
        fitmentMap.put("1","毛坯");
        fitmentMap.put("2","普通装修");
        fitmentMap.put("3","精装修");
        fitmentMap.put("4","豪华装修");
        fitmentMap.put("5","其他");
    }
    public static String getFitment(String fitmentId){
        String fitment = fitmentMap.get(fitmentId);
        if (fitment!=null){
            return fitment;
        }
        return null;
    }
}
