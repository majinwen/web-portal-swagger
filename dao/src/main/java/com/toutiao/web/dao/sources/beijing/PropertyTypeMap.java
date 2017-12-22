package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class PropertyTypeMap {
    private static Map<String, String> propertyTypeMap;

    static {
        propertyTypeMap = new HashMap<>();
        propertyTypeMap.put("1","住宅");
        propertyTypeMap.put("2","别墅");
        propertyTypeMap.put("3","写字楼");
        propertyTypeMap.put("4","商铺");
        propertyTypeMap.put("5","底商");
    }

    public static String getPropertyType(String propertyTypeId) {
        String propertyType = propertyTypeMap.get(propertyTypeId);
        if (propertyType != null) {
            return propertyType;
        }
        return null;
    }
}
