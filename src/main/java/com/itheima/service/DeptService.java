package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> findAll();
    public void deleteById(Integer id) throws Exception;
    public void insert(Dept dept);
    public Dept findById(Integer id);
    public void update(Dept dept);
}
