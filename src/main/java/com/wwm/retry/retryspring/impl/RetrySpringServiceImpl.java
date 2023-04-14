package com.wwm.retry.retryspring.impl;

import com.wwm.retry.exception.BusinessException;
import com.wwm.retry.exception.ValidException;
import com.wwm.retry.retryspring.RetrySpringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetrySpringServiceImpl  implements RetrySpringService {
    /**
     * include 需要重试的异常
     * ValidException  不需要重试的异常
     * maxAttempts 最大重试次数(包含正常调用的一次)
     *
     * backoff：delay 延迟时间  multiplier 每次延迟的间隔时间成倍增加
     * @param age
     * @return
     */
    @Override
    @Retryable(include = {Exception.class},exclude = {ValidException.class},maxAttempts = 3,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public int retrySpringTest(int age) {
        if(age == 1){
            log.info("重试次数为1");
            throw new BusinessException("age为1重试");
        }
        if(age == 2){
            throw new ValidException("age为2不重试");
        }
        return 0;
    }


    /**
     * 该方法是重试失败后回调,重点 即使你排除的异常，如果写该方法，没有在该方法显示抛出那么异常信息就不会打印
     * @param age
     * @param e
     * @return
     */
    @Recover
    public int  recover(Exception e,int age){
        //记录日志 异步处理

        //抛出异常  todo
        throw new BusinessException(e.getMessage());

    }
}
