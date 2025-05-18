package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
@Slf4j
@Aspect
@Component
public class OperateLogAspect {
    private final OperateLogMapper operateLogMapper;
    @Autowired
    public OperateLogAspect(OperateLogMapper operateLogMapper){
        this.operateLogMapper = operateLogMapper;
    }

    @Around("@annotation(com.itheima.anno.Log)")
    public Object logOperate(ProceedingJoinPoint joinPoint) throws Throwable {
        OperateLog operateLog = new OperateLog();
        
        // 设置操作人ID（这里假设可以通过某种方式获取当前登录用户的ID）
        operateLog.setOperateEmpId(getCurrentUserId());
        
        // 设置操作时间
        operateLog.setOperateTime(LocalDateTime.now());

        // 获取目标类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);

        // 获取方法参数并转换为字符串形式
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        operateLog.setMethodParams(methodParams);

        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed(); // 执行目标方法
        long endTime = System.currentTimeMillis();

        // 设置方法执行耗时
        operateLog.setCostTime(endTime - startTime);

        // 如果需要，可以将返回值设置到日志中，注意敏感信息的处理
        operateLog.setReturnValue(returnValue != null ? returnValue.toString() : "null");

        // 保存操作日志
        operateLogMapper.insert(operateLog);
        log.info("操作日志：{}",operateLog);
        CurrentHolder.remove();
        return returnValue;
    }

    private Integer getCurrentUserId() {
        // 实现获取当前用户ID的逻辑
        return CurrentHolder.getCurrentId(); // 假设当前用户ID为1
    }
}