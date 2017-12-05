package com.toutiao.web.common.constant.resultcode;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-28 下午2:24 星期四
 * @Version : v1.0
 **/
@Data
public class RC {
    /**
     * 信息码
     */
    private final String infoCode;

    /**
     * 模块码
     */
    private final String moduleCode;

    /**
     * 系统码
     */
    private final String systemCode;

    /**
     * 信息描述
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param systemCode 系统码
     * @param moduleCode 模块码
     * @param infoCode   信息码
     * @param message    信息描述
     */
    public RC(String systemCode, String moduleCode, String infoCode, String message) {
        this.systemCode = systemCode;
        this.moduleCode = moduleCode;
        this.infoCode = infoCode;
        this.message = message;
    }
}
