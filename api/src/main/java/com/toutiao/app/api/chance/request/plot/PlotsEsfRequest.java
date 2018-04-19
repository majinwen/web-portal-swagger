package com.toutiao.app.api.chance.request.plot;


import com.toutiao.web.common.assertUtils.First;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotsEsfRequest {

    @NotNull(message = "缺少小区Id")
    private Integer plotsId;

    @NotNull(groups = {First.class},message = "缺少房源户型")
    private Integer room;
}
