package com.toutiao.app.domain.Rent;

import lombok.Data;

import java.util.List;

@Data
public class RentDetailsDoList {
    /**
     * 出租房源集合
     */
    private List<RentDetailsDo> rentDetailsDoList;

    /**
     * 总数
     */
    private Integer totalNum;
}
