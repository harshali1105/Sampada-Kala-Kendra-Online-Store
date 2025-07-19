package com.skk.controller;

import java.io.IOException;

import com.skk.impl.EmployeeDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmployee")
public class DeleteEmployeeMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		String empId;
		empId = req.getParameter("id");
		int id = Integer.parseInt(empId);
		
		edi.deleteEmployee(id);
		resp.sendRedirect("ViewEmployee");
	}
}

