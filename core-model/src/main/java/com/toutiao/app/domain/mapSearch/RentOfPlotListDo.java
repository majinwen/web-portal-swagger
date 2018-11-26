package com.toutiao.app.domain.mapSearch;

import com.toutiao.app.domain.rent.RentDetailsFewDo;
import lombok.Data;

import java.util.List;

@Data
public class RentOfPlotListDo {
    /**
     * 租房列表
     */
    private List<RentDetailsFewDo> data;
    /**
     * 附近的地铁线
     */
    private String nearSubwayLine;
    /**
     * 总套数
     */
    private Integer totalNum;
}
