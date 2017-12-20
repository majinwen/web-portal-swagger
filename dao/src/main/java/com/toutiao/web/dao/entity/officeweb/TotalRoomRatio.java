package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TotalRoomRatio {
    private BigDecimal total;

    private Integer room;

    private BigDecimal ratio;

    /**
     * 用户画像类型1,2,3,4,5,6,7
     */
    private Integer userPortrayalType;


}