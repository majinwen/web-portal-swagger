package com.toutiao.web.apiimpl.impl;

import com.toutiao.web.common.assertUtils.ValidationResult;
import com.toutiao.web.common.assertUtils.ValidationUtils;
import com.toutiao.web.common.constant.resultcode.CommonResultCode;
import com.toutiao.web.common.exceptions.NashRequestException;
import org.springframework.util.ObjectUtils;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-28 下午6:42 星期四
 * @Version : v1.0
 **/
public abstract class BaseImplService {
    /**
     * 参数校验(基于validator api)
     */
    protected void checkQueryData(Object param) {

        try {
            if (ObjectUtils.isEmpty(param)) {
                throw new NashRequestException(CommonResultCode.COMM_ARG_ILLEGAL.getInfoCode(), "请求参数为空.");
            }
            ValidationResult result = ValidationUtils.validateEntity(param);
            if (result.isHasErrors()) {
                throw new NashRequestException(CommonResultCode.COMM_ARG_ILLEGAL.getInfoCode(), result.getMsg());
            }
        }catch (Exception e){
            throw new NashRequestException(CommonResultCode.COMM_ARG_ILLEGAL.getInfoCode(), e.getMessage());
        }

    }
}
