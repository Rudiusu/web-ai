package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.time.LocalDate;
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
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 添加员工
     * @param emp 员工表单信息
     */
    public void insert(Emp emp);

    /**
     * 删除员工
     */
    public void delete(List<Integer> ids);
}
