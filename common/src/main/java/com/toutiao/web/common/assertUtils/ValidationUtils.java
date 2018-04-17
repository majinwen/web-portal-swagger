package com.toutiao.web.common.assertUtils;


import com.toutiao.web.common.exceptions.NashRequestException;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * Created by jyl on 17/9/7.
 */
public class ValidationUtils {

    private static Validator validator =  CommonValidatorFactory.getInstance();

    /**
     * requestModel校验工具类
     * @param obj  requestModel
     * @param code 错误代码
     * @param msg  错误描述信息(可不传)
     * @throws NashRequestException
     */
    public static void checkValidate(Object obj,Integer code,String... msg) throws NashRequestException {
        ValidationResult result = validateEntity(obj);
        if(msg==null || msg.length==0){
            AssertUtils.assertTrue(result.isHasErrors(),code,result.getMsg());
        }else{
            AssertUtils.assertTrue(result.isHasErrors(),code,msg[0]);
        }
    }
    public static void checkValidate(Object obj,Integer code,Class group,String... msg) throws NashRequestException {
        ValidationResult result = validateEntity(obj,group);
        if(msg==null || msg.length==0){
            AssertUtils.assertTrue(result.isHasErrors(),code,result.getMsg());
        }else{
            AssertUtils.assertTrue(result.isHasErrors(),code,msg[0]);
        }
    }

    public static <T> ValidationResult validateEntity(T obj){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj,Default.class);
        if(CollectionUtils.isNotEmpty(set) ){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<String,String>();
            for(ConstraintViolation<T> cv : set){
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
            result.setMsg(errorMsg.toString());
        }
        return result;
    }

    public static <T> ValidationResult validateEntity(T obj,Class group){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj,group);
        if( CollectionUtils.isNotEmpty(set) ){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<String,String>();
            for(ConstraintViolation<T> cv : set){
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
            result.setMsg(errorMsg.toString());
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj,String propertyName){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
        if( CollectionUtils.isNotEmpty(set) ){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<String,String>();
            for(ConstraintViolation<T> cv : set){
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }


}


