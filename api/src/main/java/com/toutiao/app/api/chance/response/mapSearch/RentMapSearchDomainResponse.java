package com.toutiao.app.api.chance.response.mapSearch;

import lombok.Data;

import java.util.List;

@Data
public class RentMapSearchDomainResponse {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回结果
     */
    private List<RentMapSearchDoResponse> data;
}
