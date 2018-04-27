package com.toutiao.app.api.chance.request.newhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;


@Data
public class NewHouseListRequest extends BaseQueryRequest {

    /**
     * 销售状态
     */
    private  Integer saleStatusId;


}
