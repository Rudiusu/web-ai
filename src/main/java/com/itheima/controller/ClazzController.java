package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    private final ClazzService clazzService;
    public ClazzController(ClazzService clazzService){
        this.clazzService = clazzService;
    }
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
          log.info("查询参数：{}",clazzQueryParam);
          return Result.success(clazzService.page(clazzQueryParam));
    }
}
