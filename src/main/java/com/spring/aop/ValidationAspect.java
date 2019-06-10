package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: xym760
 * @Date: 2019/6/10 10:38
 * @Description:
 */
@Order(1)
@Aspect
@Component
public class ValidationAspect {
    @Before("com.spring.aop.LoggingAspect.declareJoinPointExpression()")
    public void validateArgs(JoinPoint joinPoint) {
        System.out.println("--->Validate:" + Arrays.asList(joinPoint.getArgs()));
    }
}
