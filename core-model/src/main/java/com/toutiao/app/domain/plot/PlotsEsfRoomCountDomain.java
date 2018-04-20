package com.toutiao.app.domain.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotsEsfRoomCountDomain {

    /**
     * 户型总数量
     */
    private Long totalCount;
    /**
     * 户型
     */
    private List<PlotsEsfRoomCountDo> rooms;

}
