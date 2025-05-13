package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExpMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService  {
    private final EmpMapper empMapper;
    private final EmpExpMapper empExpMapper;
    private final EmpLogService empLogService;
    @Autowired
    public  EmpServiceImpl(EmpMapper empMapper, EmpExpMapper empExpMapper,EmpLogService empLogService) {
        this.empMapper = empMapper;
        this.empExpMapper = empExpMapper;
        this.empLogService = empLogService;
    }
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> list = empMapper.list(empQueryParam);
        Page<Emp> p = ( Page<Emp>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        try {
            // 保存员工信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            empMapper.insert(emp);

            // 保存员工经历
            List<EmpExpr> expList = emp.getExprList();
            if(!expList.isEmpty()){
                 expList.forEach(exp -> {
                     exp.setEmpId(emp.getId());
                 });

                 empExpMapper.insertBatch(expList);
             }
        }
        finally {
            empLogService.insertLog(new EmpLog(null,LocalDateTime.now(),"新增员工："+emp.toString()));
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //批量删除员工
        empMapper.delete(ids);

        //批量删除员工经历
        empExpMapper.deleteByEmpId(ids);
    }
    @Override
    public Emp getInfo(Integer id) {
       return empMapper.find(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        //修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        //删除员工工作经历
        empExpMapper.deleteByEmpId(Collections.singletonList(emp.getId()));
        //重新添加员工工作经历
        List<EmpExpr> expList = emp.getExprList();
        if(expList!=null && !expList.isEmpty()){
            expList.forEach(exp -> {
              if(exp.getEmpId()==null)  exp.setEmpId(emp.getId());
            });
            empExpMapper.insertBatch(expList);
        }
    }

    public List<Emp> getMasterEmp(){
        return empMapper.empMaster();
    }
}
