package com.toutiao.app.service.sys;

import com.toutiao.app.domain.activity.UserNewBuildingActivity;
import com.toutiao.web.common.restmodel.NashResult;

/**
 * 短信接口
 */
public interface ShortMessageService {



    NashResult sendVerifyCode(String phone);

    /**
     * 发送优惠活动短信信息
     * @param userPhone
     * @param userNewBuildingActivity
     * @return
     */
    NashResult sendSmsByActivity(String userPhone, UserNewBuildingActivity userNewBuildingActivity);

}
