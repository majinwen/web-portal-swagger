package com.toutiao.app.api.chance.request.sellhouse;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SellHouseDerailsRequest {

    /**
     * 二手房id
     */
    @NotEmpty(message = "二手房房源id不能为空")
    private String houseId;
}
