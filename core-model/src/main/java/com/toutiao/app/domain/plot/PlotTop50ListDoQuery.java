package com.toutiao.app.domain.plot;
import lombok.Data;

@Data
public class PlotTop50ListDoQuery  {


    /**
     * 区域id
     */
    private Integer districtId;

    /**
     * 页码
     */

    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize=10;
}
