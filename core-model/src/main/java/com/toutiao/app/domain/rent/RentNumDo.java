package com.toutiao.app.domain.rent;

import lombok.Data;

@Data
public class RentNumDo {
    /**
     * 出租房源标志(普租/公寓)
     */
    private Integer rentSign;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rentSignName;
    /**
     * 个数
     */
    private Integer num;


}
