package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {
    /**
     * 班级分页查询
     * @param clazzQueryParam 查询参数
     * @return 班级分页数据列表
     */
    public List<Clazz> pageList(ClazzQueryParam clazzQueryParam);

    /**
     * @return 所有班级数据列表
     */
    public List<Clazz> allList();

    /**
     * 添加班级
     * @param clazz 班级表单
     */
    public void insert(Clazz clazz);

    public Clazz getClazzById(Integer clazzId);

    public void update(Clazz clazz);

    public void deleteById(Integer clazzId);

    @MapKey("clazz")
    public List<Map<String,Object>> getClazzStudent();
}
