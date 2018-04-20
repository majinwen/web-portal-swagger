package com.toutiao.app.domain.sellhouse;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class NearBySellHousesDo {


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
     * 二手房房源图片
     */
    private List<String> housePhoto;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private String[] tagsName;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    private String forwardName;

    /**
     * 当前房源距离附件小区的距离
     */
    private String housetToPlotDistance;

     /**
        * 附近地铁信息
     */
    private  String roundStation;


    /**
     *
     * 车位配比
     */
    private String parkRadio;

    /**
     * 建成年代
     */
    private String year;


    /**
     * 页码
     */
    private Integer pageNum = 1;


    /**
     *
     * 城市id
     */
    private Integer cityId;


    /**
     * y坐标
     */
    private Double lat;

    /**
     * x坐标
     */
    private Double lon;


    /**
     * 附近距离
     */
    private Integer distance;

}
