package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    public PageResult<Student> page(StudentQueryParam studentQueryParam);
    public Integer getCountStudentOfClazz(Integer clazzId);
    public void insert(Student student);
    public Student getStudentById(Integer id);
    public void update(Student student);
}
