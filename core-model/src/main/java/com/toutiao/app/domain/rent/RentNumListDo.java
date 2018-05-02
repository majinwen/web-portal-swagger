package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.List;

@Data
public class RentNumListDo {
    /**
     * 房源信息
     */
    private List<RentNumDo> data;
    /**
     * 总数
     */
    private Integer totalNum;
}
