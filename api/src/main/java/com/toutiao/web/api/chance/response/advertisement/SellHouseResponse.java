package com.toutiao.web.api.chance.response.advertisement;

import com.toutiao.web.domain.advertisement.SellHouseAggAdLandingDo;
import lombok.Data;

import java.util.List;

@Data
public class SellHouseResponse {

    /**
     * 出售房源数据集
     */
    private List<SellHouseAggAdLandingDo> sellHouseAggAdLandingDoList;

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

}
