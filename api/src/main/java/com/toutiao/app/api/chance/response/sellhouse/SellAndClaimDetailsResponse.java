package com.toutiao.app.api.chance.response.sellhouse;

import com.toutiao.app.domain.sellhouse.AgentsBySellHouseDo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SellAndClaimDetailsResponse {
    /**
     * 二手房房源id
     */
    private Integer houseId;
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
    private String area;
    /**
     * 区域id
     */
    private Integer areaId;
    /**
     * 商圈id
     */
    private Integer houseBusinessNameId;
    /**
     * 商圈名称
     */
    private String houseBusinessName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer newcode;
    /**
     * 小区名称
     */
    private String plotName;
    /**
     * 小区图片
     */
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
    private String year;
    /**
     * 房源信息来源（0-未知来源，1-编辑录入，2-我爱我家导入，3-中原地产导入）
     */
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
    private Integer elevator;
    /**
     * 电梯
     */
    private String elevatorName;
    /**
     *供暖
     */
    private Integer houseHeating;
    /**
     * 交通状况（最近地铁信息）
     */
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
    private String housePlotLocation;
    /**
     * 二手房房源标题图
     */
    private String housePhotoTitle;
    /**
     * 标签(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private Integer[] tags;
    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private String[] tagsName;
    /**
     * 总的楼层数
     */
    private Integer totalFloor;
    /**
     * 房源所在层数(地下室填负数)
     */
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
    private String floor;
    /**
     * 朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    private Integer forward;
    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
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

//    /**
//     * 二手房附件好房
//     */
//    private List<NearBySellHousesDo> NearBySellHousesDo;
//    /**
//     * 二手房房源认领经纪人
//     */
//    private AgentsBySellHouseDo agentsBySellHouseDo;
    /**
     * 房源总数
     */
    private Integer totalNum;
    /**
     * 认领房源标签
     */
    private String[] claimTags;
    /**
     * 认领房源标题
     */
    private String claimHouseTitle;
    /**
     * 认领房源id
     */
    private String claimHouseId;
    /**
     * 认领房源标签名称
     */
    private String[] claimTagsName;
    /**
     * 认领房源图片
     */
    private String claimHousePhotoTitle;
}
