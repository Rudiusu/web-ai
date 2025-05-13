package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
     * 批量删除员工
     */
    public void delete(List<Integer> ids);

    /**
     * 通过id查找员工
     * @param id 员工id
     * @return 员工信息
     */
    public Emp find(Integer id);
    /**
     * 更新员工信息
     * @param emp 员工表单信息
     */
    public void update(Emp emp);
    /**
     * 获取员工职位数据
     * @return 职位数据
     */
    @MapKey("pos")
    public List<Map<String,Object>> countEmpJobData();
}
