package com.toutiao.app.domain.sellhouse;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearBySellHouseQueryDo {

    /**
     * 起始价格
     */
    private  Double beginPrice;

    /**
     * 结束价格
     */
    private Double endPrice;


    /**
     *起始面积
     */

    private  Double beginArea;

    /**
     * 结束面积
     */
    private  Double endArea;

    /**
     * 居室
     */
    private  Integer [] layoutId;

    /**
     * 朝向
     */
    private  Integer[] forwardId;

    /**
     * 楼龄[0-5]
     */
    private String houseYearId;

    /**
     *
     * 标签
     */
    private  Integer [] labelId;


    /**
     * 区域
     */
    private Integer districtId;

    /**
     * 商圈id
     */
    private Integer areaId;


    /**
     * 地铁线Id
     */
    private Integer subwayLineId;

    /**
     * 地铁站id
     */
    private Integer subwayStationId;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 页码
     */

    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize=10;


    /**
     *
     */

    private  Integer cityId;

    /**
     * y坐标
     */
    @NotNull(message = "缺少坐标y")
    private Double lat;
    /**
     * x坐标
     */
    @NotNull(message = "缺少坐标x")
    private Double lon;
    /**
     * 附近距离
     */
    @NotNull(message = "缺少附近距离")
    private Integer distance;

}
