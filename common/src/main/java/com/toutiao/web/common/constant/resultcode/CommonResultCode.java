package com.toutiao.web.common.constant.resultcode;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-28 下午2:19 星期四
 * @Version : v1.0
 **/
public interface CommonResultCode {
    // 用于便捷创建结果集对象，公共结果集没有 系统码 及 模块码
    RCFactory RF = new RCFactory("", "");

    // 公共结果码定义
    RC SUCCESS          = RF.RC("200", "成功");
    RC COMM_ARG_ILLEGAL = RF.RC("001", "入参验证失败");
    RC COMM_IDEMPOTENT  = RF.RC("002", "操作幂等");

    // 异常，非未知
    RC COMM_EXCEPTION = RF.RC("-99", "服务异常");
    // 因历史原因，未知异常使用此码
    RC COMM_UNKNOWN_EXCEPTION = RF.RC("-100", "未知异常");

    RC COMM_USER_OWNER_EXIST = RF.RC("1000", "该个人业主已存在，已为你开放编辑权限!");
    RC COMM_USER_OWNER_NO_EXIST = RF.RC("1001", "该个人业主不存在!");

    RC COMM_USER_LESSEE_EXIST = RF.RC("2001", "该个人租户已存在，已为你开放编辑权限!");
    RC COMM_USER_LESSEE_NO_EXIST = RF.RC("2002", "该个人租户不存在!");

    RC COMM_USER_CHANNEL_EXIST = RF.RC("3001", "该个人渠道已存在!");
    RC COMM_USER_CHANNEL_NO_EXIST = RF.RC("3002", "该个人渠道已不存在!");

    RC COMM_COMPANY_OWNER_EXIST = RF.RC("4001", "该公司业主已存在, 已为你开发编辑权限!");
    RC COMM_COMPANY_OWNER_NO_EXIST = RF.RC("4002", "该公司业主已不存在!");

    RC COMM_COMPANY_LESSEE_EXIST = RF.RC("5001", "该公司租户已存在，已为你开放编辑权限!");
    RC COMM_COMPANY_LESSEE_NO_EXIST = RF.RC("5002", "该公司租户已不存在!");

    RC COMM_COMPANY_CHANNEL_EXIST = RF.RC("6001", "该公司渠道已存在!");
    RC COMM_COMPANY_CHANNEL_NO_EXIST = RF.RC("6002", "该公司渠道已不存在!");

    RC COMM_EMPLOYEE_EXIST = RF.RC("7001", "该员工已存在!");
    RC COMM_EMPLOYEE_NO_EXIST = RF.RC("7002", "该员工不存在!");

    RC COMM_PHONE_IS_NULL = RF.RC("8002", "该手机号为空!");
    RC COMM_PHONE_EXIST = RF.RC("8001", "该手机号已存在,无法更改!");
    RC COMM_NAME_EXIST = RF.RC("8003", "该名称已存在,无法更改!");
    RC COMM_CUSTOM_NO_EXIST = RF.RC("8004", "该客户不存在!");

    RC COMM_CUSTOM_EXIST_EXCEPTION = RF.RC("-999", "该客户已经存在!");
}
