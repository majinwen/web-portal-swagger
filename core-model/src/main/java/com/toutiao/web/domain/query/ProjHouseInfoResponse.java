package com.toutiao.web.domain.query;

import lombok.Data;

import java.util.Map;

@Data
public class ProjHouseInfoResponse {

    /* * 房源ID
 */
    private String houseId;

    /**
     * 房源标题
     */
    private String houseTitle;


    /**
     * 建筑面积，在使用面积的基础上加上了墙体所占用的面积(房源面积)
     */
    private Double buildArea;

    /**
     * 使用面积，户主真正使用的面积，住宅买卖中一般不采用使用面积来计算价格
     */
    private Double liveArea;

    /**
     * 装修(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)
     */
    private Integer fitment;

    private String fitmentName;

    /**
     * 室 数字
     */
    private Integer room;


    /**
     * 厅
     */
    private Integer hall;

    /**
     * 厨
     */
    private Integer kitchen;

    /**
     * 卫
     */
    private Integer toilet;

    /**
     * 阳台
     */
    private Integer balcony;

    /**
     * 朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    private Integer forward;

    private String forwardName;

    // 房源总价
    private Double houseTotalPrices;


    // 房源单价(52365元/㎡)
    private Double houseUnitCost;

    /**
     * 价格单位
     */
    private String priceUnit;

    /**
     * 标签(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private Integer[] tags;

    private String[] tagsName;

    /**
     * 是否推荐房源，0-否，1-是
     */
    private Integer isRecommend;

    /**
     * 发布公司
     */
    private String ofCompany;


    /**
     * 房源所在层（高层/低层/中层）
     */
    private String floor;

    /**
     * 总的楼层数
     */
    private Integer totalFloor;

    /**
     * 房源所在层数(地下室填负数)
     */
    private Integer floorNo;


    /**
     * 建成年份 "year": "2012" 楼龄
     */
    private String year;


    /**
     * 产权性质(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    private Integer propertyRight;

    private String propertyRightName;

    /**
     * 房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     * （ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    private Integer houseType;

    private String houseTypeName;

    /**
     * 房源信息来源（0-未知来源，1-编辑录入，2-我爱我家导入，3-中原地产导入）
     */
    private Integer source;

    private String sourceName;

    /**
     * 推荐理由
     */
    private String recommendReason;


    // 房源照片
    private String[] housePhoto;


    //房源首付 （后台提供）
    private Double housingDeposit;

    //房源月供
    private Double houseMonthPayment;

    /**
     * 交通状况
     */
    private String traffic;


    /**
     * 是否删除(0-未删除/1-已删除)
     */
    private int isDel;


    /**
     * 更新时间 "updateTime": 1513310890506,
     */
    private String updateTime;


    /**
     * 建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private String buildCategory;

    private String buildCategoryName;


    /**
     * 建筑形式1：低层，2：多层，3：小高层，4：高层，5：超高层
     */
    private String buildForm;

    private String buildFormName;


    /**
     * 住宅-房屋结构（平层、错层、跃层、复式、开间） 别墅-厅结构（平层、挑高）
     */
    private String houseStructure;

    private String houseStructureName;


    /**
     * 状态(0-未发布/1-已发布)
     */
    private Short status;

    /**
     * 房源分级，0-默认，1-最好，2-次之，3-再次
     */
    private Integer houseLevel;


    /**
     * 房源描述
     */
    private String houseDesc;

    // 经纪人头像
    private String houseProxyPhoto;

    // 经纪人姓名
    private String houseProxyName;

    // 经纪人电话号
    private String houseProxyPhone;

    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer newcode;

    //小区名称
    private String plotName;


    // 商圈id
    private Integer houseBusinessNameId;

    // 区域id
    private Integer areaId;


    // 商圈名称
    private String houseBusinessName;

    // 区域名称
    private String area;

    // 地铁线id
    private String[] subwayLineId;


    // 地铁站id
    private String[] subwayStationId;

    //距离
    private Map<String, String> subwayDistince;


    //版本控制
    private Integer version;


    // 房源小区地理坐标
    private String housePlotLocation;


    //小区照片
    private String plotPhoto;

    //小区描述

    private String plotdesc;

    //查询距离使用的key
    private String key;


    /**
     * 维度
     */
    private double lat;
    /**
     * 经度
     */
    private double lon;

    //电梯(有 无)
    private String elevator;

    private String elevatorName;

    //供暖
    private Integer houseHeating;
    //小区坐标
    private String location;
    /**
     * 房源标题图片
     */
    private String housePhotoTitle;

    private String housetToPlotDistance;

    private Long total;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 认领后房源id
     */
    private String claimHouseId ;

    private String claimHouseTitle;

    private String claimHousePhotoTitle;

    private String[] claimTagsName;

    private Integer sortingScore;

    private Integer is_claim;

    private Integer userId;
    private String agentBusinessCard;
    private String companyCard;
}
