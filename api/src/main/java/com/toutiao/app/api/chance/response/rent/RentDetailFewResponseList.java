package com.toutiao.app.api.chance.response.rent;

import com.toutiao.app.domain.rent.RentDetailsFewDo;
import lombok.Data;

import java.util.List;

@Data
public class RentDetailFewResponseList {
    /**
     * 出租房源集合
     */
    private List<RentDetailsFewDo> rentDetailsList;

    /**
     * 总数
     */
    private Integer totalCount;
}
