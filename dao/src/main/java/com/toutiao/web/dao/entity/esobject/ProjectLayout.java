package com.toutiao.web.dao.entity.esobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjectLayout {
    /**
     * 序号
     */
    private Integer layoutId;

    /**
     * 楼盘ID
     */
    private Integer newcode;

    /**
     * 室
     */
    private Short room;

    /**
     * 厅
     */
    private Short hall;

    /**
     * 卫
     */
    private Short toilet;

    /**
     * 厨
     */
    private Short kitchen;

    /**
     * 建筑面积
     */
    private BigDecimal buildingArea;

    /**
     * 户型描述/户型解析
     */
    private String layoutDesc;

    /**
     * 居住面积
     */
    private BigDecimal livingArea;

    /**
     * 销售面积
     */
    private BigDecimal saleArea;

    /**
     * 户型标题
     */
    private String layoutTitle;

    /**
     * 标签
     */
    private String tag;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 参考均价
     */
    private BigDecimal referencePrice;

    /**
     * 参考总价
     */
    private BigDecimal referenceTotalPrice;

    /**
     * 是否删除(0-否，1-是)
     */
    private Short isDel;

    /**
     * 删除原因
     */
    private String deleteReason;

}