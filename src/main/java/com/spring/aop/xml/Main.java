package com.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: xym760
 * @Date: 2019/6/6 15:12
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-xml.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) applicationContext.getBean("arithmeticCalculator");
        System.out.println(arithmeticCalculator);
        int result = arithmeticCalculator.add(1, 2);
        System.out.println("result:" + result);
        result = arithmeticCalculator.div(1000, 10);
        System.out.println("result:" + result);
    }
}
