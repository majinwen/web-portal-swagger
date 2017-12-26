package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class OwnerShipMap {

    private static Map<String, String> labelMap;

    static {
        labelMap = new HashMap<>();
        labelMap.put("1","已购公房");
        labelMap.put("2","商品房");
        labelMap.put("3","空置房");
        labelMap.put("4","使用权房");
        labelMap.put("5","央产");
        labelMap.put("6","经济适用房");
    }

    public static String getOwnership(String labelId) {
        String label = labelMap.get(labelId);
        if (label != null) {
            return label;
        }
        return null;
    }
}
