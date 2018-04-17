package com.toutiao.app.api.chance.response.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseLayoutCountDo;
import lombok.Data;

import java.util.List;

@Data
public class NewHouseLayoutCountResponse {

    /**
     * 户型数量
     */
    private Long totalCount;
    /**
     * 户型
     */
    private List<NewHouseLayoutCountDo> rooms;

}
