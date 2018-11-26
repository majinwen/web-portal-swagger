package com.toutiao.app.domain.mapSearch;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

/**
 * @ClassName esfMapFindHouseDo
 * @Author jiangweilong
 * @Date 2018/11/22 2:11 PM
 * @Description:
 **/

@Data
public class EsfMapSearchDoQuery extends QueryDo {

    /**
     * 组类型：区域district，商圈bizcircle，社区community
     */
    private String groupType;

    /**
     * 右上 经度
     */
    private Double maxLongitude;

    /**
     * 右上 纬度
     */
    private Double maxLatitude;

    /**
     * 左下 经度
     */
    private Double minLongitude;

    /**
     * 左下 纬度
     */
    private Double minLatitude;

    /**
     * 附近 1000 2000 3000
     */
    private Integer distance;

    /**
     * 定位坐标 纬度
     */
    private Double lat;
    /**
     * 定位坐标 经度
     */
    private Double lon;

    /**
     * 小区id
     */
    private Integer newcode;







}
