package com.toutiao.app.domain.mapSearch;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

/**
 * @ClassName RentMapFindHouseDistrictDoQuery
 * @Author jiangweilong
 * @Date 2018/11/23 12:50 PM
 * @Description:
 **/

@Data
public class RentMapSearchDoQuery  extends QueryDo {

    /**
     * 组类型：区域district，商圈bizcircle，社区community
     */
    private String groupType;

    /**
     * 整租户型
     */
    private String elo = null;

    /**
     * 合租户型
     */
    private String jlo = null;

    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType = null;

    /**
     * 附近距离
     */
    private Integer distance;

    /**
     * 左下维度
     */
    private Double minLatitude;

    /**
     * 左下经度
     */
    private Double minLongitude;

    /**
     * 右上维度
     */
    private Double maxLatitude;

    /**
     * 右上经度
     */
    private Double maxLongitude;
    /**
     * 中心维度
     */
    private Double lat;
    /**
     * 中心经度
     */
    private Double lon;
    /**
     * 小区id
     */
    private Integer newcode;

}
