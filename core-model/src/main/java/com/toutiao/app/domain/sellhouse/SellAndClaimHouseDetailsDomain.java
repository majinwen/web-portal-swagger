package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import java.util.List;

/**
 * Created by wk on 2018/12/14.
 */
@Data
public class SellAndClaimHouseDetailsDomain {

    private List<SellAndClaimHouseDetailsDo> data;

    private Integer totalNum;

}
