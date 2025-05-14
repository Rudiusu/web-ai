package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    public List<Student> list(StudentQueryParam studentQueryParam);
    public Integer  getCountStudentOfClazz(Integer clazzId);
    public void insert(Student student);
    public Student getStudentById(Integer id);
    public void update(Student student);
    public void delete(List<Integer> ids);
    @MapKey("name")
    public List<Map<String, Object>> studentGender();
}
