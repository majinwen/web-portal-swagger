package com.toutiao.web.domain.payment;

import lombok.Data;

@Data
public class CommodityOrderQuery {

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 楼盘id
     */
    private Integer buildingId;

//    /**
//     * 楼盘名称
//     */
//    private String buildName;

}
