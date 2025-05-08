package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;
    @Autowired
        public  EmpServiceImpl(){
        this.empMapper
    }


    public PageResult<Emp> page(Integer page, Integer pageSize) {
        Long total =  empMapper.count();
        List<Emp> list = empMapper.list((page-1)*pageSize,pageSize);
        return new PageResult<Emp>(total,list);
    }
}
