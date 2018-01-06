package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class DistrictMap {
    private static Map<String, String> districtMap;

    static {
        districtMap = new HashMap<>();
        districtMap.put("105041","石景山");
        districtMap.put("105045","顺义");
        districtMap.put("105043","大兴");
        districtMap.put("105034","海淀");
        districtMap.put("105035","朝阳");
        districtMap.put("105036","东城");
        districtMap.put("105037","西城");
        districtMap.put("105040","丰台");
        districtMap.put("105046","昌平");
        districtMap.put("105047","密云");
        districtMap.put("105048","怀柔");
        districtMap.put("105049","延庆");
        districtMap.put("105050","平谷");
        districtMap.put("105051","门头沟");
        districtMap.put("105044","通州");
        districtMap.put("105042","房山");
        districtMap.put("106013","北京周边");
        districtMap.put("106022","武清");
        districtMap.put("106023","燕郊");
        districtMap.put("106024","香河");
        districtMap.put("106025","大厂");
        districtMap.put("106026","固安");
        districtMap.put("106027","永清");
        districtMap.put("106028","廊坊");
        districtMap.put("106029","霸州");
        districtMap.put("106030","涿州");
        districtMap.put("106031","涞水");
        districtMap.put("106032","怀来");
        districtMap.put("106033","崇礼");
        districtMap.put("106034","秦皇岛");
        districtMap.put("106035","天津");
    }

    public static String getDistrict(String districtId) {
        String district = districtMap.get(districtId);
        if (district != null) {
            return district;
        }
        return null;
    }
}
