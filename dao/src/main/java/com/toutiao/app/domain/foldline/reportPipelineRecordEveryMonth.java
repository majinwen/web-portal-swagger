package com.toutiao.app.domain.foldline;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class reportPipelineRecordEveryMonth {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 小区/商圈/区域id
     */
    private String code;

    /**
     * 本月待售房源数量
     */
    private Integer forSale;

    /**
     * 均价
     */
    private BigDecimal avgPrice;

    /**
     * 本月新增房源
     */
    private Integer increaseHouse;

    /**
     * 本月下架房源
     */
    private Integer decreaseHouse;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 城市id
     */
    private Integer cityId;

}