package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class DistrictAreaConfigQuery {

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 房屋类型
     */
    private Integer houseType=0;

    /**
     * 城市id
     */
    private Integer cityId;
}
