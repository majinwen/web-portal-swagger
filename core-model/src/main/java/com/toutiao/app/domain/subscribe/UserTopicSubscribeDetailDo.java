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

    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "areaId")
    private String areaId;

    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "areaName")
    private String areaName;

    /**
     * 居室
     */
    @ApiModelProperty(value = "居室", name = "layoutId")
    private Integer[] layoutId;
}
