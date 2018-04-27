package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SellHouseRequest {

    /**
     * 关键字
     */
    private String keyWord;

    /**
     * 房源id
     */
    private Integer houseId;
    /**
     * 房源面积
     */
    private String houseAreaSize;

    /**
     * 室 数字
     */
    private String layoutId;

    /**
     * 朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    private String houseOrientationId;

    /**
     * 房源总价 起始价
     */
    private String beginPrice;

    /**
     * 房源总价 结束价
     */
    private String endPrice;

    /**
     * 房源标签(满二)（1，2，3，4）
     */
    private String houseLabelId;

    /**
     * 房源所在层（高层/低层/中层）
     */
    private String houseFloorId;

    /**
     * 房源楼龄
     */
    private String houseYearId;

    /**
     * 房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     * （ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    private String propertyTypeId;

    /**
     * 产权性质(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    private String ownership;


    /**
     * 建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼 5：其他
     */
    private String buildingTypeId;


    /**
     * 排序  0--默认（按房源级别（广告优先））--1总价升排序--2总价降排序
     */
    private Integer sort;


    /**
     * 房源小区名称
     */
    private String housePlotName;

    /**
     * 小区id
     */
    private String newcode;

    /**
     * 商圈名称
     */
    private String houseBusinessName;

    /**
     * 商圈id
     */
    private String areaId;

    /**
     * 区域id
     */
    private String districtId;

    /**
     * 区域名称
     */
    private String area;

    /**
     * 地铁线id
     */
    private String subwayLineId;

    /**
     * 地铁站id
     */
    private String subwayStationId;

    /**
     * 经度
     */
    private double lat;
    /**
     * 维度
     */
    private double lon;
    /**
     * 是否有电梯
     */
    private String elevatorFlag;
    /**
     * 供暖
     */
    private Integer houseHeating;

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 二手房id
     */
    private Integer id;
    /**
     * 附近1,3,5km
     *
     */
    private String near ;
    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 推荐房源查询标志
     */
    @NotEmpty(groups = {First.class},message = "推荐房源查询标志为空")
    private String uid;

}
