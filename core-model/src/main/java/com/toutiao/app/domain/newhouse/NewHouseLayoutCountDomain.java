package com.toutiao.app.domain.newhouse;

import lombok.Data;

import java.util.List;

@Data
public class NewHouseLayoutCountDomain {

    /**
     * 户型数量
     */
    private Long totalCount;
    /**
     * 户型
     */
    private List<NewHouseLayoutCountDo> rooms;

}
