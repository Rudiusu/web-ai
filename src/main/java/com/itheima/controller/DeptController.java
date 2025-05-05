package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    private final DeptService deptService;

    @Autowired
    public DeptController(DeptService deptService){
        this.deptService = deptService;
    }

    @GetMapping("/depts")
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList.stream().map(dept -> {
            return new Dept(
                    dept.getId(),
                    dept.getName(),
                    dept.getCreateTime(),
                    dept.getUpdateTime()

            );
        }));
    }


}
