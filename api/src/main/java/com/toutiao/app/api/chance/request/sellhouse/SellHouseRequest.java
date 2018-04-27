package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SellHouseRequest extends BaseQueryRequest {

    /**
     * 推荐房源查询标志
     */
    @NotEmpty(groups = {First.class},message = "推荐房源查询标志为空")
    private String uid;
    /**
     * 附近1,3,5km
     *
     */
    private String near ;

}
