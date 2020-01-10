package com.moti.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.moti.entities.Department;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "运维部"));
		departments.put(102, new Department(102, "后端开发部"));
		departments.put(103, new Department(103, "产品部"));
		departments.put(104, new Department(104, "后勤部"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}
