package com.toutiao.app.domain.invitation;

import lombok.Data;

import java.util.Date;

@Data
public class SuperInviteHistoryDoQuery {
    private Integer firstLevelCode;

    private Integer secondLevelCode;

    private Integer thirdLevelCode;

    private String equipmentNo;

    private Date createTime;

    private Integer pageNum;

    private Integer pageSize;
}
