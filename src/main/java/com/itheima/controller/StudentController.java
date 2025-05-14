package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
     private final StudentService studentService;
     @Autowired
     public StudentController(StudentService studentService){
         this.studentService = studentService;
     }
     @GetMapping
     public Result page(StudentQueryParam studentQueryParam){
         log.info("查询参数：{}",studentQueryParam);
         return Result.success(studentService.page(studentQueryParam)) ;
     }
     @PostMapping
     public Result insert(@RequestBody Student student){
         log.info("添加学生：{}",student);
         studentService.insert(student);
         return Result.success();
     }
     @GetMapping("/{id}")
     public Result getStudentById(@PathVariable Integer id){
         log.info("获取学生信息，id={}",id);
         return Result.success(studentService.getStudentById(id));
     }
     @PutMapping
     public Result update(@RequestBody Student student){
         log.info("修改学生：{}",student);
         studentService.update(student);
         return Result.success();
     }

     @DeleteMapping("/{ids}")
     public Result delete(@PathVariable List<Integer> ids){
         log.info("删除学生：{}",ids);
         studentService.delete(ids);
         return Result.success();
     }

     @PutMapping("/violation/{id}/{score}")
     public Result violation(@PathVariable Integer id,@PathVariable Integer score){
          log.info("违纪学生id:{},扣{}分",id,score);
          studentService.violate(id,score);
          return Result.success();
     }

}
