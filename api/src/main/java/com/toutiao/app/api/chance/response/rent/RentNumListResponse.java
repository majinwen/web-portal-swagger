package com.toutiao.app.api.chance.response.rent;

import lombok.Data;

import java.util.List;

@Data
public class RentNumListResponse {
    /**
     * 房源信息
     */
    private List<RentNumResponse> rentNum;
    /**
     * 总数
     */
    private Integer totalCount;
}
