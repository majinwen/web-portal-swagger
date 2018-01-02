package com.toutiao.web.domain.intelligenceFh;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class IntelligenceFhTdRatio {

    /**
     * 12个月市场成交套数总和
     */
    private Integer sumTargetSd;

    /**
     * 12个月目标市场成交套数总和
     */
    private Integer sumAllSd;

    /**
     * 月度最低成交量
     */
    private Integer minVolume;

    /**
     * 月度最低成交量占市场百分比
     */
    private String minVolumeRatio;

    /**
     * 月度最高成交量
     */
    private Integer maxVolume;

    /**
     * 月度最高成交量
     */
    private String maxVolumeRatio;

    /**
     * 月度平均成交量
     */
    private String averageVolume;

    /**
     * 月度平均成交量占市场百分比
     */
    private String averageVolumeRatio;

}