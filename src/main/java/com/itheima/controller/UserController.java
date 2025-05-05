package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;

import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

      class  LoginRes{
        private final String token;
        public LoginRes(String token){
            this.token = token;
        }

         public String getToken() {
             return token;
         }
     }
     private final UserService userService;
     @Autowired
     public UserController(UserService userService){
         this.userService = userService;
     }

    @PostMapping("/login")

    public Result login(@RequestBody User user){
//        String password = user.getPassword();
        String username = user.getUsername();
        System.out.println(username);
        LoginRes loginRes = new LoginRes(username+"-token");
        return Result.success(loginRes);
    }

    @PostMapping("/userInfo")
    public  Result userInfo(@RequestBody User user){
       Integer id = user.getId();
        System.out.println(id);
       User userInfo =  userService.getUserInfo(id);
       return Result.success(userInfo);
    }
}
