package com.toutiao.appV2.model.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReportTeSeJianLouRespose {

    /**
     * 捡漏房
     */
    @ApiModelProperty(value = "捡漏房")
    private List<LowerHouseQuotationResponse> houseQuotationList;

    /**
     * 捡漏房
     */
    @ApiModelProperty(value = "二手房")
    private List<EsfQuotationRespose> esfQuotationList;

}