package com.toutiao.web.common.constant.map;

import com.toutiao.web.common.constant.base.IntBaseType;


/**
 * @ClassName MapLevelEnum
 * @Author jiangweilong
 * @Date 2018/11/22 11:48 AM
 * @Description:
 **/
public enum MapGroupEnum implements IntBaseType {


    DISTRICT(MapGroupConstant.DISTRICT,MapGroupConstant.DISTRICT_CODE),
    BIZCIRCLE(MapGroupConstant.BIZCIRCLE,MapGroupConstant.BIZCIRCLE_CODE),
    COMMUNITY(MapGroupConstant.COMMUNITY,MapGroupConstant.COMMUNITY_CODE),
    BUILD(MapGroupConstant.BUILD,MapGroupConstant.BUILD_CODE),
    LINE(MapGroupConstant.LINE,MapGroupConstant.LINE_CODE),
    STATION(MapGroupConstant.STATION,MapGroupConstant.STATION_CODE);


    private String desc;
    private int value;

    MapGroupEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public static Integer getValue(String value) {
        MapGroupEnum[] mapLevelEnums = values();
        for (MapGroupEnum mapLevelEnum : mapLevelEnums) {
            if (mapLevelEnum.desc.equals(value)) {
                return mapLevelEnum.value;
            }
        }
        return null;
    }


    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
