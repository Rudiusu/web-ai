package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    private final ClazzMapper clazzMapper;
    private final StudentMapper studentMapper;
    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper,StudentMapper studentMapper){
        this.studentMapper = studentMapper;
        this.clazzMapper = clazzMapper;
    }
    //班级分页查询
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam){
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> list = clazzMapper.pageList(clazzQueryParam);
        Page<Clazz> page = ( Page<Clazz> ) list;
        return new PageResult<>(page.getTotal(), page.getResult());
    }
    // 查询所有班级
    @Override
    public List<Clazz> listAll (){
        return clazzMapper.allList();
    }
    //添加班级
    @Override
    public void insert(Clazz clazz){
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }
    //通过id获取班级信息
    @Override
    public Clazz getClazzById(Integer clazzId){
        return clazzMapper.getClazzById(clazzId);
    }
    //编辑更新班级
    @Override
    public void update(Clazz clazz){
         clazz.setUpdateTime(LocalDateTime.now());
         clazzMapper.update(clazz);
    }
    //通过id删除班级
    @Override
    public void deleteById(Integer clazzId) throws Exception {
        Integer count = studentMapper.getCountStudentOfClazz(clazzId);
        if(count!=null){
            throw new Exception();
        }else{
            clazzMapper.deleteById(clazzId);
        }

    }

}
