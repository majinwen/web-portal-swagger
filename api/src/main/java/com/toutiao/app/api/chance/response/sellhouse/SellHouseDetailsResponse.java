package com.toutiao.app.api.chance.response.sellhouse;


import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;
import java.util.Map;

@Data
public class SellHouseDetailsResponse {

    /**
     * 二手房房源id
     */
    private String houseId;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;
    /**
     * 卫
     */
    private Integer toilet;

    /**
     * 阳台
     */
    private Integer balcony;
    /**
     * 厨
     */
    private Integer kitchen;
    /**
     * 区域名称
     */
    @ChangeName("districtName")
    private String area;
    /**
     * 区域id
     */
    @ChangeName("districtId")
    private Integer areaId;
    /**
     * 商圈id
     */
    @ChangeName("areaId")
    private Integer houseBusinessNameId;
    /**
     * 商圈名称
     */
    @ChangeName("areaName")
    private String houseBusinessName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    @ChangeName("buildingId")
    private Integer newcode;
    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;
    /**
     * 小区图片
     */
    @ChangeName("buildingImages")
    private String plotPhoto;
    /**
     * 地铁站id
     */
    private String[] subwayStationId;
    /**
     * 地铁线id
     */
    private Integer[] subwayLineId;
    /**
     * 地铁线/站 距离
     */
    private Map<String, String> subwayDistince;
    /**
     * 是否推荐
     */
    private Integer isRecommend;
    /**
     * 建成年代
     */
    @ChangeName("buildYears")
    private String year;
    /**
     * 房源信息来源（0-未知来源，1-编辑录入，2-我爱我家导入，3-中原地产导入）
     */
    @ChangeName("houseSourceId")
    private Integer source;
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
     * 电梯(有 无)
     */
    @ChangeName("hasElevatorId")
    private Integer elevator;
    /**
     * 电梯
     */
    @ChangeName("hasElevator")
    private String elevatorName;
    /**
     *供暖
     */
    @ChangeName("heatingModeId")
    private Integer houseHeating;
    /**
     *供暖
     */
    @ChangeName("heatingMode")
    private String houseHeatingName;
    /**
     * 交通状况（最近地铁信息）
     */
    @ChangeName("nearBySubwayDesc")
    private String traffic;
    /**
     * 建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private String[] buildCategory;
    /**
     * 建筑类别名称：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private String buildCategoryName;
    /**
     * 装修规则名称(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)
     */
    private String fitmentName;
    /**
     * 房源小区地理坐标
     */
    @ChangeName("location")
    private String housePlotLocation;
    /**
     * 二手房房源标题图
     */
    private String housePhotoTitle;
    /**
     * 标签(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ChangeName("tagsId")
    private Integer[] tags;
    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ChangeName("tags")
    private String[] tagsName;
    /**
     * 总的楼层数
     */
    private Integer totalFloor;
    /**
     * 房源所在层数(地下室填负数)
     */
    @ChangeName("floor")
    private Integer floorNo;
    /**
     * 房源照片
     */
    private String[] housePhoto;
    /**
     * 房源首付
     */
    private Double housingDeposit;
    /**
     * 状态(0-未发布/1-已发布)
     */
    @ChangeName("releaseStatus")
    private Short status;
    /**
     * 房源描述
     */
    private String houseDesc;
    /**
     * 发布公司
     */
    @ChangeName("agentCompany")
    private String ofCompany;
    /**
     * 房源分级，0-默认，1-最好，2-次之，3-再次
     */
    private Integer houseLevel;
    /**
     * 房源所在层（高层/低层/中层）
     */
    @ChangeName("floorName")
    private String floor;
    /**
     * 朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ChangeName("forwardId")
    private Integer forward;
    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ChangeName("forward")
    private String forwardName;
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
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 建筑面积
     */
    private Double buildArea;
    /**
     * 使用面积
     */
    private Double liveArea;
    /**
     * 月供
     */
    private Double houseMonthPayment;
    /**
     * 是否删除(0-未删除/1-已删除)
     */
    private Integer isDel;
    /**
     * 房源总数
     */
    private Integer totalNum;
    /**
     * 是否认领
     */
    private Integer isClaim;
    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String parkRadio;
    /**
     * 经纪人姓名
     */
    @ChangeName("agentName")
    private String houseProxyName;
    /**
     * 经纪人电话
     */
    @ChangeName("displayPhone")
    private String houseProxyPhone;
    /**
     * 经纪人头像
     */
    @ChangeName("headPhoto")
    private String houseProxyPhoto;
    /**
     * 均价
     */
    @ChangeName("avgPrice")
    private Double houseUnitCost;

    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    /**
     * 是否收藏
     */
    private Boolean isFavorite;
    /**
     * 价格涨降标志
     */
    private String priceIncreaseDecline;
    /**
     * 价格涨降金额
     */
    private Double priceIncreaseDeclineAmount;

}
