package com.toutiao.app.api.chance.response.suggest;

import lombok.Data;

@Data
public class SearchEnginesResponse {
    /**
     * 房源id
     */
    private Integer search_id;
    /**
     * 房源名称
     */
    private String search_name;
    /**
     * 排序名称
     */
    private String search_sort;
    /**
     * 房屋类型
     */
    private String search_type;
    /**
     * 房屋类型标志
     */
    private Integer search_type_sings;
    /**
     * 区县名称
     */
    private String district;
    /**
     * 区县id
     */
    private Integer district_id;
    /**
     * 商圈名称
     */
    private String area;
    /**
     * 商圈id
     */
    private Integer area_id;
    /**
     * 是否发布
     */
    private Integer is_approve;
    /**
     * 是否删除
     */
    private Integer is_del;
    /**
     * 城市id
     */
    private Integer city_id;
}
