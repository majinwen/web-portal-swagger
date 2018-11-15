package com.toutiao.web.api.chance.response.subscribeListModel;

import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/15.
 */
@Data
public class UserSubscribeList {
    List<UserSubscribe>userSubscribeData;
    Integer total;
}
