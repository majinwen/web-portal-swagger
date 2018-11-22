package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class DistrictAreaConfig {
    /**
     * 区县id
     */
    private Integer districtId;

    /**
     * 商圈id
     */
    private Integer areaId;

    /**
     * 房屋类型（1-新房，2-二手房）
     */
    private Integer houseType;
}
