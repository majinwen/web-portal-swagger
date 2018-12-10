package com.toutiao.app.domain.mapSearch;

import com.toutiao.app.domain.rent.RentDetailsFewDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RentOfPlotListDo {
    /**
     * 租房列表
     */
    private List<RentDetailsFewDo> data;

    /**
     * 社区名称
     */
    private String ploatName;

    /**
     * 社区ID
     */
    private Integer newcode;
    /**
     * 附近的地铁线
     */
    private String nearSubwayLine;
    /**
     * 总套数
     */
    private Integer totalNum;
}
