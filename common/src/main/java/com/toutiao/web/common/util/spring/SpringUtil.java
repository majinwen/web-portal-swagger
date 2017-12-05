package com.toutiao.web.common.util.spring;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-28 下午5:48 星期四
 * @Version : v1.0
 **/
@Component
public class SpringUtil implements ApplicationContextAware {

    /**
     * 获取 ApplicationContext 对象
     * @return
     */
    public static final ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过代理对象获取真实的目标对象
     * 如果入参为非代理对象，则返回入参对象
     */
    public static final Object unwrapProxy(Object bean) throws Exception {
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            bean = advised.getTargetSource().getTarget();
        }
        return bean;
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        SpringUtil.applicationContext = applicationContext;
    }

}
