package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by wk on 2018/12/25.
 */
@Data
public class ReportStatisticsResponse implements Serializable {

    @ApiModelProperty(value = "举报人数", name = "reportPeople")
    private Integer reportPeople;

    @ApiModelProperty(value = "举报房源", name = "reportCount")
    private Integer reportCount;

    @ApiModelProperty(value = "下架数量", name = "unShelveCount")
    private Integer unShelveCount;

    @ApiModelProperty(value = "赔付金额", name = "payMoney")
    private Integer payMoney;

}
