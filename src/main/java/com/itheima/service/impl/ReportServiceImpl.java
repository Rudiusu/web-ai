package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
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
    @Autowired
    public ReportServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
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

}
