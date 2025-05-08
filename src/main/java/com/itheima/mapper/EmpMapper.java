package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工信息查询
 */
@Mapper
public interface EmpMapper {
    /**
     * 获取员工记录数
     * @return 记录总数
     */
    public Long count();

    /**
     * 分页查询员工数据
     */
    public List<Emp> list(Integer start,Integer pageSize);
}
