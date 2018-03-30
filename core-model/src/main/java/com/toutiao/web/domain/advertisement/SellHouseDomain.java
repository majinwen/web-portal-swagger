package com.toutiao.web.domain.advertisement;

import lombok.Data;

import java.util.List;

@Data
public class SellHouseDomain {

    /**
     * 出售房源数据集
     */
    private List<SellHouseAggAdLandingDo> sellHouseAggAdLandingDoList;

    /**
     * 用户浏览的起始位置
     */
    private Integer startBit;

    /**
     * 查询位置
     */
    private Integer queryBit;

    /**
     * 是否补充
     */
    private Integer sign = 0;

    /**
     * 当前分页
     */
    private Integer pageNum;

    /**
     * 当前分页结果数量
     */
    private Integer pageSize;

}
