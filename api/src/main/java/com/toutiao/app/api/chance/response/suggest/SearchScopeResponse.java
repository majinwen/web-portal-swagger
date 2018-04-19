package com.toutiao.app.api.chance.response.suggest;

import lombok.Data;

@Data
public class SearchScopeResponse {
    /**
     * 区县或者商圈id
     */
    private Integer search_id;
    /**
     * 区县或者商圈类型
     */
    private String search_name;
    /**
     * 房屋类型名称：新房，小区，二手房, 普租, 公寓
     */
    private String search_type;
    /**
     * 房屋类型名称标志：0-新房，1-小区，2-二手房, 3-普租, 4-公寓
     */
    private Integer search_type_sings;
    /**
     * 区县或者商圈的名称（用于排序）
     */
    private String search_sort;
    /**
     * 城市id
     */
    private Integer city_id;
    /**
     * 区县或者商圈的类型标志名称（e.g. 区县，商圈）
     */
    private String location_type;
    /**
     * 区县或者商圈的类型标志（1-区县，2-商圈）
     */
    private Integer location_type_sings;
    /**
     * 该区县或商圈下对应房源的个数
     */
    private Integer location_num;

}
