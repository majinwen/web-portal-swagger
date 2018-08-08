package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

@Data
public class RecommendEsf5Request extends BaseQueryRequest {
    /**
     * 区域
     */
    private Integer[] districtIds;

    /**
     * 排序字段
     */
    private String sortFile = "extraTagsCount";

    /**
     * 排序
     */
    private Integer sort = 1;
}
