package com.toutiao.web.api.chance.request.invitation;

import lombok.Data;

import java.util.Date;

@Data
public class SuperInviteHistoryRequest{
    private Integer firstLevelCode;

    private Integer secondLevelCode;

    private Integer thirdLevelCode;

    private String equipmentNo;

    private String phone;

    private Date createTime;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
