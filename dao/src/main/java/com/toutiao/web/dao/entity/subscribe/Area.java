package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class Area {
    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 商圈名称
     */
    private String areaName;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 商圈名称拼音
     */
    private String areaPinyin;

    /**
     * 商圈拼音首字母
     */
    private String areaPinyinInitials;
}
