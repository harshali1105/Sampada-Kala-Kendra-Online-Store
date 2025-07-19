package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skk.dao.EmployeeDao;
import com.skk.pojo.Employee;
import com.skk.pojo.Product;
import com.skk.utility.DBUtility;

public class EmployeeDaoImpl implements EmployeeDao {
	
	String insertQuery, viewEmployeeQuery, deleteQuery, updateQuery, updateByIdQuery;
	Connection conn;
	ResultSet rs;
	PreparedStatement ps;
	int status = 0;
	Employee e = new Employee();
	@Override
	public int addEmployee(Employee e) {
		insertQuery = "insert into Employee(empId, empName, empLocation, empEmailId, empContactNo, empSalary) values (?,?,?,?,?,?)";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, e.getEmpId());
			ps.setString(2, e.getEmpName());
			ps.setString(3, e.getEmpLoc());
			ps.setString(4, e.getEmpEmailId());
			ps.setLong(5, e.getEmpContact());
			ps.setDouble(6, e.getEmpSalary());
			
			status = ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Employee> viewEmployee() {
		viewEmployeeQuery = "select * from Employee";
		List<Employee> al = new ArrayList<Employee>();
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(viewEmployeeQuery);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Employee e = new Employee();
				
				e.setEmpId(rs.getInt("empId"));
				e.setEmpName(rs.getString("empName"));
				e.setEmpLoc(rs.getString("empLocation"));
				e.setEmpEmailId(rs.getString("empEmailId"));
				e.setEmpContact(rs.getLong("empContactNo"));
				e.setEmpSalary(rs.getDouble("empSalary"));
				al.add(e);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return al;
	}

	@Override
	public int deleteEmployee(int empId) {
		deleteQuery = "delete from Employee where empId=?";
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, empId);
			status = ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int updateEmployee(Employee e) {
		updateQuery = "update Employee set empName=?, empLocation=?, empEmailId=?, empContactNo=?, empSalary=? where empId=?";
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updateQuery);
			ps.setString(1, e.getEmpName());
			ps.setString(2, e.getEmpLoc());
			ps.setString(3, e.getEmpEmailId());
			ps.setLong(4, e.getEmpContact());
			ps.setDouble(5, e.getEmpSalary());
			ps.setInt(6, e.getEmpId());
			status = ps.executeUpdate();
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return status;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		updateByIdQuery = "select * from Employee where empId=?";
		Employee e=null;
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updateByIdQuery);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			if(rs.next())
			{
				e = new Employee();
				e.setEmpId(rs.getInt(1));
				e.setEmpName(rs.getString(2));
				e.setEmpLoc(rs.getString(3));
				e.setEmpEmailId(rs.getString(4));
				e.setEmpContact(rs.getLong(5));	
				e.setEmpSalary(rs.getDouble(6));
			}
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}

}
