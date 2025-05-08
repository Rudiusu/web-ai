package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/depts")
@RestController
public class DeptController {
    private final DeptService deptService;

    @Autowired
    public DeptController(DeptService deptService){
        this.deptService = deptService;
    }

    @GetMapping
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }


    @DeleteMapping
    public Result deleteDept(@RequestParam(value = "id") Integer deptId){
        deptService.deleteById(deptId);
        System.out.println(deptId);
        return Result.success("删除成功");

    }


    @PostMapping
    public Result insertDept(@RequestBody Dept dept){
      deptService.insert(dept);
      System.out.println("新增部门："+dept.getName());
      return Result.success("添加成功");
    }


    @GetMapping("/{id}")
    public Result getInfo(@PathVariable(value = "id") Integer deptId){
        System.out.println(deptId);
        Dept dept = deptService.findById(deptId);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("更新部门："+dept);
        deptService.update(dept);
        return Result.success("更新成功");
    }
}
