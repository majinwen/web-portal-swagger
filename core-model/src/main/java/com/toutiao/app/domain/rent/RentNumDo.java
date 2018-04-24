package com.toutiao.app.domain.rent;

import lombok.Data;

@Data
public class RentNumDo {
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
    private Integer num;
    /**
     * 总数
     */
    private Integer totalNum;

}
