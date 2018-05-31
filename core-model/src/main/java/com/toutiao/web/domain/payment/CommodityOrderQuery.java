package com.toutiao.web.domain.payment;

import lombok.Data;

@Data
public class CommodityOrderQuery {

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 备注
     */
    private String comment;

}
