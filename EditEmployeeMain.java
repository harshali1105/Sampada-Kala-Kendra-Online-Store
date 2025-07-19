package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.EmployeeDaoImpl;
import com.skk.pojo.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditEmployee")
@MultipartConfig
public class EditEmployeeMain extends HttpServlet{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");
			EmployeeDaoImpl edi = new EmployeeDaoImpl();
			PrintWriter out = resp.getWriter();
			
			String empId = req.getParameter("id");
			int id = Integer.parseInt(empId);
			
			String empName = req.getParameter("name");
			String empLoc = req.getParameter("loc");
			String empEmailId = req.getParameter("email");
			String empContactNo = req.getParameter("contact");
			long contact = Long.parseLong(empContactNo);
			String empSalary = req.getParameter("salary");
			double salary = Double.parseDouble(empSalary);
			
			
			Employee e = new Employee();
			e.setEmpId(id);
			e.setEmpName(empName);
			e.setEmpLoc(empLoc);
			e.setEmpEmailId(empEmailId);
			e.setEmpContact(contact);
			e.setEmpSalary(salary);
			
			
			int status = edi.updateEmployee(e);
			if(status>0)
			{
				resp.sendRedirect("ViewEmployee");
			}
			else
			{
				out.print("<script>alert('Sorry!! Unable to connect')</script>");
			}
			out.close();
		
		}
}
