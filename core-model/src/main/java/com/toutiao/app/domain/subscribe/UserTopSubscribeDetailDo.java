package com.toutiao.app.domain.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserTopSubscribeDetailDo {
    /**
     * 区域Id
     */
    @ApiModelProperty(value = "区域Id", name = "districtId")
    private String districtId;
    /**
     * 区域
     */
    @ApiModelProperty(value = "区域", name = "districtName")
    private String districtName;
    /**
     * 商圈Id
     */
    @ApiModelProperty(value = "商圈Id", name = "areaId")
    private Integer areaId;
    /**
     * 户型
     */
    @ApiModelProperty(value = "户型", name = "room")
    private Integer room;
    /**
     * 平均价格
     */
    @ApiModelProperty(value = "平均价格", name = "avgPrice")
    private Double avgPrice;
}
