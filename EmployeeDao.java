package com.skk.dao;

import java.util.List;

import com.skk.pojo.Employee;
import com.skk.pojo.Product;

public interface EmployeeDao {
	int addEmployee(Employee e);
	List<Employee> viewEmployee();
	int deleteEmployee(int empId);
	int updateEmployee(Employee e);
	Employee getEmployeeById(int empId);
}
