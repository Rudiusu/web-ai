package com.itheima.service;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface OperateLogService {
    public PageResult<OperateLog> page(OperateLogQueryParam operateLogQueryParam);
}
