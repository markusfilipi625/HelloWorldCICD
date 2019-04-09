package com.helloworld.cicd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.cicd.dto.Employee;

@RestController
public class HelloWorldController {

	private List<Employee> employeeList = new ArrayList<Employee>();

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello folks message";
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		employeeList.clear();
		if (CollectionUtils.isEmpty(employeeList)) {
			Employee emp = new Employee();
			emp.setEmployeeName("User3");
			emp.setEmployeeDept("CSIT");
			emp.setEmployeeNumber(41l);
			emp.setEmployeeSupervisor("Fransic");
			employeeList.add(emp);
		}
		return employeeList;
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployees(final String id) {
		if (null == id) {
			return null;
		}
		for (Employee emp : employeeList) {
			if (emp.getEmployeeNumber().equals(id)) {
				return emp;
			}
		}
		return null;
	}

	@PostMapping("/add")
	public String addEmployees(Employee emp) {
		employeeList.add(emp);
		return "Employee Added Successfully";
	}

}
