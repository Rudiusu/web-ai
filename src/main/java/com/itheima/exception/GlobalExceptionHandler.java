package com.itheima.exception;

import com.itheima.pojo.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {

        log.error("服务器异常:{}", e.getMessage());

        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result handlerDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库异常:{}", e.getMessage());
        String  message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i, message.indexOf("for key", i));
        String errKey = errMsg.replace("Duplicate entry ","");
        return Result.error("数据库异常："+errKey+"已存在");
    }

    @ExceptionHandler
    public Result handlerDuplicateKeyException(ExpiredJwtException e) {
        log.error("令牌过期:{}", e.getMessage());
        return Result.error("登录身份过期，请重新登录");
    }
}
