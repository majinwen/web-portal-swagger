package com.toutiao.web.dao.entity.compared;

import lombok.Data;

import java.util.Date;

@Data
public class HouseCompared {
    private Integer id;

    private Integer userId;

    private String houseId;

    private Short houseStatus;

    private Date createTime;

    private Short isDel;

    private Integer cityId;
}