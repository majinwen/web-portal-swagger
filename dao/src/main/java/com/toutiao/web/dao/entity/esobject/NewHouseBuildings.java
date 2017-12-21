package com.toutiao.web.dao.entity.esobject;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


import java.util.Date;
import java.util.Map;

/**
 * 新房--楼盘es映射对象
 *
 */
@Data
public class NewHouseBuildings {
    /**
     * 城市id
     */
    @JSONField(name = "cityId")
    private Integer cityId;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 区县id
     */
    @JSONField(name = "districtId")
    private Integer districtId;
    /**
     * 区县名称
     */
    private String districtName;
    /**
     * 商圈id
     */
    @JSONField(name = "areaId")
    private Integer areaId;
    /**
     * 商圈名称
     */

    private String areaName;
    /**
     * 地铁线id
     */
    @JSONField(name = "subwayLine")
    private Integer[] subwayLineId;
    /**
     * 地铁线名称
     */
    private String[] subwayLine;
    /**
     * 地铁站id
     */
    @JSONField(name = "subwayStation")
    private Integer[] subwayStationId;
    /**
     * 地铁站名称
     */
    private String[] subwayStation;
    /**
     * 物业类型id
     */
    @JSONField(name = "propertyType")
    private Integer[] propertyTypeId;
    /**
     * 物业类型名称
     */
    @JSONField(name = "propertyType")
    private String[] propertyType;
    /**
     * 是否有电梯
     */
    @JSONField(name = "hasLift")
    private Integer elevatorFlag;
    /**
     * 建筑类型id
     */
    @JSONField(name = "buildCategory")
    private Integer[] buildingTypeId;
    /**
     * 建筑类型名称
     */
    private String buildingType;
    /**
     * 销售状态id
     */
    @JSONField(name = "saling")
    private Integer saleStatusId;
    /**
     * 销售状态名称
     */
    private String saleStatusName;
    /**
     * 楼盘特色id
     */
    @JSONField(name = "projFeature")
    private Integer[] buildingFeatureId;
    /**
     * 楼盘特色名称
     */
    private String[] buildingFeature;
    /**
     * 装修类型id
     */
    @JSONField(name = "fixstatus")
    private Integer redecorateTypeId;
    /**
     * 装修类型名称
     */
    private String redecorateType;
    /**
     * 楼盘id
     */
    @JSONField(name = "newcode")
    private Integer buildingNameId;
    /**
     * 楼盘名称
     */
    @JSONField(name = "projname")
    private String buildingName;
    /**
     * 均价
     */
    @JSONField(name = "totalPrice")
    private Double averagePrice;
    /**
     * 楼盘标签(特色)id
     */
    @JSONField(name = "projFeature")
    private Integer[] buildingTagsId;
    /**
     * 楼盘标签内容
     */
    private String[] buildingTags;
    /**
     * 活动描述
     */
    @JSONField(name = "activityInfo")
    private String activityDesc;
    /**
     * 大楼图片
     */
    @JSONField(name = "projectImage")
    private String[] buildingImgs;
    /**
     * 大楼别名
     */
    @JSONField(name = "nickname")
    private String buildingNickname;
    /**
     * 楼盘地址
     */
    @JSONField(name = "address")
    private String buildingAddress;
    /**
     * 交通状况
     */
    @JSONField(name = "traffic")
    private String trafficCondition;
    /**
     * 开盘时间
     */
    @JSONField(name = "saledate")
    private Date openedTime;
    /**
     * 交房时间
     */
    @JSONField(name = "livindate")
    private Date deliverTime;
    /**
     * 开发商
     */
    @JSONField(name = "developer")
    private String developers;
    /**
     * 销售许可证信息
     */
    @JSONField(name = "salesLicenseInfo")
    private String sellLicence;
    /**
     * 产权年限
     */
    @JSONField(name = "rightYear")
    private Integer buildingLife;
    /**
     * 车位配比
     */
    @JSONField(name = "parkRadio")
    private String parkRadio;
    /**
     * 楼盘地理坐标
     */
    private String[] location;
    @JSONField(name = "coordX")
    private Double coordX;
    @JSONField(name = "coordY")
    private Double coordY;
    /**
     * 环路
     */
    @JSONField(name = "ringRoad")
    private Integer roundstation;
    /**
     * 售楼地址
     */
    @JSONField(name = "saleaddress")
    private String saleAddress;
    /**
     * 占地面积(平方米)
     */
    @JSONField(name = "groundArea")
    private Double groundArea;
    /**
     * 建筑面积(平方米)
     */
    @JSONField(name = "purposeArea")
    private Double purposeArea;
    /**
     * 容积率(%)
     */
    @JSONField(name = "dimension")
    private Double dimension;
    /**
     * 绿化率(%)
     */
    @JSONField(name = "virescencerate")
    private Double virescencerate;
    /**
     * 总户数
     */
    @JSONField(name = "totaldoor")
    private String totaldoor;
    /**
     * 停车位数量
     */
    @JSONField(name = "parkspace")
    private Integer parkSpace;
    /**
     * 物业管理公司
     */
    @JSONField(name = "propertymanage")
    private String propertymanage;
    /**
     * 物业管理费
     */
    @JSONField(name = "propertyfee")
    private Double propertyfee;
    /**
     * 供暖方式名称(0-未知，1-集中供暖，2-自供暖）
     */
    @JSONField(name = "heatingMode")
    private String heatingType;
    /**
     * 供暖方式id
     *(0-未知，1-集中供暖，2-自供暖）
     */
    @JSONField(name = "heatingMode")
    private Integer heatingTypeId;
    /**
     * 最小面积
     */
    private Double houseMinArea;
    /**
     * 最大面积
     */
    private Double houseMaxArea;
    /**
     * 附件地铁信息
     */
    @JSONField(name = "subwayDistince")
    private Map<String,String> nearbysubway;
    /**
     * 楼盘等级
     *
     */
    @JSONField(name = "level")
    private Integer buildingLevel;
    /**
     * es 类型
     */
    @JSONField(name = "Type")
    private String Type;
    /**
     * es 自定义version
     */
    @JSONField(name = "version")
    private Integer version;
    /**
     * 楼盘id
     */
    @JSONField(name = "key")
    private Integer key;

}
