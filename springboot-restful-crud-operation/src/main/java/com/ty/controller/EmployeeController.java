package com.ty.controller;

import com.ty.dao.DepartmentDao;
import com.ty.dao.EmployeeDao;
import com.ty.entities.Department;
import com.ty.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Administrator on 2018/5/7.
 */
@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String getAllEmps(Model model) {
        logger.info("查询所有的员工");
        Collection<Employee> collection = employeeDao.getAll();
        model.addAttribute("emps", collection);
        return "/emp/list";
    }

    //查出所有的部门
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "/emp/add";
    }

    //员工的添加功能
    @PostMapping("/emp")
    public String addOneEmp(Employee employee) {
        logger.info("员工添加" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //查询一个员工
    @GetMapping("/emp/{id}")
    public String toPageOneEmp(@PathVariable("id") Integer id, Model model) {
        logger.info("进入编辑页面");
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        //查出部门,如果不查出部门则显示为空
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //重用add的页面
        return "/emp/add";
    }

    //修改一个员工
    @PutMapping("/emp")
    public String upDateOneEmp(Employee employee) {
        logger.info("进入修改页面");
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除一个员工
    @DeleteMapping("emp/{id}")
    public String deleteOneEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
