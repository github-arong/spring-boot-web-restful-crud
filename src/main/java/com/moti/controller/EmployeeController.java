package com.moti.controller;

import com.moti.dao.DepartmentDao;
import com.moti.dao.EmployeeDao;
import com.moti.entities.Department;
import com.moti.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao dao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * 跳转到添加员工页面
     * @param model 将查询到的所有部门放到model中返回到页面显示
     * @return "emp/add"
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "emp/add";
    }

    /**
     * 添加员工,使用POST方式
     * @param employee 前端提交过来的数据,Spring MVC帮我们封装成对象
     * @return 请求转发到员工列表
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        /*
        redirect:表示重定向到一个地址
        forward:表示转发到一个地址
        / 代表当前项目下的
         */
        dao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工,使用DELETE方式
     * @param id 需要删除的员工ID
     * @return 请求转发到员工列表
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        dao.delete(id);
        return "redirect:/emps";
    }

    /**
     * 获取所有的员工,使用GET方式
     * @param map 将查询到的员工结果放到map中返回到页面
     * @return "emp/list"
     */
    @GetMapping("/emps")
    public String list(Map<String,Object> map){
        Collection<Employee> employees = dao.getAll();
        map.put("emps",employees);
        return "emp/list";
    }

    /**
     * 跳转到修改员工信息的页面
     * @param id 需要获取员工的ID
     * @param model 将查询到的员工放到model中返回页面显示
     * @return "emp/edit"
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = dao.get(id);
        model.addAttribute("emp",employee);
        //返回修改页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("deps",departments);
        return "emp/edit";
    }

    /**
     * 修改员工信息,使用PUT方式
     * @param employee 前端提交过来的数据,Spring MVC帮我们封装成对象
     * @return 请求转发到员工列表
     */
    @PutMapping("/emp")
    public String editEmp(Employee employee){
        dao.save(employee);
        return "redirect:/emps";
    }
}
