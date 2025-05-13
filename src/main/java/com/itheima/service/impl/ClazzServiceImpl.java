package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    private final ClazzMapper clazzMapper;
    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper){
        this.clazzMapper = clazzMapper;
    }
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam){
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        Page<Clazz> page = ( Page<Clazz> ) list;
        return new PageResult<>(page.getTotal(), page.getResult());
    }
}
