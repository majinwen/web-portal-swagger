package com.toutiao.app.domain.suggest;

import lombok.Data;

@Data
public class SearchScopeDo {
    /**
     * 区县或者商圈id
     */
    private Integer searchId;
    /**
     * 区县或者商圈类型
     */
    private String searchName;
    /**
     * 房屋类型名称：新房，小区，二手房, 普租, 公寓
     */
    private String searchType;
    /**
     * 房屋类型名称标志：0-新房，1-小区，2-二手房, 3-普租, 4-公寓
     */
    private Integer searchTypeSings;
    /**
     * 区县或者商圈的名称（用于排序）
     */
    private String searchSort;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 区县或者商圈的类型标志名称（e.g. 区县，商圈）
     */
    private String locationType;
    /**
     * 区县或者商圈的类型标志（1-区县，2-商圈）
     */
    private Integer locationTypeSings;
    /**
     * 该区县或商圈下对应房源的个数
     */
    private Integer locationNum;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 维度
     */
    private Double latitude;
}
