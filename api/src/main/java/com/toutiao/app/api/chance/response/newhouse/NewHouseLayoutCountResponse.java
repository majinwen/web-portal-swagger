package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

@Data
public class NewHouseLayoutCountResponse {

    /**
     * 户型
     */
    private Object room;

    /**
     * 户型数量
     */
    private Long count;

}
