package com.luv2code.customerTracker.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect // does not include the function of @Component
@Component
public class LoggingAspect {
    public static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Before("com.luv2code.customerTracker.aspect.PointCutExpressions.forAppFlow()")
    public void beforeDaoPackageAdvice(JoinPoint joinPoint){
        String methodSignature = joinPoint.getSignature().toShortString();

        Object[] args = joinPoint.getArgs();
        StringBuilder argStrBuilder = new StringBuilder();
        for(Object arg : args){
            if(argStrBuilder.length() > 0){
                argStrBuilder.append(", ");
            }
            argStrBuilder.append(arg == null ? "null" : arg.toString());
        }

        logger.info("Start execution of " + methodSignature + " with argument: " + argStrBuilder.toString());
    }

    @AfterReturning(pointcut = "com.luv2code.customerTracker.aspect.PointCutExpressions.forAppFlow()", returning = "result")
    public void afterDaoPackageAdvice(JoinPoint joinPoint, Object result){
        String methodSignature = joinPoint.getSignature().toShortString();

        logger.info("End execution of " + methodSignature + (result == null ? " with void result" : " with result: " + result.toString()));
    }
}
