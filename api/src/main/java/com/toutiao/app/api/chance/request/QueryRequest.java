package com.toutiao.app.api.chance.request;
import lombok.Data;

@Data
public class QueryRequest {

    /**
     * 起始价格
     */
    private  double beginPrice;

    /**
     * 结束价格
     */
    private double endPrice;


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



}
