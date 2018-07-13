package com.toutiao.app.domain.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotsThemeDomain {
    /**
     * 小区主题List
     */
    private List<PlotsThemeDo> data;

    /**
     * 查询总数
     */
    private long totalCount;
}
