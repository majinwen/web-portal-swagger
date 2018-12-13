package com.toutiao.app.domain.plot;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wk on 2018/12/12.
 */
@Data
public class PlotMarketDo {

    private Integer id;

    private Integer cityId;

    private Integer districtId;

    private Integer newCode;

    private Integer visit30Sort;

    private Integer visit30;

    private Integer avgDeal;

    private BigDecimal rentSell30;

    private Date time;

    private Integer avgDealSort;

    private Integer rentSell30Sort;

    private Integer totalSort;

    private Integer totalScore;
}
