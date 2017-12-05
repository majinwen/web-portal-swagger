package com.toutiao.web.apiimpl.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * zhangjinglei 2017/8/31 下午2:38
 *
 * 设置异步任务的线程池
 * 异步任务：拥有 @Async 的method及为异步任务
 */
@Configuration
@EnableAsync
public class TaskExecutePool implements AsyncConfigurer {
    Logger logger = LoggerFactory.getLogger(TaskExecutePool.class);
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(0);
        executor.setKeepAliveSeconds(3000);
        executor.setThreadNamePrefix("MyExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return new AsyncUncaughtExceptionHandler() {

            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                logger.error("异步任务异常",arg0);
//                arg0.printStackTrace();
//                logger.error("=========================="+arg0.getMessage()+"=======================", arg0);
//                log.error("exception method:"+arg1.getName());
            }
        };
    }
}