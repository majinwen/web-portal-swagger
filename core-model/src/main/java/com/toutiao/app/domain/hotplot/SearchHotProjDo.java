package com.toutiao.app.domain.hotplot;

import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-22
 * Time:   17:45
 * Theme:
 */
@Data
public class SearchHotProjDo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 小区id
     */
    private Integer newcode;

    /**
     * 小区名称
     */
    private String projname;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 时间
     */
    private Date time;

    /**
     * 小区类型  1表示新房  2表示二手房  3表示租房
     */
    private Integer houseType;

}
