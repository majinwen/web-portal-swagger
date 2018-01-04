package com.toutiao.web.dao.entity.officeweb;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class IntelligenceFindhouse {
    /**
     * 序号
     */
    private Integer newcode;

    /**
     * 新房/二手房(0-新房，1-二手房)
     */
    private Short nOrE;

    /**
     * 楼盘名称
     */
    private String projname;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 楼盘分期
     */
    private String installment;

    /**
     * 地址
     */
    private String address;

    /**
     * 地址描述
     */
    private String addressInfo;

    /**
     * 房源特色
     */
    private String housefeature;

    /**
     * 项目特色/标签(数组)
     */
    private String projFeature;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private Integer cityId;

    /**
     * 区县
     */
    private Integer districtId;

    /**
     * 商圈
     */
    private Integer areaId;

    /**
     * 环线
     */
    private String round;

    /**
     * 环线方位
     */
    private String rounddirection;

    /**
     * 所属社区
     */
    private Integer communityId;

    /**
     * 项目描述
     */
    private String projdesc;

    /**
     * 产权年限
     */
    private Short rightYear;

    /**
     * 占地面积(平方米)
     */
    private BigDecimal groundarea;

    /**
     * 建筑面积(平方米)
     */
    private BigDecimal purposearea;

    /**
     * 容积率(%)
     */
    private BigDecimal dimension;

    /**
     * 绿化率(%)
     */
    private BigDecimal virescencerate;

    /**
     * 楼层状况
     */
    private String buildingdes;

    /**
     * 总户数
     */
    private Integer totaldoor;

    /**
     * 停车位描述
     */
    private String parkdesc;

    /**
     * 停车位数量
     */
    private Integer parkspace;

    /**
     * 停车位租价
     */
    private BigDecimal carRentPrice;

    /**
     * 停车位售价
     */
    private BigDecimal carSellPrice;

    /**
     * 物业管理费
     */
    private BigDecimal propertyfee;

    /**
     * 物业管理费价格单位
     */
    private String propfeetype;

    /**
     * 物业管理费附加信息
     */
    private String propertyaddition;

    /**
     * 小区内部配套
     */
    private String layout;

    /**
     * 工程进度
     */
    private String workSchedule;

    /**
     * 开工时间
     */
    private Date startdate;

    /**
     * 竣工时间
     */
    private Date finishdate;

    /**
     * 开盘时间
     */
    private String saledate;

    /**
     * 开盘时间描述
     */
    private String saledateS;

    /**
     * 入住时间/交房时间
     */
    private String livindate;

    /**
     * 入住时间描述
     */
    private String livindateS;

    /**
     * 售楼热线
     */
    private String saletelphone;

    /**
     * 售楼地址
     */
    private String saleaddress;

    /**
     * 销售证
     */
    private String salecard;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private Short saling;

    /**
     * 销售阶段(0:期房.1:现房.2:尾房.3:二手房)
     */
    private Short sailSchedule;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 投资商
     */
    private String investor;

    /**
     * 物业管理公司
     */
    private String propertymanage;

    /**
     * 承建商
     */
    private String construct;

    /**
     * 景观设计单位
     */
    private String sightdesign;

    /**
     * 审批状态（0-不通过，1-通过，2-错误）（0-未发布，1-已发布）
     */
    private Short isApprove;

    /**
     * 网站地址
     */
    private String webaddress;

    /**
     * 网上售楼中心地址
     */
    private String websaleaddress;

    /**
     * 完成度
     */
    private String completeFraction;

    /**
     * x坐标
     */
    private BigDecimal coordX;

    /**
     * y坐标
     */
    private BigDecimal coordY;

    /**
     * 楼盘置为售完的时间
     */
    private Date salingOptime;

    /**
     * 移交二手房或者从二手房转回的时间
     */
    private Date adminstautsTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deldate;

    /**
     * 删除说明
     */
    private String delreason;

    /**
     * 价格增长率
     */
    private BigDecimal pricerate;

    /**
     * 最高价格
     */
    private BigDecimal pricemax;

    /**
     * 最低价格
     */
    private BigDecimal pricemin;

    /**
     * 名称全拼
     */
    private String pinyinName;

    /**
     * 名称拼音首字母
     */
    private String pinyinInitials;

    /**
     * 开盘更新时间
     */
    private Date updateSaledateTime;

    /**
     * 入住更新时间
     */
    private Date updateLivindateTime;

    /**
     * 产权描述
     */
    private String rightDesc;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 物业电话
     */
    private String propertyTele;

    /**
     * 物业地址
     */
    private String propertyAddress;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 删除标志位(0-否，1-是)
     */
    private Short isDel;

    /**
     * 平均单价-二手房
     */
    private BigDecimal esfPrice;

    /**
     * 平均总价-新房
     */
    private BigDecimal totalPrice;

    /**
     * 平均单价-新房
     */
    private BigDecimal price;

    /**
     * 平均总价-二手房
     */
    private BigDecimal esfTotalPrice;

    /**
     * 供暖方式(0-未知，1-集中供暖，2-自供暖）
     */
    private Integer heatingMode;

    /**
     * 梯户比
     */
    private String liftDoorRadio;

    /**
     * 车位配比
     */
    private String parkRadio;

    /**
     * 物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */
    private Integer propertyType;

    /**
     * 住宅建筑形式(数组)：1-低层，2-多层，3-小高层，4-高层，5-超高层，6-联排、7-独栋、8-双拼、9-叠拼、10-空中花园、11-空中别墅、12-开间、13-平层、14-复式、15-跃层、16-其他、17-合院
     */
    private Object buildForm;

    /**
     * 建筑类别(数组)：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private Object buildCategory;

    /**
     * 别墅建筑风格(数组)：1-中式、2-欧式、3-日式、4-美式、5-英式、6-澳式、7-法式、8-西班牙式、9-东南亚式、10-地中海式、11-意大利式、12-现代
     */
    private Object villaStyle;

    /**
     * 新房平均单价单位
     */
    private String priceUnit;

    /**
     * 新房平均总价单位
     */
    private String totalPriceUnit;

    /**
     * 二手房平均单价单位
     */
    private String esfPriceUnit;

    /**
     * 二手房平均总价单位
     */
    private String esfTotalPriceUnit;

    /**
     * 住宅类别(数组)：（1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼，6：独栋别墅，7：联排别墅，8：经济适用房， 9：廉租房，10：公共租赁房，11：定向安置房，12：两限商品房，13：自住型商品房，14：其他，15-商铺，16-写字楼，17-平房，18-车位，19-办公，20-四合院，21-住宅商铺，22-写字楼商铺，23-商务型商铺，24-市场类商铺，25-标准写字楼，26-酒店写字楼，27-别墅，28-办公别墅，29-购物中心，30-商业，31-企业独栋，32-住宅底商，33-自持租赁，34-总部园区）
     */
    private Object residentialCategory;

    /**
     * 空气质量
     */
    private String airQuality;

    /**
     * 电梯配备(1-有，2-无)
     */
    private Short hasLift;

    /**
     * 活动信息
     */
    private Object activityInfo;

    /**
     * 销售许可证信息
     */
    private Object salesLicenseInfo;

    /**
     * 楼盘动态
     */
    private Object dynamicInfo;

    /**
     * 楼盘优惠
     */
    private String discountInfo;

    /**
     * 楼盘评级(1-广告/付费，2-优，3-良，4-一般)
     */
    private Short level;

    /**
     * 楼盘评分
     */
    private Integer score;

    /**
     * 楼栋数
     */
    private Integer buildCount;

    /**
     * 环路(1-二环以内,2-二至三环,3-三至四环,4-四至五环,5-五至六环,6-六环以外)
     */
    private Short ringRoad;

    /**
     * 供水
     */
    private String waterSupply;

    /**
     * 供电
     */
    private String electricSupply;

    /**
     * 交通信息
     */
    private String traffic;

    /**
     * 装修(数组)(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他 6:非毛坯 7:公共部分简单装修)
     */
    private Object fitment;

    /**
     * 楼盘标签
     */
    private Object buildTags;

    /**
     * 小区到环线主路的距离
     */
    private BigDecimal villageMainroad;

    /**
     * 新房面积范围（起始）
     */
    private BigDecimal newhRangeS;

    /**
     * 新房面积范围（结束）
     */
    private BigDecimal newhRangeE;

    /**
     * 小区面积范围（起始）
     */
    private BigDecimal villageRangeS;

    /**
     * 小区面积范围（结束）
     */
    private BigDecimal villageRangeE;

    /**
     * 供电
     */
    private String powerSupply;

    /**
     * 优惠信息
     */
    private String residentialHouseType;

    /**
     * 小区换手率
     */
    private BigDecimal turnoverRate;

    /**
     * 小区出租率
     */
    private BigDecimal rentals;

    /**
     * 小区图片
     */
    private String plotImage;

    /**
     * 区域名称
     */
    private String districtName;

    /**
     * 商圈名称
     */
    private String areaName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 方圆1.0km内是否有地铁(1-有，2-无)
     */
    private Short hasSubway;

    /**
     * 是否有教育配套
     */
    private Short hasSchool;

    /**
     * 1.0km是否有购物配套
     */
    private Short hasMarket;

    /**
     * 附近地铁站
     */
    private String nearbyStation;

    /**
     * 附近学校
     */
    private String nearbySchool;

    /**
     * 附近购物配套
     */
    private String nearbyMarket;

    /**
     * 最小面积
     */
    private BigDecimal minArea;

    /**
     * 最大面积
     */
    private BigDecimal maxArea;

    /**
     * 1.0km是否有医疗配套
     */
    private Short hasMedical;

    /**
     * 附近学校类别数组
     */
    private Object schoolTypeArray;

    /**
     * 附近医疗配套
     */
    private String nearbyMedical;

    /**
     * 1.0km是否有餐厅(1-有，2-无)
     */
    private Short hasRestaurant;

    /**
     * 附近餐厅
     */
    private String nearbyRestaurant;

    /**
     * 1.0km是否有休闲设施(1-有，2-无)
     */
    private Short hasLeisure;

    /**
     * 附近休闲设施
     */
    private String nearbyLeisure;

    /**
     * 1.0km是否有健身设施(1-有，2-无)
     */
    private Short hasBodybuilding;

    /**
     * 附近健身设施
     */
    private String nearbyBodybuilding;

    /**
     * 附近符合条件地铁站个数
     */
    private Integer nearbyStationCount;

    /**
     * 附近符合条件购物中心个数
     */
    private Integer nearbyMarketCount;

    /**
     * 距离最近的环线
     */
    private String nearbyRoad;

    /**
     * 距离最近的环线米
     */
    private Integer nearbyRoadMeter;

    /**
     * 最低总价
     */
    private BigDecimal minTotalPrice;

    /**
     * 最高总价
     */
    private BigDecimal maxTotalPrice;

    /**
     * 楼盘包含的户型(数组)
     */
    private Object layoutRange;

    /**
     * 最近地铁站描述
     */
    private String nearestSubwayDesc;

    /**
     * 楼盘3km地铁线
     */
    private Object subwayLine;

    /**
     * 楼盘3km地铁站
     */
    private Object subwayStation;

    /**
     * 楼盘距地铁站的距离
     */
    private String subwayDistince;

    /**
     * 小区户型集合
     */
    private String projLayout;

    /**
     * 明星楼盘搜索量
     */
    private Integer starProperty;
    /**
     * 附近的桥
     */
    private String nearbyQiao;

}