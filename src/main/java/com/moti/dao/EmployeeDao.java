package com.moti.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.moti.entities.Department;
import com.moti.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "张强", "12189794@qq.com", 1, new Department(101, "运维部")));
		employees.put(1002, new Employee(1002, "李闯", "lichuang@163.com", 1, new Department(102, "后端开发部")));
		employees.put(1003, new Employee(1003, "李密密", "mimi@163.com", 0, new Department(102, "后端开发部")));
		employees.put(1004, new Employee(1004, "王旭", "234598984@qq.com", 1, new Department(103, "产品部")));
		employees.put(1005, new Employee(1005, "阿卡丽", "987572346@163.com", 0, new Department(104, "后勤部")));
	}
	
	private static Integer initId = 1006;
	
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	public void delete(Integer id){
		employees.remove(id);
	}
}
