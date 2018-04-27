package com.toutiao.app.api.chance.response.rent;

import com.toutiao.app.domain.rent.RentDetailsFewDo;
import lombok.Data;

import java.util.List;

@Data
public class RentListResponse {
    /**
     * 出租房源详情集合
     */
    private List<RentDetailsFewDo> rentDetailsDoList;
    /**
     * 总数
     */
    private Integer totalNum;
}
