package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    public List<Clazz> listAll();

    public void insert(Clazz clazz);

    public Clazz getClazzById(Integer clazzId);

    public void update(Clazz clazz);

    public void deleteById(Integer clazzId) throws Exception;
}
