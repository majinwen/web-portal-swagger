package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Data
public class RentCustomDo {

    /***
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 房源统计
     */
    private List<RentCustomConditionDo> rentCustomConditionDos = null;
}
