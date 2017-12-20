package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class VillaMap {
    public static Map<String, String> villaBuildStyleMap;
    static {
        villaBuildStyleMap = new HashMap<>();
        villaBuildStyleMap.put("1","中式");
        villaBuildStyleMap.put("2","欧式");
        villaBuildStyleMap.put("3","日式");
        villaBuildStyleMap.put("4","美式");
        villaBuildStyleMap.put("5","英式");
        villaBuildStyleMap.put("6","澳式");
        villaBuildStyleMap.put("7","法式");
        villaBuildStyleMap.put("8","西班牙式");
        villaBuildStyleMap.put("9","东南亚式");
        villaBuildStyleMap.put("10","地中海式");
        villaBuildStyleMap.put("11","意大利式");
        villaBuildStyleMap.put("12","现代");
    }
    public static String getVillaBuildStyle(String villaBuildStyleId) {
        String villaBuildStyle = villaBuildStyleMap.get(villaBuildStyleId);

        if (villaBuildStyle != null) {
            return villaBuildStyle;
        }
        return null;
    }
}
