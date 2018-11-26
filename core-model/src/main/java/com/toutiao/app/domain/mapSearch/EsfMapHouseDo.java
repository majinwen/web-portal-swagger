package com.toutiao.app.domain.mapSearch;

import com.toutiao.app.domain.sellhouse.HouseLable;
import com.toutiao.app.domain.sellhouse.HouseSubject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by CuiShihao on 2018/11/26
 */
@Data
public class EsfMapHouseDo {

    /**
     * 房源id
     */
    @ApiModelProperty(value = "二手房房源id", name = "houseId")
    private String houseId;

    /**
     * 房源标题
     */
    @ApiModelProperty(value = "房源标题", name = "houseTitle")
    private String houseTitle;

    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    /**
     * 厅
     */
    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;

    /**
     * 卫
     */
    @ApiModelProperty(value = "卫", name = "toilet")
    private Integer toilet;

    /**
     * 二手房房源图片
     */
    @ApiModelProperty(value = "二手房房源图片", name = "housePhoto")
    private String[] housePhoto;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ApiModelProperty(value = "标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)", name = "tagsName")
    private String[] tagsName;

    /**
     * 房源总价
     */
    @ApiModelProperty(value = "房源总价", name = "houseTotalPrices")
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积", name = "buildArea")
    private Double buildArea;

    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ApiModelProperty(value = "朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)", name = "forwardName")
    private String forwardName;

    /**
     * 均价
     */
    @ApiModelProperty(value = "均价", name = "houseUnitCost")
    private  Double houseUnitCost;

    @ApiModelProperty(value = "附近距离xxkm", name = "nearbyDistance")
    private String nearbyDistance;

    /**
     * 房源导入时间
     */
    @ApiModelProperty(value = "房源导入时间", name = "importTime")
    private String importTime;

    @ApiModelProperty(value = "标题图")
    private String housePhotoTitle;

    private Integer housePhotoTitleTags = -1;

    /**
     * 是否显示默认图片标志
     */
    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;

    private Integer userId;

    /**
     * 楼盘专家
     */
    @ApiModelProperty(value = "楼盘专家", name = "projExpertUserId")
    private String projExpertUserId;

    /**
     * 各个类型数量
     */
    @ApiModelProperty(value = "各个类型数量", name = "typeCounts")
    private Map<Integer,Map<String,Integer>> typeCounts;

    @ApiModelProperty(value = "公司图标", name = "companyIcon")
    private String companyIcon;

    @ApiModelProperty(value = "标签列表", name = "sellHouseLableList")
    private List<HouseLable> houseLableList;

    @ApiModelProperty(value = "专题列表", name = "sellHouseSubjectList")
    private List<HouseSubject> houseSubjectList;

    /**
     * 地铁到房源的距离
     */
    @ApiModelProperty(value = "地铁到房源的距离", name = "subwayDistince")
    private Map subwayDistince;

    /**
     * 地铁与房子之间的距离
     */
    @ApiModelProperty(value = "地铁与房子之间的距离", name = "subwayDistanceInfo")
    private  String  subwayDistanceInfo;
}
