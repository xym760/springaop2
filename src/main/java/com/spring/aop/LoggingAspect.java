package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: xym760
 * @Date: 2019/6/6 15:29
 * @Description:
 */
@Order(2)
@Aspect
@Component
public class LoggingAspect {
    /**
     * 定义一个方法声明切点表达式，一般地，该方法不需要添加其它代码
     * 使用@Pointcut来声明切点表达式
     * 后面的通知直接使用方法名来引用当前的切点表达式
     */
    @Pointcut("execution(public int com.spring.aop.ArithmeticCalculator.*(..))")
    public void declareJoinPointExpression() {
    }

    /**
     * 在com.spring.aop.ArithmeticCalculator接口的每一个实现类的每一个方法开始之前执行一段代码
     *
     * @param joinPoint
     */
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
    }

    /**
     * 在方法执行之后执行的代码。无论该方法是否发生异常
     *
     * @param joinPoint
     */
    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends");
    }

    /**
     * 在方法正常结束时执行的代码
     * 返回通知是可以访问到方法的返回值的！
     *
     * @param joinPoint
     */
    @AfterReturning(value = "declareJoinPointExpression()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " return " + result);
    }

    /**
     * 在目标方法出现异常时会执行的代码。
     * 可以访问到异常对象，且可以指定在出现特定异常时再执行通知代码（如下是出现ArithmeticException时执行通知）
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "declareJoinPointExpression()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, ArithmeticException exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs exception:" + exception);
    }

    /**
     * 环绕通知需要携带 ProceedingJoinPoint 类型的参数。
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
     * 且环绕通知必须有返回值，返回值即为目标方法的返回值
     *
     * @param proceedingJoinPoint
     */
    @Around("declareJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        //执行目标方法
        try {
            //前置通知
            System.out.println("The method " + methodName + " begins with " + Arrays.asList(proceedingJoinPoint.getArgs()));
            result = proceedingJoinPoint.proceed();
            //返回通知
            System.out.println("The method " + methodName + " ends with " + result);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("The method " + methodName + " occurs exception:" + throwable);
            throw new RuntimeException(throwable);
        }
        //后置通知
        System.out.println("The method " + methodName + " ends ");
        return result;
    }
}
