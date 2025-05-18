package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public Result list(){
        return Result.success(clazzService.listAll());
    }
    @GetMapping("/{id}")
    public Result getClassById(@PathVariable(value = "id") Integer clazzId){
        log.info("班级id：{}",clazzId);
        return Result.success(clazzService.getClazzById(clazzId));
    }
    @Log
    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        log.info("添加班级：{}",clazz);
        clazzService.insert(clazz);
        return Result.success();
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete( @PathVariable(value = "id") Integer clazzId) throws Exception  {
        log.info("要删除的班级id：{}",clazzId);
        try{
            clazzService.deleteById(clazzId);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }

        return Result.success();
    }
}
