package com.toutiao.web.api.chance.response.advertisement;


import com.toutiao.web.domain.advertisement.RentHouseAggAdLandingDo;
import lombok.Data;

import java.util.List;

@Data
public class RentHouseResponse {


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
