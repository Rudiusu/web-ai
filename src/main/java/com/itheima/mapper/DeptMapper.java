package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
   //获取部门列表
   public List<Dept> findAll();

   //通过id删除部门
   public void deleteById(Integer id);

   //添加部门
   public void insert(Dept dept);

   //根据id查询部门
   public Dept findById(Integer id);

   public void update(Dept dept);
}
