package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {
    private final EmpMapper empMapper;
    private final StudentMapper studentMapper;
    private final ClazzMapper clazzMapper;
    @Autowired
    public ReportServiceImpl(EmpMapper empMapper,StudentMapper studentMapper,ClazzMapper clazzMapper) {
        this.empMapper = empMapper;
        this.studentMapper = studentMapper;
        this.clazzMapper = clazzMapper;
    }
    @Override
    public JobOption getEmpJobData(){
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> pos = list.stream().map(map -> map.get("pos")).toList();
        List<Object> num = list.stream().map(map -> map.get("num")).toList();
        return new JobOption(pos,num);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public ClazzOption getClassStudentData(){
        List<Map<String,Object>> list = clazzMapper.getClazzStudent();
        List<Object> clazz = list.stream().map(map->map.get("clazz")).toList();
        List<Object> num = list.stream().map(map->map.get("num")).toList();
        return new ClazzOption(clazz,num);
    };
    @Override
    public List<Map<String, Object>> getStudentDegreeData(){
       return studentMapper.studentGender();
    };
}
