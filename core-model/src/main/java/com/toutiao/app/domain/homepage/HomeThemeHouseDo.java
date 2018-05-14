package com.toutiao.app.domain.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class HomeThemeHouseDo {
    /**
     * 房源id
     */
    private String houseId;
    /**
     * 房源标题图
     */
    private String housePhotoTitle;
    /**
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 几室
     */
    private Integer room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 房源面积
     */
    private Double buildArea;
    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;
    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 交通信息
     */
    @ChangeName("nearBySubwayDesc")
    private String traffic;
}
