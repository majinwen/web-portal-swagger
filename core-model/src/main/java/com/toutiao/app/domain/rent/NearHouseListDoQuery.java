package com.toutiao.app.domain.rent;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearHouseListDoQuery extends QueryDo {

    /**
     * 附近距离
     */
    private Integer distance = 5;
    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType;
    /**
     * 来源id
     */
    private Integer[] source;

    /**
     * 维度
     */
    @NotNull(message = "维度不能为空")
    private Double lat;
    /**
     * 经度
     */
    @NotNull(message = "经度不能为空")
    private Double lon;
}
