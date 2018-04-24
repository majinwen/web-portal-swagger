package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotsEsfRoomCountDo;
import lombok.Data;

import java.util.List;

@Data
public class PlotsEsfRoomCountResponse {

    /**
     * 户型总数量
     */
    private Long totalCount;
    /**
     * 户型
     */
    private List<PlotsEsfRoomCountDo> rooms;

}
