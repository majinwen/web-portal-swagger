package com.toutiao.web.domain.advertisement;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class SellHouseAgentDo {

    @JSONField(name = "ad_sort")
    private Integer adSort;

    @JSONField(name = "agent_headphoto")
    private String agentHeadphoto;

    @JSONField(name = "agent_id")
    private Integer agentId;

    @JSONField(name = "corp_house_id")
    private Integer corpHouseId;

    @JSONField(name = "house_id")
    private Integer houseId;

    @JSONField(name = "is_recommend")
    private Integer isRecommend;



}
