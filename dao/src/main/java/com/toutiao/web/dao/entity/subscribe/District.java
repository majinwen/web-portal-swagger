package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class District {
    /**
     * 区域ID
     */
    private Integer districtId;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 城市ID
     */
    private Integer cityId;

}
