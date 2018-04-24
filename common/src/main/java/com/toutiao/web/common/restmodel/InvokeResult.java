package com.toutiao.web.common.restmodel;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.toutiao.web.common.commonmodel.NashPaging;
import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.syserror.ShortMessageInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.SystemExceptionErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public class InvokeResult<T> implements Serializable{

    /**
     * 状态码
     * 0表示成功
     */
    private Integer code = 0;

    /**
     * 状态信息
     */
    private String msg = "";

    private Object data;

    public static <T> InvokeResult<T> Fail(Integer code, String msg){
        return Fail(code, msg, new Object());
    }

    public static <T> InvokeResult<T> Fail(Integer code, String msg, Object data){
        InvokeResult<T> tResultFail = new InvokeResult<>();
        tResultFail.setCode(code);
        tResultFail.setMsg(msg);
        tResultFail.setData(data);
        return tResultFail;
    }

    /**
     * 系统级错误
     * @param <T>
     * @return
     */
    public static <T> InvokeResult<T> Fail(SystemExceptionErrorCodeEnum systemErrorCodeEnum) {
        return Fail(systemErrorCodeEnum.getValue(), systemErrorCodeEnum.getDesc());
    }

    public static <T> InvokeResult<T> Fail(SystemExceptionErrorCodeEnum systemErrorCodeEnum, Object data) {
        return Fail(systemErrorCodeEnum.getValue(), systemErrorCodeEnum.getDesc(), data);
    }

    /**
     * 服务级错误
     * @param serviceErrorCodeEnum
     * @param <T>
     * @return
     */
    public static <T> InvokeResult<T> Fail(ShortMessageInterfaceErrorCodeEnum serviceErrorCodeEnum) {
        return Fail(serviceErrorCodeEnum.getValue(), serviceErrorCodeEnum.getDesc());
    }

    public static <T> InvokeResult<T> Fail(ShortMessageInterfaceErrorCodeEnum serviceErrorCodeEnum, Object data) {
        return Fail(serviceErrorCodeEnum.getValue(), serviceErrorCodeEnum.getDesc(), data);
    }

    /**
     * 自定义错误
     * @param exceptionEnum
     * @param <T>
     * @return
     */
    public static <T extends IntBaseType> InvokeResult Fail(T exceptionEnum){
        return Fail(exceptionEnum.getValue(),exceptionEnum.getDesc());
    }
    public static <T extends IntBaseType> InvokeResult Fail(T exceptionEnum,Object data){
        return Fail(exceptionEnum.getValue(),exceptionEnum.getDesc(),data);
    }

    /**
     * NashResult
     * 如果 T对象是通过分页插件查询的数据
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> InvokeResult<T> build(T obj){
        InvokeResult<T> tResultOK = new InvokeResult<>();
        if (obj instanceof Page) {
            tResultOK.setData(NashPaging.build(obj));
        } else if(obj instanceof List){
            tResultOK.setData(NashPaging.noPagebuild(obj));
        } else if(obj instanceof PageInfo){
            tResultOK.setData(NashPaging.pageInfobuild(obj));
        } else{
            tResultOK.setData(obj);
        }
        return tResultOK;
    }

}
