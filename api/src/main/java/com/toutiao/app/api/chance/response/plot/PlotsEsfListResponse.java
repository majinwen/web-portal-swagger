package com.toutiao.app.api.chance.response.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.Map;

@Data
public class PlotsEsfListResponse {

    /**
     * 二手房房源id
     */
    private Integer houseId;

    /**
     * 室
     */
    private Integer room;

    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer newCode;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 建筑面积
     */
    private Double buildArea;
    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;
    /**
     * 标题图
     */
    private String housePhotoTitle;

}
