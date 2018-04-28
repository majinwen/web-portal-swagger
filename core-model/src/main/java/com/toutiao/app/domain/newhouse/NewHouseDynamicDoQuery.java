package com.toutiao.app.domain.newhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;


@Data
public class NewHouseDynamicDoQuery extends QueryDo{

    /**
     * 大楼id
     */
    private Integer newCode;

}
