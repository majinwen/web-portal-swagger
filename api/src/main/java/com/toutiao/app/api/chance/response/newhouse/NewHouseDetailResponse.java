package com.toutiao.app.api.chance.response.newhouse;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NewHouseDetailResponse {

    /**
     * 销售信息
     */
    private  String saleStatusName;

    /**
     * 销售许可
     *
     */
    private Map<String,String> sellLicence;


    /**
     * 商圈id
     */
    private Integer areaId;

    /**
     * 环路桥
     */
    private  String ringRoadName;

    /**
     * nearbysubway
     */
    private Map<String,String> nearbysubway;

    /**
     * 城市id
     */
    private  Integer cityId;

    /**
     * 房屋的最大面积
     */
    private  Double houseMaxArea;

    /**
     * 空气质量
     */
    private  String airQuality;

    /**
     *物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */
    private  Integer propertyTypeId;

    /**
     * 区域id
     */
    private  Integer districtId;

    /**
     *
     *物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */

    private  String propertyType;


    /**
     * 交通信息
     */
    private  String trafficCondition;

    /**
     * 大楼图片
     */
    private List<String> buildingImgs;

    /**
     * 建筑面积(平方米)
     */

    private  Double purposeArea;

    /**
     * 楼盘名称
     *
     */
    private  String buildingName;

    /**
     * 物业管理公司
     */
    private  String propertymanage;


    /**
     * 售楼热线
     */
    private String saletelphone;

    /**
     *  售楼地址
     */
    private  String saleAddress;

    /**
     * 停车位数量
     */
    private  Integer parkSpace;

    /**
     *供水
     */
    private String waterSupply;


    /**
     * 最小面积
     *
     */
    private  Double houseMinArea;

    /**
     * 坐标
     */

    private String location;

    /**
     * 供电
     */
    private  String electricSupply;


    /**
     * 区域名称
     */
    private  String districtName;

    /**
     * 楼盘标签1-近地铁，3-现房，4-精装修，5-花园洋房，6-复式，7-车位充足，8-低密度，9-500强房企，10-优质物业，11-购物方便，12-教育配套，13-医疗配套，14-换手率高，15-租金月供比高，16-五证齐全，17-人车分流
     */
    private  Integer [] buildingTagsId;


    /**
     * 开发商
     */
    private  String  developers;


    /**
     *楼盘标签1-近地铁，3-现房，4-精装修，5-花园洋房，6-复式，7-车位充足，8-低密度，9-500强房企，10-优质物业，11-购物方便，12-教育配套，13-医疗配套，14-换手率高，15-租金月供比高，16-五证齐全，17-人车分流
     */
    private  String [] buildingTags;

    /**
     * 楼栋数
     */
    private  Integer buildCount;

    /**
     * 楼盘newcode
     */
    private Integer  buildingNameId;

    /**
     * 总户数
     */
    private  Integer totaldoor;

    /**
     * 入住时间/交房时间
     */
    private String deliverTime;

    /**
     *车位配比
     */
    private  String parkRadio;


    /**
     * 距离最近的环线米
     */
    private  Integer ringRoadDistance;

    /**
     * 区域名称
     */
    private  String areaName;


    /**
     * 地铁站
     */
    private  Integer[] subwayStationId;

    /**
     *  新房名称
     */
    private  String buildingNickname;

    /**
     * 新盘地址
     */
    private  String buildingAddress;

    /**
     *  活动信息
     */
    private  String activityDesc;

    /**
     *新房标题图
     */
    private  String buildingTitleImg;

    /**
     * 最近地铁站描述
     */
    private  String roundstation;

    /**
     * 总价
     */
    private  Double totalPrice;

    /**
     * 审核状态
     */
    private  Integer isApprove;

    /**
     * 供暖方式(0-未知，1-集中供暖，2-自供暖）
     */
    private  Integer heatingType;

    /**
     * 物业管理费
     */

    private  Double propertyfee;

    /**
     * 开盘时间
     */
    private  String openedTime;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private  Integer saleStatusId;

    /**
     * 装修(数组)(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他 6:非毛坯 7:公共部分简单装修)
     */

    private  Integer [] redecorateTypeId;

    /**
     * 产权年限
     */
    private  Integer  buildingLife;

    /**
     * 建筑类别
     */
    private String  buildingType;

    /**
     * 占地面积(平方米)
     */
    private  Double groundArea;

    /**
     * 平均单价-新房
     */
    private  Double averagePrice;

    /**
     * 楼盘评分
     */
    private  Integer buildingSort;

    /**
     * 停车位租价
     */
    private  Double carRentPrice;

    /**
     * 梯户比
     */
    private  String liftDoorRadio;

    /**
     * 项目特色
     */
    private  String buildingFeature;
    /**
     * 容积率(%)
     */
    private  Double dimension;
    /**
     * 绿化率
     */
    private Double virescencerate;


}
