package com.itheima.service;

import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
   public  JobOption getEmpJobData();
   public List<Map<String, Object>> getEmpGenderData();
}
