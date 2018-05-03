package com.toutiao.app.domain.rent;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

import javax.validation.constraints.NotNull;

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
    private Integer rentHouseType = 1;
    /**
     * 过滤标志
     */
    private String uid;
}
