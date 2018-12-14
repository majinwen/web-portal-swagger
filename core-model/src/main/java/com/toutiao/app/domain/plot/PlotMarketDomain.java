package com.toutiao.app.domain.plot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by wk on 2018/12/12.
 */
@Data
public class PlotMarketDomain {

    @ApiModelProperty(value = "区域id", name = "districtId")
    private Integer districtId;

    @ApiModelProperty(value = "区域名称", name = "districtName")
    private String districtName;

    @ApiModelProperty(value = "30天访问热度排名", name = "visit30Sort")
    private Integer visit30Sort;

    @ApiModelProperty(value = "30天访问热度", name = "visit30")
    private Integer visit30;

    @ApiModelProperty(value = "平均成交周期", name = "avgDeal")
    private Integer avgDeal;

    @ApiModelProperty(value = "30天租售比", name = "rentSell30")
    private BigDecimal rentSell30;

    @ApiModelProperty(value = "平均预期成交周期排名", name = "avgDealSort")
    private Integer avgDealSort;

    @ApiModelProperty(value = "30天租售比排名", name = "rentSell30Sort")
    private Integer rentSell30Sort;

    @ApiModelProperty(value = "总排名", name = "totalSort")
    private Integer totalSort;

    @ApiModelProperty(value = "总分数", name = "totalScore")
    private Integer totalScore;

}
