package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.List;

@Data
public class RentDetailsListDo {
    /**
     * 出租房源集合
     */
    private List<RentDetailsFewDo> rentDetailsDoList;

    /**
     * 总数
     */
    private Integer totalNum;
}
