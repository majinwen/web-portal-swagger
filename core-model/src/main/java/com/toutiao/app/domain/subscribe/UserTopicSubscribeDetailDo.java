package com.toutiao.app.domain.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserTopicSubscribeDetailDo {
    /**
     * 专题类型 1：降价房 2：价格洼地 3：逢出必抢
     */
    @ApiModelProperty(value = "专题类型 1：降价房 2：价格洼地 3：逢出必抢", name = "topicType")
    private Integer topicType;
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
     * 开始价格
     */
    @ApiModelProperty(value = "开始价格", name = "beginPrice")
    private Double beginPrice;
    /**
     * 结束价格
     */
    @ApiModelProperty(value = "结束价格", name = "endPrice")
    private Double endPrice;
}
