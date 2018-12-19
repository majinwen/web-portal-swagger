package com.toutiao.appV2.model.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * RentDetailsListResponse
 */
@Data
public class RentDetailsListResponse {

    @ApiModelProperty(value = "出租房源集合", name = "data")
    @ChangeName("data")
    private List<RentDetailsFewDo> rentDetailsList;

    @ApiModelProperty(value = "出租房源集合", name = "totalNum")
    @ChangeName("totalNum")
    private Integer totalCount;


}

