package com.toutiao.app.api.chance.response.suggest;

import lombok.Data;

import java.util.List;

@Data
public class SearchEnginesResponse {
    /**
     * 房源id
     */
    private Integer searchId;
    /**
     * 房源名称
     */
    private String searchName;
    /**
     * 房源别名
     */
    private List searchNickname;
    /**
     * 排序名称
     */
    private String searchSort;
    /**
     * 房屋类型
     */
    private String searchType;
    /**
     * 房屋类型标志
     */
    private Integer searchTypeSings;
    /**
     * 区县名称
     */
    private String district;
    /**
     * 区县id
     */
    private Integer districtId;
    /**
     * 商圈名称
     */
    private String area;
    /**
     * 商圈id
     */
    private Integer areaId;
    /**
     * 是否发布
     */
    private Integer isApprove;
    /**
     * 是否删除
     */
    private Integer isDel;
    /**
     * 城市id
     */
    private Integer cityId;
}
