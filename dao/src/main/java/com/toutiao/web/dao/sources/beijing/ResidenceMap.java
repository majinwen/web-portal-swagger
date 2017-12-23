package com.toutiao.web.dao.sources.beijing;

import java.util.HashMap;
import java.util.Map;

public class ResidenceMap {
    public static Map<String, String> residenceCategoryFormMap;

    public static Map<String, String> residenceBuildFormMap;

    public static Map<String, String> residenceBuildCategoryMap;

    static {
        residenceBuildCategoryMap = new HashMap<>();
        residenceBuildCategoryMap.put("1", "板楼");
        residenceBuildCategoryMap.put("2", "塔楼");
        residenceBuildCategoryMap.put("3", "板塔结合");
        residenceBuildCategoryMap.put("4", "砖楼");
        residenceBuildCategoryMap.put("5", "其他");
    }

    static {
        residenceBuildFormMap = new HashMap<>();
        residenceBuildFormMap.put("1", "低层");
        residenceBuildFormMap.put("2", "多层");
        residenceBuildFormMap.put("3", "小高层");
        residenceBuildFormMap.put("4", "高层");
        residenceBuildFormMap.put("5", "超高层");
        residenceBuildFormMap.put("6", "联排");
        residenceBuildFormMap.put("7", "独栋");
        residenceBuildFormMap.put("8", "双拼");
        residenceBuildFormMap.put("9", "叠拼");
        residenceBuildFormMap.put("10", "空中花园");
        residenceBuildFormMap.put("11", "空中别墅");
        residenceBuildFormMap.put("12", "开间");
        residenceBuildFormMap.put("13", "平层");
        residenceBuildFormMap.put("14", "复式");
        residenceBuildFormMap.put("15", "跃层");
        residenceBuildFormMap.put("16", "其他");
    }

    static {
        residenceCategoryFormMap = new HashMap<>();
        residenceCategoryFormMap.put("1", "普通住宅");
        residenceCategoryFormMap.put("2", "公寓");
        residenceCategoryFormMap.put("3", "酒店式公寓");
        residenceCategoryFormMap.put("4", "花园洋房");
        residenceCategoryFormMap.put("5", "商住楼");
        residenceCategoryFormMap.put("6", "独栋别墅");
        residenceCategoryFormMap.put("7", "联排别墅");
        residenceCategoryFormMap.put("8", "经济适用房");
        residenceCategoryFormMap.put("9", "廉租房");
        residenceCategoryFormMap.put("10", "公共租赁房");
        residenceCategoryFormMap.put("11", "定向安置房");
        residenceCategoryFormMap.put("12", "两限商品房");
        residenceCategoryFormMap.put("13", "自住型商品房");
        residenceCategoryFormMap.put("14", "其他");
        residenceCategoryFormMap.put("15", "商铺");
        residenceCategoryFormMap.put("16", "写字楼");
        residenceCategoryFormMap.put("17", "平房");
        residenceCategoryFormMap.put("18", "车位");
        residenceCategoryFormMap.put("19", "办公");
        residenceCategoryFormMap.put("20", "四合院");
    }

    public static String getResidenceCategory(String residenceCategoryId) {
        String residenceCategory = residenceCategoryFormMap.get(residenceCategoryId);

        if (residenceCategory != null) {
            return residenceCategory;
        }
        return null;
    }

    public static String getResidenceBuildForm(String residenceBuildFormId) {
        String residenceBuildForm = residenceBuildFormMap.get(residenceBuildFormId);

        if (residenceBuildForm != null) {
            return residenceBuildForm;
        }
        return null;
    }

    public static String getResidenceBuildCategory(String residenceBuildCategoryId) {
        String residenceBuildCategory = residenceBuildCategoryMap.get(residenceBuildCategoryId);

        if (residenceBuildCategory != null) {
            return residenceBuildCategory;
        }
        return null;
    }

}
