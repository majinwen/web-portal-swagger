package com.toutiao.app.domain.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSubscribeDetailDo {
    /**
     * 专题类型 1：降价房 2：价格洼地 3：逢出必抢
     */
    @ApiModelProperty(value = "专题类型 1：降价房 2：价格洼地 3：逢出必抢", name = "topicType")
    private Integer topicType;

    @ApiModelProperty(value = "专题名称", name = "topicTypeName")
    private String topicTypeName;
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
     * 平均价格
     */
    @ApiModelProperty(value = "平均价格", name = "avgPrice")
    private Double avgPrice;
    /**
     * 标题图
     */
    @ApiModelProperty(value = "标题图", name = "titleImg")
    private String titleImg;

}
