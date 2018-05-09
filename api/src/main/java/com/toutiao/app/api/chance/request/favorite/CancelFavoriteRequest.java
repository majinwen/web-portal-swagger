package com.toutiao.app.api.chance.request.favorite;
import com.toutiao.web.common.assertUtils.Second;
import lombok.Data;
import net.sf.jsqlparser.statement.select.First;

import javax.validation.constraints.NotNull;

@Data
public class CancelFavoriteRequest {

    @NotNull(groups = {First.class},message = "缺少新房id")
    private Integer newHouseId;

    @NotNull(groups = {First.class,Second.class},message = "缺少用户id")
    private  Integer userId;

    @NotNull(groups = {Second.class},message ="缺少小区id")
    private Integer villageId;


}
