package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    private final DeptMapper deptMapper;
    @Autowired

    public DeptServiceImpl(DeptMapper deptMapper){
           this.deptMapper = deptMapper;
    }

    /**
     * 获取部门列表
     * @return 所有部门的列表数据
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    @Override
    public void deleteById(Integer id) {
         deptMapper.deleteById(id);
    }

    /**
     * 添加部门
     * @param deptName 部门名称
     */
    @Override
    public void insert(Dept dept) {
       deptMapper.insert(dept);
    };

    /**
     * 通过id查询部门数据
     * @param id 部门id
     * @return 部门数据
     */
   @Override
   public Dept findById(Integer id){
       return deptMapper.findById(id);
   }

    /**
     *
     * @param dept 部门数据
     */
   @Override
    public void update(Dept dept) {
       dept.setUpdateTime(LocalDateTime.now());
       deptMapper.update(dept);
   }
}
