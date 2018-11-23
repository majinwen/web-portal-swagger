package com.toutiao.app.domain.mapfindhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

/**
 * @ClassName NewHouseMapFindHouseDoQuery
 * @Author jiangweilong
 * @Date 2018/11/22 9:37 PM
 * @Description:
 **/

@Data
public class NewHouseMapFindHouseDoQuery extends QueryDo {

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
     * 销售状态
     */
    private  Integer[] saleStatusId;

}
