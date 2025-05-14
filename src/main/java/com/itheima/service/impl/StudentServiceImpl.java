package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    public  StudentServiceImpl(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    /**
     * 学生列表条件分页查询
     * @param studentQueryParam 条件参数
     * @return 分页列表数据
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam){
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> list = studentMapper.list(studentQueryParam);
        Page<Student> page = (Page<Student>) list;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 通过班级id查询该班级下的学生人数
     * @param clazzId 班级id
     * @return 学生人数
     */
    @Override
    public Integer getCountStudentOfClazz(Integer clazzId){
        return studentMapper.getCountStudentOfClazz(clazzId);
    };

    /**
     * 添加学生
     * @param student 学生表单数据
     */
    @Override
    public void insert(Student student){
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    /**
     * 通过id查询学生信息
     * @param id 学生id
     * @return 学生信息
     */
    @Override
    public Student getStudentById(Integer id){
      return studentMapper.getStudentById(id);
    }

    /**
     * 编辑修改学生
     * @param student 学生表单数据
     */
    @Override
    public void update(Student student){
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 批量删除学生
     * @param ids 学生id
     */
    @Override
    public void delete(List<Integer> ids){
        studentMapper.delete(ids);
    };

    /**
     * 学生违纪扣分
     * @param id 学生id
     * @param score 扣分值
     */
    @Override
    public void violate(Integer id, Integer score){
        Student student = studentMapper.getStudentById(id);
        student.setViolationCount((short) (student.getViolationCount()+1));
        student.setViolationScore((short) (student.getViolationScore()+score));
        studentMapper.update(student);
    };
}
