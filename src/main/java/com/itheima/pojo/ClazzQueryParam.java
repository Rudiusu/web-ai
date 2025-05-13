package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ClazzQueryParam {
    private LocalDate begin;
    private LocalDate end;
    private String name;
    private Integer page;
    private Integer pageSize;
}
