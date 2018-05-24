package com.toutiao.app.api.chance.response.rent;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class RentNumResponse {
    /**
     * 出租房源标志(普租/公寓)
     */
//    @JSONField(name = "rentSignId")
    private Integer rentSign;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rentSignName;
    /**
     * 个数
     */
    private Integer num;

}
