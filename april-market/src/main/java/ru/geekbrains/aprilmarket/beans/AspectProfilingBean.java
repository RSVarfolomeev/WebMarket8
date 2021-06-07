package ru.geekbrains.aprilmarket.beans;

import lombok.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Aspect
//@Component
//@Getter
public class AspectProfilingBean {
    Map<String, Long> timeMap;

//    @PostConstruct
    private void init() {
        timeMap = new ConcurrentHashMap<>();
    }

//    @Around("execution(public * ru.geekbrains.aprilmarket.controllers.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();
        Long duration = end - begin;
        timeMap.put(proceedingJoinPoint.getSignature().toString(), duration);
        return result;
    }

}
