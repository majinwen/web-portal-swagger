package com.toutiao.web.api.chance.response.subscribeListModel;

import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/15.
 */
@Data
public class UserSubscribeListDoList {
    List<UserSubscribeListDo> UserSubscribeListDoData;
    Integer total;
}
