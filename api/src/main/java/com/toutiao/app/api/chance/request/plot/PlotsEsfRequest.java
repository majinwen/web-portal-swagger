package com.toutiao.app.api.chance.request.plot;


import com.toutiao.web.common.assertUtils.First;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotsEsfRequest {

    /**
     * 小区id
     */
    @NotNull(message = "缺少小区Id")
    private Integer plotId;

    /**
     * 户型
     */
    @NotNull(groups = {First.class},message = "缺少房源户型")
    private Integer room;

    /**
     * 页数
     */
    private Integer pageNum = 1;

    /**
     * 每页显示数量
     */
    private Integer pageSize = 10;
}
