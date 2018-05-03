package com.toutiao.app.api.chance.response.rent;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class RentDetailFewResponseList {
    /**
     * 出租房源集合
     */
    @ChangeName("data")
    private List<RentDetailFewResponse> RentDetailsList;
    /**
     * 总数
     */
    @ChangeName("totalNum")
    private Integer totalCount;
}
