package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

@Data
public class CpcSellHouse {
    private Integer id;

    private String houseId;

    private Integer click;

    private String date;

    /**
     * 标识
     */
    private Short status;

}