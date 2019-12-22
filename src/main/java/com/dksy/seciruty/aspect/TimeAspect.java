package com.dksy.seciruty.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *          <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-aop</artifactId>
 *         </dependency>
 */
//@Aspect
//@Component
public class TimeAspect {

/**
 * 环绕通知，切入点表达式  execution、within、annotation 等等
 */
    @Around("execution(* com.dksy.seciruty.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("time aspect start");
        long time = new Date().getTime();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数："+arg);
        }
        Object object = joinPoint.proceed();
        System.out.println(object);
        System.out.println("time aspect 耗时："+ (new Date().getTime()-time));
        System.out.println("time aspect end");
        return object;
    }





}
