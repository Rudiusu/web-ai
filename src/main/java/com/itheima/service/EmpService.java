package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;


public interface EmpService {
    public PageResult<Emp> page(EmpQueryParam empQueryParam);
    public void save(Emp emp);
}
