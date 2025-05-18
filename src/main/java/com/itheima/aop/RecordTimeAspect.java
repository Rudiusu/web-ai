package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Slf4j
//@Aspect
@Component
public class RecordTimeAspect {
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt(){}
    @Before("pt()")
    public void before(JoinPoint jp){
        log.info("before");
        //获取目标对象
        Object target = jp.getTarget();
        log.info("目标对象：{}",target);
        //获取目标类
        String className = jp.getTarget().getClass().getName();
        log.info("获取目标类：{}",className);
        //获取目标方法
        String methodName = jp.getSignature().getName();
        log.info("获取目标方法：{}",methodName);
        //获取目标方法参数
        Object[] args = jp.getArgs();
        log.info("获取目标方法参数：{}",args);

    }
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        Long start = System.currentTimeMillis();
        log.info("开始监听方法");
        Object res = pjp.proceed();
        Long end = System.currentTimeMillis();
        log.info("方法{} 运行耗时：{}ms" ,pjp.getSignature(),end-start);
        return res;
    }

    @After("pt()")
    public void after(){
       log.info("after");
    }

    @AfterReturning("pt()")
    public void afterReturn(){
      log.info("afterReturn");
    }

    @AfterThrowing("pt()")
    public void afterThrow(){
        log.info("afterThrow");
    }
}
