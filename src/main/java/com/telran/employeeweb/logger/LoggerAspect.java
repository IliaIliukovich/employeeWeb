package com.telran.employeeweb.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Pointcut("within(com.telran.employeeweb.controller..*) || within(com.telran.employeeweb.service..*)")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.debug("Method {} started with args:{}", () -> joinPoint.getSignature().getName(),
                () -> Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "returnValue")
    public void afterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
        logger.debug("Method {} completed with return value: {}", () -> joinPoint.getSignature().getName(), () -> returnValue);
    }

}
