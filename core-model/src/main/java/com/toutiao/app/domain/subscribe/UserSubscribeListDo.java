package com.toutiao.app.domain.subscribe;

import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDomain;
import lombok.Data;

import java.util.Date;

@Data
public class UserSubscribeListDo {
    private Integer id;

    private Integer userId;

    private String userSubscribeMap;

    private Date createTime;

    private Date updateTime;

    private Long newCount;

    private UserSubscribeDetailDo userSubscribeDetail;

    private Object houseList;
}
