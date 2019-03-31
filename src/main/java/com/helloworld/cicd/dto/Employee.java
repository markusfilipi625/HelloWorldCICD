package com.helloworld.cicd.dto;

public class Employee {

	private String employeeName;
	private String employeeDept;
	private Long employeeNumber;
	private String employeeSupervisor;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDept() {
		return employeeDept;
	}
	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}
	public Long getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(Long employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeSupervisor() {
		return employeeSupervisor;
	}
	public void setEmployeeSupervisor(String employeeSupervisor) {
		this.employeeSupervisor = employeeSupervisor;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", employeeDept=" + employeeDept + ", employeeNumber="
				+ employeeNumber + ", employeeSupervisor=" + employeeSupervisor + "]";
	}
}
