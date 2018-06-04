package com.toutiao.web.domain.payment;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class CommodityOrderQuery {

    /**
     * 商品编号
     */
    @NotEmpty(message="商品编号不能为空")
    private String productNo;

    /**
     * 楼盘id
     */
    @NotNull(message = "楼盘ID不能为空")
    private Integer buildingId;

//    /**
//     * 楼盘名称
//     */
//    private String buildName;

}
