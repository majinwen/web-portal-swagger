package com.toutiao.web.annotation.config;

import com.toutiao.web.common.commonmodel.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * zhangjinglei 2017/9/5 上午10:11
 */
@Aspect
@Component
public class InsertUpdateInterceptor {
    @Pointcut("execution(* com.toutiao.web.dao..*.*(..))")
    private void daoAspect(){}
    //请求method前打印内容
    @Before(value = "daoAspect()")
    public void methodBefore(JoinPoint point)throws NoSuchMethodException, SecurityException,IllegalArgumentException{
        Object[] args = point.getArgs();
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        Method m = classz[0].getMethod(method, parameterTypes);
        if (args != null && args.length != 0) {
            for (Object object : args) {
                if (object instanceof BaseEntity) {
                    BaseEntity baseDomain = (BaseEntity) object;
                    if(method.toLowerCase().contains("insert")){
                        if(StringUtils.isBlank(baseDomain.getCreator()) || baseDomain.getCreateAt()==null){
                            throw new IllegalArgumentException(classz[0].getName()+"."+method+"缺少赋值，insert的时候必须填：creator,createAt,modifier,modifyAt");
                        }
                        else {
                            baseDomain.setModifier(baseDomain.getCreator());
                            baseDomain.setModifyAt(baseDomain.getCreateAt());
                        }
                    }
                    else if(method.toLowerCase().contains("update")){
                        if(StringUtils.isBlank(baseDomain.getModifier()) || baseDomain.getModifyAt()==null){
                            throw new IllegalArgumentException(classz[0].getName()+"."+method+"缺少赋值，update的时候必须填：modifier,modifyAt");
                        }
                    }

                }
            }
        }

    }
//    //在方法执行完结后打印返回内容
//    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
//    public void methodAfterReturing(Object o ){
//        log.info("--------------返回内容----------------");
//        log.info("Response内容:"+gson.toJson(o));
//        log.info("--------------返回内容----------------");
//    }
}
