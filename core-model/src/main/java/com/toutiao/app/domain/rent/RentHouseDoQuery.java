package com.toutiao.app.domain.rent;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class RentHouseDoQuery extends QueryDo{


    /**
     * 附近距离
     */
    private Integer distance;

    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType;

    /**
     * 来源id
     */
    private String source;

    /**
     * 维度
     */
    private Double lat;
    /**
     * 经度
     */
    private Double lon;

    /**
     * 导入:3/录入:1
     */
    private Integer rentHouseType;
    /**
     * 过滤标志
     */
    private String uid;

    /**
     * 户型
     */
    private String elo;//整租户型

    private String jlo;//合租户型

    /**
     * 时间
     */
    private String time;

    /**
     * 交通类型(0:步行,1:骑车,2:公交,3:驾车)
     */
    private String trafficType;
    private String sort;

    /**
     * 房源id
     */
    private String houseId;

    /**
     * 出租价格
     */
    private double totalPrice;

    /**
     * 小区id
     */
    private Integer buildingId;
}
