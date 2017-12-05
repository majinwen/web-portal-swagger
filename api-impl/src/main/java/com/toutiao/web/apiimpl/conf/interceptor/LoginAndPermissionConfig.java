package com.toutiao.web.apiimpl.conf.interceptor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * zhangjinglei 2017/9/15 下午3:51
 */
@Data
@Component
public class LoginAndPermissionConfig {

    @Value("${crm.cookiename}")
    private String cookiename;
}
