package com.toutiao.web.domain.query;

import lombok.Data;

/**
 * @author WuShoulei on 2017/11/15
 */
@Data
public class HousingProjectQuery {

    /**
     * 项目标志位（0-新房/1-二手房）
     */
    private Integer projFlag = 0;

    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 项目名称
     */
    private String projName;

    /**
     * 搜索词
     */
    private String keyword;

    /**
     * 审批状态（
     0-不通过，1-通过，2-错误）
     */
    private Short isApprove;
}
