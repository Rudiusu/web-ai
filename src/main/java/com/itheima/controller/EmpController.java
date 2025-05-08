package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    private final EmpServiceImpl empServiceImpl;

    public EmpController(EmpServiceImpl empServiceImpl) {
        this.empServiceImpl = empServiceImpl;
    }

    @GetMapping
   public Result page(Integer page, Integer pageSize) {
         log.info("第{}页，分页大小{}", page, pageSize);
         PageResult<Emp> pageResult = empServiceImpl.page( page,  pageSize);
         return Result.success(pageResult);
   }
}
