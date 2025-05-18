package com.itheima.controller;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {
    private final OperateLogService operateLogService;
    public OperateLogController(OperateLogService operateLogService){
        this.operateLogService = operateLogService;
    }
    @GetMapping("/page")
    public Result page(OperateLogQueryParam operateLogQueryParam){
        log.info("日志查询参数：{}",operateLogQueryParam);
        PageResult<OperateLog> page = operateLogService.page(operateLogQueryParam);
        return Result.success(page);
    }
}
