package com.toutiao.app.domain.newhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class UserFavoriteConditionDoQuery extends QueryDo {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 区域id
     */
    private String[] districtIds;
    /**
     * 起始价格
     */
    private Double beginPrice;
    /**
     * 结束价格
     */
    private Double endPrice;
    /**
     * 户型id
     */
    private String[] layoutId;
    /**
     * 城市
     */
    private String city;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 是否有预设条件(0:无,1:有 默认0)
     */
    private Integer flag ;
}
