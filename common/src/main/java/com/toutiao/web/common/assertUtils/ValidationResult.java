package com.toutiao.web.common.assertUtils;

import lombok.Data;

import java.util.Map;

/**
 * Created by jyl on 17/9/8.
 */
@Data
public class ValidationResult {

    //校验结果是否有错
    private boolean hasErrors;

    //校验错误信息
    private Map<String, String> errorMsg;

    private String msg;


    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "hasErrors=" + hasErrors +
                ", errorMsg=" + errorMsg +
                '}';
    }
}