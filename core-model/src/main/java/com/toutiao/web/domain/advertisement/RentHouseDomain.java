package com.toutiao.web.domain.advertisement;

import lombok.Data;

import java.util.List;

@Data
public class RentHouseDomain {


    private List<RentHouseAggAdLandingDo> rentHouseAggAdLandingDoList;

    /**
     * 用户浏览的起始位置
     */
    private Long startBit;

    /**
     * 查询位置
     */
    private Long queryBit;

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

    private Integer count;

    private Long sourceStart;

    private Long sourceEnd;

}
