package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
         return Result.success();
     }
}
