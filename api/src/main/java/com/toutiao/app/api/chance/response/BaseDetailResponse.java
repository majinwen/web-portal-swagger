package com.toutiao.app.api.chance.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BaseDetailResponse {
    /**
     * 推优查询id
     */
    private String uid;
    /**
     * 新房id
     */
    private Integer newHouseId;
    /**
     * 新房名称
     */
    private String newHouseName;
    /**
     * 二手房id
     */
    private String sellHouseId;
    /**
     * 二手房名称
     */
    private String sellHouseName;
    /**
     * 租房id
     */
    private String rentId;
    /**
     * 租房名称
     */
    private String rentName;
    /**
     * 小区id
     */
    private Integer plotId;
    /**
     * 小区名称
     */
    private String plotName;
    /**
     * 房源别名
     */
    private String houseAlias;
    /**
     * 录入/导入房源标题
     */
    private String houseTitle;

    /**
     * 房源标题图
     */
    private String houseTitleImg;
    /**
     * 房源照片
     */
    private String[] houseImage;
    /**
     * 区域编号
     */
    private Integer districtId;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 商圈编号
     */
    private Integer areaId;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 地址
     */
    private String houseAddress;
    /**
     * 坐标
     */
    private String location;
    /**
     * 地铁站编号
     */
    private String[] subwayStationId;
    /**
     * 地铁站名称
     */
    private String[] subwayStation;
    /**
     * 地铁线编号
     */
    private String[] subwayLineId;
    /**
     * 地铁线名称
     */
    private String[] subwayLine;
    /**
     * 地铁到房源的距离
     */
    private Map subwayWithHouseDistance;
    /**
     * 交通信息
     */
    private String trafficInformation;
    /**
     * 标签编号
     */
    private String[] tagsId;
    /**
     * 标签
     */
    private String[] tags;
    /**
     * 待售房源
     */
    private String[] sellHouse;
    /**
     * 租金
     */
    private Double rentPrice;
    /**
     * 租赁方式id
     */
    private String rentTypeId;
    /**
     * 租赁方式名称
     */
    private String rentTypeName;
    /**
     * 出租房源标志(普租/公寓)
     */
    private Integer rentSignId;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rentSignName;

    /**
     * 均价
     */
    private Double avgPrice;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 几卫
     */
    private Integer toilet;
    /**
     * 几厨
     */
    private Integer kitchen;
    /**
     * 阳台
     */
    private Integer balcony;
    /**
     * 房源所在楼层(地下室为负数)
     */
    private Integer floor;
    /**
     * 房源所在层（高层/低层/中层）
     */
    private String floorName;
    /**
     * 总楼层数
     */
    private Integer totalFloor;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private Integer forwardId;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;

    /**
     * 建成年代
     */
    private String buildYears;
    /**
     * 车位配比
     */
    private String parkRatio;
    /**
     * 获取地铁与房源的距离
     */
    private String distanceKey;
    /**
     * 居住面积
     */
    private String liveArea;
    /**
     * 房源面积
     */
    private String houseArea;
    /**
     * 占地面积
     */
    private String coversArea;
    /**
     * 建筑面积
     */
    private String buildingArea;
    /**
     * 总栋数
     */
    private Integer totalBuilding;
    /**
     * 总户数
     */
    private Integer totalHousehold;
    /**
     * 户均绿化率
     */
    private Double avgGreening;
    /**
     * 梯户比
     */
    private Double liftDoorRadio;
    /**
     * 户均电梯
     */
    private String avgElevator;
    /**
     * 电梯配备id
     */
    private Integer hasElevatorId;
    /**
     * 电梯配备
     */
    private String hasElevator;
    /**
     * 停车费(租)
     */
    private String parkingRentFee;
    /**
     * 停车费(售)
     */
    private String parkingSellFee;
    /**
     * 房源描述
     */
    private String houseDesc;
    /**
     * 特色
     */
    private String feature;
    /**
     * 开发商
     */
    private String developers;
    /**
     * 物业公司
     */
    private String propertyCompany;
    /**
     * 物业费
     */
    private String propertyFee;
    /**
     * 物业类型id
     */
    private String[] propertyTypeId;
    /**
     * 物业类型名称
     */
    private String propertyType;
    /**
     * 产权年限
     */
    private String yopr;
    /**
     * 建筑类型编号
     */
    private String[] buildingTypeId;
    /**
     * 建筑类型名称
     */
    private String[] buildingType;
    /**
     * 建筑结构
     */
    private String buildingStructure;
    /**
     * 住宅类型
     */
    private String residentialType;
    /**
     * 供暖方式
     */
    private String heatingMode;
    /**
     * 供需关系
     */
    private String sdr;
    /**
     * 房源级别
     */
    private Integer houseLevel;
    /**
     * 城市编号
     */
    private String cityId;
    /**
     * 环线
     */
    private String ringRoad;
    /**
     * 详情环线
     */
    private String ringRoadDesc;
    /**
     * 详情环线距离
     */
    private Integer ringRoadDistance;

    /**
     * 容积率
     */
    private Double dimension;
    /**
     * 空气质量
     */
    private String airQuality;
    /**
     * 供电
     */
    private String electricSupply;
    /**
     * 电费
     */
    private String electricFee;
    /**
     * 供水
     */
    private String waterSupply;
    /**
     * 水费
     */
    private String waterFee;
    /**
     * 装修标准
     */
    private String decorationType;
    /**
     * 同比
     */
    private Double tongbi;
    /**
     * 环比
     */
    private Double huanbi;

    /**
     * 房源分数
     */
    private Integer houseScore;
    /**
     * 需求
     */
    private String demand;
    /**
     * 付款方式(1-面议，2-押一付一，3-押一付二，4-押一付三，5-押二付一，6-押二付二，7-押二付三，8-半年付，9-年付)
     */
    private Integer payMode;
    /**
     * 付款方式名称
     */
    private String payModeName;
    /**
     * 配套设施(字符串数组)
     */
    private String[] supportingFacilities;
    /**
     * 经纪机构
     */
    private String brokerageAgency;

    /**
     * 房源来源类型(录入/导入)
     */
    private Integer houseSourceTypeId;
    /**
     * 房源来源
     */
    private String houseSource;
    /**
     * 房源来源id
     */
    private Integer houseSourceId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 发版时间
     */
    private String publishTime;
    /**
     * 发布状态
     */
    private Integer releaseStatus;
    /**
     * 是否删除
     */
    private Integer isDel;
    /**
     * 是否是推荐
     */
    private Integer isRecommend;
    /**
     * 是否是置顶
     */
    private Integer isTop;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 置顶关键词
     */
    private String topKeyword;
    /**
     * 置顶时间
     */
    private String topTime;
    /**
     * 整租户型
     */
    private Integer eRentLayout;
    /**
     * 合租户型
     */
    private Integer jRentLayout;
    /**
     * 经纪人id
     */
    private Integer agentId;
    /**
     * 经纪人姓名
     */
    private String agentName;
    /**
     * 经纪人电话
     */
    private String agentPhone;
    /**
     * 经纪人头像
     */
    private String agentHeadPhoto;
    /**
     * 经纪公司
     */
    private String agentCompany;
    /**
     * 认领标识
     */
    private Integer isClaim;
    /**
     * 认领房源标题图
     */
    private String claimHousePhotoTitle;
    /**
     * 认领标签id
     */
    private String[] claimTags;
    /**
     * 认领房源id
     */
    private String claimHouseId;
    /**
     * 认领房源标题
     */
    private String claimHouseTitle;
    /**
     * 认领房源标题名称
     */
    private String[] claimTagsName;
    /**
     * 父类认领标识
     */
    private String isParentClaim;
    /**
     * 产权性质(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    private Integer propertyRight;
    /**
     * 产权性质名称(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    private Integer propertyRightName;
    /**
     * 建筑形式1：低层，2：多层，3：小高层，4：高层，5：超高层
     */
    private String buildFormName;
    /**
     * 建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private String[] buildCategory;
    /**
     * 建筑类别名称：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private String[] buildCategoryName;
    /**
     * 装修规则id(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)
     */
    private String[] fitmentId;
    /**
     * 装修规则名称(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)
     */
    private String[] fitmentName;
    /**
     * 房源首付
     */
    private Double houseDeposit;
    /**
     * 月供
     */
    private Double houseMonthPayment;
    /**
     * 发布公司
     */
    private String ofCompany;
    /**
     * 房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     * （ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    private Integer houseType;
    /**
     * 房屋类型名称：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     * （ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    private String houseTypeName;
    /**
     * 销售信息
     */
    private String saleStatusName;
    /**
     * 销售许可
     */
    private Map<String, String> sellLicence;
    /**
     * 大楼图片
     */
    private List<String> buildingImages;
    /**
     * 售楼地址
     */
    private String saleAddress;
    /**
     * 售楼处电话
     */
    private String salePhone;
    /**
     * 停车位数量
     */
    private Integer parkSpaceNum;
    /**
     * 最小面积
     */
    private Double houseMinArea;
    /**
     * 最大面积
     */
    private Double houseMaxArea;
    /**
     * 入住时间/交房时间
     */
    private String deliverTime;
    /**
     * 活动信息
     */
    private String activityDesc;
    /**
     * 审核状态
     */
    private Integer isApprove;
    /**
     * 开盘时间
     */
    private String openedTime;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private Integer saleStatusId;
    /**
     * 房源评分
     */
    private Integer houseSort;
    /**
     * 时间
     */
    private String time;
    /**
     * 动态类型
     */
    private Integer dynamicType;
    /**
     * 内容
     */
    private String detail;
    /**
     * 跳转地址
     */
    private String jumpUrl;
    /**
     * 动态跳转
     */
    private String linkUrl;
    /**
     * 户型id
     */
    private Integer layoutId;
    /**
     * 参考均价
     */
    private Double referencePrice;
    /**
     * 户型标题
     */
    private String layoutTitle;

    /**
     * 户型图片
     */
    private String layoutImg;
    /**
     * 参考总价
     */
    private Double referenceTotalPrice;
    /**
     * 销售面积
     */
    private Double saleArea;
    /**
     * 户型描述/户型解析
     */
    private String layoutDesc;
    /**
     * 新房收藏数量
     */
    private Integer newHouseFavorite;
    /**
     * 公交站id
     */
    private Integer[] busStationId;
    /**
     * 公交站
     */
    private String[] busStation;

    /**
     * 公交线路
     */
    private Integer[] busLinesId;
    /**
     * 公交线路
     */
    private String[] busLines;

}
