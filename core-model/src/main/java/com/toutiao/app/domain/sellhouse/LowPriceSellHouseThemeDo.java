package com.toutiao.app.domain.sellhouse;

import com.toutiao.web.common.constant.city.CityEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CutPriceSellHouseThemeDo
 * @Author jiangweilong
 * @Date 2018/12/25 11:13 AM
 * @Description:
 **/

@Data
public class LowPriceSellHouseThemeDo {


    @ApiModelProperty(value = "房源id", name = "houseId")
    private String houseId;


    @ApiModelProperty(value = "小区名称", name = "plotName")
    private String plotName;

    @ApiModelProperty(value = "房源价格(单位:万)", name = "houseTotalPrices")
    private Double houseTotalPrices;

    @ApiModelProperty(value = "房源标题图片", name = "housePhotoTitle")
    private String housePhotoTitle;

    @ApiModelProperty(value = "室", name = "room")
    private Integer room;

    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;

    @ApiModelProperty(value = "房源面积(单位:平方米)", name = "buildArea")
    private Double buildArea;

    @ApiModelProperty(value = "上架时间", name = "importTime")
    private String importTime;

    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;

    @ApiModelProperty(value = "与小区平均单价的绝对值差", name = "avgAbsoluteWithCommunity")
    private Double avgAbsoluteWithCommunity;

    @ApiModelProperty(value = "与小区平均单价的相对值(百分比)", name = "avgRelativeWithCommunity")
    private Double avgRelativeWithCommunity;

    @ApiModelProperty(value = "与商圈平均单价的绝对值差", name = "avgAbsoluteWithBizcircle")
    private Double avgAbsoluteWithBizcircle;

    @ApiModelProperty(value = "与商圈平均单价的相对值(百分比)", name = "avgRelativeWithBizcircle")
    private Double avgRelativeWithBizcircle;

    @ApiModelProperty(value = "与区县平均单价的绝对值差", name = "avgAbsoluteWithDistrict")
    private Double avgAbsoluteWithDistrict;

    @ApiModelProperty(value = "与区县平均单价的相对值(百分比)", name = "avgRelativeWithDistrict")
    private Double avgRelativeWithDistrict;

    @ApiModelProperty(value = "捡漏房节省多少钱", name = "saveMoney")
    private Double saveMoney;

    @ApiModelProperty(value = "捡漏房判定标志位(1-同小区比较，2-同商圈比较)", name = "lowPriceFlag")
    private Integer lowPriceFlag;


    public void setAvgRelativeWithCommunity(Double avgRelativeWithCommunity) {
        this.avgRelativeWithCommunity = avgRelativeWithCommunity;
        if(null!=avgRelativeWithCommunity){
            this.avgRelativeWithCommunity = Math.abs(avgRelativeWithCommunity);
        }
    }

    public void setAvgAbsoluteWithCommunity(Double avgAbsoluteWithCommunity) {
        this.avgAbsoluteWithCommunity = avgAbsoluteWithCommunity;
        if(null!=avgAbsoluteWithCommunity){
            this.avgAbsoluteWithCommunity = Math.abs(avgAbsoluteWithCommunity);
        }
    }

    public void setAvgRelativeWithBizcircle(Double avgRelativeWithBizcircle) {
        this.avgRelativeWithBizcircle = avgRelativeWithBizcircle;
        if(null!=avgRelativeWithBizcircle){
            this.avgRelativeWithBizcircle = Math.abs(avgRelativeWithBizcircle);
        }
    }

    public void setAvgAbsoluteWithBizcircle(Double avgAbsoluteWithBizcircle) {
        this.avgAbsoluteWithBizcircle = avgAbsoluteWithBizcircle;
        if(null!=avgAbsoluteWithBizcircle){
            this.avgAbsoluteWithBizcircle = Math.abs(avgAbsoluteWithBizcircle);
        }
    }

    public void setAvgRelativeWithDistrict(Double avgRelativeWithDistrict) {
        this.avgRelativeWithDistrict = avgRelativeWithDistrict;
        if(null!=avgRelativeWithDistrict){
            this.avgRelativeWithDistrict = Math.abs(avgRelativeWithDistrict);
        }
    }

    public void setAvgAbsoluteWithDistrict(Double avgAbsoluteWithDistrict) {
        this.avgAbsoluteWithDistrict = avgAbsoluteWithDistrict;
        if(null!=avgAbsoluteWithDistrict){
            this.avgAbsoluteWithDistrict = Math.abs(avgAbsoluteWithDistrict);
        }
    }



}
