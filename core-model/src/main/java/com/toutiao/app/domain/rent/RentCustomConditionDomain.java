package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class RentCustomConditionDomain {

    /**
     * id与名称
     */
    private RentCustomDo rentCustomDo = null;

    /**
     * 房源统计
     */
    private List<RentCustomConditionDo> rentCustomConditionDos = null;
}
