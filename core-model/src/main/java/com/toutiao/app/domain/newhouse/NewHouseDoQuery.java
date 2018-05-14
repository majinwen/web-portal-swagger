package com.toutiao.app.domain.newhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class NewHouseDoQuery extends QueryDo {


    /**
     * 销售状态
     */
    private  Integer [] saleStatusId;

}
