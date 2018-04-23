package com.toutiao.app.service.sys;

import com.toutiao.web.common.restmodel.InvokeResult;

/**
 * 短信接口
 */
public interface ShortMessageService {



    InvokeResult sendVerifyCode(String phone);
}
