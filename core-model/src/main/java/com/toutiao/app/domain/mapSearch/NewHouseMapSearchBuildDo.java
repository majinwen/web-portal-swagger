package com.toutiao.app.domain.mapSearch;

import com.toutiao.app.domain.newhouse.ActivityInfoDo;
import com.toutiao.app.domain.sellhouse.HouseLable;
import lombok.Data;

import java.util.List;

/**
 * @ClassName NewHouseMapFindHouseBuildDo
 * @Author jiangweilong
 * @Date 2018/11/23 11:06 AM
 * @Description:
 **/

@Data
public class NewHouseMapSearchBuildDo {

    /**
     * 区域id
     */
    private Integer districtId;

    /**
     * 区域名称
     */
    private String districtName;

    /**
     * 物业类型
     */
    private  String propertyType;

    /**
     * 楼盘名称
     */
    private  String buildingName;

    /**
     * 楼盘id
     */
    private Integer buildingNameId;

    /**
     * 均价
     */
    private Double averagePrice;

    /**
     * 销售状态
     */
    private  String  saleStatusName;

    /**
     * 楼盘标题图
     */
    private  String buildingTitleImg;

    /**
     * 总价
     */
    private Double  totalPrice;

    /**
     * 标签
     */
    private  String [] buildingTags;

    /**
     * 建筑面积
     */
    private  Double buildingArea;

    /**
     * 坐标，纬度
     */
    private String latitude;

    /**
     * 坐标，经度
     */
    private String longitude;

    /**
     * 活动折扣 0:未参与,1:参与
     */
    private Integer isActive;

    /**
     * 价格描述
     */
    private String priceDesc;

    /**
     * 标签列表
     */
    private List<HouseLable> houseLabelList;

    /**
     * 楼盘动态数量
     */
    private Long dynamicTotal;

    /**
     * 户型类型
     */
    private String roomType;

    /**
     * 最小面积
     */
    private Double houseMinArea;

    /**
     * 最大面积
     */
    private  Double houseMaxArea;

    /**
     * 优惠活动
     */
    private List<ActivityInfoDo> activityInfo;

}
