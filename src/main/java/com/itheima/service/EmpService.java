package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

public interface EmpService {
    public PageResult<Emp> page(Integer page, Integer pageSize);
}
