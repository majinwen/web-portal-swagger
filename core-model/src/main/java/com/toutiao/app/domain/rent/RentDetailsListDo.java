package com.toutiao.app.domain.rent;


import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RentDetailsListDo {
    /**
     * 出租房源集合
     */
    @ApiModelProperty("出租房源集合")
    @ChangeName("data")
    private List<RentDetailsFewDo> rentDetailsList;

    /**
     * 总数
     */
    @ApiModelProperty("总数")
    private Integer totalCount;
}
