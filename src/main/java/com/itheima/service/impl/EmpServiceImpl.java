package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.controller.LoginController;
import com.itheima.mapper.EmpExpMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 条件分页查询员工列表
     * @param empQueryParam 分页查询条件
     * @return 员工分页列表
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> list = empMapper.list(empQueryParam);
        Page<Emp> p = ( Page<Emp>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 添加新员工
     * @param emp 新员工信息
     */
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

    /**
     * 通过id删除员工
     * @param ids 要删除的员工id
     */
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

    /**
     * 编辑更新员工信息
     * @param emp 员工信息
     */
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

    /**
     * 获取职位为班主任的所有员工
     * @return 班主任员工信息列表
     */
    @Override
    public List<Emp> getMasterEmp(){
        return empMapper.empMaster();
    }

    /**
     * 用户登录
     * @return 用户信息以及token令牌
     */
    @Override
    public LoginInfo login(Emp empParams){
       Emp emp = empMapper.selectByUsernameAndPassword(empParams);

       if(emp!=null){
           Map<String, Object> dataMap = new HashMap<>();
           dataMap.put("id",emp.getId());
           dataMap.put("name",emp.getName());
           dataMap.put("username",emp.getUsername());
           String token = JwtUtils.generateJwt(dataMap);
           return new LoginInfo(emp.getId(),emp.getUsername(),emp.getName(),token);
       }else{
           return null;
       }
    };
}
