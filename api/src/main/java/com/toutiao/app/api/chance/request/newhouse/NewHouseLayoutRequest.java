package com.toutiao.app.api.chance.request.newhouse;


import com.toutiao.web.common.assertUtils.First;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseLayoutRequest {


    @NotNull(message = "缺少新房Id")
    private Integer newCode;

    @NotNull(groups = {First.class},message = "缺少户型")
    private Integer room;


}
