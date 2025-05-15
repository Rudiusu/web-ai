package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.util.List;


public interface EmpService {
    public PageResult<Emp> page(EmpQueryParam empQueryParam);
    public void save(Emp emp);
    public Emp getInfo(Integer id);
    public void delete(List<Integer> ids);
    public void update(Emp emp);
    public List<Emp> getMasterEmp();
    public LoginInfo login(Emp emp);
}
