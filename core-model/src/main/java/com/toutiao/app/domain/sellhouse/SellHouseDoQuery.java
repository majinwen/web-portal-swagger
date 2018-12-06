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

    /**
     * 排序字段
     */
    private String sortFields;

    /**
     * 排序标志
     */
    private String sort;

    /**
     * 小区id
     */
    private Integer buildingId;

    /**
     * 是否降价房
     */
    private Integer isCutPrice;

    /**
     * 是否价格洼地
     */
    private Integer isLowPrice;

    /**
     * 是否逢出必抢
     */
    private Integer isMustRob;

    /**
     * 房源总价
     */
    private double totalPrice;

    /**
     * 厅
     */
    private Integer hall;

    /**
     * 房源id
     */
    private String houseId;

}
