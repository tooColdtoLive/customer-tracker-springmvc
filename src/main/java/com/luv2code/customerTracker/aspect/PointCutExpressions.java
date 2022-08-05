package com.luv2code.customerTracker.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutExpressions {
    @Pointcut("execution(* *(..))")
    public static void allMethods(){}

    @Pointcut("execution(* com.luv2code.customerTracker.dao.*.*(..))")  // 1st * for any class,2nd * for any method
    public static void forDaoPackage(){}

    @Pointcut("execution(* com.luv2code.customerTracker.service.*.*(..))")
    public static void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.customerTracker.controller.*.*(..))")
    public static void forControllerPackage(){}

    @Pointcut("forDaoPackage() || forServicePackage() || forControllerPackage()")
    public static void forAppFlow(){}
}
