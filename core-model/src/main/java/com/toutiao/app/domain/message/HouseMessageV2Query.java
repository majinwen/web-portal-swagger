package com.toutiao.app.domain.message;

import lombok.Data;

/**
 * @author : zym
 * @date : 2018/12/6 10:31
 * @desc :
 */
@Data
public class HouseMessageV2Query {

    private Integer type;

    private Integer lastMessageId;
}
