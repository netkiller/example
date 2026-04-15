package cn.netkiller.aop.service;

import org.springframework.stereotype.Service;

import cn.netkiller.aop.pojo.Employee;

@Service
public class EmployeeService {

	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}

	public Employee createEmployee(String id, String name) {

		Employee emp = new Employee();
		emp.setName(name);
		emp.setId(id);
		return emp;
	}

	public void deleteEmployee(String id) {

	}
}
