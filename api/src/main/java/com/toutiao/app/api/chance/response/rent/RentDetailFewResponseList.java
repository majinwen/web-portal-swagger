package com.toutiao.app.api.chance.response.rent;

import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RentDetailFewResponseList {
    /**
     * 出租房源集合
     */
    @ChangeName("data")
    @ApiModelProperty("出租房源集合")
    private List<RentDetailFewResponse> rentDetailsList;
    /**
     * 总数
     */
    @ChangeName("totalNum")
    @ApiModelProperty("总数")
    private Integer totalCount;
}
