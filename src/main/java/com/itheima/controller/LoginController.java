package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;

import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class LoginController {
    private final EmpService empService;
    @Autowired
    public LoginController( EmpService empService){
        this.empService = empService;
    }
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("用户名：{},密码：{}",emp.getUsername(),emp.getPassword());
        LoginInfo info = empService.login(emp);
        if(info!=null){
            return Result.success(info);
        }else{
            return Result.error("用户名密码错误,登录失败");
        }

    }
}
