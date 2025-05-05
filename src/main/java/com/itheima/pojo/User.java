package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String introduction;
    private String avatar;
    private String name;
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public User(Integer id){
        this.id = id;
    }
    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
}
