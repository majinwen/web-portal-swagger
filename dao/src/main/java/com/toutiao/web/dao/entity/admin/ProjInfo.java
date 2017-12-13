package com.toutiao.web.dao.entity.admin;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjInfo {

    /**
     * 新房/二手房
        0-新房，1-二手房
     */
    private Short nOrE;

    /**
     * 楼盘ID
     */
    private Integer newcode;

    /**
     * 楼盘名称
     */
    private String projname;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 业态，具体分类不详
     */
    private Short operastion;

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
     * 住宅类别（普通住宅/公寓/酒店式公寓/花园洋房等）
     */
    private String projType;

    /**
     * 房源特色
     */
    private String housefeature;

    /**
     * 建筑类别（板楼/塔楼/板塔结合/砖楼）
     */
    private String buildcategory;

    /**
     * 建筑形式（低层/多层/小高层/高层/超高层）
     */
    private String buildingform;

    /**
     * 项目特色
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
     * 环线位置
     */
    private String roundstation;

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
    private Long totaldoor;

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
    private Double carrentprice;

    /**
     * 停车位售价
     */
    private Double carrentpricetype;

    /**
     * 物业管理费
     */
    private Double propertyfee;

    /**
     * 物业管理费价格单位
     */
    private String propfeetype;

    /**
     * 物业管理费附加信息
     */
    private String propertyaddition;

    /**
     * 装修状况
     */
    private String fixstatus;

    /**
     * 建材设备
     */
    private String equipment;

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
    @JSONField(format = "yyyy-MM-dd")
    private Date finishdate;

    /**
     * 开盘时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date saledate;

    /**
     * 开盘时间描述
     */
    private String saledateS;

    /**
     * 入住时间
     */
    private Date livindate;

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
     * 按揭银行
     */
    private String mortgageBank;

    /**
     * 销售状态
 0：售完.
 1：在售.
 2：不在售.
 3：出租.
 4：租售
 
     */
    private Short saling;

    /**
     * 销售阶段
 0:期房.1:现房.2:尾房.3:二手房
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
     * 物业顾问公司
     */
    private String propertyadviser;

    /**
     * 建筑及园林设计单位
     */
    private String landscape;

    /**
     * 承建商
     */
    private String construct;

    /**
     * 代理商
     */
    private String agent;

    /**
     * 景观设计单位
     */
    private String sightdesign;

    /**
     * 整合推广单位
     */
    private String conextend;

    /**
     * 平面媒体广告单位
     */
    private String media;

    /**
     * 审批状态（
 0-不通过，1-通过，2-错误）
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
     * 删除标志位(0-否)
     */
    private Short isDel;

    /**
     * 删除时间
     */
    private Date deldate;

    /**
     * 删除说明
     */
    private String delreason;

    /**
     * 400电话
     */
    private String tel400;

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
     * 二手房区县
     */
    private String esfDistrict;

    /**
     * 二手房商圈
     */
    private String esfComarea;

    /**
     * 名称全拼
     */
    private String pinyinName;

    /**
     * 名称拼音首字母
     */
    private String pinyinInitials;

    /**
     * 二手房地址
     */
    private String esfAddress;

    /**
     * 二手房业态
     */
    private String esfOperastion;

    /**
     * 二手房是否展示（Y/N）
 0-否，1-是
     */
    private Short esfShow;

    /**
     * 二手房审核状态（Y/N）
     */
    private String esfIsApprove;

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
}