package com.toutiao.web.dao.entity.esobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 新房--户型es映射对象
 *
 */
@Data
public class NewHouseLayout {

    /**
     * 建筑面积
     */
    private BigDecimal buildingArea;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 室
     */
    private Short room;
    /**
     * 厅
     */
    private Short hall;
    /**
     * 厨
     */
    private Short kitchen;
    /**
     * 卫
     */
    private Short toilet;
    /**
     * 是否主推
     */
    private Integer isRecommend;

    /**
     * 户型描述/户型解析
     */
    private String layoutDesc;
    /**
     * 序号
     */
    private Integer layoutId;
    /**
     * 户型标题
     */
    private String layoutTitle;
    /**
     * 居住面积
     */
    private BigDecimal livingArea;

    /**
     * 楼盘ID
     */
    private Integer buildingId;
    /**
     * 参考均价
     */
    private BigDecimal referencePrice;

    /**
     * 参考总价
     */
    private BigDecimal referenceTotalPrice;
    /**
     * 销售面积
     */
    private BigDecimal saleArea;
    /**
     * 标签
     */
    private String layoutTag;
    /**
     *  户型图
     */
    private String layoutImg;
    /**
     * 是否删除(0-否，1-是)
     */
    private Short isDel;
    /**
     * 更新人ID
     */
    private Integer updateTime;

}