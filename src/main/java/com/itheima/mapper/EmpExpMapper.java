package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExpMapper {
    public void insertBatch(List<EmpExpr>  expList);
}
