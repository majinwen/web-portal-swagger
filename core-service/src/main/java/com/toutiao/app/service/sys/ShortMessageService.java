package com.toutiao.app.service.sys;

import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;

/**
 * 短信接口
 */
public interface ShortMessageService {



    NashResult sendVerifyCode(String phone);
}
