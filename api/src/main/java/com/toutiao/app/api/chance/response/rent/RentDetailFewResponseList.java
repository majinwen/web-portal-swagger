package com.toutiao.app.api.chance.response.rent;

import lombok.Data;

import java.util.List;

@Data
public class RentDetailFewResponseList {
    /**
     * 出租房源集合
     */
    private List<RentDetailFewResponse> rentDetailsDoList;
    /**
     * 总数
     */
    private Integer totalNum;
}
