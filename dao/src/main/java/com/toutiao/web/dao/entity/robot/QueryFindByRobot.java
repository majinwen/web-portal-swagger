package com.toutiao.web.dao.entity.robot;

import lombok.Data;

/**
 * 智能找房实体类
 */
@Data
public class QueryFindByRobot {
    private Integer newcode; //序号
    private Integer n_or_e;//新房/二手房(0-新房，1-二手房)
    private String projname;//楼盘名称(小区名称)
    private String nickname;//别名
    private String installment;//楼盘分期
    private String address;//地址
    private String address_info;//地址描述
    private String housefeature;//房源特色
    private String proj_feature;//项目特色
    private String province;//省
    private Integer city_id;//城市
    private Integer district_id;//区县
    private Integer area_id;//商圈
    private String round;//环线
    private String roundstation;//环线位置
    private String rounddirection;//环线方位
    private Integer community_id;//所属社区
    private String projdesc;//项目描述
    private Integer right_year;//产权年限
    private Double groundarea;//占地面积(平方米)
    private Double purposearea;//建筑面积(平方米)
    private Double dimension;//容积率(%)
    private Double virescencerate;//绿化率(%)
    private String buildingdes;//楼层状况
    private Integer totaldoor;//总户数
    private String parkdesc;//停车位描述
    private Integer parkspace;//停车位数量
    private Double car_rent_price;//停车位租价（年租）
    private Double car_sell_price;//停车位售价
    private Double propertyfee;//物业管理费
    private String propfeetype;//物业管理费价格单位
    private String propertyaddition;//物业管理费附加信息
    private String fixstatus;//装修状况
    private String equipment;//建材设备
    private String layout;//小区内部配套
    private String work_schedule;//工程进度
    private String startdate;//开工时间
    private String finishdate;//竣工时间
    private String saledate;//开盘时间
    private String saledate_s;//开盘时间描述
    private String livindate;//入住时间/交房时间
    private String livindate_s;//入住时间描述
    private String saletelphone;//售楼热线
    private String saleaddress;//售楼地址
    private Object salecard;//销售证
    private String mortgage_bank;//按揭银行
    private Integer saling;//销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
    private Integer sail_schedule;//销售阶段(0:期房.1:现房.2:尾房.3:二手房)
    private String developer;//开发商
    private String investor;//投资商
    private String propertymanage;//物业管理公司
    private String propertyadviser;//物业顾问公司
    private String landscape;//建筑及园林设计单位
    private String construct;//承建商
    private String agent;//代理商
    private String sightdesign;//景观设计单位
    private String conextend;//整合推广单位
    private String media;//平面媒体广告单位
    private Integer is_approve;//审批状态（0-不通过，1-通过，2-错误）（0-未发布，1-已发布）
    private String webaddress;//网站地址
    private String websaleaddress;//网上售楼中心地址
    private String complete_fraction;//完成度
    private Double coord_x;//x坐标
    private Double coord_y;//y坐标
    private String saling_optime;//楼盘置为售完的时间
    private String adminstauts_time;//移交二手房或者从二手房转回的时间
    private String create_time;//创建时间
    private String update_time;//修改时间
    private String deldate;//删除时间
    private String delreason;//删除说明
    private String tel400;//400电话
    private Double pricerate;//价格增长率
    private Double pricemax;//最高价格
    private Double pricemin;//最低价格
    private String esf_district;//二手房区县
    private String esf_comarea;//二手房商圈
    private String pinyin_name;//名称全拼
    private String pinyin_initials;//名称拼音首字母
    private String esf_address;//二手房地址
    private String esf_operastion;//二手房业态
    private Integer esf_show;//二手房是否展示(0-否，1-是)
    private String esf_is_approve;//二手房审核状态（Y/N）
    private String update_saledate_time;//开盘更新时间
    private String update_livindate_time;//入住更新时间
    private String right_desc;//产权年限（下拉：70，50,40）
    private String zip_code;//邮编
    private String property_tele;//物业电话
    private String property_address;//物业地址
    private Integer creator_id;//创建人ID
    private Integer updater_id;//更新人ID
    private Integer is_del;//删除标志位(0-否，1-是)
    private Double price;//平均单价-新房
    private Double total_price;//平均总价-新房
    private Double esf_price;//平均单价-二手房
    private Double esf_total_price;//平均总价-二手房
    private Integer[] heating_mode;//供暖方式(0-未知，1-集中供暖，2-自供暖）
    private String lift_door_radio;//梯户比
    private String park_radio;//车位配比
    //物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商）
    private Integer[] property_type;
    //住宅建筑形式(数组)：1-低层，2-多层，3-小高层，4-高层，5-超高层，
    //6-联排、7-独栋、8-双拼、9-叠拼、10-空中花园、11-空中别墅
    private Integer[] build_form;
    //建筑类别(数组)：1-板楼，2-塔楼，3-板塔结合
    private Integer[] build_category;
    //别墅建筑风格(数组)：1-中式、2-欧式、3-日式、4-美式、5-英式、6-澳式、
    //7-法式、8-西班牙式、9-东南亚式、10-地中海式、11-意大利式、12-现代
    private Integer[] villa_style;
    //新房平均单价单位
    private String price_unit;
    //新房平均总价单位
    private String total_price_unit;
    //二手房平均单价单位
    private String esf_price_unit;
    //二手房平均总价单位
    private String esf_total_price_unit;
    //住宅类别(数组)：1-普通住宅，2-公寓，3-酒店式公寓，4-花园洋房，5-商住楼，6-独栋别墅，
    //7-联排别墅，8-经济适用房，9-廉租房，10-公共租赁房，11-定向安置房，
    //12-两限商品房，13-自住型商品房，14-其他
    private Integer[] residential_category;
    //房天下楼盘Id
    private String soufun_newcode;
    //空气质量
    private String air_quality;
    //小区照片
    private String[] plotPhoto;
   //小区总价最低
    private Integer  plotTotalricesBegin;
    //小区总价最高
    private Integer  plotTotalricesEnd;

}
