package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 18710 on 2018/12/13.
 */
@Data
public class RentGuessYourLikeRequest {
    /**
     * 整租:1/合租:2/未知:3
     */
    @JsonProperty("rentType")
    @ApiParam("出租类型:整租:1/合租:2/未知:3")
    @ApiModelProperty("出租类型:整租:1/合租:2/未知:3")
    private Integer rentType;

    /**
     * 几室
     */
    @JsonProperty("room")
    @ApiParam("几室")
    @ApiModelProperty("几室")
    private String room;//几室

    /**
     * 几厅
     */
    @JsonProperty("hall")
    @ApiParam("几厅")
    @ApiModelProperty("几厅")
    private Integer hall;//几厅

    /**
     * 出租价格
     */
    @JsonProperty("totalPrice")
    @ApiParam("出租价格")
    @ApiModelProperty("出租价格")
    private Double totalPrice;

    /**
     * 商圈id
     */
    @JsonProperty("areaId")
    @ApiParam("商圈id")
    @ApiModelProperty("商圈id")
    private Integer areaId;


    /**
     * 页数
     */
    @JsonProperty("pageNum")
    @ApiParam("页数")
    @ApiModelProperty("页数")
    private Integer pageNum =1;

    /**
     * 页面数量
     */
    @JsonProperty("pageSize")
    @ApiParam("页面数量")
    @ApiModelProperty("页面数量")
    private Integer pageSize =10;
}
