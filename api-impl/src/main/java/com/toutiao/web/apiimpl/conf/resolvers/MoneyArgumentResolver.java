package com.toutiao.web.apiimpl.conf.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.toutiao.web.common.commonmodel.Money;
/**
 * zhangjinglei 2017/9/8 下午6:17
 * 处理请求时候
 */
public class MoneyArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameterType()==Money.class){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String value = nativeWebRequest.getParameter(methodParameter.getParameterName());
        RequestParam parameterAnnotation = methodParameter.getParameterAnnotation(RequestParam.class);

        if (value == null) {
            if (parameterAnnotation!=null && parameterAnnotation.required()) {
                throw new Exception("parameter [" + methodParameter.getParameterName() + "] is required.");
            }
            return new Money(value);
        }
        return null;
    }
}
