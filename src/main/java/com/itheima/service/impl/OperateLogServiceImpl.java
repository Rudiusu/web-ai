package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.OperateLogService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperateLogServiceImpl implements OperateLogService {
    private final OperateLogMapper  operateLogMapper;
    public  OperateLogServiceImpl(OperateLogMapper operateLogMapper){
        this.operateLogMapper = operateLogMapper;
    }

    @Override
    public PageResult<OperateLog> page(OperateLogQueryParam operateLogQueryParam) {
        PageHelper.startPage(operateLogQueryParam.getPage(), operateLogQueryParam.getPageSize());
        List<OperateLog> list = operateLogMapper.page();
        Page<OperateLog> page = (Page<OperateLog>) list;
        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
