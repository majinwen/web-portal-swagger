package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

@Data
public class City {
    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 所属大区
     */
    private String belong;

    /**
     * 排序
     */
    private Integer sorting;

    /**
     * 城市名称拼音
     */
    private String cityPinyin;

    /**
     * 城市拼音首字母
     */
    private String cityPinyinInitials;

}