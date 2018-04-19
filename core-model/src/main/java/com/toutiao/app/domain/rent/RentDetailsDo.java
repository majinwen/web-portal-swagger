package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class RentDetailsDo {
    /**
     * 经纪人头像路径
     */
    private String agent_headphoto;
    /**
     * 小区Id
     */
    private Integer zufang_id;
    /**
     * 小区名称
     */
    private String zufang_name;
    /**
     * 小区分数
     */
    private Integer zufang_score;
    /**
     * 城市编号
     */
    private Integer city_id;
    /**
     * 区域编号
     */
    private Integer district_id;
    /**
     * 区域名称
     */
    private String district_name;
    /**
     * 商圈编号
     */
    private Integer area_id;
    /**
     * 商圈名称
     */
    private String area_name;
    /**
     * 出租房源Id
     */
    private String house_id;
    /**
     * 租赁方式(1-整租，2-合租)
     */
    private Integer rent_type;
    /**
     * 租赁方式名称
     */
    private String rent_type_name;
    /**
     * 出租房源标志(普租/公寓)
     */
    private Integer rent_sign;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rent_sign_name;
    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer village_id;
    /**
     * 需求
     */
    private String demand;
    /**
     * 坐标(lat,lon)
     */
    private String location;
    /**
     * 房源面积
     */
    private Double house_area;
    /**
     * 朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private Integer forward_type;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 供热方式
     */
    private Integer heating_type;
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
     * 总楼层数
     */
    private Integer total_floor;
    /**
     * 楼盘3km地铁线ID
     */
    private Integer[] subway_line_id;
    /**
     * 楼盘3km地铁站ID
     */
    private Integer[] subway_station_id;
    /**
     * 附近地铁站
     */
    private HashMap nearby_subway;
    /**
     * 最近的地铁
     */
    private String nearest_subway;
    /**
     * 出租房源标签(1-采光好，2-地铁近，3-首次出租，4-独立阳台，5-独立卫生间，6-集中供暖，7-可注册办公)
     */
    private Integer[] rent_house_tags_id;
    /**
     * 出租房源标签名称
     */
    private String[] rent_house_tags_name;
    /**
     * 租金(元/月)
     */
    private Double rent_house_price;
    /**
     * 房源标题图片
     */
    private String house_title_img;
    /**
     * 房源图片
     */
    private List rent_house_img;
    /**
     * 付款方式(1-面议，2-押一付一，3-押一付二，4-押一付三，5-押二付一，6-押二付二，7-押二付三，8-半年付，9-年付)
     */
    private Integer pay_mode;
    /**
     * 付款方式名称
     */
    private String pay_mode_name;
    /**
     * 配套设施(字符串数组)
     */
    private String[] supporting_facilities;
    /**
     * 经纪机构
     */
    private String brokerage_agency;
    /**
     * 电话
     */
    private String phone;
    /**
     * 房源描述
     */
    private String house_desc;
    /**
     * 房源来源
     */
    private String data_source_name;
    /**
     * 房源来源标志
     */
    private Integer data_source_sign;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 更新时间
     */
    private String upString_time;
    /**
     * 发版时间
     */
    private String publish_time;
    /**
     * 发布状态
     */
    private Integer release_status;
    /**
     * 是否上架
     */
    private Integer is_del;
    /**
     * 是否是推荐
     */
    private Integer is_recommend;
    /**
     * 是否是置顶
     */
    private Integer is_top;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 置顶关键词
     */
    private String top_keyword;
    /**
     * 置顶时间
     */
    private String top_time;
    /**
     * 经纪人
     */
    private String estate_agent;
    /**
     * 整租户型
     */
    private Integer erent_layout;
    /**
     * 合租户型
     */
    private Integer jrent_layout;

}
