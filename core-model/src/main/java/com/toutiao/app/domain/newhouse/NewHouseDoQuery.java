package com.toutiao.app.domain.newhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class NewHouseDoQuery extends QueryDo {


    /**
     * 销售状态
     */
    private  Integer [] saleStatusId;

    /**
     * 环线
     */
    private String ringRoad;

    /**
     * 楼盘特色
     */
    private String buildingFeature;

    /**
     * 开始总价
     */
    private double beginTotalPrice;

    /**
     * 结束总价
     */
    private double endTotalPrice;

    /**
     * 排序 0 默认  3均价从低到高  4均价从高到低
     */
    private String sort;

    /**
     * 均价
     */
    private double avgPrice;

    /**
     * 总价
     */
    private double totalPrice;

    /**
     * 区域名称
     */
    private String districtName;

}
