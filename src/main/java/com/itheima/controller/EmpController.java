package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    private final EmpServiceImpl empServiceImpl;

    public EmpController(EmpServiceImpl empServiceImpl) {
        this.empServiceImpl = empServiceImpl;
    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
    // log.info("第{}页，分页大小{},姓名：{},性别：{}，入职日期：{}~{}", page, pageSize,name,gender,begin,end);

         PageResult<Emp> pageResult = empServiceImpl.page(empQueryParam);
         return Result.success(pageResult);
   }

   @PostMapping
   public Result save(@RequestBody Emp emp){
        log.info("员工信息：{}",emp);
        empServiceImpl.save(emp);
        return Result.success();
   }
}
