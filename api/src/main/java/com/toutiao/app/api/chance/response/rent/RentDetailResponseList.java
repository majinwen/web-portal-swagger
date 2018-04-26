package com.toutiao.app.api.chance.response.rent;

import lombok.Data;

import java.util.List;

@Data
public class RentDetailResponseList {
    /**
     * 出租房源详情集合
     */
    private List<RentDetailResponse> rentDetailsDoList;
    /**
     * 总数
     */
    private Integer totalNum;
}
