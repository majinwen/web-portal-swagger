package com.toutiao.app.domain.newhouse;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NewHouseDetailDo {


    /**
     * 销售信息
     */
    private  String sale_status_name;

    /**
     * 销售许可
     *
     */
    private Map<String,String> sell_licence;

    /**
     * 商圈id
     */
    private Integer area_id;

    /**
     * 总价
     */
     private  Double totalPrice;

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
    private  Integer city_id;

    /**
     * 房屋的最大面积
     */
    private  Double house_max_area;

    /**
     * 空气质量
     */
    private  String air_quality;

    /**
     *物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */
    private  Integer property_type_id;

    /**
     * 区域id
     */
    private  Integer district_id;

    /**
     *
     *物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */

    private  String property_type;


    /**
     * 交通信息
     */
    private  String traffic_condition;

    /**
     * 大楼图片
     */
    private List<String> building_imgs;

    /**
     * 建筑面积(平方米)
     */

    private  Double purpose_area;

    /**
     * 楼盘名称
     *
     */
    private  String building_name;

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
    private  String sale_address;

    /**
     * 停车位数量
     */
    private  Integer park_space;

    /**
     *供水
     */
    private String water_supply;


    /**
     * 最小面积
     *
     */
    private  Double house_min_area;

    /**
     * 坐标
     */

    private String location;

    /**
     * 供电
     */
    private  String electric_supply;


    /**
     * 区域名称
     */
    private  String district_name;

    /**
     * 楼盘标签1-近地铁，3-现房，4-精装修，5-花园洋房，6-复式，7-车位充足，8-低密度，9-500强房企，10-优质物业，11-购物方便，12-教育配套，13-医疗配套，14-换手率高，15-租金月供比高，16-五证齐全，17-人车分流
     */
    private  Integer [] building_tags_id;


    /**
     * 开发商
     */
    private  String  developers;


    /**
     *楼盘标签1-近地铁，3-现房，4-精装修，5-花园洋房，6-复式，7-车位充足，8-低密度，9-500强房企，10-优质物业，11-购物方便，12-教育配套，13-医疗配套，14-换手率高，15-租金月供比高，16-五证齐全，17-人车分流
     */
    private  String [] building_tags;

    /**
     * 楼栋数
     */
    private  Integer build_count;

    /**
     * 楼盘newcode
     */
    private Integer  building_name_id;

    /**
     * 总户数
     */
     private  Integer totaldoor;

    /**
     * 入住时间/交房时间
     */
     private String deliver_time;

    /**
     *车位配比
     */
    private  String park_radio;


    /**
     * 距离最近的环线米
     */
    private  Integer ringRoadDistance;

    /**
     * 区域名称
     */
    private  String area_name;


    /**
     * 地铁站
     */
    private  Integer[] subway_station_id;

    /**
     *  新房名称
     */
    private  String building_nickname;

    /**
     * 新盘地址
     */
    private  String building_address;

    /**
     *  活动信息
     */
    private  String activity_desc;

    /**
     *新房标题图
     */
    private  String building_title_img;

    /**
     * 最近地铁站描述
     */
    private  String roundstation;

    /**
     * 总价
     */
    private  Double total_price;

    /**
     * 审核状态
     */
    private  Integer is_approve;

    /**
     * 供暖方式(0-未知，1-集中供暖，2-自供暖）
     */
    private  Integer heating_type;

    /**
     * 物业管理费
     */

    private  Double propertyfee;

    /**
     * 开盘时间
     */
    private  String opened_time;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private  Integer sale_status_id;

    /**
     * 装修(数组)(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他 6:非毛坯 7:公共部分简单装修)
     */

    private  Integer [] redecorate_type_id;

    /**
     * 产权年限
     */
    private  Integer  building_life;

    /**
     * 建筑类别
     */
    private String  building_type;

    /**
     * 占地面积(平方米)
     */
    private  Double ground_area;

    /**
     * 平均单价-新房
     */
    private  Double average_price;

    /**
     * 楼盘评分
     */
    private  Integer building_sort;

    /**
     * 停车位租价
     */
    private  Double car_rent_price;

    /**
     * 梯户比
     */
    private  String lift_door_radio;

    /**
     * 项目特色
     */
    private  String building_feature;
    /**
     * 容积率(%)
     */
    private  Double dimension;
    /**
     * 绿化率
     */
    private Double virescencerate;

}
