package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class LabelMap {
    private static Map<String, String> labelMap;

    static {
        labelMap = new HashMap<>();
        labelMap.put("1","近地铁");
        labelMap.put("4","随时看");
        labelMap.put("8","满二年");
        labelMap.put("16","满五年");
        labelMap.put("32","近公园");
    }

    public static String getLabel(String labelId) {
        String label = labelMap.get(labelId);
        if (label != null) {
            return label;
        }
        return null;
    }
}
