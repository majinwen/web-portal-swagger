package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class RentCustomConditionDomain {

    /**
     * 房源统计信息
     */
    private List<RentCustomDo> rentCustomDos = null;

    /**
     * 搜索结果描述
     */
    private String description;
}
