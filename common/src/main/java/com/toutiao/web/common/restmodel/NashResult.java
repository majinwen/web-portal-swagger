package com.toutiao.web.common.restmodel;

import com.github.pagehelper.Page;
import com.toutiao.web.common.commonmodel.NashPaging;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * zhangjinglei 2017/8/31 上午11:26
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public class NashResult<T> implements Serializable {

    /**
     * 调用接口失败
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> NashResult<T> Fail(String msg){
        return NashResult.Fail("fail",msg);
    }

    public static <T> NashResult<T> Fail(String code,String msg){
        return Fail(code,msg,new Object());
    }

    public static <T> NashResult<T> Fail(String code,String msg,Object data){
        NashResult<T> tResultOK = new NashResult<>();
        tResultOK.setCode(code);
        tResultOK.setMsg(msg);
        tResultOK.setData(data);
        return tResultOK;
    }

    /**
     * NashResult
     * 如果 T对象是通过分页插件查询的数据
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> NashResult<T> build(T obj){
        NashResult<T> tResultOK = new NashResult<>();
        if (obj instanceof Page) {
            tResultOK.setData(NashPaging.build(obj));
        } else if(obj instanceof List){
            tResultOK.setData(NashPaging.noPagebuild(obj));
        } else {
            tResultOK.setData(obj);
        }
        return tResultOK;
    }


    /**状态码*/
    private String code="success";
    /**额外消息*/
    private String msg="";
    /**数据*/
    private Object data;
    public String getCode() {
        return code;
    }
    public void setCode(String status) {
        this.code = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
