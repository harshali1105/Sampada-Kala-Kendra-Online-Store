package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.EmployeeDaoImpl;
import com.skk.impl.ProductDaoImpl;
import com.skk.pojo.Employee;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Employee")
public class EmployeeMain extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Employee e = new Employee();
		EmployeeDaoImpl pdi = new EmployeeDaoImpl(); 
		int empId, status;
		String empName, empEmailId, empLocation;
		long empContactNo;
		double empSalary;
		
		empName = req.getParameter("emp_name");
		empLocation = req.getParameter("emp_loc");
		empEmailId = req.getParameter("emp_email");
		empContactNo = Long.parseLong(req.getParameter("emp_contact"));
		empSalary = Double.parseDouble(req.getParameter("emp_salary"));
		
		e.setEmpName(empName);
		e.setEmpLoc(empLocation);
		e.setEmpEmailId(empEmailId);
		e.setEmpContact(empContactNo);
		e.setEmpSalary(empSalary);
		
		status = pdi.addEmployee(e);
		if(status>0)
		{
			out.print("<script>alert('Employee data Saved Successfully!!');</script>");
			req.getRequestDispatcher("AdminEmployee.html").include(req,resp);
		}
		else
		{
			out.print("<script>alert('Sorry!! Unable to save record');</script>");
		}
		out.close();
	}
}
