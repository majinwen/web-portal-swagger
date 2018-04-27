package com.toutiao.app.api.chance.request.newhouse;

import com.toutiao.app.api.chance.request.QueryRequest;
import lombok.Data;


@Data
public class NewHouseListRequest extends QueryRequest{

    /**
     * 销售状态
     */
    private  Integer saleStatusId;


}
