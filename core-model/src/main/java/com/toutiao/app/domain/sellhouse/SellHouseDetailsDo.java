package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class SellHouseDetailsDo {
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
//    @ChangeName("buildingImages")
//    private String plotPhoto;
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
    @ChangeName("hasElevator")
    private Integer elevator;
    /**
     * 电梯
     */
    private String elevatorName;
    /**
     * 供暖
     */
    @ChangeName("heatingMode")
    private Integer houseHeating;
    /**
     * 供暖
     */
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
    @ChangeName("houseTitleImg")
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
    @ChangeName("totalPrice")
    private Double houseTotalPrices;

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date date = format.parse(updateTime);
            this.updateTime = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 建筑面积
     */
    @ChangeName("buildingArea")
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
     * 均价
     */
    private Double houseUnitCost;
    /**
     * 经纪人姓名
     */
    private String houseProxyName;
    /**
     * 经纪人电话
     */
    private String houseProxyPhone;
    /**
     * 经纪人头像
     */
    private String houseProxyPhoto;

    /**
     * 经纪人信息
     */
    private AgentBaseDo agentBaseDo;

    /**
     * 经纪人id
     */
    private Integer userId;

    /**
     * 是否收藏
     */
    private Boolean isFavorite = false;

    /**
     * 价格涨降标志
     */
    private String priceIncreaseDecline;
    /**
     * 价格涨降金额
     */
    private Double priceIncreaseDeclineAmount;

    private Integer housePhotoTitleTags;


    /**
     * 是否主力户型(0-否，1-是)
     */
    private Integer isMainLayout;

    /**
     * 是否成交户型(0-否，1-是)
     */
    private Integer isDealLayout;

    /**
     * 平均成交天数
     */
    private Integer avgDealCycle;

    /**
     * 是否价格洼地(0-否，1-是)
     */
    private Integer isLowPrice;

    /**
     * 是否降价房(0-否，1-降价房，2-涨价房)
     */
    private Integer isCutPrice;

    /**
     * 是否逢出必抢房(0-否，1-是)
     */
    private Integer isMustRob;

    /**
     * 是否同户型小区均价最低(0-否，1-是)
     */
    private Integer isLowest;

    /**
     * 是否新导入房源(0-否，1-是)
     */
    private Integer isNew;

    /**
     * 是否是top50小区房源(0-否，1-是)
     */
    private Integer isCommunityTopHouse;


    /**
     * 与商圈平均单价的绝对值差
     */
    private Double avgAbsoluteWithCommunity;

    /**
     * 与商圈平均单价的绝对值差
     */
    private Double avgAbsoluteWithBizcircle;

    /**
     * 与区县平均单价的绝对值差
     */
    private Double avgAbsoluteWithDistrict;

    /**
     * 与小区平均单价的相对值(百分比)
     */
    private Double avgRelativeWithCommunity;

    /**
     * 与商圈平均单价的相对值(百分比)
     */
    private Double avgRelativeWithBizcircle;

    /**
     * 与区县平均单价的相对值(百分比)
     */
    private Double avgRelativeWithDistrict;

    /**
     * 与小区平均总价的绝对值差
     */
    private Double totalAbsoluteWithCommunity;

    /**
     * 与商圈平均总价的绝对值差
     */
    private Double totalAbsoluteWithBizcircle;

    /**
     * 与区县平均总价的绝对值差
     */
    private Double totalAbsoluteWithDistrict;

    /**
     * 与小区平均总价的相对值(百分比)
     */
    private Double totalRelativeWithCommunity;

    /**
     * 与商圈平均总价的相对值(百分比)
     */
    private Double totalRelativeWithBizcircle;

    /**
     * 与区县平均总价的相对值(百分比)
     */
    private Double totalRelativeWithDistrict;

    /**
     * 涨降金额
     */
    private Double priceFloat;

    /**
     * 推荐标签id
     */
    private List recommendBuildTagsId;
    /**
     * 推荐标签名称
     */
    private List recommendBuildTagsName;

    /**
     * 近公园
     */
    private String nearPark;

    /**
     * 各个类型数量
     */
    private Map<Integer, Map<String, Integer>> typeCounts;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;
    /**
     * 名片
     */
    private String agentBusinessCard;

    /**
     * 经济公司营业执照
     */
    private String companyCard;

    /**
     * 楼盘专家
     */
    private String projExpertUserId;

    /**
     * 房源导入时间
     */
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    private Integer isDefaultImage = 0;

    /**
     * 公司图标
     */
    private String companyIcon;

    /**
     * 小区信息
     */
    private PlotDetailsDo plotDetailsDo;

    /**
     * 初始价格
     */
    private Double initialPrice;

    /**
     * 降价房排名表述
     */
    private String houseCutLabel;

    /**
     * 捡漏房排名表述
     */
    private String houseLowerLabel;

    /**
     * 抢手房排名表述
     */
    private String houseRobLabel;

    @ApiModelProperty(value = "房源排行榜标签列表", name = "houseRankLableList")
    private List<HouseRankLable> houseRankLableList;

    @ApiModelProperty(value = "房源Tags标签列表", name = "houseSubjectList")
    private List<HouseSubject> houseSubjectList;
}
