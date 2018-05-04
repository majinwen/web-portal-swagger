package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class SellHouseDoQuery extends QueryDo {

    /**
     * 推荐房源查询标志
     */
    private String uid;
    /**
     * 附近1,3,5km
     *
     */
    private Integer distance ;

    /**
     * y坐标
     */
    private Double lat;
    /**
     * x坐标
     */
    private Double lon;


}
