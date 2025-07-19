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

@WebServlet("/EditEmployee2")
public class EditEmployeeMain2 extends HttpServlet{
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		out.println("<h1>Update Employee<h1>");
		String empId = req.getParameter("id");
		int id = Integer.parseInt(empId);
		
		Employee e = edi.getEmployeeById(id);
		out.print("<html>");
		out.print("<head><title>Edit employees</title><link rel='stylesheet' href='AdminEmployee.css'></head>");
		out.print("<body>");
		out.print("<div class='admin-employee-form-container'>");
		out.print("<form action='EditEmployee' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id' class='box' value='"+e.getEmpId()+"'/><td></tr>");
		out.print("<tr><td>Name</td><td><input type='text' name='name' class='box' value='"+e.getEmpName()+"'/><td></tr>");
		out.print("<tr><td>Location</td><td><input type='text' name='loc' class='box' value='"+e.getEmpLoc()+"'/><td></tr>");
		out.print("<tr><td>EmailId</td><td><input type='email' name='email' class='box' value='"+e.getEmpEmailId()+"'/><td></tr>");
		out.print("<tr><td>Contact No</td><td><input type='tel' name='contact' class='box' value='"+e.getEmpContact()+"'/><td></tr>");
		out.print("<tr><td>Salary</td><td><input type='number' name='salary' class='box' value='"+e.getEmpSalary()+"'/><td></tr>");
		out.print("<tr><td colspan='2'><input type='submit' class='btn' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
		
	}
}
