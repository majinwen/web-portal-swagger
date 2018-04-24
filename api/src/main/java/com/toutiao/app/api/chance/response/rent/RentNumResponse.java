package com.toutiao.app.api.chance.response.rent;

import lombok.Data;

@Data
public class RentNumResponse {
    /**
     * 出租房源标志(普租/公寓)
     */
    private Integer rent_sign;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rent_sign_name;
    /**
     * 个数
     */
    private Integer totalNum;
}
