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

    private static Map<String, String> districtMaps;

    static {
        districtMaps = new HashMap<>();
        districtMaps.put("石景山","105041");
        districtMaps.put("顺义","105045");
        districtMaps.put("大兴","105043");
        districtMaps.put("海淀","105034");
        districtMaps.put("朝阳","105035");
        districtMaps.put("东城","105036");
        districtMaps.put("西城","105037");
        districtMaps.put("丰台","105040");
        districtMaps.put("昌平","105046");
        districtMaps.put("密云","105047");
        districtMaps.put("怀柔","105048");
        districtMaps.put("延庆","105049");
        districtMaps.put("平谷","105050");
        districtMaps.put("门头沟","105051");
        districtMaps.put("通州","105044");
        districtMaps.put("房山","105042");
        districtMaps.put("北京周边","106013");
        districtMaps.put("武清","106022");
        districtMaps.put("燕郊","106023");
        districtMaps.put("香河","106024");
        districtMaps.put("大厂","106025");
        districtMaps.put("固安","106026");
        districtMaps.put("永清","106027");
        districtMaps.put("廊坊","106028");
        districtMaps.put("霸州","106029");
        districtMaps.put("涿州","106030");
        districtMaps.put("涞水","106031");
        districtMaps.put("怀来","106032");
        districtMaps.put("崇礼","106033");
        districtMaps.put("秦皇岛","106034");
        districtMaps.put("天津","106035");
    }

    public static String getDistrict(String districtId) {
        String district = districtMap.get(districtId);
        if (district != null) {
            return district;
        }
        return null;
    }

    public static String getDistricts(String districtId) {
        String district = districtMaps.get(districtId);
        if (district != null) {
            return district;
        }
        return null;
    }
}
