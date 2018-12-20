package com.toutiao.app.api.chance.response.rent;

import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RentListResponse {
    /**
     * 出租房源详情集合
     */
    @ChangeName("data")
    @ApiModelProperty("出租房源详情集合")
    private List<RentDetailsFewDo> rentDetailsList;
    /**
     * 总数
     */
    @ChangeName("totalNum")
    @ApiModelProperty("总数")
    private Integer totalCount;
}
