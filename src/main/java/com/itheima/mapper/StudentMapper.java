package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public List<Student> list(StudentQueryParam studentQueryParam);
    public Integer  getCountStudentOfClazz(Integer clazzId);
    public void insert(Student student);
    public Student getStudentById(Integer id);
    public void update(Student student);
}
